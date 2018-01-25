/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.logic;

import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang.time.DateUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

import com.stpl.app.gtnutilities.charts.dto.ChartsDTO;
import com.stpl.app.gtnutilities.charts.dto.JobDTO;
import com.stpl.app.gtnutilities.util.CommonMethods;
import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.app.gtnutilities.util.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author Karthik.Raja
 */
public class SearchLogic {

	private static SearchLogic searchLogic;
	private Date selectedDate = CommonMethods.getYesterdayDate();
	private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
	// Declaring resusable global Variables
	private Window detailedQuery = new Window(Constants.QUERY);
	private Button link;
	private String query = Constants.EMPTY;
	private SimpleStringFilter stringFilter;
	private List returnList = new ArrayList<>();
	private ChartsDTO chartsDTO;
	private JobDTO jobDTO = null;
	private Connection connection = null;
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(SearchLogic.class);
	// For Singleton

	public static SearchLogic getInstance() {
		if (searchLogic == null) {
			searchLogic = new SearchLogic();
			decimalFormat.setRoundingMode(RoundingMode.UP);
		}
		return searchLogic;
	}

	private SearchLogic() {
	}

	public List fetchDataFromDB(Object input[], int start, int end, boolean isCount, Set<Container.Filter> filterSet,
			List<SortByColumn> sortByList, boolean isExcelExport) {
		query = Constants.EMPTY;
		String tableName = String.valueOf(input[NumericConstants.TWO]).replaceAll(Constants.SPACE,
				Constants.UNDERSCORE);
		String typeQuery = tableName.equals(Constants.INDEX_FRAGMENTATION_STATISTICS) ? Constants.EMPTY
				: " AND TYPE='" + input[NumericConstants.ZERO] + "' ";
		String dateCondition = String.format(
				!DateUtils.isSameDay((Date) input[NumericConstants.THREE], new Date())
						? "FORMAT( INSERTED_DATE, 'yyyy-MM-dd HH' )+':00:00' =   '%s'" : "INSERTED_DATE = '%s'",
				input[NumericConstants.ONE]);
		String dbCondtion = String.valueOf(input[NumericConstants.FOUR]).isEmpty()
				|| tableName.equals(Constants.INDEX_FRAGMENTATION_STATISTICS) ? Constants.EMPTY
						: String.format("AND DATABASE_NAME LIKE '%s'", String.valueOf(input[NumericConstants.FOUR]));
		if (isCount) {
			query = String.format("select count(*) from  %s where %s %s %s %s ", tableName, dateCondition, typeQuery,
					dbCondtion, getFilterQuery(filterSet));
			return HelperTableLocalServiceUtil.executeSelectQuery(query);
		}
		query = String.format("select %s from  %s where %s %s %s %s %s OFFSET %s ROWS FETCH NEXT %s ROWS ONLY ",
				getRequiredColumnsBasedOnTable(tableName), tableName, dateCondition, dbCondtion,
				getFilterQuery(filterSet), typeQuery, getSortByQuery(sortByList, tableName), start, end);
		List<Object[]> resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);

