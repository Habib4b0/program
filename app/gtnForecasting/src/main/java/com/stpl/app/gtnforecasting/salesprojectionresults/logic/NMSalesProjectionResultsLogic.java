/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.logic;

import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeaderSPR;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_SALES_AT_WAC;
import static com.stpl.app.utils.Constants.LabelConstants.DEMAND_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.EX_FACTORY_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.INVENTORY_WITHDRAW;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.SALES;
import static com.stpl.app.utils.Constants.LabelConstants.SALES_PERC_OF_EX_FACTORY_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.SPRDASH;
import static com.stpl.app.utils.Constants.LabelConstants.UNITS;
import static com.stpl.app.utils.Constants.LabelConstants.UNIT_VOL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author Lokeshwari
 */
public class NMSalesProjectionResultsLogic {

	/**
	 * The unit volume.
	 */
	private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0");
	/**
	 * The dollar.
	 */
	private static final DecimalFormat DOLLAR = new DecimalFormat("#");
	/**
	 * The Currency Zero Decimal Places Format.
	 */
	private static final DecimalFormat CUR_ZERO = new DecimalFormat("$,##0.00");
	private static final DecimalFormat CUR_PER = new DecimalFormat("#,##0.00");
	/**
	 * The Percent Two Decimal Places Format.
	 */
	/**
	 * The Numeric Zero Decimal Places Format.
	 */
	private static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
	private static final String CURRENCY = "$";
	private static final String PERCENT = "%";
	SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
	boolean viewFlag = false;
	public List<SalesProjectionResultsDTO> projectionTotalList = new ArrayList<>();
	List<Object[]> nonmandatedGtsList = new ArrayList<>();
	Object[] nonmandatedorderedArgs;
	List<Object[]> mandatedGtsList = new ArrayList<>();
	Object[] mandatedorderedArgs;

	/**
	 * The Constant LOGGER.
	 */
	public static final Logger LOGGER = Logger.getLogger(NMSalesProjectionResultsLogic.class);

