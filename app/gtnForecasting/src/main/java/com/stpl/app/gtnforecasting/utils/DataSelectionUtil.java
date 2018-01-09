/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.ForecastDTO;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.form.DataSelectionIndex;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.model.ForecastConfig;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_CONTRACT;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_CUSTOMER;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_NDC;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_SEARCH;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TreeTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionUtil {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionUtil.class);

	public static Map<String, String> userMap = new HashMap<>();
	public static Map<String, String> userIdMap = new HashMap<>();
	public static Map<String, String> discountMap = new HashMap<>();

	public static Date calculateHistory(final String frequency, final int histValue) {

		Calendar cal = Calendar.getInstance();
		Calendar cal1;
		Date date = null;
		String obtainedDate = StringUtils.EMPTY;
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
		try {
			if (Constant.MONTH1.equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -histValue);
			} else if ("Quarter".equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -(histValue * NumericConstants.THREE)); // Quarter
																				// *
																				// 3
			} else if (Constant.SEMI_ANNUAL_SMALL.equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -(histValue / NumericConstants.TWO)); // Semi
																				// annual
																				// /
																				// 2
			} else if ("Annual".equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -histValue);
			}
			obtainedDate = getDate(cal);
			date = format.parse(obtainedDate);
			cal1 = Calendar.getInstance();
			cal1.setTime(date);

		} catch (ParseException ex) {
			LoggerFactory.getLogger(DataSelectionIndex.class.getName()).error( StringUtils.EMPTY, ex);
		}
		return date;
	}

	public static Date calculateProjection(final String frequency, final int histValue) {
		Calendar cal = Calendar.getInstance();
		Calendar cal1;
		Date date = null;
		String obtainedDate = StringUtils.EMPTY;
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
		try {
			if (Constant.MONTH1.equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, histValue);
			} else if ("Quarter".equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, histValue * NumericConstants.THREE); // Quarter
																				// *
																				// 3
			} else if (Constant.SEMI_ANNUAL_SMALL.equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, histValue / NumericConstants.TWO); // Semi
																			// annual
																			// /
																			// 2
			} else if ("Annual".equalsIgnoreCase(frequency)) {
				cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, histValue);
			}
			obtainedDate = getDate(cal);
			date = format.parse(obtainedDate);
			cal1 = Calendar.getInstance();
			cal1.setTime(date);

		} catch (ParseException ex) {
			LoggerFactory.getLogger(DataSelectionIndex.class.getName()).error( StringUtils.EMPTY, ex);
		}
		return date;
	}

	public static String getDate(Calendar cal) {
		return (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
	}

	public static String getCompanySidForGroup(List<Leveldto> innerCustLevels, List<String> companies) {

		StringBuilder companiesList = new StringBuilder(StringUtils.EMPTY);
		if (companies != null && !companies.isEmpty()) {
			companies = new ArrayList<>(new LinkedHashSet<>(companies));
			for (int loop = 0, limit = companies.size(); loop < limit; loop++) {
				companiesList.append("'");
				companiesList.append(companies.get(loop));
				companiesList.append("'");
				if (loop != (limit - 1)) {
					companiesList.append(", ");
				}
			}
		}
		String indicatorTable = Constant.TABLE1;
		String indicatorColumn = Constant.COLUMN1;
		StringBuilder query = new StringBuilder(" ");
		query.append(" SELECT distinct cm.");
		query.append(UiUtils.generateHqlField(Constant.COMPANY_MASTER_SID1, indicatorColumn));
		query.append(Constant.FROM_SPACE);
		query.append(UiUtils.generateHqlField(Constant.COMPANY_MASTER, indicatorTable));
		query.append(" cm ");
		query.append(Constant.WHERE_SPACE);
		Map<String, Boolean> fieldDuplicationCheck = new HashMap<>();
		Leveldto ddo;
		boolean orFlag = false;
		for (int loop = 0, limit = innerCustLevels.size(); loop < limit; loop++) {
			ddo = innerCustLevels.get(loop);
			if ((ddo.getTableName().equalsIgnoreCase(Constant.COMPANY_MASTER)) && (fieldDuplicationCheck
					.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) == null
					|| !fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)))) {
				if (orFlag) {
					query.append(" or ");
				}
				query.append(" cm.");
				orFlag = true;
				fieldDuplicationCheck.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn), true);
				query.append(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
				query.append(" in (");

				query.append(companiesList.toString());

				query.append(")");
			}

		}

		return query.toString();

	}

	public static String getItemSidForGroup(List<Leveldto> innerProdLevels, List<String> items) {

		StringBuilder itemList = new StringBuilder(StringUtils.EMPTY);
		if (items != null && !items.isEmpty()) {
			items = new ArrayList<>(new LinkedHashSet<>(items));
			for (int loop = 0, limit = items.size(); loop < limit; loop++) {
				itemList.append("'");
				itemList.append(items.get(loop));
				itemList.append("'");
				if (loop != (limit - 1)) {
					itemList.append(", ");
				}
			}
		}
		String indicatorTable = Constant.TABLE1;
		String indicatorColumn = Constant.COLUMN1;
		StringBuilder query = new StringBuilder(" ");
		query.append(" SELECT distinct im.");
		query.append(UiUtils.generateHqlField(Constant.ITEM_MASTER_SID1, indicatorColumn));
		query.append(Constant.FROM_SPACE);
		query.append(UiUtils.generateHqlField(Constant.ITEM_MASTER, indicatorTable));
		query.append(" im ");

		query.append(Constant.WHERE_SPACE);

		Map<String, Boolean> fieldDuplicationCheck = new HashMap<>();

		Leveldto ddo;
		boolean orFlag = false;
		for (int loop = 0, limit = innerProdLevels.size(); loop < limit; loop++) {
			ddo = innerProdLevels.get(loop);

			if ((ddo.getTableName().equalsIgnoreCase(Constant.ITEM_MASTER)) && (fieldDuplicationCheck
					.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) == null
					|| !fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)))
					&& (!orFlag)) {
				query.append(" or ");
				query.append(" im.");
				orFlag = true;
				fieldDuplicationCheck.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn), true);
				query.append(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
				query.append("  in (");

				query.append(itemList.toString());

				query.append(")");
			}

		}

		return query.toString();
	}

	public static void removeItemsRecursively(final Object item, final TreeTable selectedTable,
			final ExtTreeContainer<Leveldto> selectedContainer) {
		if (selectedTable.hasChildren(item)) {
			Collection<?> children = selectedTable.getChildren(item);
			if (children != null && children.size() > 0) {
				BeanItemContainer<Leveldto> tempBean = new BeanItemContainer<>(Leveldto.class);
				LinkedList<Object> children2 = new LinkedList<>();
				for (Iterator<?> i = children.iterator(); i.hasNext();) {
					children2.add((Object) i.next());
				}
				for (Iterator<Object> i = children2.iterator(); i.hasNext();) {
					Object child = i.next();
					Leveldto beanItem = getBeanFromId(child);
					tempBean.addBean(beanItem);
					removeItemsRecursively(child, selectedTable, selectedContainer);

					selectedTable.removeItem(child);
					selectedContainer.removeItemRecursively(child);
				}
			}
		}
	}

	/**
	 * Gets the bean from id.
	 *
	 * @param obj
	 *            the id
	 * @return the bean from id
	 */
	public static Leveldto getBeanFromId(Object obj) {

		BeanItem<?> targetItem = null;
		if (obj instanceof BeanItem<?>) {
			targetItem = (BeanItem<?>) obj;
		} else if (obj instanceof Leveldto) {
			targetItem = new BeanItem<>((Leveldto) obj);
		}

		return (Leveldto) targetItem.getBean();
	}

	public static String getCcpWithCC(List<Leveldto> ccList, final String tableIndicator) {

		String indicatorTable = Constant.TABLE1;
		String indicatorColumn = Constant.COLUMN1;

		int i, j, k;
		i = 0;
		j = 0;
		k = 0;
		StringBuilder query = new StringBuilder(StringUtils.EMPTY);
		if ("item".equalsIgnoreCase(tableIndicator)) {
			query.append(" SELECT  distinct ccpd.");
			query.append(UiUtils.generateHqlField(Constant.ITEM_MASTER_SID1, indicatorColumn));

		} else if ("company".equalsIgnoreCase(tableIndicator)) {
			query.append(" SELECT distinct  ccpd.");
			query.append(UiUtils.generateHqlField(Constant.COMPANY_MASTER_SID1, indicatorColumn));

		} else if (Constant.CONTRACT.equalsIgnoreCase(tableIndicator)) {
			query.append(" SELECT distinct  ccpd.");
			query.append(UiUtils.generateHqlField(Constant.CONTRACT_MASTER_SID1, indicatorColumn));

		} else if ("ccp".equalsIgnoreCase(tableIndicator)) {
			query.append(" SELECT distinct ccpd.");
			query.append(UiUtils.generateHqlField("CCP_DETAILS_SID", indicatorColumn));
		}
		query.append(Constant.FROM_SPACE);
		query.append(UiUtils.generateHqlField("CCP_DETAILS", indicatorTable));
		query.append(" ccpd ");
		Leveldto ddo;
		for (int loop = 0, limit = ccList.size(); loop < limit; loop++) {
			ddo = ccList.get(loop);

			if (ddo.getTableName().equalsIgnoreCase(Constant.CONTRACT_MASTER)) {
				if (i == 0) {
					query.append(" ,");
					query.append(UiUtils.generateHqlField(Constant.CONTRACT_MASTER, indicatorTable));
					query.append(" conm ");
					i = 1;
				}

			} else if ((ddo.getTableName().equalsIgnoreCase(Constant.COMPANY_MASTER)) && (j == 0)) {
				query.append(", ");
				query.append(UiUtils.generateHqlField(Constant.COMPANY_MASTER, indicatorTable));
				query.append(" cm ");
				j = 1;

			}

		}
		if (i == 1 || j == 1 || k == 1) {
			query.append(Constant.WHERE_SPACE);

			if (i == 1) {
				query.append(Constant.CCPD_DOT);
				query.append(UiUtils.generateHqlField(Constant.CONTRACT_MASTER_SID1, indicatorColumn));
				query.append("=conm.");
				query.append(UiUtils.generateHqlField(Constant.CONTRACT_MASTER_SID1, indicatorColumn));
				query.append(" ");
			}
			if (j == 1) {
				if (i == 1) {
					query.append("  AND   ");
				}
				query.append(Constant.CCPD_DOT);
				query.append(UiUtils.generateHqlField(Constant.COMPANY_MASTER_SID1, indicatorColumn));
				query.append("=cm.");
				query.append(UiUtils.generateHqlField(Constant.COMPANY_MASTER_SID1, indicatorColumn));
				query.append(" ");
			}

			if (k == 1) {
				if (i == 1 || j == 1) {
					query.append(" AND ");
				}
				query.append(Constant.CCPD_DOT);
				query.append(UiUtils.generateHqlField(Constant.ITEM_MASTER_SID1, indicatorColumn));
				query.append("=im.");
				query.append(UiUtils.generateHqlField(Constant.ITEM_MASTER_SID1, indicatorColumn));
				query.append(" ");
			}

		}

		List<String> companyMasterValues;
		Map<String, List<String>> companyMasterMap = new HashMap<>();
		List<String> contractMasterValues;
		Map<String, List<String>> contractMasterMap = new HashMap<>();
		for (int loop = 0, limit = ccList.size(); loop < limit; loop++) {
			ddo = ccList.get(loop);

			if (ddo.getTableName().equalsIgnoreCase(Constant.CONTRACT_MASTER)) {

				if (contractMasterMap.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) != null) {
					contractMasterValues = contractMasterMap
							.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
				} else {
					contractMasterValues = new ArrayList<>();
				}
				if (!contractMasterValues.contains("'" + ddo.getRelationshipLevelValue() + "'")) {
					contractMasterValues.add("'" + ddo.getRelationshipLevelValue() + "'");
				}
				contractMasterMap.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn),
						contractMasterValues);
			} else if (ddo.getTableName().equalsIgnoreCase(Constant.COMPANY_MASTER)) {

				if (companyMasterMap.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) != null) {
					companyMasterValues = companyMasterMap
							.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
				} else {
					companyMasterValues = new ArrayList<>();
				}
				if (!companyMasterValues.contains("'" + ddo.getRelationshipLevelValue() + "'")) {
					companyMasterValues.add("'" + ddo.getRelationshipLevelValue() + "'");
				}
				companyMasterMap.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn),
						companyMasterValues);
			}
		}

		Map<String, Boolean> fieldDuplicationCheck = new HashMap<>();
		for (int loop1 = 0, limit1 = ccList.size(); loop1 < limit1; loop1++) {
			ddo = ccList.get(loop1);

			if (ddo.getTableName().equalsIgnoreCase(Constant.CONTRACT_MASTER)) {

				if (fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) == null
						|| !fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn))) {
					query.append(" AND conm.");
					fieldDuplicationCheck.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn), true);
					query.append(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
					query.append(" in  (");
					query.append(" select distinct ht.helperTableSid from HelperTable ht where ht.listName = '");
					query.append(ddo.getFieldName());
					query.append("' and ht.description in (");
					for (int loop = 0, limit = contractMasterMap
							.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn))
							.size(); loop < limit; loop++) {
						query.append(contractMasterMap
								.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)).get(loop));
						if (loop != limit - 1) {
							query.append(", ");
						}
					}
					query.append(")");
				} else if (helperTableConverter(ddo.getFieldName()) != null
						&& !"null".equals(helperTableConverter(ddo.getFieldName()))) {
					query.append(" select distinct ht.helperTableSid from HelperTable ht where ht.listName = '");
					query.append(helperTableConverter(ddo.getFieldName()));
					query.append("' and ht.description in (");
					for (int loop = 0, limit = contractMasterMap
							.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn))
							.size(); loop < limit; loop++) {
						query.append(contractMasterMap
								.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)).get(loop));
						if (loop != limit - 1) {
							query.append(", ");
						}
					}
					query.append(")");
				} else {
					for (int loop = 0, limit = contractMasterMap
							.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn))
							.size(); loop < limit; loop++) {
						query.append(contractMasterMap
								.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)).get(loop));
						if (loop != limit - 1) {
							query.append(", ");
						}
					}

					query.append(")");
				}

			} else if ((ddo.getTableName().equalsIgnoreCase(Constant.COMPANY_MASTER)) && (fieldDuplicationCheck
					.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) == null
					|| !fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)))) {
				query.append(" AND cm.");
				fieldDuplicationCheck.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn), true);
				query.append(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
				query.append(" in (");

				for (int loop = 0, limit = companyMasterMap
						.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn))
						.size(); loop < limit; loop++) {
					query.append(companyMasterMap.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn))
							.get(loop));
					if (loop != limit - 1) {
						query.append(", ");
					}
				}

				query.append(")");
			}

		}

		return query.toString();
	}


	public static List<Integer> getSelectedRelationshipLevelSids(List<Leveldto> itemIds) {
		List<Integer> selectedRelationshipLevelSids = null;
		if (itemIds != null && !itemIds.isEmpty()) {
			Leveldto dto;
			selectedRelationshipLevelSids = new ArrayList<>();
			for (Object item : itemIds) {
				dto = DataSelectionUtil.getBeanFromId(item);
				selectedRelationshipLevelSids.add(dto.getRelationshipLevelSid());
			}
		}
		return selectedRelationshipLevelSids;
	}

	private static String helperTableConverter(String value) {
		Map<String, String> helperTableMap = new HashMap<>();
		helperTableMap.put("THERAPEUTIC_CLASS", "ITEM_THERP_CLASS");

		return helperTableMap.get(value);
	}

	public static List<String> loadHelperTable() throws SystemException {
		NonMandatedLogic logic = new NonMandatedLogic();
		return logic.getHelperTableListNames();
	}

	public static List<String> storeUncommonValues(List<String> list1, List<String> list2) {
		// Prepare a union
		List<String> union = new ArrayList<>(list1);
		union.addAll(list2);
		// Prepare an intersection
		List<String> intersection = new ArrayList<>(list1);
		intersection.retainAll(list2);
		// Subtract the intersection from the union
		union.removeAll(intersection);
		return union;
	}

	public static void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Date from, Date to, final String mode,
			String screenName) {
		try {
			Date fromDate = null;
			Date toDate = null;
			DataSelectionLogic logic = new DataSelectionLogic();
			ForecastConfig forecastConfig = logic.getTimePeriod(screenName);
			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION)) {
				Date currentDate = new Date();
				currentDate.setMonth(currentDate.getMonth() - NumericConstants.SIX);
				fromDate = currentDate;
				currentDate = new Date();
				currentDate.setMonth(currentDate.getMonth() + NumericConstants.THIRTY_SIX);
				toDate = currentDate;
			} else {
				if (forecastConfig != null) {
					fromDate = forecastConfig.getFromDate();
					toDate = forecastConfig.getToDate();
				}
			}

			List<String> timePeriodList = new ArrayList<>();
			if (MODE_SEARCH.getConstant().equalsIgnoreCase(mode)) {
				timePeriodList.add(SELECT_ONE);
			}
			timePeriodList.addAll(getTimePeriodList(fromDate, toDate));
			fromPeriod.setContainerDataSource(new IndexedContainer(timePeriodList));
			toPeriod.setContainerDataSource(new IndexedContainer(timePeriodList));
			if (forecastConfig != null) {
				if (Constant.MONTH1
						.equals(HelperListUtil.getInstance().getDescriptionByID(forecastConfig.getHistFreq()))
						|| !forecastConfig.getProcessMode()) {
					fromPeriod.setData(fromDate);
				} else {
					fromPeriod.setData(null);
				}
				if (Constant.MONTH1
						.equals(HelperListUtil.getInstance().getDescriptionByID(forecastConfig.getProjFreq()))
						|| !forecastConfig.getProcessMode()) {
					toPeriod.setData(toDate);
				} else {
					toPeriod.setData(null);
				}
			}
			if (MODE_SEARCH.getConstant().equalsIgnoreCase(mode)) {
				fromPeriod.select(timePeriodList.get(0));
				toPeriod.select(timePeriodList.get(0));
			} else {
				if (from == null && to == null) {
					fromPeriod.select(timePeriodList.get(0));
					toPeriod.select(timePeriodList.get(timePeriodList.size() - 1));
				} else {
					String fromValue = DataSelectionUtil.getTimePeriod(from);
					String toValue = DataSelectionUtil.getTimePeriod(to);
					fromPeriod.select(fromValue);
					toPeriod.select(toValue);
				}
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public static List<String> getTimePeriodList(Date start, Date end) {
		SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
		List<String> quartersList = new ArrayList<>();
		int startQuarter = getQuarter(start);
		int endQuarter = getQuarter(end);
		int startYear = Integer.parseInt(getYear.format(start));
		int limit = NumericConstants.FOUR;
		int endYear = Integer.parseInt(getYear.format(end));
		while (startYear <= endYear) {
			if (startYear == endYear) {
				limit = endQuarter;
			}

			while (startQuarter <= limit) {
				quartersList.add(Constant.Q + startQuarter + " - " + startYear);
				startQuarter++;
			}
			startYear++;
			startQuarter = 1;
		}
		return quartersList;
	}

	public static int getQuarter(Date date) {
		SimpleDateFormat getMonth = new SimpleDateFormat(Constant.MM);
		return Integer.parseInt(getMonth.format(date)) % NumericConstants.THREE == 0
				? (Integer.parseInt(getMonth.format(date)) / NumericConstants.THREE)
				: (Integer.parseInt(getMonth.format(date)) / NumericConstants.THREE) + 1;
	}

	public static List<String> getEndLevelHierNo(List<Leveldto> levels) {
		List<String> hierNos = new ArrayList<>();
		for (Leveldto level : levels) {
			hierNos.add(String.valueOf(level.getHierarchyNo()));
		}
		return hierNos;
	}

	public static String getDateFromQuarter(String quarter) {
		String slash = "/";
		String dd = "01";
		String MM = "01";
		String date;
		String split[] = quarter.split(" - ");
		String splitQuarter = split[0];
		int quarterValue = UiUtils.parseStringToInteger(splitQuarter);
		String yyyy = split[1];
		if (quarterValue == 1) {
			MM = "01";
		} else if (quarterValue == NumericConstants.TWO) {
			MM = "04";
		} else if (quarterValue == NumericConstants.THREE) {
			MM = "07";
		} else if (quarterValue == NumericConstants.FOUR) {
			MM = "10";
		}

		date = MM + slash + dd + slash + yyyy;
		return date;
	}

	public static String getTimePeriod(Date date) {
		String timePeriod = StringUtils.EMPTY;
		SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
		int quarter = getQuarter(date);
		int year = Integer.parseInt(getYear.format(date));
		if (date != null) {
			timePeriod = Constant.Q + quarter + " - " + year;
		}
		return timePeriod;
	}

	public static void setHistoryLimit(ForecastDTO forecastDTO) {
		Date tempDate = new Date();
		tempDate.setMonth(tempDate.getMonth() - 1);
		forecastDTO.setHistoryEndYear(tempDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
		forecastDTO.setHistoryEndMonth(tempDate.getMonth() + 1);
		forecastDTO.setHistoryEndDate(tempDate);
		Date tempStartDate = new Date();
		tempStartDate.setYear(tempStartDate.getYear() - NumericConstants.THREE);
		tempStartDate.setMonth(0);
		forecastDTO.setHistoryStartYear(tempStartDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
		forecastDTO.setHistoryStartMonth(tempStartDate.getMonth());
		forecastDTO.setHistoryStartDate(tempStartDate);

	}

	public static void getForecastDTO(DataSelectionDTO dataSelectionDTO, SessionDTO session) {
		ForecastDTO forecastDTO;
		if (session.getForecastDTO() == null) {
			forecastDTO = new ForecastDTO();
		} else {
			forecastDTO = session.getForecastDTO();
		}
		DataSelectionLogic logic = new DataSelectionLogic();
		logic.setForcastFileDate(dataSelectionDTO);
		if (dataSelectionDTO.getFileEndMonth() != null && dataSelectionDTO.getFileEndYear() != null) {
			Date tempDate = new Date();
			tempDate.setMonth(dataSelectionDTO.getFileEndMonth() - 1);
			tempDate.setYear(dataSelectionDTO.getFileEndYear() - NumericConstants.ONE_NINE_ZERO_ZERO);
			if (tempDate.before(dataSelectionDTO.getToDate())) {
				forecastDTO.setProjectionEndYear(tempDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
				forecastDTO.setProjectionEndMonth(tempDate.getMonth() + 1);
				forecastDTO.setProjectionEndDate(tempDate);
				forecastDTO.setForecastEndYear(
						dataSelectionDTO.getToDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
				forecastDTO.setForecastEndMonth(dataSelectionDTO.getToDate().getMonth() + 1);
				forecastDTO.setForecastEndDate(dataSelectionDTO.getToDate());
			} else {
				forecastDTO.setProjectionEndYear(
						dataSelectionDTO.getToDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
				forecastDTO.setProjectionEndMonth(dataSelectionDTO.getToDate().getMonth() + 1);
				forecastDTO.setProjectionEndDate(dataSelectionDTO.getToDate());
				forecastDTO.setForecastEndYear(
						dataSelectionDTO.getToDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
				forecastDTO.setForecastEndMonth(dataSelectionDTO.getToDate().getMonth() + 1);
				forecastDTO.setForecastEndDate(dataSelectionDTO.getToDate());
			}

			DataSelectionUtil.setHistoryLimit(forecastDTO);
			Date tempForecastDate = new Date();
			forecastDTO.setForecastStartYear(tempForecastDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
			forecastDTO.setForecastStartMonth(tempForecastDate.getMonth() + 1);
			forecastDTO.setForecastStartDate(tempForecastDate);
			if (dataSelectionDTO.getFromDate().before(forecastDTO.getHistoryStartDate())) {
				forecastDTO.setProjectionStartYear(
						forecastDTO.getHistoryStartDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
				forecastDTO.setProjectionStartMonth(forecastDTO.getHistoryStartDate().getMonth() + 1);
				forecastDTO.setProjectionStartDate(forecastDTO.getHistoryStartDate());
			} else {
				forecastDTO.setProjectionStartYear(
						dataSelectionDTO.getFromDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
				forecastDTO.setProjectionStartMonth(dataSelectionDTO.getFromDate().getMonth() + 1);
				forecastDTO.setProjectionStartDate(dataSelectionDTO.getFromDate());
			}

			// Added to handle the case of file date is greater than the
			// forecast date
			if (forecastDTO.getProjectionEndDate().after(forecastDTO.getForecastEndDate())) {
				forecastDTO.setProjectionEndYear(forecastDTO.getForecastEndYear());
				forecastDTO.setProjectionEndMonth(forecastDTO.getForecastEndMonth());
				forecastDTO.setProjectionEndDate(forecastDTO.getForecastEndDate());
			}
		}
		session.setForecastDTO(forecastDTO);
	}

	public static List<String> getRemovedLevelsRsid(List<Leveldto> originalList, List<Leveldto> newList) {
		List<String> endLevel = null;
		List<String> oldRsids = new ArrayList<>();
		List<String> newRsds = new ArrayList<>();
		for (Leveldto level : originalList) {
			oldRsids.add(String.valueOf(level.getRelationshipLevelSid()));
		}
		for (Leveldto level : newList) {
			newRsds.add(String.valueOf(level.getRelationshipLevelSid()));
		}
		endLevel = new ArrayList<>(oldRsids);
		endLevel.removeAll(newRsds);
		return endLevel;
	}

	public static List<String> getRemovedLevelsHno(List<Leveldto> originalList, List<Leveldto> newList) {
		List<String> endLevel = null;
		List<String> oldRsids = new ArrayList<>();
		List<String> newRsds = new ArrayList<>();
		for (Leveldto level : originalList) {
			oldRsids.add(String.valueOf(level.getHierarchyNo()));
		}
		for (Leveldto level : newList) {
			newRsds.add(String.valueOf(level.getHierarchyNo()));
		}
		endLevel = new ArrayList<>(oldRsids);
		endLevel.removeAll(newRsds);
		return endLevel;
	}

	public static List<String> getNewLevelRsid(List<Leveldto> originalList, List<Leveldto> newList) {
		List<String> addLevel = null;
		List<String> oldHno = new ArrayList<>();
		List<String> newHno = new ArrayList<>();
		for (Leveldto level : originalList) {
			oldHno.add(String.valueOf(level.getRelationshipLevelSid()));
		}
		for (Leveldto level : newList) {
			newHno.add(String.valueOf(level.getRelationshipLevelSid()));
		}
		addLevel = new ArrayList<>(newHno);
		addLevel.removeAll(oldHno);
		return addLevel;
	}

	public static List<String> getNewLevelHnos(List<Leveldto> originalList, List<Leveldto> newList) {
		List<String> addLevel = null;
		List<String> oldHno = new ArrayList<>();
		List<String> newHno = new ArrayList<>();
		for (Leveldto level : originalList) {
			oldHno.add(level.getHierarchyNo());
		}
		for (Leveldto level : newList) {
			newHno.add(level.getHierarchyNo());
		}
		addLevel = new ArrayList<>(newHno);
		addLevel.removeAll(oldHno);
		return addLevel;
	}

	public static List<String> getTempTableList() {
		List<String> tempTables = new ArrayList<>();
		tempTables.add("ST_NM_SALES_PROJECTION");
		tempTables.add("ST_NM_ACTUAL_SALES");
		tempTables.add("ST_NM_SALES_PROJECTION_MASTER");
		tempTables.add("ST_NM_DISCOUNT_PROJECTION");
		tempTables.add("ST_NM_ACTUAL_DISCOUNT");
		tempTables.add("ST_NM_DISCOUNT_PROJ_MASTER");
		tempTables.add("ST_NM_PPA_PROJECTION");
		tempTables.add("ST_NM_ACTUAL_PPA");
		tempTables.add("ST_NM_PPA_PROJECTION_MASTER");
		tempTables.add("ST_NM_ASSUMPTIONS");
		tempTables.add("NM_SALES_PROJECTION");
		tempTables.add("NM_ACTUAL_SALES");
		tempTables.add("NM_SALES_PROJECTION_MASTER");
		tempTables.add("NM_DISCOUNT_PROJECTION");
		tempTables.add("NM_ACTUAL_DISCOUNT");
		tempTables.add("NM_DISCOUNT_PROJ_MASTER");
		tempTables.add("NM_PPA_PROJECTION");
		tempTables.add("NM_ACTUAL_PPA");
		tempTables.add("NM_PPA_PROJECTION_MASTER");
		tempTables.add("NM_ASSUMPTIONS");
		tempTables.add("PROJECTION_DETAILS");
		return tempTables;
	}

	public static String getLastDateFromQuarter(String quarter) {
		String slash = "/";
		String dd = "30";
		String MM = "01";
		String date;
		String split[] = quarter.split(" - ");
		String splitQuarter = split[0];
		int quarterValue = UiUtils.parseStringToInteger(splitQuarter);
		String yyyy = split[1];
		if (quarterValue == 1) {
			MM = "03";
			dd = "31";
		} else if (quarterValue == NumericConstants.TWO) {
			MM = "06";
			dd = "30";
		} else if (quarterValue == NumericConstants.THREE) {
			MM = "09";
			dd = "30";
		} else if (quarterValue == NumericConstants.FOUR) {
			MM = "12";
			dd = "31";
		}

		date = MM + slash + dd + slash + yyyy;
		return date;
	}

	public static void mapUsers() {
		userMap.clear();
		userMap = CommonUtils.getAllUsers();
	}

	public static String filterUser(String filter) {
		List<String> keys = new ArrayList<>();
		String userIds;
		if (userMap != null) {
			for (Map.Entry<String, String> entry : userMap.entrySet()) {
				if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
					keys.add(String.valueOf(entry.getKey()));
				}
			}
		}
		if (!keys.isEmpty()) {
			userIds = UiUtils.stringListToString(keys);
		} else {
			userIds = "0";
		}
		return userIds;
	}

	public static String getUserName(String userId) {
		String userName = StringUtils.EMPTY;
		if (userMap != null) {
			userName = userMap.get(userId);
		}
		return userName;
	}

	public static List<Leveldto> getFSValue(final String relationshipLevelValue, final String fieldName) {
		List<Leveldto> list = new ArrayList<>();
		DataSelectionLogic logic = new DataSelectionLogic();
		List<Object> listValue = logic.getFSValue(relationshipLevelValue, fieldName);
		Leveldto dto = new Leveldto();
		for (int i = 0; i < listValue.size(); i++) {
			Object[] obj = (Object[]) listValue.get(i);
			dto.setForm(String.valueOf(obj[0]));
			dto.setStrength(String.valueOf(obj[1]));
			list.add(dto);
		}
		return list;
	}

	public static String getCcpWithCC(final List<Leveldto> ccList) {
		StringBuilder query = new StringBuilder(StringUtils.EMPTY);
		query.append("SELECT DISTINCT CCP.itemMasterSid FROM CcpDetails CCP ");
		List<String> companyMasterValues = new ArrayList<>();
		List<String> contractMasterValues = new ArrayList<>();
		Leveldto ddo;
		for (int loop = 0, limit = ccList.size(); loop < limit; loop++) {
			ddo = ccList.get(loop);
			if (ddo.getTableName().equalsIgnoreCase(Constant.CONTRACT_MASTER)) {
				contractMasterValues.add(ddo.getRelationshipLevelValue());
			} else if (ddo.getTableName().equalsIgnoreCase(Constant.COMPANY_MASTER)) {
				companyMasterValues.add(ddo.getRelationshipLevelValue());
			}
		}
		if (!companyMasterValues.isEmpty() || !contractMasterValues.isEmpty()) {
			String and = StringUtils.EMPTY;
			query.append(Constant.WHERE_SPACE);
			if (!companyMasterValues.isEmpty()) {
				query.append(" CCP.companyMasterSid IN (");
				query.append(UiUtils.stringListToString(companyMasterValues));
				query.append(")");
				and = " AND ";
			}
			if (!contractMasterValues.isEmpty()) {
				query.append(and);
				query.append(" CCP.contractMasterSid IN (");
				query.append(UiUtils.stringListToString(contractMasterValues));
				query.append(")");
			}
		}

		return query.toString();
	}

	public static List<String> getItemSidFromHierarchy(List<Leveldto> innerProdLevels) {
		List<String> items = new ArrayList<>();
		if (innerProdLevels != null) {
			for (Leveldto leveldto : innerProdLevels) {
				if (leveldto.isFromItem()) {
					items.add(leveldto.getRelationshipLevelValue());
				}
			}
		}
		return items;
	}

	public static List<String> getCustomerSidFromHierarchy(List<Leveldto> innerCustLevels) {
		List<String> customers = new ArrayList<>();
		if (innerCustLevels != null) {
			for (Leveldto leveldto : innerCustLevels) {
				if (leveldto.isFromCompany()) {
					customers.add(leveldto.getRelationshipLevelValue());
				}
			}
		}
		return customers;
	}

	public static List<String> getCompanySidFromHierarchy(List<Leveldto> innerProdLevels, String screenName) {
		List<String> companies = new ArrayList<>();
		if (innerProdLevels != null) {
			for (Leveldto leveldto : innerProdLevels) {
				if ((screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS))
						&& (leveldto.isFromCompany() || (Constant.COMPANY_SMALL.equalsIgnoreCase(leveldto.getLevel())
								|| "GL Comp".contains(leveldto.getLevel())
								|| "GL Company".contains(leveldto.getLevel())))) {
					companies.add(leveldto.getRelationshipLevelValue());
				} else if ((leveldto.isFromCompany())
						&& (StringUtils.isNotEmpty(leveldto.getRelationshipLevelValue()))) {
					companies.add(leveldto.getRelationshipLevelValue());
				}
			}
		}
		return companies;
	}

	public static void mapUserIds() {
		userIdMap.clear();
		userIdMap = CommonUtils.getAllUserIds();
	}

	public static String filterUserId(String filter) {
		List<String> keys = new ArrayList<>();
		String userIds;
		if (userIdMap != null) {
			for (Map.Entry<String, String> entry : userIdMap.entrySet()) {
				if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
					keys.add(String.valueOf(entry.getKey()));
				}
			}
		}
		if (!keys.isEmpty()) {
			userIds = UiUtils.stringListToString(keys);
		} else {
			userIds = "0";
		}
		return userIds;
	}

	public static String getUserId(String userId) {
		String userName = StringUtils.EMPTY;
		if (userIdMap != null) {
			userName = userIdMap.get(userId);
		}
		return userName;
	}

	public static String identifyLevel(Leveldto levelDto) {
		String level = StringUtils.EMPTY;
		if (!StringUtils.isBlank(levelDto.getLevel()) && !NULL.equalsIgnoreCase(levelDto.getLevel())) {
			if (Constant.COMPANY_MASTER.equalsIgnoreCase(levelDto.getTableName())
					&& (Constant.CUSTOMER_SMALL.equalsIgnoreCase(levelDto.getLevel())
							|| Constant.COMPANY_SMALL.equalsIgnoreCase(levelDto.getLevel())
							|| Constant.TRADING_PARTNER.equalsIgnoreCase(levelDto.getLevel()))) {
				level = INDICATOR_LEVEL_CUSTOMER.getConstant();
			} else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(levelDto.getTableName())
					&& levelDto.getLevel().contains(Constant.CONTRACT_SMALL)) {
				level = INDICATOR_LEVEL_CONTRACT.getConstant();
			} else if (Constant.ITEM_MASTER.equalsIgnoreCase(levelDto.getTableName())
					&& (levelDto.getLevel().contains(Constant.NDC) || "Item".equalsIgnoreCase(levelDto.getLevel()))) {
				level = INDICATOR_LEVEL_NDC.getConstant();
			}
		}
		return level;
	}

	public static void mapDiscounts() {
		discountMap.clear();
		discountMap = CommonUtils.getAllDiscounts();
	}

	public static String filterDiscount(String filter) {
		List<String> keys = new ArrayList<>();
		String discountIds;
		if (discountMap != null) {
			for (Map.Entry<String, String> entry : discountMap.entrySet()) {
				if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
					keys.add(String.valueOf(entry.getKey()));
				}
			}
		}
		if (!keys.isEmpty()) {
			discountIds = UiUtils.stringListToString(keys);
		} else {
			discountIds = DASH;
		}
		return discountIds;
	}

	public static String getDiscountName(String discountId) {
		String userName = StringUtils.EMPTY;
		if (discountMap != null) {
			userName = discountMap.get(discountId);
		}
		return userName;
	}

	public static String getQuarterFromDate(Date date) {
		SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
		int startQuarter = getQuarter(date);
		int startYear = Integer.parseInt(getYear.format(date));
		return Constant.Q + startQuarter + " - " + startYear;
	}

	public static int getYearFromDate(Date date) {
		SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
		int year = Integer.parseInt(getYear.format(date));
		return year;
	}

}
