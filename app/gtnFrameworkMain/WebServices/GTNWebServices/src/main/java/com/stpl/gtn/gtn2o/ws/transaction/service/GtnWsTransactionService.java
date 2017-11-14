/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.sql.JoinType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Vinoth.Parthasarathy
 */
@Service
@Scope("singleton")
public class GtnWsTransactionService {
	public GtnWsTransactionService() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsTransactionService.class);
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public Object getSearchDetails(GtnWsSearchRequest gtnWsSearchRequest, boolean isCount, boolean isExcel)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside getSearchDetails Transaction---");
		List<Object[]> resultList = new ArrayList<>();
		Map<String, String> columnDataTypeMap = new HashMap<>();
		try (Session session = sessionFactory.openSession()) {

			Criteria criteria = session.createCriteria(gtnWebServiceAllListConfig
					.getTransctionDynamicClass(GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION
							+ gtnWsSearchRequest.getSearchModuleName()),
					"c1");
			ClassMetadata classMetadata = sessionFactory.getClassMetadata(gtnWebServiceAllListConfig
					.getTransctionDynamicClass(GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION
							+ gtnWsSearchRequest.getSearchModuleName()));

			for (Field object : gtnWebServiceAllListConfig
					.getTransctionDynamicClass(GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION
							+ gtnWsSearchRequest.getSearchModuleName())

					.getDeclaredFields()) {
				String columnID = object.getName();
				String columnType = object.getType().getName();
				if (object.getType().isPrimitive()) {
					columnType = ClassUtils.primitiveToWrapper(object.getType()).getSimpleName();
				}
				columnDataTypeMap.put(columnID, columnType);
			}

			if (isExcel && gtnWsSearchRequest.getSearchColumnNameList()
					.contains(GtnFrameworkWebserviceConstant.CHECK_RECORD)) {
				List<Object> excelColumnList = new ArrayList<>(gtnWsSearchRequest.getSearchColumnNameList());
				excelColumnList.remove(GtnFrameworkWebserviceConstant.CHECK_RECORD);

				gtnWsSearchRequest.setSearchColumnNameList(excelColumnList);
			}
			if (!isCount) {
				generateCountProjectionAndCriteria(gtnWsSearchRequest, columnDataTypeMap, criteria, classMetadata);
			} else {
				criteria.setProjection(Projections.rowCount());

			}
			boolean isInvalid = getValidOrInvalidModules(gtnWsSearchRequest.getSearchModuleName());
			appendWhereCondition(gtnWsSearchRequest, columnDataTypeMap, criteria, classMetadata, isInvalid);
			if (!isCount) {
				resultList = getResultList(criteria.list(), gtnWsSearchRequest.getSearchColumnNameList(),
						gtnWebServiceAllListConfig.getUserIdNameMap(), gtnWsSearchRequest.getSearchModuleName());
			} else {
				return ((Long) criteria.uniqueResult()).intValue();
			}

		} catch (Exception ex) {
			logger.error("Exception in search query-", ex);
			throw new GtnFrameworkGeneralException("Error in search query : ", ex);
		}
		logger.debug("Existing getSearchDetails Transaction---");
		return resultList;
	}

	private void appendWhereCondition(GtnWsSearchRequest gtnWsSearchRequest, Map<String, String> columnDataTypeMap,
			Criteria criteria, ClassMetadata classMetadata, boolean isInvalid) throws ParseException {
		for (GtnWebServiceSearchCriteria columns : gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList()) {
			String type = columnDataTypeMap.get(columns.getFieldId());

			String value = columns.isFilter() ? "%" + columns.getFilterValue1() + "%" : columns.getFilterValue1();
			columns.setExpression(columns.isFilter()
					? getExpressionType(columns, gtnWsSearchRequest.getSearchModuleName()) : columns.getExpression());
			boolean isUser = columns.getFieldId().contains(GtnFrameworkCommonConstants.CREATED_BY)
					|| columns.getFieldId().contains(GtnFrameworkCommonConstants.MODIFIED_BY);
			String dateFormat = columns.isFilter() ? "E MMM dd HH:mm:ss Z yyyy" : "yyyy-MM-dd";
			boolean isInvalidFilter = isInvalid && columns.getFieldId().contains("Date");
			switch (columns.getExpression()) {
			case "BETWEEN":
				betweenConditon(criteria, columns, type, dateFormat);
				break;
			case "LIKE":
				likeCriteria(criteria, classMetadata, columns, value, isUser, isInvalidFilter);
				break;
			case "EQUAL":
				equalCriteria(criteria, classMetadata, columns, type, dateFormat, value);
				break;

			case "EQUALS":
				equalsCriteria(criteria, columns, type, value, dateFormat);

				break;
			case "GREATER":
				criteria.add(Restrictions.gt(columns.getFieldId(),
						getValueBasedOnType(type, value, columns.getFilterValue1(), dateFormat)));
				break;
			case "LESS":
				criteria.add(Restrictions.lt(columns.getFieldId(),
						getValueBasedOnType(type, value, columns.getFilterValue1(), dateFormat)));
				break;
			case "GREATER_OR_EQUAL":
				criteria.add(Restrictions.ge(columns.getFieldId(),
						getValueBasedOnType(type, value, columns.getFilterValue1(), dateFormat)));
				break;

			case "LESS_OR_EQUAL":
				criteria.add(Restrictions.le(columns.getFieldId(),
						getValueBasedOnType(type, value, columns.getFilterValue1(), dateFormat)));
				break;
			case "AND":
				andCriteria(criteria, columns, value, type, dateFormat);
				break;

			case "DATE_BETWEEN":
				String columnName = ((AbstractEntityPersister) classMetadata)
						.getPropertyColumnNames(columns.getFieldId())[0];
				SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy hh:mmaa");
				Date date1 = new SimpleDateFormat(dateFormat).parse(columns.getFilterValue1());
				value = formatter.format(date1);
				Object[] values = { columnName, value };
				Type[] types = { StandardBasicTypes.STRING, StandardBasicTypes.STRING };
				criteria.add(Restrictions.sqlRestriction(
						"REPLACE(REPLACE(REPLACE(?,' ','{}'),'}{',''),'{}',' ') like ?", values, types));
				break;
			case "BOOLEAN_SEARCH":
				criteria.add(
						Restrictions.eq(columns.getFieldId(), "YES".equals(columns.getFilterValue1()) ? "1" : "0"));
				break;
			default:
				criteria.add(Restrictions.ilike(columns.getFieldId(), value.replaceAll("\\*", "%")));
				break;
			}
		}
	}

	private void andCriteria(Criteria criteria, GtnWebServiceSearchCriteria columns, String value, String type,
			String dateFormat) throws ParseException {
		criteria.add(Restrictions.lt(columns.getFieldId(),
				getValueBasedOnType(type, value, columns.getFilterValue2(), dateFormat)));
	}

	Object getValueBasedOnType(String type, String value, String filterValue, String dateFormat) throws ParseException {
		if (Date.class.getName().equalsIgnoreCase(type)) {
			return new SimpleDateFormat(dateFormat).parse(filterValue);
		} else if ("java.lang.Double".equalsIgnoreCase(type)) {
			return Double.valueOf(filterValue);
		} else if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type)) {
			return Integer.valueOf(filterValue);
		} else {
			return value;
		}

	}

	private void equalsCriteria(Criteria criteria, GtnWebServiceSearchCriteria columns, String type, String value,
			String dateFormat) throws ParseException {
		if ("com.stpl.gtn.gtn2o.ws.entity.HelperTable".equalsIgnoreCase(type)) {
			criteria.createAlias("c1." + columns.getFieldId(), columns.getFieldId(), JoinType.INNER_JOIN);
			criteria.add(Restrictions.eq(columns.getFieldId() + "." + "helperTableSid", Integer.valueOf(value)));
		} else if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type)) {
			criteria.add(Restrictions.eq(columns.getFieldId(), Integer.valueOf(value)));
		} else if ("java.util.Date".equalsIgnoreCase(type)) {
			Date fromDate = new SimpleDateFormat(dateFormat).parse(columns.getFilterValue1());
			Date toDate = getDateForSearch(fromDate);
			criteria.add(Restrictions.between(columns.getFieldId(), fromDate, toDate));

		} else if (columns.getFieldId().equals(GtnFrameworkWebserviceConstant.CHECK_RECORD)) {
			criteria.add(Restrictions.eq(columns.getFieldId(), true));
		} else {
			criteria.add(Restrictions.eq(columns.getFieldId(), value));
		}
	}

	private void equalCriteria(Criteria criteria, ClassMetadata classMetadata, GtnWebServiceSearchCriteria columns,
			String type, String dateFormat, String value) throws ParseException {
		if ("java.lang.Double".equalsIgnoreCase(type) && columns.isFilter()) {
			String columnName = ((AbstractEntityPersister) classMetadata)
					.getPropertyColumnNames(columns.getFieldId())[0];
			Object[] doubleFilterValues = { columnName, columns.getFilterValue1() };
			Type[] doubleFilterTypes = { StandardBasicTypes.STRING, StandardBasicTypes.STRING };
			criteria.add(Restrictions.sqlRestriction(" round(?,3)=?", doubleFilterValues, doubleFilterTypes));
		} else {
			criteria.add(Restrictions.eq(columns.getFieldId(),
					getValueBasedOnType(type, value, columns.getFilterValue1(), dateFormat)));

		}
	}

	private void likeCriteria(Criteria criteria, ClassMetadata classMetadata, GtnWebServiceSearchCriteria columns,
			String value, boolean isUser, boolean isInvalidFilter) {
		if (isUser) {
			Set<String> keys = new HashSet<>();
			for (Entry<Integer, String> entry : gtnWebServiceAllListConfig.getUserIdNameMap().entrySet()) {
				String entryValue = entry.getValue().contains("  ")
						? entry.getValue().replace("  ", " ").toLowerCase(Locale.ENGLISH)
						: entry.getValue().toLowerCase(Locale.ENGLISH);
				if (entryValue.contains(columns.getFilterValue1())) {
					keys.add(entry.getKey().toString());
				}
			}
			criteria.add(Restrictions.in(columns.getFieldId(), keys));
		} else {
			if ("%".equals(value)) {
				Criterion c1 = Restrictions.isNull(columns.getFieldId());
				Criterion c2 = Restrictions.ilike(columns.getFieldId(), value.replaceAll("\\*", "%"));
				criteria.add(Restrictions.or(c1, c2));
			} else if (isInvalidFilter) {
				String columnName = ((AbstractEntityPersister) classMetadata)
						.getPropertyColumnNames(columns.getFieldId())[0];
				Object[] invalidFilterValues = { columnName, value };
				Type[] invalidFilterTypes = { StandardBasicTypes.STRING, StandardBasicTypes.STRING };
				criteria.add(
						Restrictions.sqlRestriction("REPLACE(REPLACE(REPLACE( ? ,' ','{}'),'}{',''),'{}',' ') like ?",
								invalidFilterValues, invalidFilterTypes));
			} else {
				criteria.add(Restrictions.ilike(columns.getFieldId(), value.replaceAll("\\*", "%")));
			}
		}
	}

	private void betweenConditon(Criteria criteria, GtnWebServiceSearchCriteria columns, String type, String dateFormat)
			throws ParseException {
		criteria.add(Restrictions.gt(columns.getFieldId(), getDateValue(type, columns.getFilterValue1(), dateFormat)));
		criteria.add(Restrictions.lt(columns.getFieldId(), getDateValue(type, columns.getFilterValue2(), dateFormat)));
	}

	Object getDateValue(String type, String filterValue, String dateFormat) throws ParseException {
		if ("java.util.Date".equalsIgnoreCase(type)) {
			return new SimpleDateFormat(dateFormat).parse(filterValue);
		}
		return filterValue;
	}

	private void generateCountProjectionAndCriteria(GtnWsSearchRequest gtnWsSearchRequest,
			Map<String, String> columnDataTypeMap, Criteria criteria, ClassMetadata classMetadata) {
		ProjectionList projectionList = Projections.projectionList();
		for (Object columns : gtnWsSearchRequest.getSearchColumnNameList()) {
			projectionList.add(Projections.property(String.valueOf(columns)));
		}
		projectionList.add(Projections.property(classMetadata.getIdentifierPropertyName()));
		criteria.setProjection(projectionList);
		criteria.setFirstResult(gtnWsSearchRequest.getTableRecordStart());
		criteria.setMaxResults(gtnWsSearchRequest.getTableRecordOffset());
		for (GtnWebServiceOrderByCriteria column : gtnWsSearchRequest.getGtnWebServiceOrderByCriteriaList()) {

			boolean isHelper = "com.stpl.gtn.gtn2o.ws.entity.HelperTable"
					.equalsIgnoreCase(columnDataTypeMap.get(column.getPropertyId().getClass().getName()));
			if (isHelper) {
				criteria.createAlias("c1." + column.getPropertyId(), column.getPropertyId(), JoinType.INNER_JOIN);
				criteria.addOrder("ASC".equalsIgnoreCase(column.getOrderByCriteria())
						? Order.asc(column.getPropertyId() + "." + "description")
						: Order.desc(column.getPropertyId() + "." + "description"));
			} else {
				criteria.addOrder("ASC".equalsIgnoreCase(column.getOrderByCriteria())
						? Order.asc(column.getPropertyId()) : Order.desc(column.getPropertyId()));
			}

		}
	}

	private String getExpressionType(GtnWebServiceSearchCriteria columns, String tableName) {
		String expression = columns.getExpression();

		if (tableName.contains(GtnFrameworkWebserviceConstant.GL_BALANCE)
				&& GtnFrameworkWebserviceConstant.ACTIVE.equals(columns.getFieldId())) {
			expression = "BOOLEAN_SEARCH";
		}
		return expression;

	}

	@SuppressWarnings("unchecked")
	public Object[] getViewDetails(GtnWsTransactionRequest gtnWsTransactionRequest)
			throws GtnFrameworkGeneralException {

		Object[] ob = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(gtnWebServiceAllListConfig
					.getTransctionDynamicClass(GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION
							+ gtnWsTransactionRequest.getTableName()));
			ClassMetadata classMetadata = sessionFactory.getClassMetadata(gtnWebServiceAllListConfig
					.getTransctionDynamicClass(GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION
							+ gtnWsTransactionRequest.getTableName()));
			ProjectionList projectionList = Projections.projectionList();
			for (String columns : gtnWsTransactionRequest.getProjectionColumns()) {
				projectionList.add(Projections.property(columns));
			}

			criteria.setProjection(projectionList);
			criteria.add(Restrictions.eq(classMetadata.getIdentifierPropertyName(),
					gtnWsTransactionRequest.getPrimayColumnValue()));
			if (!gtnWsTransactionRequest.getDemandTypeColumnName().isEmpty()) {
				criteria.add(Restrictions.eq(gtnWsTransactionRequest.getDemandTypeColumnName(),
						gtnWsTransactionRequest.getDemandTypeColumnValue()));
			}
			List<Object> resultList = criteria.list();
			ob = getViewRecord(resultList, gtnWsTransactionRequest);
		} catch (Exception ex) {
			logger.error("Exception in view query-", ex);
			throw new GtnFrameworkGeneralException("Error in view query : ", ex);
		}
		return ob;
	}

	private Object[] getViewRecord(List<Object> resultList, GtnWsTransactionRequest gtnWsTransactionRequest) {
		Object[] ob = null;
		Iterator<Object> iterator = resultList.iterator();

		while (iterator.hasNext()) {
			ob = (Object[]) iterator.next();
			for (int i = 0; i < ob.length; i++) {
				if (ob[i] instanceof HelperTable) {
					ob[i] = "-Select One-".equals(((HelperTable) ob[i]).getDescription()) ? ""
							: ((HelperTable) ob[i]).getDescription();

				} else if (ob[i] instanceof Double) {
					ob[i] = new BigDecimal(String.valueOf(ob[i])).toPlainString();
				}
			}

			getFormattedData(gtnWsTransactionRequest.getProjectionColumns(), ob,
					gtnWsTransactionRequest.getTableName());
		}
		return ob;
	}

	public List<Object> createFile(File tempFile, PrintWriter printWriter, String modulName, List<String> headers) {
		List<Object> list = new ArrayList<>();
		if (modulName.contains("Ivld")) {
			List<String> headersList = headers.subList(1, headers.size());
			GtnCommonUtil.createHeaderRow(printWriter, headersList);
		} else {
			GtnCommonUtil.createHeaderRow(printWriter, headers);
		}
		list.add(printWriter);
		list.add(tempFile.getAbsolutePath());

		return list;
	}

	public void writeFile(List<Object[]> resultList, PrintWriter printWriter, FileWriter writer,
			Boolean excelComplete) {

		try {
			GtnCommonUtil.createDataRows(printWriter, resultList);
			printWriter.flush();
			if (excelComplete) {
				printWriter.close();
				writer.close();

			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}

	private String getFilePath() throws GtnFrameworkGeneralException {
		StringBuilder filePath = new StringBuilder(System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH));
		filePath.append("/");
		filePath.append("exceltransaction");
		filePath.append("/");
		Path path = Paths.get(filePath.toString());
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {

				throw new GtnFrameworkGeneralException(e);

			}
		}
		return filePath.toString();
	}

	Object getHelperValue(Object value) {
		if (value instanceof HelperTable) {
			return "-Select One-".equals(((HelperTable) value).getDescription()) ? ""
					: ((HelperTable) value).getDescription();
		}
		return value;
	}

	String getUserName(Object value, Map<Integer, String> userMap) {
		String modifiedValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (value != null && String.valueOf(value).matches(GtnFrameworkCommonStringConstants.REGEX_FOR_INTEGER)) {
			int modifiedUserId = Integer.parseInt(String.valueOf(value));
			modifiedValue = userMap.get(modifiedUserId);
		}
		return modifiedValue;
	}

	String getYesNoValue(Object value) {
		if (value != null) {
			return String.valueOf(value).trim().equals("1") ? "YES" : "NO";
		}
		return GtnFrameworkCommonStringConstants.STRING_EMPTY;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getResultList(Object resultSet, List<Object> searchColumnNameList,
			Map<Integer, String> userMap, String tableName) {
		List<Object[]> resultList = new ArrayList<>();
		Iterator<Object> iterator = ((List<Object>) resultSet).iterator();
		while (iterator.hasNext()) {
			Object[] ob = (Object[]) iterator.next();
			for (int i = 0; i < ob.length; i++) {
				ob[i] = getHelperValue(ob[i]);
			}
			if (searchColumnNameList.contains(GtnFrameworkCommonConstants.CREATED_BY)) {
				int index = searchColumnNameList.indexOf(GtnFrameworkCommonConstants.CREATED_BY);
				ob[index] = getUserName(ob[index], userMap);
			}
			if (searchColumnNameList.contains(GtnFrameworkCommonConstants.MODIFIED_BY)) {
				int modifiedIndex = searchColumnNameList.indexOf(GtnFrameworkCommonConstants.MODIFIED_BY);
				ob[modifiedIndex] = getUserName(ob[modifiedIndex], userMap);
			}

			if (tableName.contains(GtnFrameworkWebserviceConstant.GL_BALANCE)
					&& searchColumnNameList.contains(GtnFrameworkWebserviceConstant.ACTIVE)) {
				int isActiveIndex = searchColumnNameList.indexOf(GtnFrameworkWebserviceConstant.ACTIVE);
				ob[isActiveIndex] = getYesNoValue(ob[isActiveIndex]);
			}

			resultList.add(ob);

		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getExcelFile(
			GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse, GtnWsGeneralRequest generalRequest,
			GtnWsSearchRequest gtnWsSearchRequest, GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		int count = gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset();
		Boolean excelComplete = false;
		List<Object> list = new ArrayList<>();
		String filePath = getFilePath();
		String filename = gtnWsSearchRequest.getSearchModuleName() + GtnFrameworkCommonStringConstants.UNDERSCORE
				+ gtnWsRequest.getGtnWsGeneralRequest().getUserId() + GtnFrameworkCommonStringConstants.UNDERSCORE
				+ gtnWsRequest.getGtnWsGeneralRequest().getSessionId()
				+ GtnFrameworkCommonStringConstants.CSV_EXTENSION;

		File tempFile = GtnFileNameUtils.getFile(filePath + filename);
		try (FileWriter writer = new FileWriter(tempFile, true)) {

			PrintWriter printWriter = new PrintWriter(writer);
			list = createFile(tempFile, printWriter, gtnWsSearchRequest.getSearchModuleName(),
					(List<String>) generalRequest.getExtraParameter());
			printWriter = (PrintWriter) list.get(GtnWsNumericConstants.ZERO);
			if (count > GtnWsNumericConstants.BATCH_COUNT) {
				int maxNbrOfLoop = count / GtnWsNumericConstants.BATCH_COUNT;
				int offset = 0;
				for (int i = 1; i <= maxNbrOfLoop; i++) {
					gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(offset);
					if (i == maxNbrOfLoop) {
						gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(count);
						excelComplete = true;
					} else {
						offset = i * GtnWsNumericConstants.BATCH_COUNT;
						gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(GtnWsNumericConstants.BATCH_COUNT);
					}
					List<Object[]> resultList = (List<Object[]>) getSearchDetails(gtnWsRequest.getGtnWsSearchRequest(),
							gtnWsRequest.getGtnWsSearchRequest().isCount(), true);
					writeFile(resultList, printWriter, writer, excelComplete);
				}

			} else {
				List<Object[]> resultList = (List<Object[]>) getSearchDetails(gtnWsRequest.getGtnWsSearchRequest(),
						gtnWsRequest.getGtnWsSearchRequest().isCount(), true);
				writeFile(resultList, printWriter, writer, true);

			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		logger.info("=====Excel writing finished======");
		gtnUIFrameworkWebserviceResponse.setExportFilePath(String.valueOf(list.get(GtnWsNumericConstants.ONE)));
		return gtnUIFrameworkWebserviceResponse;
	}

	private Date getDateForSearch(Date ob) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(ob);
		int dateOfYear = calender.get(Calendar.DATE);
		calender.set(Calendar.DATE, dateOfYear + 1);
		return calender.getTime();
	}

	public void getFormattedData(List<String> projectionColumns, Object[] ob, String tableName) {
		if (projectionColumns.contains(GtnFrameworkCommonConstants.CREATED_BY)) {
			String value = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			int index = projectionColumns.indexOf(GtnFrameworkCommonConstants.CREATED_BY);
			if (ob[index] != null
					&& String.valueOf(ob[index]).matches(GtnFrameworkCommonStringConstants.REGEX_FOR_INTEGER)) {
				int userId = Integer.parseInt(String.valueOf(ob[index]));
				value = gtnWebServiceAllListConfig.getUserIdNameMap().get(userId);
			}
			ob[index] = value;
		}
		if (projectionColumns.contains(GtnFrameworkCommonConstants.MODIFIED_BY)) {
			String modifiedValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			int modifiedIndex = projectionColumns.indexOf(GtnFrameworkCommonConstants.MODIFIED_BY);
			if (ob[modifiedIndex] != null
					&& String.valueOf(ob[modifiedIndex]).matches(GtnFrameworkCommonStringConstants.REGEX_FOR_INTEGER)) {
				int modifiedUserId = Integer.parseInt(String.valueOf(ob[modifiedIndex]));
				modifiedValue = gtnWebServiceAllListConfig.getUserIdNameMap().get(modifiedUserId);
			}
			ob[modifiedIndex] = modifiedValue;
		}
		if (tableName.contains(GtnFrameworkWebserviceConstant.GL_BALANCE)
				&& projectionColumns.contains(GtnFrameworkWebserviceConstant.ACTIVE)) {
			String isActiveValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			int isActiveIndex = projectionColumns.indexOf(GtnFrameworkWebserviceConstant.ACTIVE);
			if (ob[isActiveIndex] != null) {
				isActiveValue = ob[isActiveIndex].equals(true) ? "YES" : "NO";
			}
			ob[isActiveIndex] = isActiveValue;
		}

	}

	private boolean getValidOrInvalidModules(String searchModuleName) {
		Boolean isInvalid = false;
		if (searchModuleName.startsWith("Ivld")) {
			isInvalid = true;
		}
		return isInvalid;
	}

	public String getValidationForRunning(String searchModuleName) {
		List<?> validationList = new ArrayList<>();
		List<Object> inputList = new ArrayList<>();
		inputList.add(searchModuleName);
		try {
			String query = gtnWsSqlService.getQuery(inputList, "getWorkflowValidation");
			validationList = gtnWebServiceAllListConfig.getGtnSqlQueryEngine().executeSelectQuery(query);
		} catch (GtnFrameworkGeneralException ex) {
			logger.info(ex.getMessage());
		}
		return String.valueOf(validationList.get(0));
	}
}