	public List<List> generateSalesProjectionResults(Object[] selections, String salesOrUnits, String actualOrProj,
			List<Object> headerList, String pivotView) {
		LOGGER.debug("generateSalesProjectionResults method starts" + actualOrProj);
		List sprList;
		List levelCount = new ArrayList();
		SalesProjectionResultsDTO sprDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO salesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO unitsDTO = new SalesProjectionResultsDTO();
		String frequency = String.valueOf(selections[1]);
		List<SalesProjectionResultsDTO> resultList = new ArrayList<>();
		List<SalesProjectionResultsDTO> salesList = new ArrayList<>();
		List<SalesProjectionResultsDTO> unitList = new ArrayList<>();
		List<List> finalList = new ArrayList<>();
		try {
			levelCount = salesProjectionDAO.getSalesProjectionResultLevels(selections);
			sprList = salesProjectionDAO.getSalesProjectionResults(selections);
			if ("period".equalsIgnoreCase(pivotView)) {
				if (sprList != null && !sprList.isEmpty()) {
					for (int j = 0; j < levelCount.size(); j++) {
						sprDTO = new SalesProjectionResultsDTO();
						final Object[] levelObj = (Object[]) levelCount.get(j);
						sprDTO.addStringProperties(Constant.LEVEL_VALUE_SMALL, String.valueOf(levelObj[1]));
						sprDTO.setLevelNo(Integer.parseInt(String.valueOf(levelObj[0])));
						sprDTO.setHierarchyNo(String.valueOf(levelObj[NumericConstants.TWO]));
						salesDTO = new SalesProjectionResultsDTO();
						unitsDTO = new SalesProjectionResultsDTO();
						for (int i = 0; i < sprList.size(); i++) {
							final Object[] obj = (Object[]) sprList.get(i);
							String commonColumn = StringUtils.EMPTY;
							if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
								commonColumn = Constant.Q + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
							} else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
								commonColumn = Constant.S + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
							} else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
								String monthName = getMonthForInt(
										Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.THREE]) - 1);
								commonColumn = monthName + obj[NumericConstants.TWO];
							} else {
								commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
							}
							if (Constant.SALES.equalsIgnoreCase(salesOrUnits)
									|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
								salesDTO.setLevelValue("Contract Sales @ WAC");
								salesDTO.addStringProperties(commonColumn + Constant.ACTUALS,
										obj[NumericConstants.FOUR] != null
												&& !Constant.NULL.equals(String.valueOf(obj[NumericConstants.FOUR]))
												&& !StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.FOUR]))
														? "$".concat(DOLLAR.format(Double.parseDouble(
																String.valueOf(obj[NumericConstants.FOUR]))))
														: "-");
								if (headerList.contains(commonColumn + Constant.PROJECTIONS)) {
									salesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
											obj[NumericConstants.SIX] != null
													&& !Constant.NULL.equals(String.valueOf(obj[NumericConstants.SIX]))
													&& !StringUtils.EMPTY
															.equals(String.valueOf(obj[NumericConstants.SIX]))
																	? "$".concat(DOLLAR.format(Double.parseDouble(
																			String.valueOf(obj[NumericConstants.SIX]))))
																	: "-");
								} else {
									salesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
											obj[NumericConstants.EIGHT] != null
													&& !Constant.NULL
															.equals(String.valueOf(obj[NumericConstants.EIGHT]))
													&& !StringUtils.EMPTY
															.equals(String.valueOf(obj[NumericConstants.EIGHT]))
																	? "$".concat(DOLLAR.format(Double.parseDouble(String
																			.valueOf(obj[NumericConstants.EIGHT]))))
																	: "-");
								}
							}
							if (Constant.UNITS.equalsIgnoreCase(salesOrUnits)
									|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
								unitsDTO.setLevelValue(Constant.UNIT_VOLUME);
								unitsDTO.addStringProperties(commonColumn + Constant.ACTUALS,
										obj[NumericConstants.FIVE] != null
												&& !Constant.NULL.equals(String.valueOf(obj[NumericConstants.FIVE]))
												&& !StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.FIVE]))
														? UNITVOLUME.format(Double.parseDouble(
																String.valueOf(obj[NumericConstants.FIVE])))
														: "-");
								if (headerList.contains(commonColumn + Constant.PROJECTIONS)) {
									unitsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
											obj[NumericConstants.SEVEN] != null
													&& !StringUtils.EMPTY
															.equals(String.valueOf(obj[NumericConstants.SEVEN]))
													&& !StringUtils.EMPTY
															.equals(String.valueOf(obj[NumericConstants.SEVEN]))
																	? UNITVOLUME.format(Double.parseDouble(String
																			.valueOf(obj[NumericConstants.SEVEN])))
																	: "-");
								} else {
									unitsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
											obj[NumericConstants.NINE] != null
													&& !Constant.NULL.equals(String.valueOf(obj[NumericConstants.NINE]))
													&& !StringUtils.EMPTY
															.equals(String.valueOf(obj[NumericConstants.NINE]))
																	? UNITVOLUME.format(Double.parseDouble(
																			String.valueOf(obj[NumericConstants.NINE])))
																	: "-");
								}
							}
							salesList.add(salesDTO);
							unitList.add(unitsDTO);
						}

						resultList.add(sprDTO);
					}
					finalList.add(resultList);
					finalList.add(salesList);
					finalList.add(unitList);
				}
			} else {
				StringBuilder statementBuilder = new StringBuilder("{call ");
				statementBuilder.append(SalesUtils.PRC_PROJECTION_RESULTS).append(" (?,?,?,?,?)}");
				Object[] paramArray = new Object[5];
				paramArray[0] = NumericConstants.TWO_SEVEN_EIGHT;
				paramArray[1] = frequency;
				paramArray[2] = StringUtils.EMPTY;
				paramArray[3] = Integer.parseInt(String.valueOf(selections[NumericConstants.EIGHT]));
				paramArray[4] = Integer.parseInt(String.valueOf(selections[NumericConstants.SEVEN]));
				List gtsList = GtnSqlUtil.getResultFromProcedure(statementBuilder.toString(), paramArray);
				if (sprList != null && !sprList.isEmpty()) {
					List<List> list = getRowList(selections);
					for (int j = 0; j < levelCount.size(); j++) {
						sprDTO = new SalesProjectionResultsDTO();
						final Object[] levelObj = (Object[]) levelCount.get(j);
						sprDTO.addStringProperties(Constant.LEVEL_VALUE_SMALL, String.valueOf(levelObj[1]));
						sprDTO.setLevelNo(Integer.parseInt(String.valueOf(levelObj[0])));
						unitsDTO = new SalesProjectionResultsDTO();
						for (int i = 0; i < sprList.size(); i++) {
							salesDTO = new SalesProjectionResultsDTO();
							final Object[] obj = (Object[]) sprList.get(i);
							String commonColumn = StringUtils.EMPTY;
							if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
								commonColumn = Constant.Q + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
							} else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
								commonColumn = Constant.S + obj[NumericConstants.THREE] + obj[NumericConstants.TWO];
							} else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
								String monthName = getMonthForInt(
										Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.THREE]) - 1);
								commonColumn = monthName + obj[NumericConstants.TWO];
							} else {
								commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
							}
							if (list.get(0).contains(commonColumn) || list.get(1).contains(commonColumn)) {
								salesDTO.setLevelValue(commonColumn);
								salesDTO.addStringProperties("cswActuals", obj[NumericConstants.FOUR] != null
										|| obj[NumericConstants.FOUR] == StringUtils.EMPTY
												? "$".concat(DOLLAR.format(
														Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]))))
												: "-");
								salesDTO.addStringProperties("uvActuals",
										obj[NumericConstants.FIVE] != null
												|| obj[NumericConstants.FIVE] == StringUtils.EMPTY
														? UNITVOLUME.format(
																Double.parseDouble(
																		String.valueOf(obj[NumericConstants.FIVE])))
														: "-");
								salesDTO.addStringProperties("PerExFactoryActuals", obj[NumericConstants.SIX] != null
										|| obj[NumericConstants.SIX] == StringUtils.EMPTY
												? "$".concat(DOLLAR.format(
														Double.parseDouble(String.valueOf(obj[NumericConstants.SIX]))))
												: "-");
								for (int k = 0; k < gtsList.size(); k++) {
									Object[] gtsObj = (Object[]) gtsList.get(k);
									String gtsCommonColumn = StringUtils.EMPTY;
									if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
										gtsCommonColumn = Constant.Q + gtsObj[NumericConstants.FIVE]
												+ gtsObj[NumericConstants.SIX];
									} else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
										gtsCommonColumn = Constant.S + gtsObj[NumericConstants.FIVE]
												+ gtsObj[NumericConstants.SIX];
									} else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
										String monthName = getMonthForInt(
												Integer.valueOf(StringUtils.EMPTY + gtsObj[NumericConstants.FOUR]) - 1);
										gtsCommonColumn = monthName + gtsObj[NumericConstants.SIX];
									} else {
										gtsCommonColumn = StringUtils.EMPTY + gtsObj[NumericConstants.SIX];
									}
									if (commonColumn.equalsIgnoreCase(gtsCommonColumn)) {
										if (list.get(0).contains(commonColumn)) {
											salesDTO.addStringProperties("gtsActuals",
													gtsObj[NumericConstants.TWO] != null
															|| gtsObj[NumericConstants.TWO] == StringUtils.EMPTY
																	? "$".concat(DOLLAR.format(Double.parseDouble(String
																			.valueOf(gtsObj[NumericConstants.TWO]))))
																	: "-");
										} else {
											salesDTO.addStringProperties("gtsProjections",
													gtsObj[NumericConstants.TWO] != null
															|| gtsObj[NumericConstants.TWO] == StringUtils.EMPTY
																	? "$".concat(DOLLAR.format(Double.parseDouble(String
																			.valueOf(gtsObj[NumericConstants.TWO]))))
																	: "-");
										}
									}
								}
								if (list.get(0).contains(commonColumn)) {
									salesDTO.addStringProperties("cswProjections",
											obj[NumericConstants.SIX] != null
													|| obj[NumericConstants.SIX] == StringUtils.EMPTY
															? "$".concat(DOLLAR.format(Double.parseDouble(
																	String.valueOf(obj[NumericConstants.SIX]))))
															: "-");
									salesDTO.addStringProperties("uvProjections", obj[NumericConstants.SEVEN] != null
											|| obj[NumericConstants.SEVEN] == StringUtils.EMPTY
													? UNITVOLUME.format(
															Double.parseDouble(
																	String.valueOf(obj[NumericConstants.SEVEN])))
													: "-");
								} else {
									salesDTO.addStringProperties("cswProjections", obj[NumericConstants.EIGHT] != null
											|| obj[NumericConstants.EIGHT] == StringUtils.EMPTY
													? "$".concat(DOLLAR.format(Double
															.parseDouble(String.valueOf(obj[NumericConstants.EIGHT]))))
													: "-");
									salesDTO.addStringProperties("uvProjections", obj[NumericConstants.NINE] != null
											|| obj[NumericConstants.NINE] == StringUtils.EMPTY
													? UNITVOLUME.format(
															Double.parseDouble(
																	String.valueOf(obj[NumericConstants.NINE])))
													: "-");
								}
								salesList.add(salesDTO);
								unitList.add(unitsDTO);
							}
						}
						resultList.add(sprDTO);
					}
					finalList.add(resultList);
					finalList.add(salesList);
					finalList.add(unitList);
				}
			}
			LOGGER.debug("generateSalesProjectionResults method ends");
		} catch (SystemException se) {
			LOGGER.error(se);
		} catch (PortalException pe) {
			LOGGER.error(pe);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return finalList;
	}

	public List<SalesProjectionResultsDTO> getGTSResult(int projectionID, String sessionId, String userId,
			Object[] selections, String pivotView) {
		LOGGER.debug("getGTSResult method starts");
		String frequency = String.valueOf(selections[1]);
		StringBuilder statementBuilder = new StringBuilder("{call ");
		statementBuilder.append(SalesUtils.PRC_PROJECTION_RESULTS).append(" (?,?,?,?,?)}");
		Object[] paramArray = new Object[5];
		paramArray[0] = projectionID;
		paramArray[1] = frequency;
		paramArray[2] = StringUtils.EMPTY;
		paramArray[3] = Integer.parseInt(sessionId);
		paramArray[4] = Integer.parseInt(userId);

		SalesProjectionResultsDTO gtsDTO = new SalesProjectionResultsDTO();
		List<SalesProjectionResultsDTO> gtsList = new ArrayList<>();

		try {
			List list =GtnSqlUtil.getResultFromProcedure(statementBuilder.toString(), paramArray);
			if (!list.isEmpty()) {
				if ("period".equalsIgnoreCase(pivotView)) {
					for (int i = 0; i < list.size(); i++) {
						Object[] obj = (Object[]) list.get(i);
						String commonColumn;
						if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
							commonColumn = Constant.Q + obj[NumericConstants.FOUR] + obj[NumericConstants.FIVE];
						} else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
							commonColumn = Constant.S + obj[NumericConstants.THREE] + obj[NumericConstants.SIX];
						} else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
							String monthName = getMonthForInt(
									Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.FOUR]) - 1);
							commonColumn = monthName + obj[NumericConstants.SIX];
						} else {
							commonColumn = StringUtils.EMPTY + obj[NumericConstants.SIX];
						}
						gtsDTO.setLevelValue("Gross Trade Sales");
						gtsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
								obj[NumericConstants.TWO] != null
										&& StringUtils.EMPTY.equals(String.valueOf(obj[NumericConstants.TWO]))
												? "$".concat(DOLLAR.format(
														Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]))))
												: "-");
						gtsList.add(gtsDTO);
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						gtsDTO = new SalesProjectionResultsDTO();
						Object[] obj = (Object[]) list.get(i);
						gtsDTO.addStringProperties("gtsProjections", "$"
								.concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[NumericConstants.TWO])))));
						gtsList.add(gtsDTO);
					}
				}
			} else {
				gtsDTO.setLevelValue("Gross Trade Sales");
				gtsList.add(gtsDTO);
			}
			LOGGER.debug("getGTSResult method ends");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return gtsList;
	}


	public List<List> getRowList(Object[] selections) {
		LOGGER.debug("getRowList method starts");
		List<List> list = new ArrayList();
		List historyList = new ArrayList();
		List projectionList = new ArrayList();
		String freq = selections[1].toString();
		String hist = selections[NumericConstants.FOUR].toString();
		String projFreq = selections[NumericConstants.FOUR].toString();

		Calendar ob = Calendar.getInstance();
		int curMonth = ob.get(Calendar.MONTH);
		int curYear = ob.get(Calendar.YEAR);
		int current = 1;
		int frequency = 0;
		int projectFrequency = 0;
		int division = 1;
		if (freq.equals(QUARTERLY.getConstant())) {
			current = curMonth / NumericConstants.THREE;
			division = NumericConstants.FOUR;
			try {
				frequency = Integer.valueOf(
						hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
				projectFrequency = Integer.valueOf(projFreq.replace("Quarter", StringUtils.EMPTY)
						.replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
			} catch (NumberFormatException e) {
			}
		} else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
			current = curMonth / NumericConstants.SIX;
			division = NumericConstants.TWO;
			try {
				frequency = Integer.valueOf(hist.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
				projectFrequency = Integer.valueOf(projFreq.replace(Constant.SEMI_ANNUALY, StringUtils.EMPTY).trim());
			} catch (NumberFormatException e) {
			}
		} else if (freq.equals(MONTHLY.getConstant())) {
			current = curMonth;
			division = NumericConstants.TWELVE;
			try {
				frequency = Integer.valueOf(
						hist.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
				projectFrequency = Integer.valueOf(projFreq.replace("Month", StringUtils.EMPTY)
						.replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
			} catch (NumberFormatException e) {
			}
		} else if (freq.equals(ANNUALLY.getConstant())) {
			current = curYear;
			division = 1;
			try {
				frequency = Integer.valueOf(hist.replace(Constant.YEAR, StringUtils.EMPTY).trim());
				projectFrequency = Integer.valueOf(projFreq.replace(Constant.YEAR, StringUtils.EMPTY).trim());
			} catch (NumberFormatException e) {
			}
		}
		projectFrequency = projectFrequency + 1;
		int pastYear = curYear;

		int startFreq = current + 1;

		int tempFreq = frequency - current;
		if (tempFreq > 0) {
			pastYear = pastYear - tempFreq / division;
			startFreq = 1;
			if (tempFreq % division > 0) {
				pastYear = pastYear - 1;
				startFreq = division - (tempFreq % division) + 1;
			}
		} else {
			startFreq = startFreq - frequency;
		}

		int squr = startFreq;
		int syear = pastYear;
		if (freq.contains(ANNUALLY.getConstant()) && !freq.contains(SEMI_ANNUALLY.getConstant())) {
			syear = current - frequency;
		}
		for (int i = 0; i < frequency; i++) {
			String commonColumn = StringUtils.EMPTY;
			if (freq.contains(QUARTERLY.getConstant())) {
				commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
			} else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
				commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
			} else if (freq.contains(ANNUALLY.getConstant())) {
				commonColumn = StringUtils.EMPTY + syear;
			} else if (freq.contains(MONTHLY.getConstant())) {
				String monthName = getMonthForInt(squr - 1);
				commonColumn = monthName + syear;
			}
			squr++;
			if (squr > division) {
				squr = 1;
				syear++;
			}
			historyList.add(commonColumn);
		}
		squr = current + 1;
		for (int i = 0; i < projectFrequency; i++) {
			String commonColumn = StringUtils.EMPTY;
			if (freq.contains(QUARTERLY.getConstant())) {
				commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
			} else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
				commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
			} else if (freq.contains(ANNUALLY.getConstant())) {
				commonColumn = StringUtils.EMPTY + syear;
			} else if (freq.contains(MONTHLY.getConstant())) {
				String monthName = getMonthForInt(squr - 1);
				commonColumn = monthName + syear;
			}
			squr++;
			if (squr > division) {
				squr = 1;
				syear++;
			}
			projectionList.add(commonColumn);
		}
		list.add(historyList);
		list.add(projectionList);
		LOGGER.debug("getRowList method ends");
		return list;
	}

	public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResults(Object parentId, int start, int offset,
			ProjectionSelectionDTO projSelDTO) {
		List<SalesProjectionResultsDTO> resultList;
		if (!projSelDTO.isIsFilter() || (parentId instanceof SalesProjectionResultsDTO)) {
			projSelDTO.setYear(Constant.ALL);

			if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
				projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
			}

			if (parentId instanceof SalesProjectionResultsDTO) {
				projSelDTO.setIsProjectionTotal(false);
				SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
				projSelDTO.setLevelNo(parentDto.getLevelNo());
				projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
				projSelDTO.setLevelValue(parentDto.getLevelValue());
				projSelDTO.setParentNode(parentDto.getParentNode());
				projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
				if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY
						.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
				}
				projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
				projSelDTO.setGroup(parentDto.getGroup());
				projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
			} else {
				if (projSelDTO.getGroupFilter() == null || projSelDTO.getGroupFilter().isEmpty()) {
					projSelDTO.setGroupFilter("All Sales Groups");
				}

				projSelDTO.setIsProjectionTotal(true);
				if (projSelDTO.isIsCustomHierarchy()) {
					projSelDTO.setLevelNo(0);
					projSelDTO.setTreeLevelNo(0);
				} else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
				}
				projSelDTO.setLevelValue(StringUtils.EMPTY);
				projSelDTO.setHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
			}
			resultList = getSalesProjectionResults(start, offset, projSelDTO);
		} else {
			projSelDTO.setIsProjectionTotal(false);
			projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
			projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
			resultList = configureLevels(start, offset, start, projSelDTO);
		}
		return resultList;
	}

	public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResultsTotal(int start, int offset,
			ProjectionSelectionDTO projSelDTO) {
		List<SalesProjectionResultsDTO> resultList = new ArrayList<>();
		projSelDTO.setIsProjectionTotal(true);
		if (!projSelDTO.isIsFilter()) {
			projSelDTO.setYear(Constant.ALL);

			if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
				projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
			}
			if (Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(1);
			} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.TWO);
			} else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.FOUR);
			} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.TWELVE);
			}
			resultList = getSalesProjectionResultsTotal(start, offset, projSelDTO);
		}

		return resultList;
	}

	public List<SalesProjectionResultsDTO> configureLevels(int start, int offset, int started,
			ProjectionSelectionDTO projSelDTO) {
		int neededRecord = offset;
		CommonLogic comm = new CommonLogic();
		List<SalesProjectionResultsDTO> resultList = new ArrayList<>();
		Map<String, List> levelMap = null;
		if (projSelDTO.isIsCustomHierarchy()) {
			projSelDTO.setHierarchyIndicator(comm.getHiearchyIndicatorFromCustomView(projSelDTO));
		}
		levelMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
		if (projSelDTO.isIsCustomHierarchy()) {
			List obj = comm.getHiearchyNoForCustomView(projSelDTO, start, offset);
			for (Object object : obj) {
				String hierarchyNo = String.valueOf(object);

				if (levelMap.containsKey(hierarchyNo)) {
					List levelValues = levelMap.get(object);
					Integer levelNo = Integer.valueOf((String) levelValues.get(NumericConstants.TWO));
					SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
					dto.setLevelNo(levelNo);
					dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
					dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNo,
							projSelDTO.getHierarchyIndicator()));
					dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNo,
							projSelDTO.getHierarchyIndicator()));
					dto.setHierarchyNo(hierarchyNo);
					dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
					if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
						dto.setCustomerHierarchyNo(dto.getHierarchyNo());
						dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
					} else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
						dto.setProductHierarchyNo(dto.getHierarchyNo());
						dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
					}
					dto.setOnExpandTotalRow(1);
					dto.setParent(1);
					resultList.add(dto);
					neededRecord--;
				}
			}
		} else if (neededRecord > 0) {
			List<String> hierarchyNoList = comm.getHiearchyNoAsList(projSelDTO, start, offset);
			Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();

			for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
				SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
				dto.setLevelNo(Integer.valueOf(
						relationshipLevelDetailsMap.get(hierarchyNoList.get(i)).get(NumericConstants.TWO).toString()));
				dto.setTreeLevelNo(dto.getLevelNo());
				dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNoList.get(i),
						projSelDTO.getHierarchyIndicator()));
				dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNoList.get(i),
						projSelDTO.getHierarchyIndicator()));
				dto.setHierarchyNo(hierarchyNoList.get(i));
				dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
				if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
					dto.setCustomerHierarchyNo(dto.getHierarchyNo());
					dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
				} else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
					dto.setProductHierarchyNo(dto.getHierarchyNo());
					dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
				}
				dto.setOnExpandTotalRow(1);
				dto.setParent(1);
				resultList.add(dto);
				started++;
			}
		}
		return resultList;
	}

	public List<SalesProjectionResultsDTO> getSalesProjectionResultsTotal(int start, int offset,
			ProjectionSelectionDTO projSelDTO) {

		int neededRecord = offset;
		int mayBeAdded = 0;
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
		String freq = StringUtils.EMPTY;
		if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.ANNUAL_CAPS;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO
				|| Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.SEMIANNUAL_CAPS;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR
				|| Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.QUARTERLY1;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE
				|| Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.MONTHLY_COLUMN;
		}

		Object[] orderedArgs = { projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionId(),
				projSelDTO.getUserId() };
		if (start < 1) {
			SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
			dto.setLevelValue(Constant.PROJECTION_TOTAL);
			dto.setParent(0);
			projDTOList.add(dto);
			neededRecord--;
		}
		mayBeAdded++;
		if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
			SalesProjectionResultsDTO contractSalesDto = null;
			SalesProjectionResultsDTO unitVolDto = null;
			SalesProjectionResultsDTO gtsDto = projectionTotalList.get(0);
			contractSalesDto = projectionTotalList.get(1);
			unitVolDto = projectionTotalList.get(NumericConstants.TWO);
			if ((start < NumericConstants.TWO && neededRecord > 0) && (gtsDto != null)) {
				projDTOList.add(gtsDto);
				neededRecord--;
			}
			String salesUnits = projSelDTO.getSalesOrUnit();
			if ((neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)))
					&& ((start < NumericConstants.THREE) && (contractSalesDto != null))) {
				projDTOList.add(contractSalesDto);
				neededRecord--;
			}
			if ((neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL)))
					&& (((salesUnits.equals(Constant.BOTH) && start < NumericConstants.FOUR)
							|| (start < NumericConstants.THREE)) && (unitVolDto != null))) {
				projDTOList.add(unitVolDto);
			}

		} else {
			int mayBeAddedRecord = start - mayBeAdded;
			if (mayBeAddedRecord < 0) {
				mayBeAddedRecord = 0;
			}
			List<SalesProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
			for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
				projDTOList.add(projectionDtoList.get(k));
			}
		}
		return projDTOList;
	}

	public List<SalesProjectionResultsDTO> getSalesProjectionResults(int start, int offset,
			ProjectionSelectionDTO projSelDTO) {

		int neededRecord = offset;
		int started = start;
		int mayBeAdded = 0;
		String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
		String freq = StringUtils.EMPTY;
		if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.ANNUAL_CAPS;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO
				|| Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.SEMIANNUAL_CAPS;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR
				|| Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.QUARTERLY1;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE
				|| Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.MONTHLY_COLUMN;
		}
		projSelDTO.setProjectionHeaderList(CommonUtils.prepareProjectionPeriodList(projSelDTO));
		Object[] orderedArgs = { projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS",
				projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId() };
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		if (!projSelDTO.getLevelValue().startsWith(Constant.ALL)
				&& !projSelDTO.getLevelValue().contains(Constant.SALES_WITH_HYPHEN)) {
			if (projSelDTO.isIsProjectionTotal()) {
				if (started == 0) {
					if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
						SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
						dto.setLevelValue(Constant.PROJECTION_TOTAL);
						dto.setParent(0);
						projDTOList.add(dto);
					}
					neededRecord--;
					started++;
				}
				mayBeAdded++;
			}
			if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
				String salesUnits = projSelDTO.getSalesOrUnit();
				if (projSelDTO.isIsProjectionTotal()) {
					mayBeAdded++;
					if (projectionTotalList.isEmpty()) {
						getProjectionTotal(orderedArgs, projSelDTO);
					}
					if (started == 1 && neededRecord > 0) {
						if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
							SalesProjectionResultsDTO exFactorySalesDTO = projectionTotalList.get(0);
							projDTOList.add(exFactorySalesDTO);
						}
						started++;
						neededRecord--;
					}
					if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant())
							|| salesUnits.equals(SALES.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
						if (started == NumericConstants.TWO) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO demandSalesDTO = projectionTotalList.get(1);
								projDTOList.add(demandSalesDTO);
							}
							started++;
							neededRecord--;
							mayBeAdded++;
						}
						if (started == NumericConstants.THREE) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO inventoryWithdrawDTO = projectionTotalList
										.get(NumericConstants.TWO);
								projDTOList.add(inventoryWithdrawDTO);
							}
							started++;
							neededRecord--;
							mayBeAdded++;
						}
						if (started == NumericConstants.FOUR && !salesUnits.equals(UNITS.getConstant())) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO contractSalesDto = projectionTotalList
										.get(NumericConstants.THREE);
								projDTOList.add(contractSalesDto);
							}
							started++;
							neededRecord--;
							mayBeAdded++;
						}

						if (started == NumericConstants.FOUR && salesUnits.equals(UNITS.getConstant())) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO unitVolDto = projectionTotalList.get(NumericConstants.FOUR);
								projDTOList.add(unitVolDto);
							}
							started++;
							neededRecord--;
							mayBeAdded++;
						}

						if (started == NumericConstants.FIVE && !(salesUnits.equals(BOTH.getConstant()))) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO PercentageExFactorySalesDto = projectionTotalList
										.get(NumericConstants.FIVE);
								projDTOList.add(PercentageExFactorySalesDto);

							}
							started++;
							neededRecord--;
							mayBeAdded++;
						}

						if (started == NumericConstants.FIVE && !(salesUnits.equals(SALES.getConstant()))) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO unitVolDto = projectionTotalList.get(NumericConstants.FOUR);
								projDTOList.add(unitVolDto);
							}
							started++;
							neededRecord--;
							mayBeAdded++;
						}

					}
					if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()))) {
						if ((salesUnits.equals(BOTH.getConstant()) && (started == NumericConstants.SIX))) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								SalesProjectionResultsDTO PercentageExFactorySalesDto = projectionTotalList
										.get(NumericConstants.FIVE);
								projDTOList.add(PercentageExFactorySalesDto);
							}
							started++;
							neededRecord--;
						}
						mayBeAdded++;
					}
				} else if (neededRecord > 0) {
					SalesProjectionResultsDTO contractSalesDto = null;
					SalesProjectionResultsDTO unitVolDto = null;
					SalesProjectionResultsDTO exFactorySalesDTO = null;
					if ((salesUnits.equals(BOTH.getConstant()) && started < NumericConstants.THREE) || started < 1) {
						List<SalesProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
						contractSalesDto = contractSalesAndUnits.get(0);
						unitVolDto = contractSalesAndUnits.get(1);
						exFactorySalesDTO = contractSalesAndUnits.get(2);
					}
					if (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant())) {
						if (started == 0) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								projDTOList.add(contractSalesDto);
							}
							started++;
							neededRecord--;
						}
						mayBeAdded++;
					}
					if (neededRecord > 0
							&& (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
						if ((salesUnits.equals(BOTH.getConstant()) && started == 2) || started == 1 || started == 0) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								projDTOList.add(unitVolDto);
							}
							started++;
							neededRecord--;
						}
						mayBeAdded++;
					}
					if (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant())
							|| salesUnits.equals(UNITS.getConstant())) {
						if ((salesUnits.equals(BOTH.getConstant()) && started == 2) || started == 1 || started == 0) {
							if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
								projDTOList.add(exFactorySalesDTO);
							}
							started++;
							neededRecord--;
						}
						mayBeAdded++;
					}

				}
			} else if (neededRecord > 0) {
				int mayBeAddedRecord = started - mayBeAdded;
				if (mayBeAddedRecord < 0) {
					mayBeAddedRecord = 0;
				}
				if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
					List<SalesProjectionResultsDTO> projectionDtoList;
					projSelDTO.setProjTabName("SPR");
					if (projSelDTO.isIsProjectionTotal()) {
						projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
					} else {
						projectionDtoList = getProjectionPivot(projSelDTO);
					}
					projSelDTO.setProjTabName(StringUtils.EMPTY);
					for (int k = mayBeAddedRecord; k < projectionDtoList.size()
							&& neededRecord > 0; neededRecord--, k++) {
						if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
							projDTOList.add(projectionDtoList.get(k));
						}
						started++;
					}
				}
				mayBeAdded += projSelDTO.getPeriodList().size();
			}
		}
		if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
			if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
					&& ((projSelDTO.isIsCustomHierarchy())
							|| (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
					&& !projSelDTO.getLevelValue().startsWith(Constant.ALL)
					&& !projSelDTO.getLevelValue().contains(Constant.SALES_WITH_HYPHEN)) {
				if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
					SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
					dto.setLevelNo(projSelDTO.getLevelNo());
					dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
					dto.setParentNode(projSelDTO.getParentNode());
					dto.setGroup(projSelDTO.getGroupFilter());
					dto.setLevelValue(projSelDTO.getGroupFilter());
					dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
					dto.setHierarchyNo(projSelDTO.getHierarchyNo());
					if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
						dto.setCustomerHierarchyNo(dto.getHierarchyNo());
						dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
					} else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
						dto.setProductHierarchyNo(dto.getHierarchyNo());
						dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
					}
					dto.setOnExpandTotalRow(1);
					dto.setParent(1);
					projDTOList.add(dto);
				}
			} else {
				int mayBeAddedRecord = 0;
				if (projSelDTO.isIsProjectionTotal() && start > 0) {
					int count = NumericConstants.TWO;
					if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
						count = count + NumericConstants.FOUR;
					} else if (projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
						count = count + NumericConstants.THREE;
					} else if (projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
						count = count + NumericConstants.THREE;
					}
					mayBeAddedRecord = started - count;
					if (mayBeAddedRecord < 0) {
						mayBeAddedRecord = 0;
					}
				} else {
					mayBeAddedRecord = started - mayBeAdded;
					if (mayBeAddedRecord < 0) {
						mayBeAddedRecord = 0;
					}
				}
				projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
				List<SalesProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord,
						started, projSelDTO);
				projDTOList.addAll(nextLevelValueList);
			}
		}
		return projDTOList;
	}

	public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
		if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			List<Object[]> gtsList;
			LOGGER.debug("Enetring getProjectionTotal NonMandated");
			if (!projSelDTO.getSessionDTO().isSprRefreshReqd()
					&& CommonLogic.checkProcedureInputIsSame(orderedArgs, nonmandatedorderedArgs)) {
				gtsList = nonmandatedGtsList;
			} else {
				gtsList = CommonLogic.callProcedure(SalesUtils.PRC_PROJECTION_RESULTS, orderedArgs);
				nonmandatedorderedArgs = new Object[orderedArgs.length];
				System.arraycopy(orderedArgs, 0, nonmandatedorderedArgs, 0, orderedArgs.length);
				nonmandatedGtsList.clear();
				nonmandatedGtsList.addAll(gtsList);
			}
			if (gtsList != null) {
				getCustomizedProjectionTotal(gtsList, projSelDTO);
			}
			projSelDTO.getSessionDTO().setSprRefreshReqd(false);
			LOGGER.debug("Ending getProjectionTotal NonMandated");
		} else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			LOGGER.debug("Entering getProjectionTotal Mandated");
			List<Object[]> gtsList;
			if (!projSelDTO.getSessionDTO().isSprRefreshReqd()
					&& CommonLogic.checkProcedureInputIsSame(orderedArgs, mandatedorderedArgs)) {
				gtsList = mandatedGtsList;
			} else {
				gtsList = CommonLogic.callProcedure(Constant.PRC_M_PROJECTION_RESULTS, orderedArgs);
				mandatedorderedArgs = new Object[orderedArgs.length];
				System.arraycopy(orderedArgs, 0, mandatedorderedArgs, 0, orderedArgs.length);
				mandatedGtsList.clear();
				mandatedGtsList.addAll(gtsList);
			}
			if (gtsList != null) {
				projectionTotalList = getCustomizedProjectionTotalMandated(gtsList, projSelDTO);
			}
			projSelDTO.getSessionDTO().setSprRefreshReqd(false);
			LOGGER.debug("Ending getProjectionTotal Mandated");
		}
	}

	public List<SalesProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("getCustomizedProjectionTotal() starts");
		int frequencyDivision = projSelDTO.getFrequencyDivision();
		SalesProjectionResultsDTO exFactorySalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO demandSalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO inventoryWithdrawDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO conSaleDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO unitVolDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO PercentageExFactorySalesDto = new SalesProjectionResultsDTO();
		exFactorySalesDTO.setParent(0);
		exFactorySalesDTO.setLevelValue(EX_FACTORY_SALES.getConstant());

		demandSalesDTO.setParent(0);
		demandSalesDTO.setLevelValue(DEMAND_SALES.getConstant());

		inventoryWithdrawDTO.setParent(0);
		inventoryWithdrawDTO.setLevelValue(INVENTORY_WITHDRAW.getConstant());

		conSaleDTO.setParent(0);
		conSaleDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());

		unitVolDTO.setParent(0);
		unitVolDTO.setLevelValue(UNIT_VOL.getConstant());

		PercentageExFactorySalesDto.setParent(0);
		PercentageExFactorySalesDto.setLevelValue(SALES_PERC_OF_EX_FACTORY_SALES.getConstant());

		Map<String, List<String>> projHeaderMap = new HashMap<>();
		if (list != null && !list.isEmpty()) {
			int col = NumericConstants.FIVE;
			if (frequencyDivision != 1) {
				col = col + 1;
			}
			for (Object list1 : list) {
				List<String> projHeaderValueList = new ArrayList<>();
				final Object[] obj = (Object[]) list1;

				int year = Integer.valueOf(String.valueOf(obj[col - 1]));
				int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
				List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
				String commonColumn = common.get(0);

				String exFactoryActual = StringUtils.EMPTY + obj[1];
				exFactoryActual = getFormatValue(TWO_DECIMAL, exFactoryActual, CURRENCY);
				exFactorySalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, exFactoryActual);
				String exFactoryProjection = StringUtils.EMPTY + obj[NumericConstants.TWO];
				exFactoryProjection = getFormatValue(TWO_DECIMAL, exFactoryProjection, CURRENCY);
				exFactorySalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, exFactoryProjection);

				String demandActual = StringUtils.EMPTY + obj[NumericConstants.TWENTY_TWO];
				demandActual = getFormatValue(TWO_DECIMAL, demandActual, CURRENCY);
				demandSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, demandActual);
				String demandProjection = StringUtils.EMPTY + obj[NumericConstants.TWENTY_THREE];
				demandProjection = getFormatValue(TWO_DECIMAL, demandProjection, CURRENCY);
				demandSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, demandProjection);

				String inventoryWithdrawActual = StringUtils.EMPTY + obj[NumericConstants.TWENTY_FOUR];
				inventoryWithdrawActual = getFormatValue(TWO_DECIMAL, inventoryWithdrawActual, CURRENCY);
				inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.ACTUALS, inventoryWithdrawActual);
				String inventoryWithdrawProjection = StringUtils.EMPTY + obj[NumericConstants.TWENTY_FIVE];
				inventoryWithdrawProjection = getFormatValue(TWO_DECIMAL, inventoryWithdrawProjection, CURRENCY);
				inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
						inventoryWithdrawProjection);

				String PerExFactoryActuals = StringUtils.EMPTY + obj[col + NumericConstants.EIGHT];
				PerExFactoryActuals = getFormatValue(TWO_DECIMAL, PerExFactoryActuals, PERCENT);
				PercentageExFactorySalesDto.addStringProperties(commonColumn + Constant.ACTUALS, PerExFactoryActuals);
				String PerExFactoryProjections = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
				PerExFactoryProjections = getFormatValue(TWO_DECIMAL, PerExFactoryProjections, PERCENT);
				PercentageExFactorySalesDto.addStringProperties(commonColumn + Constant.PROJECTIONS,
						PerExFactoryProjections);

				String cswActuals = StringUtils.EMPTY + obj[col];
				cswActuals = getFormatValue(TWO_DECIMAL, cswActuals, CURRENCY);
				conSaleDTO.addStringProperties(commonColumn + Constant.ACTUALS, cswActuals);
				String cswProjections = StringUtils.EMPTY + obj[col + 1];
				cswProjections = getFormatValue(TWO_DECIMAL, cswProjections, CURRENCY);
				conSaleDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, cswProjections);

				String uvActuals = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
				uvActuals = getFormattedValue(UNITVOLUME, uvActuals);
				unitVolDTO.addStringProperties(commonColumn + Constant.ACTUALS, uvActuals);
				String uvProjections = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
				uvProjections = getFormattedValue(UNITVOLUME, uvProjections);
				unitVolDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, uvProjections);

				projHeaderValueList.add(commonColumn + Constant.PROJECTIONS);
				projHeaderMap.put(commonColumn, projHeaderValueList);
			}
			projSelDTO.setProjectionHeaderMap(projHeaderMap);
		}
		projectionTotalList.clear();
		projectionTotalList.add(exFactorySalesDTO);
		projectionTotalList.add(demandSalesDTO);
		projectionTotalList.add(inventoryWithdrawDTO);
		projectionTotalList.add(conSaleDTO);
		projectionTotalList.add(unitVolDTO);
		projectionTotalList.add(PercentageExFactorySalesDto);
		return projectionTotalList;
	}

	public String getFormattedValue(DecimalFormat FORMAT, String value) {
		if (value.contains(Constant.NULL)) {
			value = SPRDASH.getConstant();
		} else if (FORMAT.equals(CUR_PER)) {
			value = FORMAT.format(Double.valueOf(value)) + PERCENT;
		} else {
			value = FORMAT.format(Double.valueOf(value));
		}
		return value;
	}

	public List<SalesProjectionResultsDTO> getContractSalesAndUnits(ProjectionSelectionDTO projSelDTO) {
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			LOGGER.debug("Entering getContractSalesAndUnits NonMandated");
			projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
			String query = getContractSalesAndUnitsQuery(projSelDTO);
			List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(
					QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
			projDTOList = getCustomizedSalesProjectionResultsSales(list, projSelDTO);
			LOGGER.debug("Ending getContractSalesAndUnits NonMandated");
		} else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			LOGGER.debug("Entering getContractSalesAndUnitsMandated");
			projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
			List<Object> list = (List<Object>) SPRCommonLogic.executeSelectQuery(
					QueryUtil.replaceTableNames(getSalesProjectionResultsSalesQueryMandated(projSelDTO),
							projSelDTO.getSessionDTO().getCurrentTableNames()),
					null, null);
			projDTOList = getCustomizedSalesProjectionResultsSalesMandated(list, projSelDTO);
			LOGGER.debug("Ends getContractSalesAndUnitsMandated");
		}
		return projDTOList;
	}

	public List<SalesProjectionResultsDTO[]> getContractSalesAndUnitsMultiple(Set nodeSet,
			ProjectionSelectionDTO projSelDTO) {
		CommonLogic logic = new CommonLogic();
		String sql = logic.insertSelectedHierarchHierarchyNo(nodeSet, projSelDTO);
		sql += SQlUtil.getQuery("non-mandated-sales-projections-query-new");
		List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(
				getContractsAndUnit(sql, projSelDTO), projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
		return getCustomizedSalesProjectionResultsSalesMultiple(list, projSelDTO);

	}

	public List<SalesProjectionResultsDTO[]> getContractSalesAndUnitsMultipleCustom(Set nodeSet,
			ProjectionSelectionDTO projSelDTO) {
		CommonLogic logic = new CommonLogic();
		String sql = logic.insertAvailableHierarchyNoForCustomExpand(nodeSet, projSelDTO);
		sql += SQlUtil.getQuery("non-mandated-sales-projections-query-new-custom");
		List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(
				getContractsAndUnit(sql, projSelDTO), projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
		return getCustomizedSalesProjectionResultsSalesMultiple(list, projSelDTO);

	}

	public String getContractSalesAndUnitsQuery(ProjectionSelectionDTO projSelDTO) {
		CommonLogic commonLogic = new CommonLogic();
		String sql;

		sql = commonLogic.insertAvailableHierarchyNo(projSelDTO);
		return getContractsAndUnit(sql, projSelDTO);
	}

	public String getContractsAndUnit(String sql, ProjectionSelectionDTO projSelDTO) {
		CommonLogic commonLogic = new CommonLogic();

		switch (projSelDTO.getFrequencyDivision()) {

		case 1:
			sql = sql.replace(Constant.FREQ_AT, "year");
			break;
		case NumericConstants.TWO:
			sql = sql.replace(Constant.FREQ_AT, Constant.SEMI_ANNUAL);
			break;
		case NumericConstants.FOUR:
			sql = sql.replace(Constant.FREQ_AT, Constant.QUARTER);
			break;
		case NumericConstants.TWELVE:
			sql = sql.replace(Constant.FREQ_AT, "MONTH");
			break;
		default:
			break;
		}
		sql = sql.replace("[?SELECTED_HIERARCHY_JOIN]", commonLogic.getHierarchyJoinQuery(projSelDTO));
		sql = sql.replace("@USER_ID", projSelDTO.getSessionDTO().getUserId());
		sql = sql.replace("@SESSION_ID", projSelDTO.getSessionDTO().getSessionId());
		sql = sql.replace("@LEVEL_NO", String.valueOf(projSelDTO.getTreeLevelNo()));
		sql = sql.replace("@HIERARCHY_INDICATOR", projSelDTO.getHierarchyIndicator());
		sql = sql.replace("@PROJECTION_MASTER_SID", String.valueOf(projSelDTO.getProjectionId()));

		return sql;
	}

	public int getFrequencyNumber(String frequency) {
		int frequencyNo = NumericConstants.THREE;
		if (frequency.equals(QUARTERLY.getConstant())) {
			frequencyNo = NumericConstants.THREE;
		} else if (frequency.equals(SEMI_ANNUAL.getConstant())) {
			frequencyNo = NumericConstants.SIX;
		} else if (frequency.equals(MONTHLY.getConstant())) {
			frequencyNo = 1;
		} else if (frequency.equals(ANNUAL.getConstant())) {
			frequencyNo = NumericConstants.TWELVE;
		}
		return frequencyNo;
	}

	public String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP) {
		String ccpWhereCond = Constant.AND_SMALL_SPACE + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP
				+ ".RELATIONSHIP_LEVEL_SID and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
		return ccpWhereCond;
	}

	public String getPeriodRestrictionQuery(ProjectionSelectionDTO projSelDTO) {
		String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
				+ String.format("%02d", projSelDTO.getEndMonth()) + "-" + String.format("%02d", projSelDTO.getEndDay());

		String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
				+ String.format("%02d", projSelDTO.getStartMonth()) + "-"
				+ String.format("%02d", projSelDTO.getStartDay());
		String periodFilter = StringUtils.EMPTY;
		if (!CommonUtils.isInteger(projSelDTO.getYear())) {

			periodFilter = " and PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate
					+ "') ";
		}
		return periodFilter;
	}

	public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
		String user = Constant.AND_SMALL_SPACE + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table
				+ ".SESSION_ID=" + sessionId + " ";
		return user;
	}

	public String getCCPQuery(ProjectionSelectionDTO projSelDTO) {
		String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
		String ccpQuery;
		if (projSelDTO.isIsCustomHierarchy()) {
			ccpQuery = getCCPQueryCustom(projSelDTO);
		} else {
			ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
					+ "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
					+ "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,  CCP.CCP_DETAILS_SID "
					+ "FROM RELATIONSHIP_LEVEL_DEFINITION  RLD "
					+ "JOIN CCP_MAP CCP ON  RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
					+ "JOIN PROJECTION_DETAILS  PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID="
					+ projSelDTO.getProjectionId() + " JOIN    " + viewtable
					+ " PCH1 ON   RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID"
					+ " JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
					+ " WHERE PM.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + ") CCPMAP,"
					+ " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
					+ " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1" + " JOIN " + viewtable + " PCH "
					+ " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID" + " AND PCH.PROJECTION_MASTER_SID="
					+ projSelDTO.getProjectionId() + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo()
					+ "%' ) HLD" + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
					+ " WHERE LCCP.HIERARCHY_NO in" + " (SELECT RLD2.HIERARCHY_NO"
					+ " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2" + " JOIN " + viewtable + " PCH2"
					+ " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID" + " AND PCH2.PROJECTION_MASTER_SID="
					+ projSelDTO.getProjectionId() + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ")) CCP ";
		}
		return ccpQuery;
	}

	public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSales(List<Object> list,
			ProjectionSelectionDTO projSelDTO) {
		List<SalesProjectionResultsDTO> projDtoList = new ArrayList<>();
		List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
		columnList.remove(Constant.LEVEL_VALUE_SMALL);
		SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO exSalesDTO = new SalesProjectionResultsDTO();
		projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
		projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
		projSalesDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());
		projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
		projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());

		projUnitDTO.setLevelValue(UNIT_VOL.getConstant());
		exSalesDTO.setLevelNo(exSalesDTO.getLevelNo());
		exSalesDTO.setTreeLevelNo(exSalesDTO.getTreeLevelNo());
		exSalesDTO.setLevelValue(SALES_PERC_OF_EX_FACTORY_SALES.getConstant());
		projSalesDTO.setParent(0);
		projUnitDTO.setParent(0);
		exSalesDTO.setParent(0);
		int frequencyDivision;
		if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.FOUR;
		} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWELVE;
		} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWO;
		} else {
			frequencyDivision = 1;
		}
		String salesOrUnits = projSelDTO.getSalesOrUnit();
		if (list != null && !list.isEmpty()) {
			for (Object list1 : list) {
				final Object[] obj = (Object[]) list1;
				int year = Integer.valueOf(String.valueOf(obj[0]));
				int period = Integer.valueOf(String.valueOf(obj[1]));
				List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
				String commonColumn = common.get(0);
				int col = NumericConstants.TWO;
				if (Constant.SALES.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					if (Constant.DASH.equals(String.valueOf(obj[col + NumericConstants.FOUR]))) {
						String actual = StringUtils.EMPTY + obj[col];
						actual = getFormattedValue(CUR_ZERO, actual);
						projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
						columnList.remove(commonColumn + Constant.ACTUALS);
					}

					String projection = StringUtils.EMPTY;
					if (Constant.STRING_ONE.equals(String.valueOf(obj[col + NumericConstants.FOUR]))) {
						projection += obj[col + 1];
						projection = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection
								: Constant.ZERO_STRING;
						projection = getFormattedValue(CUR_ZERO, projection);
						projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
						columnList.remove(commonColumn + Constant.PROJECTIONS);
					}

				}
				if (Constant.UNITS.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					if (Constant.DASH.equals(String.valueOf(obj[col + NumericConstants.FOUR]))) {
						String actual = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
						actual = getFormattedValue(UNITVOLUME, actual);
						projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
						columnList.remove(commonColumn + Constant.ACTUALS);
					}
					String projection = StringUtils.EMPTY;
					if (Constant.STRING_ONE.equals(String.valueOf(obj[col + NumericConstants.FOUR]))) {
						projection += obj[col + NumericConstants.THREE];
						projection = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection
								: Constant.ZERO_STRING;
						projection = getFormattedValue(UNITVOLUME, projection);
						projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
						columnList.remove(commonColumn + Constant.PROJECTIONS);
					}

				}
			}

		}
		for (String columns : columnList) {
			projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
			projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
		}
		projDtoList.add(projSalesDTO);
		projDtoList.add(projUnitDTO);
		return projDtoList;
	}

	private SalesProjectionResultsDTO getSalesPRDTO(String levelName) {
		SalesProjectionResultsDTO salesPRdata = new SalesProjectionResultsDTO();
		salesPRdata.setLevelValue(levelName);
		salesPRdata.setParent(0);
		return salesPRdata;
	}

	public List<SalesProjectionResultsDTO[]> getCustomizedSalesProjectionResultsSalesMultiple(List<Object> list,
			ProjectionSelectionDTO projSelDTO) {
		List<SalesProjectionResultsDTO[]> contractUnitList = new ArrayList<>();
		SalesProjectionResultsDTO[] projDtoArray = new SalesProjectionResultsDTO[3];
		List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
		columnList.remove(Constant.LEVEL_VALUE_SMALL);
		SalesProjectionResultsDTO projSalesDTO = getSalesPRDTO(CONTRACT_SALES_AT_WAC.getConstant());
		SalesProjectionResultsDTO projUnitDTO = getSalesPRDTO(UNIT_VOL.getConstant());
		SalesProjectionResultsDTO projExFacDTO = getSalesPRDTO(SALES_PERC_OF_EX_FACTORY_SALES.getConstant());
		int frequencyDivision;
		if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.FOUR;
		} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWELVE;
		} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWO;
		} else {
			frequencyDivision = 1;
		}

		if (list != null && !list.isEmpty()) {
			Object[] firstData = ((Object[]) list.get(0));
			String hierarChyNo = String.valueOf(firstData[firstData.length - 1]);
			for (Object list1 : list) {
				final Object[] obj = (Object[]) list1;
				if (hierarChyNo.equals(String.valueOf(obj[obj.length - 1]))) {
					generateContractUnitData(obj, frequencyDivision, projSelDTO, projSalesDTO, projUnitDTO,
							projExFacDTO, columnList);
				} else {
					for (String columns : columnList) {
						projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
						projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
						projExFacDTO.addStringProperties(columns, getFormattedValue(CUR_PER, Constant.NULL));
					}
					columnList = new ArrayList<>(projSelDTO.getColumns());
					columnList.remove(Constant.LEVEL_VALUE_SMALL);
					projDtoArray[0] = projSalesDTO;
					projDtoArray[1] = projUnitDTO;
					projDtoArray[2] = projExFacDTO;
					contractUnitList.add(projDtoArray);
					projSalesDTO = getSalesPRDTO(CONTRACT_SALES_AT_WAC.getConstant());
					projUnitDTO = getSalesPRDTO(UNIT_VOL.getConstant());
					projExFacDTO = getSalesPRDTO(SALES_PERC_OF_EX_FACTORY_SALES.getConstant());
					projDtoArray = new SalesProjectionResultsDTO[3];
					hierarChyNo = String.valueOf(obj[obj.length - 1]);
					generateContractUnitData(obj, frequencyDivision, projSelDTO, projSalesDTO, projUnitDTO,
							projExFacDTO, columnList);
				}
			}
		}
		for (String columns : columnList) {
			projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
			projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
			projExFacDTO.addStringProperties(columns, getFormattedValue(CUR_PER, Constant.NULL));
		}
		projDtoArray[0] = projSalesDTO;
		projDtoArray[1] = projUnitDTO;
		projDtoArray[2] = projExFacDTO;
		contractUnitList.add(projDtoArray);
		return contractUnitList;
	}

	private void generateContractUnitData(Object[] obj, int frequencyDivision, ProjectionSelectionDTO projSelDTO,
			SalesProjectionResultsDTO projSalesDTO, SalesProjectionResultsDTO projUnitDTO,
			SalesProjectionResultsDTO projExFac, List<String> columnList) {
		int year = Integer.valueOf(String.valueOf(obj[0]));
		int period = Integer.valueOf(String.valueOf(obj[1]));
		List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
		String commonColumn = common.get(0);
		int col = NumericConstants.TWO;
		if (Constant.DASH.equals(String.valueOf(obj[col + NumericConstants.SIX]))) {
			String actual = StringUtils.EMPTY + obj[col];
			actual = getFormattedValue(CUR_ZERO, actual);
			projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
			columnList.remove(commonColumn + Constant.ACTUALS);
		}

		String projection1 = StringUtils.EMPTY;
		if (Constant.STRING_ONE.equals(String.valueOf(obj[col + NumericConstants.SIX]))) {
			projection1 += obj[col + 1];
			projection1 = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection1 : Constant.ZERO_STRING;
			projection1 = getFormattedValue(CUR_ZERO, projection1);
			projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection1);
			columnList.remove(commonColumn + Constant.PROJECTIONS);
		}

		if (Constant.DASH.equals(String.valueOf(obj[col + NumericConstants.SIX]))) {
			String actual = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
			actual = getFormattedValue(UNITVOLUME, actual);
			projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
			columnList.remove(commonColumn + Constant.ACTUALS);
		}
		String projection = StringUtils.EMPTY;
		if (Constant.STRING_ONE.equals(String.valueOf(obj[col + NumericConstants.SIX]))) {
			projection += obj[col + NumericConstants.THREE];
			projection = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection : Constant.ZERO_STRING;
			projection = getFormattedValue(UNITVOLUME, projection);
			projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
			columnList.remove(commonColumn + Constant.PROJECTIONS);
		}

		if (Constant.DASH.equals(String.valueOf(obj[col + NumericConstants.SIX]))) {
			String actual = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
			actual = getFormattedValue(CUR_PER, actual);
			projExFac.addStringProperties(commonColumn + Constant.ACTUALS, actual);
			columnList.remove(commonColumn + Constant.ACTUALS);
		}
		String projection3 = StringUtils.EMPTY;
		if (Constant.STRING_ONE.equals(String.valueOf(obj[col + NumericConstants.SIX]))) {
			projection3 += obj[col + NumericConstants.FOUR];
			projection3 = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection3 : Constant.ZERO_STRING;
			projection3 = getFormattedValue(CUR_PER, projection3);
			projExFac.addStringProperties(commonColumn + Constant.PROJECTIONS, projection3);
			columnList.remove(commonColumn + Constant.PROJECTIONS);
		}

	}

	public List<SalesProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getProjectionPivotTotal");
		projectionTotalList.clear();// Fix for GAL-4084
		if (projectionTotalList.isEmpty()) {
			List<Object[]> gtsList = null;
			if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				LOGGER.debug("Entering getProjectionPivotTotal NonMandated");

				if (!projSelDTO.getSessionDTO().isSprRefreshReqd()
						&& CommonLogic.checkProcedureInputIsSame(orderedArgs, nonmandatedorderedArgs)) {
					gtsList = nonmandatedGtsList;
				} else {
					gtsList = CommonLogic.callProcedure(SalesUtils.PRC_PROJECTION_RESULTS, orderedArgs);
					nonmandatedorderedArgs = new Object[orderedArgs.length];
					System.arraycopy(orderedArgs, 0, nonmandatedorderedArgs, 0, orderedArgs.length);
					nonmandatedGtsList.clear();
					nonmandatedGtsList.addAll(gtsList);
				}
				getCustomizedProjectionPivotTotal(gtsList, projSelDTO);
				projSelDTO.getSessionDTO().setSprRefreshReqd(false);
				LOGGER.debug("Ending getProjectionPivotTotal NonMandated");
			} else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
				LOGGER.debug("Entering getProjectionPivotTotal Mandated");
				if (!projSelDTO.getSessionDTO().isSprRefreshReqd()
						&& CommonLogic.checkProcedureInputIsSame(orderedArgs, mandatedorderedArgs)) {
					gtsList = mandatedGtsList;
				} else {
					gtsList = CommonLogic.callProcedure(Constant.PRC_M_PROJECTION_RESULTS, orderedArgs);
					mandatedorderedArgs = new Object[orderedArgs.length];
					System.arraycopy(orderedArgs, 0, mandatedorderedArgs, 0, orderedArgs.length);
					mandatedGtsList.clear();
					mandatedGtsList.addAll(gtsList);
				}
				projSelDTO.getSessionDTO().setSprRefreshReqd(false);
				projectionTotalList.clear();
				projectionTotalList = getCustomizedProjectionPivotTotalMandated(gtsList, projSelDTO);
				LOGGER.debug("Ending getProjectionPivotTotal Mandated");
			}
		}
		LOGGER.debug("Ending getProjectionPivotTotal");
		return projectionTotalList;
	}

	public void getCustomizedProjectionPivotTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getCustomizedProjectionPivotTotal");
		projectionTotalList.clear();
		int frequencyDivision = projSelDTO.getFrequencyDivision();
		List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
		int col = NumericConstants.FIVE;
		if (frequencyDivision != 1) {
			col = col + 1;
		}
		for (Object[] row : list) {

			String column;
			int year = Integer.valueOf(String.valueOf(row[col - 1]));
			int period = Integer.valueOf(String.valueOf(row[NumericConstants.FOUR]));
			List<String> common;
			if ("SPR".equals(projSelDTO.getProjTabName())) {
				common = getCommonColumnHeaderSPR(frequencyDivision, year, period);
			} else {
				common = getCommonColumnHeader(frequencyDivision, year, period);
			}

			String pcommonColumn = common.get(0);
			String commonHeader = common.get(1);
			String commonColumn;
			if (periodList.contains(pcommonColumn)) {
				periodList.remove(pcommonColumn);
				SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
				projDTO.setLevelValue(commonHeader);
				String value;
				commonColumn = "efs";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[1];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWO];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				commonColumn = "dms";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_TWO];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_THREE];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}

				commonColumn = "iws";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_FOUR];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				commonColumn = "csw";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + 1];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				commonColumn = "uv";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
					value = getFormattedValue(UNITVOLUME, value);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
					value = getFormattedValue(UNITVOLUME, value);
					projDTO.addStringProperties(column, value);
				}
				commonColumn = "cexs";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + 8];
					value = getFormatValue(TWO_DECIMAL, value, PERCENT);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + 9];
					value = getFormatValue(TWO_DECIMAL, value, PERCENT);
					projDTO.addStringProperties(column, value);
				}
				projDTO.setParent(0);
				projDTO.setProjectionTotal(1);
				projectionTotalList.add(projDTO);
			}

		}
		boolean leftFlag = false;
		for (String ob : periodList) {
			leftFlag = true;
			SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
			projDTO.setParent(0);
			projDTO.setProjectionTotal(1);
			projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
			projectionTotalList.add(projDTO);
		}
		if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
			if (leftFlag) {
				Collections.sort(projectionTotalList, new SalesProjectionResultsDTO());
			}
		} else {
			if (leftFlag) {
				Collections.sort(projectionTotalList, new SalesProjectionResultsDTO());
			}
			Collections.reverse(projectionTotalList);
		}
		LOGGER.debug("Ending getCustomizedProjectionPivotTotal");
	}

	public List<SalesProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			LOGGER.debug("Entering getProjection Pivot NonMandated");

			List<Object> gtsList = (List<Object>) CommonLogic
					.executeSelectQuery(CommonLogic.getCCPQuery(projSelDTO, Boolean.FALSE) + " \n"
							+ getSalesProjectionResultsSalesQuery(projSelDTO), null, null);
			projDTOList = getCustomizedProjectionPivot(gtsList, projSelDTO);
			LOGGER.debug("Ending getProjection Pivot NonMandated");
		} else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			LOGGER.debug("Entering getProjectionPivot Mandated");
			List<Object[]> gtsList = (List<Object[]>) SPRCommonLogic.executeSelectQuery(
					QueryUtil.replaceTableNames(getSalesProjectionResultsSalesQueryMandated(projSelDTO),
							projSelDTO.getSessionDTO().getCurrentTableNames()),
					null, null);
			projDTOList = getCustomizedProjectionPivotMandated(gtsList, projSelDTO);
			if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
				Collections.reverse(projDTOList);
			}
			LOGGER.debug("Ends getProjectionPivot Mandated");
		}
		return projDTOList;
	}

	public List<SalesProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list,
			ProjectionSelectionDTO projSelDTO) {

		int frequencyDivision = projSelDTO.getFrequencyDivision();
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
		int col = NumericConstants.TWO;
		for (Object rows : list) {
			final Object[] row = (Object[]) rows;
			String column;
			int year = Integer.valueOf(String.valueOf(row[0]));
			int period = Integer.valueOf(String.valueOf(row[1]));
			List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
			String pcommonColumn = common.get(0);
			String commonHeader = common.get(1);
			String commonColumn;
			if (periodList.contains(pcommonColumn)) {
				periodList.remove(pcommonColumn);
				SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
				List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
				columnList.remove(Constant.LEVEL_VALUE_SMALL);
				projDTO.setLevelValue(commonHeader);
				String value;
				commonColumn = "gts";
				value = "...";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					projDTO.addStringProperties(column, value);
					columnList.remove(column);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					projDTO.addStringProperties(column, value);
					columnList.remove(column);
				}
				commonColumn = "csw";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col];
					value = getFormattedValue(CUR_ZERO, value);
					projDTO.addStringProperties(column, value);
					columnList.remove(column);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + 1];
					value = getFormattedValue(CUR_ZERO, value);
					projDTO.addStringProperties(column, value);
					columnList.remove(column);
				}
				commonColumn = "uv";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
					value = getFormattedValue(UNITVOLUME, value);
					projDTO.addStringProperties(column, value);
					columnList.remove(column);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
					value = getFormattedValue(UNITVOLUME, value);
					projDTO.addStringProperties(column, value);
					columnList.remove(column);
				}
				projDTO.setParent(0);
				projDTO.setProjectionTotal(1);
				for (String columns : columnList) {
					projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
				}
				projDTOList.add(projDTO);
			}
		}
		List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
		columnList.remove(Constant.LEVEL_VALUE_SMALL);
		boolean leftFlag = false;
		for (String ob : periodList) {
			leftFlag = true;
			SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
			projDTO.setParent(0);
			projDTO.setProjectionTotal(1);
			projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
			for (String columns : columnList) {
				projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
			}
			projDTOList.add(projDTO);
		}
		if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
			if (leftFlag) {
				Collections.sort(projDTOList, new SalesProjectionResultsDTO());
			}
		} else {
			if (leftFlag) {
				Collections.sort(projDTOList, new SalesProjectionResultsDTO());
			}
			Collections.reverse(projDTOList);
		}
		return projDTOList;
	}

	public int getConfiguredSalesProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO,
			boolean isLevelCount) {
		projSelDTO.setGroupCount(false);
		int count = 0;
		if (!projSelDTO.isIsFilter() || (parentId instanceof SalesProjectionResultsDTO)) {
			projSelDTO.setYear(Constant.ALL);

			if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
				projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
			}

			if (parentId instanceof SalesProjectionResultsDTO) {
				projSelDTO.setIsProjectionTotal(false);
				SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
				projSelDTO.setLevelNo(parentDto.getLevelNo());
				projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
				projSelDTO.setLevelValue(parentDto.getLevelValue());
				projSelDTO.setParentNode(parentDto.getParentNode());
				projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
				if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY
						.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
				}
				projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
				projSelDTO.setGroup(parentDto.getGroup());
				projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
			} else {
				projSelDTO.setIsProjectionTotal(true);
				if (projSelDTO.isIsCustomHierarchy()) {
					projSelDTO.setLevelNo(0);
					projSelDTO.setTreeLevelNo(0);
				} else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
				}
				projSelDTO.setLevelValue(StringUtils.EMPTY);
				projSelDTO.setHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
			}
			count += getProjectionResultsCount(projSelDTO, isLevelCount);
		} else {
			projSelDTO.setIsProjectionTotal(false);
			projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
			projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
			count += configureLevelsCount(projSelDTO);
		}
		return count;
	}

	public int getConfiguredSalesProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
		int count = 0;
		projSelDTO.setIsProjectionTotal(true);
		if (!projSelDTO.isIsFilter()) {
			projSelDTO.setYear(Constant.ALL);

			if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
				projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
			}
			if (Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(1);
			} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.TWO);
			} else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.FOUR);
			} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.TWELVE);
			}
			if (projSelDTO.isIsCustomHierarchy()) {
				projSelDTO.setLevelNo(0);
				projSelDTO.setTreeLevelNo(0);
			} else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
				projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
				projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
			} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
				projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
				projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
			}
			projSelDTO.setGroup(StringUtils.EMPTY);
			projSelDTO.setHierarchyNo(StringUtils.EMPTY);
			projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
			projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
			count += getProjectionResultsTotalCount(projSelDTO);
		}
		return count;
	}

	public int getProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
		int count = 1;
		if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
			count = count + 1;
			String salesUnits = projSelDTO.getSalesOrUnit();
			if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)) {
				count++;
			}
			if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL)) {
				count++;
			}
		} else {
			count = count + projSelDTO.getPeriodList().size();
		}
		return count;
	}

	public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
		int count = 0;
		if (!projSelDTO.getLevelValue().startsWith(Constant.ALL)
				&& !projSelDTO.getLevelValue().contains(Constant.SALES_WITH_HYPHEN)) {
			if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
				if (projSelDTO.isIsProjectionTotal()) {
					count = count + NumericConstants.TWO;
					if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
						count = count + NumericConstants.FIVE;
					} else if (projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
						count = count + NumericConstants.FOUR;
					} else if (projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {

						count = count + NumericConstants.FOUR;
					}
				} else {
					if (projSelDTO.getSalesOrUnit().equals(SALES.getConstant())
							|| projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
						count = count + NumericConstants.TWO;
					}
					if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
						count = count + NumericConstants.THREE;
					}
				}
			} else {
				count = count + projSelDTO.getPeriodList().size();
				if (projSelDTO.isIsProjectionTotal()) {
					count++;
				}
			}
		}
		if (isLevelCount && !projSelDTO.isIsFilter()) {
			if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
					&& ((projSelDTO.isIsCustomHierarchy())
							|| (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
					&& !projSelDTO.getLevelValue().startsWith(Constant.ALL)
					&& !projSelDTO.getLevelValue().contains(Constant.SALES_WITH_HYPHEN)) {
				count = count + 1;
				projSelDTO.setGroupCount(true);
				projSelDTO.setLevelCount(1);
			} else {
				projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
				int levelCount = configureLevelsCount(projSelDTO);
				count = count + levelCount;
				projSelDTO.setLevelCount(levelCount);

			}

		}
		return count;

	}

	public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
		int count = 0;
		try {
			CommonLogic commonLogic = new CommonLogic();
			if (projSelDTO.isIsCustomHierarchy()) {
				count = commonLogic.getCountForCustomView(projSelDTO);
			} else {
				count = commonLogic.getCount(projSelDTO);
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error(e);
		}
		return count;
	}

	public String getCCPQueryCustom(ProjectionSelectionDTO projSelDTO) {
		String ccpQuery = "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
				+ "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
				+ "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
				+ "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID="
				+ projSelDTO.getProjectionId() + ") CCPMAPC " + "JOIN"
				+ "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
				+ "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
				+ "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
				+ "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID="
				+ projSelDTO.getProjectionId() + ") CCPMAPP"
				+ " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID JOIN "
				+ " (SELECT RLD2.HIERARCHY_NO, RLD2.RELATIONSHIP_LEVEL_SID,"
				+ " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD "
				+ "JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId()
				+ " AND CVD.LEVEL_NO  like '";
		if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
			ccpQuery += projSelDTO.getLevelNo() + "'";
		} else {
			ccpQuery += " %'";
		}

		ccpQuery += " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
				+ " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
				+ " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
				+ projSelDTO.getProjectionId() + " WHERE RLD2.HIERARCHY_NO like '" + projSelDTO.getCustomerHierarchyNo()
				+ "%' ) HLDC" + " ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'  " + " JOIN"
				+ " (SELECT RLD2.HIERARCHY_NO, RLD2.RELATIONSHIP_LEVEL_SID,"
				+ " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD "
				+ "JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId()
				+ " AND CVD.LEVEL_NO  like '";
		if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
			ccpQuery += projSelDTO.getLevelNo() + "'";
		} else {
			ccpQuery += " %'";
		}
		ccpQuery += " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
				+ " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
				+ " JOIN PROJECTION_PROD_HIERARCHY PCH2" + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
				+ " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + " WHERE RLD2.HIERARCHY_NO like '"
				+ projSelDTO.getProductHierarchyNo() + "%' ) HLDP"
				+ " ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'" + ") CCP ";
		return ccpQuery;
	}

	public String getSalesProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {

		String selectClause = Constant.SELECT_SMALL_SPACE;
		String whereClause = StringUtils.EMPTY;
		String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"\n";
		String customQuery;
		String ccpWhereCond = CommonLogic.getCCPWhereConditionQuery("H", "E", "CCP");
		groupBy = ", H.HIERARCHY_NO " + groupBy;
		selectClause += "I.\"YEAR\" as YEARS, ";
		if (CommonUtils.isInteger(projSelDTO.getYear())) {

			whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
		}
		if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
			selectClause += "I.QUARTER as PERIODS, \n";
			whereClause += StringUtils.EMPTY;
			groupBy += Constant.IQUARTER;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
			selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
			whereClause += StringUtils.EMPTY;
			groupBy += ", I.SEMI_ANNUAL";
		} else if (projSelDTO.getFrequencyDivision() == 1) {
			selectClause += "'0' as PERIODS, \n";
			whereClause += StringUtils.EMPTY;
			groupBy += StringUtils.EMPTY;

		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
			selectClause += "I.\"MONTH\" as PERIODS, \n";
			whereClause += StringUtils.EMPTY;
			groupBy += ", I.\"MONTH\"";
		}

		// To filter the data according to selected period
		String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

		String customSql = "  ST_NM_SALES_PROJECTION_MASTER B,\n" + " PROJECTION_DETAILS E , \n"
				+ " RELATIONSHIP_LEVEL_DEFINITION H,\n" + " \"PERIOD\" I, \n" + " @CCP CCP "
				+ "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
				+ " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n" + " and E.PROJECTION_MASTER_SID = "
				+ projSelDTO.getProjectionId() + "\n" + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID AND \n"
				+ periodFilter + " and H.LEVEL_NO = " + projSelDTO.getLevelNo() + whereClause + "\n"
				+ " group by H.LEVEL_NO " + groupBy;

		String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
				+ " sum(A.HISTORY_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
				+ " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n" + " sum(A.HISTORY_PROJECTION_UNITS) as PROJECTION_UNITS \n"
				+ " from ST_NM_ACTUAL_SALES A,\n " + customSql;
		String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
				+ " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n" + " 0 as ACTUAL_UNITS, \n"
				+ " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n" + " from ST_NM_SALES_PROJECTION A,\n" + customSql;
		List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
		String finalWhereCond = list.get(1);
		String orderBy = list.get(NumericConstants.TWO);
		String finalSelectClause = "select " + list.get(0)
				+ "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as PROJECTION_SALES,"
				+ "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

		customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery
				+ "\n) FUTURE \n" + finalWhereCond + " Order By " + orderBy;
		return QueryUtil.replaceTableNames(customQuery, projSelDTO.getSessionDTO().getCurrentTableNames());
	}

	// Mandated
	public int getConfiguredSalesProjectionResultsCountMandated(Object parentId, ProjectionSelectionDTO projSelDTO,
			boolean isLevelCount) {
		int count = 0;
		if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb())
				|| (parentId instanceof SalesProjectionResultsDTO)) {
			projSelDTO.setYear(Constant.ALL);

			if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
				projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
			}
			if (parentId instanceof SalesProjectionResultsDTO) {
				SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
				projSelDTO.setLevelNo(parentDto.getLevelNo());
				projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
				projSelDTO.setLevelValue(parentDto.getLevelValue());
				projSelDTO.setParentNode(parentDto.getParentNode());
				projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
				projSelDTO.setCustomLevelNo(parentDto.getCustomLevelNo());
				if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY
						.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
				}
				projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
				projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
				projSelDTO.setGroup(parentDto.getGroup());
				projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
			} else {
				projSelDTO.setIsProjectionTotal(true);
				projSelDTO.setIsTotal(true);
				if (projSelDTO.isCustomFlag()) {
					String indicator = SPRCommonLogic.getIndicator(1, projSelDTO.getCustomId());
					projSelDTO.setHierarchyIndicator(indicator);
					projSelDTO.setLevelNo(0);
					projSelDTO.setTreeLevelNo(0);
					projSelDTO.setCustomLevelNo(0);
					;
				} else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
				} else {
					projSelDTO.setLevelNo(0);
					projSelDTO.setTreeLevelNo(0);
				}
				projSelDTO.setGroup(StringUtils.EMPTY);
				projSelDTO.setHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
			}

			count += getProjectionResultsCountMandated(projSelDTO, isLevelCount);
		} else if (isLevelCount || projSelDTO.isFilterDdlb()) {
			projSelDTO.setIsProjectionTotal(false);
			projSelDTO.setIsTotal(true);
			projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
			projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
			count += configureLevelsCount(projSelDTO);
		}
		return count;
	}

	public int getProjectionResultsCountMandated(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
		int count = 0;
		boolean tempCustomFlag = false;
		if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
			if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
				count = count + NumericConstants.FOUR;
			}
			String salesUnits = projSelDTO.getSalesOrUnit();
			if (projSelDTO.isIsTotal()) {
				count = count + 1;
				if (salesUnits.equals(Constant.BOTH)) {
					count++;
				}
			}
		} else if (projSelDTO.isIsProjectionTotal()) {
			count = count + 1 + projSelDTO.getPeriodList().size();
		} else {
			count = count + projSelDTO.getPeriodList().size();
		}
		if (!projSelDTO.isFilterDdlb() && projSelDTO.isIsTotal() && isLevelCount && !projSelDTO.isIsFilter()) {
			if (projSelDTO.isCustomFlag()) {
				projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
				int i = SPRCommonLogic.getIndicatorCount(projSelDTO.getCustomId());
				if (i >= projSelDTO.getCustomLevelNo()) {
					tempCustomFlag = true;
				}
			}
			if (projSelDTO.isCustomFlag() && tempCustomFlag) {
				String indicator = SPRCommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
				projSelDTO.setHierarchyIndicator(indicator);
				projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
				projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
				projSelDTO.setHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
			}
			if (projSelDTO.isCustomFlag()) {
				if (tempCustomFlag) {
					int temp = configureLevelsCount(projSelDTO);

					count = count + temp;
					projSelDTO.setLevelCount(temp);
				}
			} else {
				projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
				projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
				int temp = configureLevelsCount(projSelDTO);
				count = count + temp;
				projSelDTO.setLevelCount(temp);
			}
		}
		return count;
	}

	public int configureLevelsCountMandated(ProjectionSelectionDTO projSelDTO) {
		return SPRCommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(),
				projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.isIsFilter(),
				projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());
	}

	public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResultsMandated(Object parentId, int start,
			int offset, ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getConfiguredSalesProjectionResultsMandated ");
		List<SalesProjectionResultsDTO> resultList;

		if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb())
				|| (parentId instanceof SalesProjectionResultsDTO)) {
			projSelDTO.setYear(Constant.ALL);

			if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
				projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
			}
			if (Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(1);
			} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.TWO);
			} else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.FOUR);
			} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
				projSelDTO.setFrequencyDivision(NumericConstants.TWELVE);
			}
			if (parentId instanceof SalesProjectionResultsDTO) {
				SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
				projSelDTO.setLevelNo(parentDto.getLevelNo());
				projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
				projSelDTO.setLevelValue(parentDto.getLevelValue());
				projSelDTO.setParentNode(parentDto.getParentNode());
				projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
				projSelDTO.setCustomLevelNo(parentDto.getCustomLevelNo());
				if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY
						.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
					projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
					projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
				}
				projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
				projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
				projSelDTO.setGroup(parentDto.getGroup());
				projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
			} else {
				projSelDTO.setIsProjectionTotal(true);
				projSelDTO.setIsTotal(true);
				if (projSelDTO.isCustomFlag()) {
					String indicator = SPRCommonLogic.getIndicator(1, projSelDTO.getCustomId());
					projSelDTO.setHierarchyIndicator(indicator);
					projSelDTO.setLevelNo(0);
					projSelDTO.setTreeLevelNo(0);
					projSelDTO.setCustomLevelNo(0);
				} else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
				} else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
					projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
					projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
				} else {
					projSelDTO.setLevelNo(0);
					projSelDTO.setTreeLevelNo(0);
				}
				projSelDTO.setGroup(StringUtils.EMPTY);
				projSelDTO.setHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
				projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
			}

			resultList = getSalesProjectionResultsMandated(start, offset, projSelDTO);
		} else {
			projSelDTO.setIsProjectionTotal(false);
			projSelDTO.setIsTotal(true);
			projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
			projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
			resultList = configureLevelsMandated(start, offset, projSelDTO);
		}

		LOGGER.debug("Ends getConfiguredSalesProjectionResultsMandated ");
		return resultList;
	}

	public List<SalesProjectionResultsDTO> getSalesProjectionResultsMandated(int start, int offset,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getSalesProjectionResultsMandated");

		int neededRecord = offset;
		int started = start;
		int mayBeAdded = 0;
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		String freq = StringUtils.EMPTY;
		boolean tempCustomFlag = false;
		projSelDTO.setProjectionHeaderList(CommonUtils.prepareProjectionPeriodList(projSelDTO));
		if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.ANNUAL_CAPS;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO
				|| Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = "SEMI-ANNUALLY";
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR
				|| Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.QUARTERLY1;
		} else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE
				|| Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
			freq = Constant.MONTHLY_COLUMN;
		}

		Object[] orderedArgs = { projSelDTO.getProjectionId(), projSelDTO.getUserId(),
				projSelDTO.getSessionDTO().getSessionId(), freq, Constant.PROJECTION_RESULTS, null, null };
		if (projSelDTO.isIsProjectionTotal()) {
			if (start < 1) {
				SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
				dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
				dto.setParent(0);
				projDTOList.add(dto);
				neededRecord--;
			}
			mayBeAdded++;
			if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
				SalesProjectionResultsDTO demandSalesDTO = null;
				SalesProjectionResultsDTO inventoryWithdrawDTO = null;
				SalesProjectionResultsDTO contractSalesDto = null;
				SalesProjectionResultsDTO unitVolDto = null;

				getProjectionTotal(orderedArgs, projSelDTO);
				SalesProjectionResultsDTO exFactorySalesDTO = projectionTotalList.get(0);
				demandSalesDTO = projectionTotalList.get(1);
				inventoryWithdrawDTO = projectionTotalList.get(NumericConstants.TWO);
				contractSalesDto = projectionTotalList.get(NumericConstants.THREE);
				unitVolDto = projectionTotalList.get(NumericConstants.FOUR);
				projectionTotalList.clear();
				mayBeAdded++;
				if ((start < NumericConstants.TWO && neededRecord > 0) && (exFactorySalesDTO != null)) {
					projDTOList.add(exFactorySalesDTO);
					neededRecord--;

				}
				String salesUnits = projSelDTO.getSalesOrUnit();
				if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)
						|| salesUnits.equals(Constant.UNITS_SMALL))) {
					if (start < NumericConstants.FOUR) {
						if (demandSalesDTO != null) {
							projDTOList.add(demandSalesDTO);
							neededRecord--;
						}
						if (inventoryWithdrawDTO != null) {
							projDTOList.add(inventoryWithdrawDTO);
							neededRecord--;
						}
						if (contractSalesDto != null && !salesUnits.equals(Constant.UNITS_SMALL)) {
							projDTOList.add(contractSalesDto);
							neededRecord--;
						}
					}
					mayBeAdded++;
				}
				if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
					if (((salesUnits.equals(Constant.BOTH) && start < NumericConstants.FIVE)
							|| (start < NumericConstants.FOUR)) && (unitVolDto != null)) {
						projDTOList.add(unitVolDto);
						neededRecord--;
					}
					mayBeAdded++;
				}

			} else {
				int mayBeAddedRecord = start - mayBeAdded;
				if (mayBeAddedRecord < 0) {
					mayBeAddedRecord = 0;
				}
				projSelDTO.setProjTabName("SPR");
				List<SalesProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
				projSelDTO.setProjTabName(StringUtils.EMPTY);
				for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
					projDTOList.add(projectionDtoList.get(k));
				}
				mayBeAdded += projectionDtoList.size();
				projectionTotalList.clear();// Fix for GAL-4084
			}
		} else if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
			SalesProjectionResultsDTO contractSalesDto = null;
			SalesProjectionResultsDTO unitVolDto = null;

			String salesUnits = projSelDTO.getSalesOrUnit();
			if (neededRecord > 0 && projSelDTO.isIsTotal()) {
				if (contractSalesDto == null || unitVolDto == null) {

					List<SalesProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
					contractSalesDto = contractSalesAndUnits.get(0);
					unitVolDto = contractSalesAndUnits.get(1);
				}
				if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)) {
					boolean toadd = false;
					if (projSelDTO.isIsProjectionTotal() && start < NumericConstants.FOUR) {
						toadd = true;
					} else if (!projSelDTO.isIsProjectionTotal() && start < 1) {
						toadd = true;
					} else {
						toadd = false;
					}
					if (toadd && !projSelDTO.isIsProjectionTotal()) {
						projDTOList.add(contractSalesDto);
						neededRecord--;
					}
					mayBeAdded++;
				}
				if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
					boolean toadd = false;
					if (projSelDTO.isIsProjectionTotal() && start < NumericConstants.FIVE) {
						toadd = true;
					} else if (!projSelDTO.isIsProjectionTotal() && start < NumericConstants.TWO) {
						toadd = true;
					} else {
						toadd = false;
					}
					if (toadd && !projSelDTO.isIsProjectionTotal()) {
						projDTOList.add(unitVolDto);
						neededRecord--;
					}
					mayBeAdded++;
				}
			}
		} else {
			List<SalesProjectionResultsDTO> projectionDtoList;

			projectionDtoList = getProjectionPivot(projSelDTO);
			for (int k = started; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
				projDTOList.add(projectionDtoList.get(k));
				started++;
			}
			mayBeAdded += projectionDtoList.size();
		}
		if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) {
			int mayBeAddedRecord = start - mayBeAdded;
			if (mayBeAddedRecord < 0) {
				mayBeAddedRecord = 0;
			}

			if (projSelDTO.isCustomFlag()) {
				projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
				int i = SPRCommonLogic.getIndicatorCount(projSelDTO.getCustomId());

				if (i >= projSelDTO.getCustomLevelNo()) {
					tempCustomFlag = true;

				}
			}
			if (projSelDTO.isCustomFlag() && tempCustomFlag) {
				String indicator = SPRCommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
				projSelDTO.setHierarchyIndicator(indicator);
				projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
				projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
			}
			if (projSelDTO.isCustomFlag()) {
				if (tempCustomFlag) {
					List<SalesProjectionResultsDTO> nextLevelValueList = configureLevelsMandated(mayBeAddedRecord,
							neededRecord, projSelDTO);
					projDTOList.addAll(nextLevelValueList);
				}
			} else {
				projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
				projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
				List<SalesProjectionResultsDTO> nextLevelValueList = configureLevelsMandated(mayBeAddedRecord,
						neededRecord, projSelDTO);
				projDTOList.addAll(nextLevelValueList);
			}
		}
		LOGGER.debug("Ends getSalesProjectionResultsMandated");
		return projDTOList;
	}

	public List<SalesProjectionResultsDTO> configureLevelsMandated(int start, int offset,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering configureLevelsMandated");
		int neededRecord = offset;
		CommonLogic comm = new CommonLogic();
		List<SalesProjectionResultsDTO> resultList = new ArrayList<>();
		Map<String, List> levelMap = null;
		if (projSelDTO.isIsCustomHierarchy()) {
			projSelDTO.setHierarchyIndicator(comm.getHiearchyIndicatorFromCustomView(projSelDTO));
		}
		levelMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
		if (projSelDTO.isIsCustomHierarchy()) {
			List obj = comm.getHiearchyNoForCustomView(projSelDTO, start, offset);
			for (Object object : obj) {
				String hierarchyNo = String.valueOf(object);

				if (levelMap.containsKey(hierarchyNo)) {
					List levelValues = levelMap.get(object);
					Integer levelNo = Integer.valueOf((String) levelValues.get(2));
					SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
					dto.setLevelNo(levelNo);
					dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
					dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNo,
							projSelDTO.getHierarchyIndicator()));
					dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNo,
							projSelDTO.getHierarchyIndicator()));
					dto.setHierarchyNo(hierarchyNo);
					dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
					dto.setRelationshipLevelName(projSelDTO.getSessionDTO()
							.getLevelValueDiscription(dto.getHierarchyNo(), projSelDTO.getHierarchyIndicator()));
					if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
						dto.setCustomerHierarchyNo(dto.getHierarchyNo());
						dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
					} else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
						dto.setProductHierarchyNo(dto.getHierarchyNo());
						dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
					}
					dto.setCustomLevelNo(projSelDTO.getCustomLevelNo());
					dto.setOnExpandTotalRow(1);
					dto.setParent(1);
					resultList.add(dto);
					neededRecord--;
				}
			}
		} else {
			if (neededRecord > 0) {
				List<String> hierarchyNoList = comm.getHiearchyNoAsList(projSelDTO, start, offset);
				Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
				for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
					SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
					dto.setLevelNo(
							Integer.valueOf(relationshipLevelDetailsMap.get(hierarchyNoList.get(i)).get(2).toString()));
					dto.setTreeLevelNo(dto.getLevelNo());
					dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNoList.get(i),
							projSelDTO.getHierarchyIndicator()));
					dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNoList.get(i),
							projSelDTO.getHierarchyIndicator()));
					dto.setHierarchyNo(hierarchyNoList.get(i));
					dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
					dto.setRelationshipLevelName(projSelDTO.getSessionDTO()
							.getLevelValueDiscription(dto.getHierarchyNo(), projSelDTO.getHierarchyIndicator()));
					if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
						dto.setCustomerHierarchyNo(dto.getHierarchyNo());
						dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
					} else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
						dto.setProductHierarchyNo(dto.getHierarchyNo());
						dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
					}
					dto.setOnExpandTotalRow(1);
					dto.setParent(1);
					resultList.add(dto);
				}

			}
		}
		LOGGER.debug("Ends configureLevelsMandated");
		return resultList;
	}

	public List<SalesProjectionResultsDTO> getProjectionTotalMandated(Object[] orderedArgs,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Enetring getProjectionTotalMandated");
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		List<Object[]> gtsList = SPRCommonLogic.callProcedure(Constant.PRC_M_PROJECTION_RESULTS, orderedArgs);
		if (gtsList != null) {
			projDTOList = getCustomizedProjectionTotalMandated(gtsList, projSelDTO);
		}
		LOGGER.debug("Ending getProjectionTotalMandated");
		return projDTOList;

	}

	public List<SalesProjectionResultsDTO> getContractSalesAndUnitsMandated(ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getContractSalesAndUnitsMandated");
		projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
		List<Object> list = (List<Object>) SPRCommonLogic
				.executeSelectQuery(QueryUtil.replaceTableNames(getSalesProjectionResultsSalesQueryMandated(projSelDTO),
						projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
		List<SalesProjectionResultsDTO> projDTOList = getCustomizedSalesProjectionResultsSalesMandated(list,
				projSelDTO);
		LOGGER.debug("Ends getContractSalesAndUnitsMandated");
		return projDTOList;

	}

	public List<SalesProjectionResultsDTO> getProjectionPivotTotalMandated(Object[] orderedArgs,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getProjectionPivotTotalMandated");
		List<SalesProjectionResultsDTO> projDTOList;
		List<Object[]> gtsList = SPRCommonLogic.callProcedure(Constant.PRC_M_PROJECTION_RESULTS, orderedArgs);
		projDTOList = getCustomizedProjectionPivotTotalMandated(gtsList, projSelDTO);
		LOGGER.debug("Ends getProjectionPivotTotalMandated");
		return projDTOList;

	}

	public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotTotalMandated(List<Object[]> list,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getCustomizedProjectionPivotTotalMandated");
		int frequencyDivision = projSelDTO.getFrequencyDivision();
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
		int col = NumericConstants.FOUR;
		if (frequencyDivision != 1) {
			col = col + 1;
		}
		if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
			Collections.reverse(list);
		}

		for (Object[] row : list) {

			String column;
			int year = Integer.valueOf(String.valueOf(row[col - 1]));
			int period = Integer.valueOf(String.valueOf(row[NumericConstants.THREE]));
			List<String> common;
			if ("SPR".equals(projSelDTO.getProjTabName())) {
				common = getCommonColumnHeaderSPR(frequencyDivision, year, period);
			} else {
				common = getCommonColumnHeader(frequencyDivision, year, period);
			}

			String pcommonColumn = common.get(0);
			String commonHeader = common.get(1);
			String commonColumn;
			if (periodList.contains(pcommonColumn)) {
				periodList.remove(pcommonColumn);
				SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
				projDTO.setLevelValue(commonHeader);
				projDTO.setRelationshipLevelName(commonHeader);
				String value;
				commonColumn = "efs";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[1];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWO];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}

				commonColumn = "dms";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}

				commonColumn = "iws";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}

				commonColumn = "csw";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + 1];
					value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
					projDTO.addStringProperties(column, value);
				}
				commonColumn = "uv";
				column = commonColumn + ACTUALS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
					value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
					projDTO.addStringProperties(column, value);
				}
				column = commonColumn + PROJECTIONS.getConstant();
				if (projSelDTO.hasColumn(column)) {
					value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
					value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
					projDTO.addStringProperties(column, value);
				}
				projDTO.setParent(0);
				projDTO.setProjectionTotal(1);
				projDTOList.add(projDTO);
			}

		}
		for (String ob : periodList) {
			SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
			projDTO.setParent(0);
			projDTO.setProjectionTotal(1);
			projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
			projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
			projDTOList.add(projDTO);
		}

		LOGGER.debug("Ends getCustomizedProjectionPivotTotalMandated");
		return projDTOList;

	}

	public List<SalesProjectionResultsDTO> getProjectionPivotMandated(ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getProjectionPivotMandated");
		List<SalesProjectionResultsDTO> projDTOList;
		List<Object[]> gtsList = (List<Object[]>) SPRCommonLogic
				.executeSelectQuery(QueryUtil.replaceTableNames(getSalesProjectionResultsSalesQueryMandated(projSelDTO),
						projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
		projDTOList = getCustomizedProjectionPivotMandated(gtsList, projSelDTO);
		if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
			Collections.reverse(projDTOList);
		}
		LOGGER.debug("Ends getProjectionPivotMandated");
		return projDTOList;
	}

	public List<SalesProjectionResultsDTO> getCustomizedProjectionTotalMandated(List<Object[]> list,
			ProjectionSelectionDTO projSelDTO) {

		int frequencyDivision = projSelDTO.getFrequencyDivision();
		String salesOrUnits = projSelDTO.getSalesOrUnit();
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		SalesProjectionResultsDTO exFactorySalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO demandSalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO inventoryWithdrawDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO conSaleDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO unitVolDTO = new SalesProjectionResultsDTO();
		exFactorySalesDTO.setParent(0);
		exFactorySalesDTO.setLevelValue(EX_FACTORY_SALES.getConstant());

		demandSalesDTO.setParent(0);
		demandSalesDTO.setLevelValue(DEMAND_SALES.getConstant());

		inventoryWithdrawDTO.setParent(0);
		inventoryWithdrawDTO.setLevelValue(INVENTORY_WITHDRAW.getConstant());

		conSaleDTO.setParent(0);
		conSaleDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());

		unitVolDTO.setParent(0);
		unitVolDTO.setLevelValue(UNIT_VOL.getConstant());

		if (list != null && !list.isEmpty()) {
			int col = NumericConstants.FOUR;
			if (frequencyDivision != 1) {
				col = col + 1;
			}
			for (Object list1 : list) {
				final Object[] obj = (Object[]) list1;
				int year = Integer.valueOf(String.valueOf(obj[col - 1]));
				int period = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
				List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
				String commonColumn = common.get(0);

				String exFactoryActual = StringUtils.EMPTY + obj[1];
				exFactoryActual = getFormatValue(TWO_DECIMAL, exFactoryActual, CURRENCY);
				exFactorySalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, exFactoryActual);
				String exFactoryProjection = StringUtils.EMPTY + obj[NumericConstants.TWO];
				exFactoryProjection = getFormatValue(TWO_DECIMAL, exFactoryProjection, CURRENCY);
				exFactorySalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, exFactoryProjection);

				String demandActual = StringUtils.EMPTY + obj[NumericConstants.TWENTY_FIVE];
				demandActual = getFormatValue(TWO_DECIMAL, demandActual, CURRENCY);
				demandSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, demandActual);
				String demandProjection = StringUtils.EMPTY + obj[NumericConstants.TWENTY_SIX];
				demandProjection = getFormatValue(TWO_DECIMAL, demandProjection, CURRENCY);
				demandSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, demandProjection);

				String inventoryWithdrawActual = StringUtils.EMPTY + obj[NumericConstants.TWENTY_SEVEN];
				inventoryWithdrawActual = getFormatValue(TWO_DECIMAL, inventoryWithdrawActual, CURRENCY);
				inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.ACTUALS, inventoryWithdrawActual);
				String inventoryWithdrawProjection = StringUtils.EMPTY + obj[NumericConstants.TWENTY_EIGHT];
				inventoryWithdrawProjection = getFormatValue(TWO_DECIMAL, inventoryWithdrawProjection, CURRENCY);
				inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.PROJECTIONS,
						inventoryWithdrawProjection);

				if (Constant.SALES.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {

					String cswActuals = StringUtils.EMPTY + obj[col];
					cswActuals = getFormatValue(TWO_DECIMAL, cswActuals, CURRENCY);
					conSaleDTO.addStringProperties(commonColumn + Constant.ACTUALS, cswActuals);
					String cswProjections = StringUtils.EMPTY + obj[col + 1];
					cswProjections = getFormatValue(TWO_DECIMAL, cswProjections, CURRENCY);
					conSaleDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, cswProjections);
				}
				if (Constant.UNITS.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					String uvActuals = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
					uvActuals = getFormatValue(UNITVOLUME, uvActuals, StringUtils.EMPTY);
					unitVolDTO.addStringProperties(commonColumn + Constant.ACTUALS, uvActuals);
					String uvProjections = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
					uvProjections = getFormatValue(UNITVOLUME, uvProjections, StringUtils.EMPTY);
					unitVolDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, uvProjections);
				}
			}

		}

		projDTOList.add(exFactorySalesDTO);
		projDTOList.add(demandSalesDTO);
		projDTOList.add(inventoryWithdrawDTO);
		projDTOList.add(conSaleDTO);
		projDTOList.add(unitVolDTO);
		return projDTOList;
	}

	private String getSalesProjectionResultsSalesQueryMandated(ProjectionSelectionDTO projSelDTO) {
		CommonLogic commonLogic = new CommonLogic();
		String sql;

		sql = commonLogic.insertAvailableHierarchyNo(projSelDTO);

		switch (projSelDTO.getFrequencyDivision()) {

		case 1:
			sql += SQlUtil.getQuery("mandated-sales-projections-query-new-annual");
			sql = sql.replace(Constant.FREQ_AT, "1 ");
			break;
		case 2:
			sql += SQlUtil.getQuery(Constant.MANDATED_SALES_PROJECTIONS_QUERY_NEW);
			sql = sql.replace(Constant.FREQ_AT, Constant.SEMI_ANNUAL);
			break;
		case 4:
			sql += SQlUtil.getQuery(Constant.MANDATED_SALES_PROJECTIONS_QUERY_NEW);
			sql = sql.replace(Constant.FREQ_AT, Constant.QUARTER);
			break;
		case 12:
			sql += SQlUtil.getQuery(Constant.MANDATED_SALES_PROJECTIONS_QUERY_NEW);
			sql = sql.replace(Constant.FREQ_AT, "MONTH");
			break;
		default:
			break;
		}
		sql = sql.replace("[?SELECTED_HIERARCHY_JOIN]", commonLogic.getHierarchyJoinQuery(projSelDTO));
		sql = sql.replace("@USER_ID", projSelDTO.getSessionDTO().getUserId());
		sql = sql.replace("@SESSION_ID", projSelDTO.getSessionDTO().getSessionId());
		sql = sql.replace("@LEVEL_NO", String.valueOf(projSelDTO.getTreeLevelNo()));
		sql = sql.replace("@HIERARCHY_INDICATOR", projSelDTO.getHierarchyIndicator());
		sql = sql.replace("@PROJECTION_MASTER_SID", String.valueOf(projSelDTO.getProjectionId()));

		return sql;
	}

	public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSalesMandated(List<Object> list,
			ProjectionSelectionDTO projSelDTO) {
		LOGGER.debug("Entering getCustomizedSalesProjectionResultsSalesMandated");
		List<SalesProjectionResultsDTO> projDtoList = new ArrayList<>();
		List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
		columnList.remove(Constant.LEVEL_VALUE_SMALL);
		SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
		projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
		projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
		projSalesDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());
		projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
		projUnitDTO.setLevelValue(UNIT_VOL.getConstant());
		projSalesDTO.setParent(0);
		projUnitDTO.setParent(0);
		int frequencyDivision;
		if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.FOUR;
		} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWELVE;
		} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWO;
		} else {
			frequencyDivision = 1;
		}
		String salesOrUnits = projSelDTO.getSalesOrUnit();

		if (list != null && !list.isEmpty()) {
			for (Object list1 : list) {
				final Object[] obj = (Object[]) list1;
				int year = Integer.valueOf(String.valueOf(obj[0]));
				int period = Integer.valueOf(String.valueOf(obj[1]));
				List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
				String commonColumn = common.get(0);
				int col = NumericConstants.TWO;
				if (Constant.SALES.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					String actual = StringUtils.EMPTY + obj[col];
					actual = getFormatValue(TWO_DECIMAL, actual, CURRENCY);
					projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
					String projection = StringUtils.EMPTY + obj[col + 1];
					projection = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection
							: Constant.ZERO_STRING;
					projection = getFormatValue(TWO_DECIMAL, projection, CURRENCY);
					projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
					columnList.remove(commonColumn + Constant.PROJECTIONS);
				}
				if (Constant.UNITS.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					String actual = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
					actual = getFormatValue(UNITVOLUME, actual, StringUtils.EMPTY);
					projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
					String projection = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
					projection = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projection
							: Constant.ZERO_STRING;
					projection = getFormatValue(UNITVOLUME, projection, StringUtils.EMPTY);
					projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
					columnList.remove(commonColumn + Constant.PROJECTIONS);
				}
			}

		}
		for (String columns : columnList) {
			projSalesDTO.addStringProperties(columns, getFormatValue(TWO_DECIMAL, Constant.NULL, CURRENCY));
			projUnitDTO.addStringProperties(columns, getFormatValue(TWO_DECIMAL, Constant.NULL, StringUtils.EMPTY));
		}

		projDtoList.add(projSalesDTO);
		projDtoList.add(projUnitDTO);
		LOGGER.debug("Ends getCustomizedSalesProjectionResultsSalesMandated");
		return projDtoList;
	}

	public String getFormatValue(DecimalFormat FORMAT, String value, String appendChar) {
		if (value.contains(Constant.NULL)) {
			value = "...";
		} else if (CURRENCY.equals(appendChar)) {
			value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
		} else {
			value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
		}
		return value;
	}

	public static List<String> getCommonColumnHeaderMandated(int frequencyDivision, int year, int period) {
		List<String> common = new ArrayList<>();
		String commonColumn = StringUtils.EMPTY;
		String commonHeader = StringUtils.EMPTY;
		if (frequencyDivision == 1) {
			commonColumn = StringUtils.EMPTY + year;
			commonHeader = StringUtils.EMPTY + year;
		} else if (frequencyDivision == NumericConstants.FOUR) {
			commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
			commonHeader = Constant.Q + period + " " + year;
		} else if (frequencyDivision == NumericConstants.TWO) {
			commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
			commonHeader = Constant.S + period + " " + year;
		} else if (frequencyDivision == NumericConstants.TWELVE) {
			String monthName = getMonthForInt(period - 1);
			commonColumn = monthName.toLowerCase() + year;
			commonHeader = monthName + " " + year;
		}
		common.add(commonColumn);
		common.add(commonHeader);
		return common;
	}

	public String getCCPWhereConditionQueryMandated(String relationShipLevelDefination, String projectionDetails,
			String CCP) {
		String ccpWhereCond = Constant.AND_SMALL_SPACE + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP
				+ ".RELATIONSHIP_LEVEL_SID and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
		return ccpWhereCond;
	}

	public String getUserSessionQueryConditionMandated(String userId, String sessionId, String table) {
		String user = Constant.AND_SMALL_SPACE + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table
				+ ".SESSION_ID=" + sessionId + " ";
		return user;
	}

	public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotMandated(List<Object[]> list,
			ProjectionSelectionDTO projSelDTO) {
		int frequencyDivision = projSelDTO.getFrequencyDivision();
		List<SalesProjectionResultsDTO> projDTOList = new ArrayList<>();
		List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
		String oldcommonCol = "nothing";
		SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
		boolean first = true;
		for (Object[] row : list) {

			String column;
			int year = Integer.valueOf(String.valueOf(row[0]));
			int period = Integer.valueOf(String.valueOf(row[1]));
			List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
			String pcommonColumn = common.get(0);
			String commonHeader = common.get(1);
			String commonColumn;

			if (periodList.contains(pcommonColumn)) {
				if (!oldcommonCol.equals(pcommonColumn) && !first) {
					periodList.remove(oldcommonCol);
					oldcommonCol = pcommonColumn;
					projDTO = new SalesProjectionResultsDTO();
					projDTOList.add(projDTO);
				}
				projDTO.setParent(0);
				projDTO.setProjectionTotal(0);
				if (first) {
					oldcommonCol = pcommonColumn;
					projDTOList.add(projDTO);
				}
				first = false;
				projDTO.setLevelValue(commonHeader);
				projDTO.setRelationshipLevelName(commonHeader);
				String value;
				commonColumn = "gts";

				String flag = StringUtils.EMPTY + row[NumericConstants.SIX];
				if (Constant.ACT.equals(flag)) {
					column = commonColumn + ACTUALS.getConstant();
					if (projSelDTO.hasColumn(column)) {
						value = Constant.NULL;
						value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
						projDTO.addStringProperties(column, value);
					}
				} else {

					column = commonColumn + PROJECTIONS.getConstant();
					if (projSelDTO.hasColumn(column)) {
						value = Constant.NULL;
						value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
						projDTO.addStringProperties(column, value);
					}
				}
				commonColumn = "csw";
				if (Constant.ACT.equals(flag)) {
					column = commonColumn + ACTUALS.getConstant();
					if (projSelDTO.hasColumn(column)) {
						value = StringUtils.EMPTY + row[NumericConstants.TWO];
						value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
						projDTO.addStringProperties(column, value);
					}
				} else {
					column = commonColumn + PROJECTIONS.getConstant();
					if (projSelDTO.hasColumn(column)) {
						value = StringUtils.EMPTY + row[NumericConstants.THREE];
						value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
						projDTO.addStringProperties(column, value);
					}
				}
				commonColumn = "uv";
				if (Constant.ACT.equals(flag)) {
					column = commonColumn + ACTUALS.getConstant();
					if (projSelDTO.hasColumn(column)) {
						value = StringUtils.EMPTY + row[NumericConstants.FOUR];
						value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
						projDTO.addStringProperties(column, value);
					}
				} else {
					column = commonColumn + PROJECTIONS.getConstant();
					if (projSelDTO.hasColumn(column)) {
						value = StringUtils.EMPTY + row[NumericConstants.FIVE];
						value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
						projDTO.addStringProperties(column, value);
					}
				}

			}

		}
		periodList.remove(oldcommonCol);
		for (String ob : periodList) {
			projDTO = new SalesProjectionResultsDTO();
			projDTO.setParent(0);
			projDTO.setProjectionTotal(0);
			projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
			projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
			projDTOList.add(projDTO);
		}
		return projDTOList;
	}

	public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSalesChannel(List<Object> list,
			ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
		List<SalesProjectionResultsDTO> projDtoList = new ArrayList<>();
		List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
		columnList.remove(Constant.LEVEL_VALUE_SMALL);
		SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
		SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
		projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
		projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
		projSalesDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());
		projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
		projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
		projUnitDTO.setLevelValue(UNIT_VOL.getConstant());
		projSalesDTO.setParent(0);
		projUnitDTO.setParent(0);
		int frequencyDivision;
		if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.FOUR;
		} else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWELVE;
		} else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
			frequencyDivision = NumericConstants.TWO;
		} else {
			frequencyDivision = 1;
		}
		String salesOrUnits = projSelDTO.getSalesOrUnit();
		if (list != null && !list.isEmpty()) {
			for (Object list1 : list) {
				final Object[] obj = (Object[]) list1;
				int year = Integer.valueOf(String.valueOf(obj[0]));
				int period = Integer.valueOf(String.valueOf(obj[1]));
				List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
				String commonColumn = common.get(0);
				int col = NumericConstants.TWO;
				if (Constant.SALES.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					String actual = StringUtils.EMPTY + obj[col];
					if (!excelFlag || (excelFlag && actual.contains(Constant.NULL))) {
						actual = getFormattedValue(CUR_ZERO, actual);
					}
					projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
					String projection = StringUtils.EMPTY + obj[col + 1];
					if (!excelFlag || (excelFlag && projection.contains(Constant.NULL))) {
						projection = getFormattedValue(CUR_ZERO, projection);
					}
					projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
					columnList.remove(commonColumn + Constant.ACTUALS);
					columnList.remove(commonColumn + Constant.PROJECTIONS);
				}
				if (Constant.UNITS.equalsIgnoreCase(salesOrUnits)
						|| Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
					String actual = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
					if (!excelFlag || (excelFlag && actual.contains(Constant.NULL))) {
						actual = getFormattedValue(UNITVOLUME, actual);
					}
					projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
					String projection = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
					if (!excelFlag || (excelFlag && projection.contains(Constant.NULL))) {
						projection = getFormattedValue(UNITVOLUME, projection);
					}
					projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
					columnList.remove(commonColumn + Constant.ACTUALS);
					columnList.remove(commonColumn + Constant.PROJECTIONS);
				}
			}

		}
		for (String columns : columnList) {
			projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
			projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
		}
		projDtoList.add(projSalesDTO);
		projDtoList.add(projUnitDTO);
		return projDtoList;
	}

}