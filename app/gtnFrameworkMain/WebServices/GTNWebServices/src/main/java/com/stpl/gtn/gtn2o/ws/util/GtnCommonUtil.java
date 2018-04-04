package com.stpl.gtn.gtn2o.ws.util;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.formatter.GtnWsFormatter;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;

public class GtnCommonUtil {
	private GtnCommonUtil() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCommonUtil.class);

	public static String getString(final Object str) {
		String stringOut = "";
		try {
			stringOut = StringUtils.trimToEmpty(String.valueOf(str));
			if (GtnWsConstants.NULL.equals(stringOut) || GtnWsConstants.SELECT_ONE.equals(stringOut)) {
				stringOut = StringUtils.EMPTY;
			}
		} catch (Exception e) {
			logger.error("Error in getString", e);
		}
		return stringOut;
	}

	public static Integer getInteger(final Object obj) {
		Integer integerOut = 0;
		try {
			integerOut = Integer.parseInt(String.valueOf(obj));

		} catch (Exception e) {
			logger.error("Error in getInteger", e);
		}
		return integerOut;
	}

	public static String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws ParseException {
		StringBuilder sql = new StringBuilder();
		switch (expersion) {
		case "BETWEEN":
			sql.append(field).append(" >= '").append(getDate(value1)).append("' AND ");
			sql.append(field).append(" <= '").append(getDate(value2)).append("' ");
			break;
		case "AND":
			sql.append(field).append(" < '").append(value1).append("' AND ");
			sql.append(field).append(" > '").append(value2).append("' ");
			break;
		case "GREATER_OR_EQUAL":
			sql.append(field).append(" >= '").append(getDate(value1)).append("' ");
			break;
		case "LESS_OR_EQUAL":
			sql.append(field).append(" <= '").append(getDate(value1)).append("' ");
			break;
		case "LIKE":
			sql.append(field).append(' ').append(expersion).append(" '").append(value1.replace('*', '%')).append("' ");
			break;
		case "EQUAL":
			sql.append(field).append(" = '").append(value1).append("' ");
			break;
		case "EQUALS":
			sql.append(field).append(" ='").append(value1).append("' ");
			break;
		case "GREATER":
			sql.append(field).append(" > '").append(value1).append("' ");
			break;
		case "LESS":
			sql.append(field).append(" < '").append(value1).append("' ");
			break;
		default:
			sql.append(field).append(' ').append(expersion).append(" '").append(value1).append("' ");
			break;
		}
		return sql.toString();
	}

	public static String getDate(String input) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = formatter.parse(input);
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		return commonDate.format(date);
	}

	public static String getDateFromQuarter(String quarter) {
		String slash = "/";
		String day = "01";
		String month = "01";
		String date = "";
		String[] split = quarter.split(" - ");
		String splitQuarter = split[0].replace("Q", "");
		int quarterValue = Integer.parseInt(splitQuarter);
		String yyyy = split[1];
		if (quarterValue == 1) {
			month = "01";
		} else if (quarterValue == 2) {
			month = "04";
		} else if (quarterValue == 3) {
			month = "07";
		} else if (quarterValue == 4) {
			month = "10";
		}

		date = yyyy + slash + month + slash + day;
		return date;
	}

	public static HelperTable getHelperTable(String description, String listName, Session session)
			throws GtnFrameworkGeneralException {
		HelperTable table = null;
		try {
			Criteria cr = session.createCriteria(HelperTable.class);
			cr.add(Restrictions.eq("description", description));
			cr.add(Restrictions.eq("listName", listName));
			@SuppressWarnings("unchecked")
			List<HelperTable> results = cr.list();
			if (results != null && !results.isEmpty()) {
				table = results.get(0);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getting HelperTable ", e);
		}
		return table;
	}

	public static String getGeneralOffsetQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		int startRecord = gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart();
		int offset = 1;
		if (gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset() != 0) {
			offset = gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset();
		}
		StringBuilder offsetQuery = new StringBuilder();
		offsetQuery.append(" OFFSET ");
		offsetQuery.append(startRecord);
		offsetQuery.append(" ROWS FETCH NEXT ");
		offsetQuery.append(offset);
		offsetQuery.append(" ROWS ONLY;");
		return offsetQuery.toString();

	}

	public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
		try {
			SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
			SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
			Date date = inputDateFormatter.parse(stringDate);
			return outputDateFormatter.format(date);
		} catch (ParseException ex) {
			return null;
		}
	}

	public static List<NotesTabBean> getNotesTabBean(final List<?> notesList,
			GtnWsAllListConfig gtnWebServiceAllListConfig) {
		List<NotesTabBean> notesTabRequestList = new ArrayList<>();
		if (notesList != null && !notesList.isEmpty()) {
			notesTabRequestList = new ArrayList<>();
			int notesListSize = notesList.size();
			NotesTabBean notesTabBean = null;
			for (int i = 0; i < notesListSize; i++) {
				notesTabBean = new NotesTabBean();
				final Object[] obj = (Object[]) notesList.get(i);
				notesTabBean.setMasterTableName(String.valueOf(obj[0]));
				notesTabBean.setFilePath(String.valueOf(obj[1]));
				notesTabBean.setCreatedDate(obj[2] == null ? null : (Date) obj[2]);
				notesTabBean.setCreatedBy(getInteger(obj[3]));
				if (gtnWebServiceAllListConfig != null) {
					notesTabBean.setCreatedByName(
							String.valueOf(gtnWebServiceAllListConfig.getUserIdNameMap().get(obj[3])));
				}

				notesTabBean.setMasterTableSystemId(getInteger(obj[4]));

				notesTabRequestList.add(notesTabBean);
			}
		}
		return notesTabRequestList;
	}

	public static String getFormatedDate(GtnWsCheckAllUpdateBean rsUpdateBean) throws GtnFrameworkGeneralException {
		String formattedDate = StringUtils.EMPTY;
		try {
			Date date = new Date(Long.valueOf(String.valueOf(rsUpdateBean.getValue())));
			SimpleDateFormat formatter = new SimpleDateFormat(GtnFrameworkWebserviceConstant.YYYY_M_MDD_H_HMMSS);
			formattedDate = formatter.format(date);
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getFormatedDate ", ex);

		}
		return formattedDate;

	}

	@SuppressWarnings("rawtypes")
	public static Object getAnyModelUsingCriteria(List<String> propertyIds, List<Object> values, Class modelClassType,
			Session session) throws GtnFrameworkGeneralException {
		Object entityTableObject = null;
		try {
			int size = propertyIds.size();
			Criteria cr = session.createCriteria(modelClassType);
			for (int i = 0; i < size; i++) {
				cr.add(Restrictions.eq(propertyIds.get(i).trim(), values.get(i)));
			}
			List results = cr.list();
			if (results != null && !results.isEmpty()) {
				entityTableObject = results.get(0);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getAnyModelUsingCriteria  ", e);
		}
		return entityTableObject;
	}

	public static void createHeaderRow(PrintWriter pw, List<String> headers) {
		int headerListSize = headers.size() - 1;
		for (int headerCount = 0; headerCount < headerListSize; headerCount++) {

			pw.print(headers.get(headerCount) + GtnFrameworkCommonStringConstants.STRING_COMMA);

		}
		pw.println(headers.get(headerListSize));
	}

	public static void createDataRows(PrintWriter printWriter, List<Object[]> resultList, int excludedColumnCount,
			List<String> columnFormatList) {

		for (Object[] record : resultList) {
			int lastItem = record.length - excludedColumnCount;
			for (int i = 0; i <= lastItem; i++) {

				Object value = emptyIfNull(record[i]);
				if (i == lastItem) {
					if (value instanceof Date) {
						printWriter.println(getFormattedDate(value).trim());
					} else {
						value = getFormattedColumn(columnFormatList, value, i);
						printWriter.println(replaceDoubleQuotes(value).trim());
					}
				} else if (value instanceof Date) {
					printWriter
							.print((getFormattedDate(value) + GtnFrameworkCommonStringConstants.STRING_COMMA).trim());
				} else {
					value = getFormattedColumn(columnFormatList, value, i);
					printWriter.print((GtnFrameworkCommonStringConstants.QUOTE + replaceDoubleQuotes(value)
							+ GtnFrameworkCommonStringConstants.QUOTE + GtnFrameworkCommonStringConstants.STRING_COMMA)
									.trim());

				}
			}
		}
	}

	public static Object emptyIfNull(Object value) {
		return value == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : value;
	}

	public static String replaceDoubleQuotes(Object rawObj) {
		String excelString = rawObj == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : String.valueOf(rawObj);
		if (GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(String.valueOf(excelString))) {
			excelString = excelString.replace(GtnFrameworkCommonStringConstants.QUOTE,
					GtnFrameworkCommonStringConstants.DOUBLE_QUOTE);
		}
		return excelString;

	}

	public static String getFormattedDate(Object ob) {
		String date;
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (ob == null) {
			date = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		} else {
			date = dateFormat.format((Date) ob);
		}
		return date;
	}

	private static Object getFormattedColumn(List<String> columnFormatList, Object value, int index) {
            
		if (columnFormatList != null && !columnFormatList.isEmpty()) {
                        String format = columnFormatList.get(index);
			if (format != null) {
				DecimalFormat columnFormat = GtnWsFormatter.DECIMAL_FORMATTER.getFormatter();
				columnFormat.applyPattern(format);
				return formatPercentValue(columnFormat, value);
			}
		}
		return value;
	}

	private static Object formatPercentValue(DecimalFormat columnFormat, Object value) {
		String formatPatter = columnFormat.toPattern();
		Object newValue = setDefaultValueForColumn(value);
		if (formatPatter.contains(GtnWsTransactionConstants.PERCENTAGE)) {
			DecimalFormat newcolumnFormat = GtnWsFormatter.DECIMAL_FORMATTER.getFormatter();
			newcolumnFormat.applyPattern(formatPatter.replace(GtnWsTransactionConstants.PERCENTAGE,
					GtnFrameworkCommonStringConstants.STRING_EMPTY));
			return newcolumnFormat.format(Double.parseDouble(newValue.toString()))
					+ GtnWsTransactionConstants.PERCENTAGE;
		}
		return columnFormat.format(Double.parseDouble(newValue.toString()));
	}

	private static Object setDefaultValueForColumn(Object value) {
		if (GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(value.toString())) {
			return "0";
		}
		return value;
	}

}