		return isExcelExport ? resultList
				: customize_data(resultList, tableName, String.valueOf(input[NumericConstants.ZERO]));
	}

	public List fetchDataFromDBForJOb(Object input[], int start, int end, boolean isCount,
			Set<Container.Filter> filterSet, List<SortByColumn> sortByList, boolean isExcelExport) {
		try {
			connectToDB();
			query = SQlUtil.getQuery(isCount ? "getScheduledJobsCount" : "getScheduledJobs");
			if (!isCount) {
				String enabledQuery = String.valueOf(input[NumericConstants.ONE]).equals(Constants.ALL)
						? Constants.EMPTY
						: String.format("and [jobs].[enabled]=%s",
								String.valueOf(input[NumericConstants.ONE]).equals(Constants.ENABLED)
										? NumericConstants.ONE : NumericConstants.ZERO);
				query += String.format("WHERE [jobs].[name] like '%s' %s %s %s OFFSET %s ROWS FETCH NEXT %s ROWS ONLY",
						input[NumericConstants.ZERO], enabledQuery, getFilterQueryForJob(filterSet),
						getSortByQuery(sortByList, Constants.JOB), start, end);
			}

			ResultSet resultSet = GtnSqlUtil.getResultSetFromProcedure(query, new Object[] {});
			return isExcelExport && !isCount ? Arrays.asList(resultSet) : customize_Job_Data(resultSet, isCount);
		} catch (Exception ex) {
			LOGGER.debug(ex);
		}
		return returnList == null ? returnList : new ArrayList<>(returnList);
	}

	private List customize_data(List<Object[]> resultList, String tableName, String type) {
		returnList = new ArrayList<>();

		for (int i = NumericConstants.ZERO; i < resultList.size(); i++) {
			int index = NumericConstants.ZERO;
			Object[] objects = resultList.get(i);
			chartsDTO = new ChartsDTO();
			if (tableName.equals(Constants.INDEX_FRAGMENTATION_STATISTICS)) {
				chartsDTO.setIndex_Name(String.valueOf(objects[index++]));
				chartsDTO.setTable_Name(String.valueOf(objects[index++]));
				chartsDTO.setFragmentation_Percentage(String.valueOf(objects[index++]));
				chartsDTO.setSpace_Used_Percentage(String.valueOf(objects[index++]));
				chartsDTO.setTotal_Rows(String.valueOf(objects[index++]));
				chartsDTO.setDistributed_Rows(String.valueOf(objects[index++]));
			} else {

				chartsDTO.setDataBase_Name(String.valueOf(objects[index++]));
				if (tableName.equals(Constants.STORED_PROCEDURE_INFO)) {
					chartsDTO.setProcedure_Name(String.valueOf(objects[index++]));
				}
				final String query = String.valueOf(objects[index++]);
				chartsDTO.setQueryName(query);
				chartsDTO.setValue(truncateValuesBasedOnType(type, String.valueOf(objects[index++])));
				chartsDTO.setText_Query(createButtonLink(query));
			}
			LOGGER.debug("End of Loop----------" + index);
			returnList.add(chartsDTO);
		}
		return Collections.unmodifiableList(returnList);
	}

	public List<Object[]> fetchCPUIODataFromDB(String type, Date selecteDate) {
		query = Constants.EMPTY;
		String sqlDate = new SimpleDateFormat(Constants.YYYY_MM_DD_HH_MM_SS).format(selecteDate);
		String dateCondition = String.format(
				!DateUtils.isSameDay(selecteDate, new Date())
						? "FORMAT( INSERTED_DATE, 'yyyy-MM-dd HH' )+':00:00' =   '%s'" : "INSERTED_DATE = '%s'",
				sqlDate);
		query = String.format(
				"select ISNULL(DATABASE_NAME,'RESOURCE DATABASE'),\"VALUE\" from  dbo.CPU_IO_USAGE_PER_DB where %s and \"TYPE\"='%s'  ORDER BY VALUE desc",
				dateCondition, type);
		List<Object[]> resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
		return resultList;
	}

	public Connection connectToDB() throws SQLException {
		String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appMonitorDataPool";

		try {
			Context initialContext = new InitialContext();
			if (initialContext == null) {
				LOGGER.error("JNDI problem. Cannot get InitialContext.");
			}
			DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				connection = datasource.getConnection();
			} else {
				LOGGER.error("Failed to lookup datasource.");
			}
		} catch (NamingException ex) {
			LOGGER.error("Cannot get connection: " + ex);
		} catch (SQLException ex) {
			LOGGER.error("Cannot get connection: " + ex);
		}
		return connection;
	}

	public String truncateValuesBasedOnType(String type, String value) {
		if (type.equals(Constants.CPU)) {
			return decimalFormat.format(Double.valueOf(value)) + Constants.PERCENTAGE;
		} else if (type.equals(Constants.LONG)) {
			return decimalFormat.format((Double.valueOf(value) / NumericConstants.THOUSAND)) + Constants.SECS;
		}
		return decimalFormat.format(Double.valueOf(value)) + Constants.MB;
	}

	Button createButtonLink(final String text_query) {
		link = new Button();
		link.setCaption(text_query.length() < NumericConstants.FIFTY ? text_query
				: text_query.substring(0, NumericConstants.FORTY_NINE) + Constants.CONTINUOUS_DOTS);
		link.addStyleName(Reindeer.BUTTON_LINK);
		link.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				detailedQuery.setContent(new Label(text_query));
				detailedQuery.center();
				detailedQuery.setWidth(NumericConstants.FLOAT_FIFTY_NINE, Sizeable.Unit.PERCENTAGE);
				detailedQuery.setHeight(NumericConstants.FLOAT_THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);
				UI.getCurrent().addWindow(detailedQuery);
			}
		});
		return link;
	}

	private String getFilterQuery(Set<Container.Filter> filterSet) {
		StringBuilder filterQuery = new StringBuilder(Constants.EMPTY);

		for (Container.Filter filter : filterSet) {
			stringFilter = (SimpleStringFilter) filter;
			String propValue = stringFilter.getFilterString();
			if (propValue == null || propValue.isEmpty()) {
				continue;
			}
			String value = Constants.PERCENTAGE + propValue + Constants.PERCENTAGE;
			filterQuery.append(String.format(" AND %s LIKE '%s' ", stringFilter.getPropertyId(), value));
		}
		return filterQuery.toString();
	}

	private String getFilterQueryForJob(Set<Container.Filter> filterSet) {
		StringBuilder filterQuery = new StringBuilder(Constants.EMPTY);

		for (Container.Filter filter : filterSet) {
			stringFilter = (SimpleStringFilter) filter;
			String property = String.valueOf(stringFilter.getPropertyId());
			String propValue = stringFilter.getFilterString();
			if (propValue == null || propValue.isEmpty()) {
				continue;
			}
			if (property.equals("jobname")) {
				property = "[JOBS].[NAME]";
			} else if (property.equals("description")) {
				property = "[JOBS].[DESCRIPTION]";
			} else if (Constants.STATUS.equals(property)) {
				filterQuery.append(String.format(" AND %s = %s ", property,
						propValue.equals(Constants.SUCCESS) ? NumericConstants.ONE : NumericConstants.ZERO));
				continue;
			} else if (Constants.ENABLED.equalsIgnoreCase(property)) {
				filterQuery.append(String.format(" AND %s = %s ", "[JOBS]." + property,
						propValue.equalsIgnoreCase(Constants.YES) ? NumericConstants.ONE : NumericConstants.ZERO));
				continue;
			}
			String value = Constants.PERCENTAGE + propValue + Constants.PERCENTAGE;
			filterQuery.append(String.format(" AND %s LIKE '%s' ", property, value));
		}
		return filterQuery.toString();
	}

	private String getSortByQuery(List<SortByColumn> sortByList, String tableName) {
		if (sortByList != null && !sortByList.isEmpty()) {
			return String.format("ORDER BY %s %s ", sortByList.get(NumericConstants.ZERO).getName(),
					sortByList.get(NumericConstants.ZERO).getType());
		}
		if (tableName.equals(Constants.JOB)) {
			return "ORDER BY JOBNAME ASC";
		}
		return tableName.equals(Constants.INDEX_FRAGMENTATION_STATISTICS)
				? "ORDER  BY INDEX_FRAGMENTATION_STATISTICS_SID DESC " : "ORDER BY VALUE desc";
	}

	private List customize_Job_Data(ResultSet resultSet, boolean isCount) {
		Field[] declaredField = JobDTO.class.getDeclaredFields();
		try {
			while (resultSet.next()) {
				if (isCount) {
					returnList.add(Integer.valueOf(resultSet.getString(NumericConstants.ONE)));
					return Collections.unmodifiableList(returnList);
				}
				int index = NumericConstants.ONE;
				jobDTO = new JobDTO();
				for (Field field : declaredField) {
					field.setAccessible(true);
					field.set(jobDTO, resultSet.getString(index++));
				}
				returnList.add(jobDTO);
			}
			closeConnection();

		} catch (Exception ex) {
			LOGGER.debug(ex);
		}
		return returnList == null ? returnList : new ArrayList<>(returnList);
	}

	String getRequiredColumnsBasedOnTable(String tableName) {
		if (tableName.equals(Constants.INDEX_FRAGMENTATION_STATISTICS)) {
			return "Index_Name,Table_Name,Fragmentation_Percentage,Space_Used_Percentage,Total_Rows,Distributed_Rows";
		} else if (tableName.equals(Constants.STORED_PROCEDURE_INFO)) {
			return "DATABASE_NAME,PROCEDURE_NAME,TEXT_QUERY,VALUE";
		}
		return "DATABASE_NAME,TEXT_QUERY,VALUE ";
	}

	public void closeConnection() throws SQLException {

		connection.close();
	}

	public Date getSelectedDate() {
		return selectedDate == null ? null : (Date) selectedDate.clone();
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate == null ? null : (Date) selectedDate.clone();
	}
}
