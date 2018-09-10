
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.logic;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.FileManagementLogicDAO;
import com.stpl.app.cff.dao.impl.FileManagementLogicDAOImpl;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.dto.FileManagementDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileMananagementResultDTO;
import com.stpl.app.cff.ui.fileSelection.dto.ItemSearchDTO;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.FileManagement;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.FileManagementLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.ComboBox;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

   
/**
 * The Class FileManagementLogic.
 *
 * @author santanukumar
 */
public class FileManagementLogic {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementLogic.class);// Logger
        
        
	// Declaration
	/**
	 * The Constant NULLCREATION.
	 */
	private static final Criterion NULLCREATION = null;
	/**
	 * The dao.
	 */
	private static final FileManagementLogicDAO DAO = new FileManagementLogicDAOImpl();
	/**
	 * The foecast year count.
	 */
	private static int foecastYearCount;
	/**
	 * The ITEM_QUAL_NAME.
	 */
	protected static final String ITEM_QUAL_NAME = "itemQualifierName";
	/**
	 * The ITEM_QUALIFIER_SID.
	 */
	protected static final String ITEM_QUALIFIER_SID = "itemQualifierSid";


	private final HashMap<String, String> columnNames = new HashMap<>();
	private final String dollar = "$";
	private final DecimalFormat dollarFormat = new DecimalFormat("#,##0.00");
	private final DecimalFormat unitsFormat = new DecimalFormat("#0.0");
	private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#0.00");
	private final DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
	private final DateFormat dateFormatToParse = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT_TO_PARSE);

	protected static final String ITEM_IDENTIFIER = "ITEM_IDENTIFIER";

	/**
	 * Gets the foecast year count.
	 *
	 * @return the foecast year count
	 */
	public int getFoecastYearCount() {
		return foecastYearCount;
	}

	/**
	 * Sets the foecast year count.
	 *
	 * @param itemPricingQualifierNameCount
	 *            the new foecast year count
	 */
	public static void setFoecastYearCount(final int itemPricingQualifierNameCount) {
		FileManagementLogic.foecastYearCount = itemPricingQualifierNameCount;
	}

	/**
	 * Gets the dao.
	 *
	 * @return the dao
	 */
	public FileManagementLogicDAO getDao() {
		return DAO;
	}

	public static final String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
	// SQL date format
	public static final String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * Gets the forecast year.
	 *
	 * @return the forecast year
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public List<ForecastingMaster> getForecastYear() throws SystemException {
		LOGGER.debug("getForecastYear started");
		final DynamicQuery dynamicQuery = ForecastingMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.setProjection(
				ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORECAST_YEAR)));
		dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.FORECAST_YEAR));
		LOGGER.debug("getForecastYear return List<ForecastingMaster> resultList");
		return DAO.getForecastList(dynamicQuery);
	}

	/**
	 * Gets the details summ.
	 *
	 * @param fileName
	 *            the file name
	 * @param version
	 *            the version
	 * @param fileType
	 *            the file type
	 * @param country
	 *            the country
	 * @return the details summ
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public FileManagementDTO getDetailsSumm(final String fileName, final String version, final String fileType,
			final String country) throws SystemException {
		List<ForecastingMaster> resultsList;
		final FileManagementDTO fileMgtDTO = new FileManagementDTO();
		final DateFormat vDateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

		Criterion criterionForCFFDetails;
		String sqlString = "";
		LOGGER.debug("getDetailsSumm started with P1:String fileName= {} and  P2:String version= {} and P3:String fileType= {} and P4:String country= {} ", fileName, version, fileType, country);
		final DynamicQuery dynamicQueryForCFFDetails = ForecastingMasterLocalServiceUtil.dynamicQuery();
		dynamicQueryForCFFDetails.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORECAST_NAME, fileName));
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType)) {
			if (ConstantsUtils.COUNTRY_US.equals(country)) {
				final Criterion criterionForUS = RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE,
						ConstantsUtils.FORE_SIGHT);
				criterionForCFFDetails = RestrictionsFactoryUtil.or(criterionForUS,
						RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.LE_FORESIGHT));
			} else if (ConstantsUtils.COUNTRY_PR.equals(country)) {
				criterionForCFFDetails = RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.FF_SALES);
			} else {
				criterionForCFFDetails = NULLCREATION;
			}
			dynamicQueryForCFFDetails.add(criterionForCFFDetails);
			dynamicQueryForCFFDetails.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country));
			dynamicQueryForCFFDetails.add(RestrictionsFactoryUtil.eq("forecastVer", version));
			resultsList = DAO.getForecastList(dynamicQueryForCFFDetails);
			if (!resultsList.isEmpty()) {
				final ForecastingMaster fmMaster = (ForecastingMaster) resultsList.get(0);
				fileMgtDTO.setForecastName(fmMaster.getForecastName());
				fileMgtDTO.setForecastVersion(fmMaster.getForecastVer());
				if (fmMaster.getForecastDate() != null) {
					fileMgtDTO.setForecastDate(vDateFormat.format(fmMaster.getForecastDate()));
				}
				if (fmMaster.getCreatedDate() != null) {
					fileMgtDTO.setCreatedDate(vDateFormat.format(fmMaster.getCreatedDate()));
				}
			}
		} else if (ConstantsUtils.DEMAND.equals(fileType)) {
			sqlString = "SELECT DF.FORECAST_NAME,DF.FORECAST_VER,DF.CREATED_DATE FROM DEMAND_FORECAST DF WHERE FORECAST_NAME like '"
					+ fileName + "' AND COUNTRY like '" + country + "' AND FORECAST_VER like  '" + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(vDateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType)) {
			sqlString = "SELECT INW.FORECAST_NAME,INW.FORECAST_VER,INW.CREATED_DATE FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME like '"
					+ fileName + "' AND INW.COUNTRY like '" + country + "' AND INW.FORECAST_VER like '" + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(vDateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType)) {
			sqlString = "SELECT INW.FORECAST_NAME,INW.FORECAST_VER,INW.CREATED_DATE FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME like '"
					+ fileName + "' AND INW.COUNTRY like '" + country + "%' AND INW.FORECAST_VER like '" + version
					+ "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(vDateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.CUSTOMERGTS.equals(fileType)) {
			sqlString = "SELECT DF.FORECAST_NAME,DF.FORECAST_VER,DF.CREATED_DATE FROM CUSTOMER_GTS_FORECAST DF WHERE FORECAST_NAME like '"
					+ fileName + "' AND COUNTRY like '" + country + "' AND FORECAST_VER like '" + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(vDateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.ADJUSTED_DEMAND.equals(fileType)) {
			sqlString = "SELECT FORECAST_NAME, FORECAST_VER, CREATED_DATE FROM dbo.ADJUSTED_DEMAND_FORECAST WHERE FORECAST_NAME like '"
					+ fileName + "' AND  COUNTRY like '" + country + "' AND FORECAST_VER like '" + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(vDateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		}
		LOGGER.debug("getDetailsResults return FileManagementDTO fileMgtDTO");
		return fileMgtDTO;
	}

	/**
	 * Save file mgt hist.
	 *
	 * @param fileMgtDTO
	 *            the file mgt dto
	 * @param fileType
	 *            the file type
	 * @return the string
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public String saveFileMgtHist(final FileMananagementResultDTO fileMgtDTO, final HelperDTO fileType,
			final SessionDTO sessionDTO) throws SystemException {
		final String userId = sessionDTO.getUserId();
		LOGGER.debug("saveFileMgtHist started with P1:FileMananagementResultDTO = {} and P2:String fileType= {}", fileMgtDTO, fileType);
                int create = (int) CounterLocalServiceUtil.increment();
		FileManagement fileManagement = FileManagementLocalServiceUtil.createFileManagement(create);
		fileManagement.setForecastName(fileMgtDTO.getFileName());
		fileManagement.setForecastSource(fileMgtDTO.getType());
		fileManagement.setFileSource(fileMgtDTO.getFileType());
		fileManagement.setVersion(fileMgtDTO.getVersion());
		fileManagement.setFromPeriod(new Date());
		fileManagement.setCreatedDate(new Date());
		fileManagement.setModifiedDate(new Date());
		fileManagement.setCreatedBy(Integer.parseInt(userId));
		fileManagement.setFileType(fileType.getId());
		List<FileManagement> resultsList;
		final DynamicQuery dynamicQuery = FileManagementLocalServiceUtil.dynamicQuery();
		final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
		dynamicQuery.addOrder(defaultOrder);
		Criterion criterion;
		final Criterion criteria = RestrictionsFactoryUtil.eq(StringConstantsUtil.FILE_TYPE, fileType.getId());
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
			final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
			final Criterion criterion2 = RestrictionsFactoryUtil.or(criterion1,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT));
			criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion2,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FF_SALES)), criteria);
		} else if (ConstantsUtils.DEMAND.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription())) {
			criterion = criteria;
		} else {
			criterion = NULLCREATION;
		}
		dynamicQuery.add(criterion);

		resultsList = DAO.getFilesList(dynamicQuery);
		if (!resultsList.isEmpty()) {
			final FileManagement currentActiveFile = resultsList.get(0);
			currentActiveFile.setToPeriod(new Date());
			currentActiveFile.setModifiedDate(new Date());
			currentActiveFile.setModifiedBy(Integer.parseInt(userId));
			currentActiveFile.setFileType(fileType.getId());

			DAO.updateFiles(currentActiveFile);
		}
		DAO.addFiles(fileManagement);
		final String msg = ConstantsUtils.SUCCESS;

		LOGGER.debug("saveFileMgtHist return String msg= {}", msg);
		return msg;
	}

	/**
	 * Gets the current file info.
	 *
	 * @param fileType
	 *            the file type
	 * @return the current file info
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public FileManagementDTO getCurrentFileInfo(final HelperDTO fileType) throws SystemException {

		final FileManagementDTO fileMgtDTO = new FileManagementDTO();
		LOGGER.debug("getCurrentFileInfo started with P1:String fileType= {}", fileType);
		List<FileManagement> resultsList;
		final DynamicQuery dynamicQuery = FileManagementLocalServiceUtil.dynamicQuery();
		final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
		dynamicQuery.addOrder(defaultOrder);
		Criterion criterion;

		Criterion criteria = RestrictionsFactoryUtil.eq(StringConstantsUtil.FILE_TYPE, fileType.getId());
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
			final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
			criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
		} else if (ConstantsUtils.DEMAND.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())) {
			criterion = criteria;
		} else {
			criterion = NULLCREATION;
		}
		dynamicQuery.add(criterion);
		resultsList = DAO.getFilesList(dynamicQuery);
		if (!resultsList.isEmpty()) {
			final FileManagement fileMgt = (FileManagement) resultsList.get(0);
			final DateFormat vDateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
			fileMgtDTO.setCurrentFile(fileMgt.getForecastName());
			fileMgtDTO.setForecastVersion(fileMgt.getVersion());
			fileMgtDTO.setEffectiveDate(fileMgt.getCreatedDate() == null ? ConstantsUtils.EMPTY
					: vDateFormat.format(fileMgt.getCreatedDate()));
		}

		LOGGER.debug("getCurrentFileInfo return FileManagementDTO fileMgtDTO");
		return fileMgtDTO;
	}

	/**
	 * Gets the forecast year count.
	 *
	 * @param filterText
	 *            the filter text
	 * @return the forecast year count
	 * @throws PortalException
	 *             the portal exception
	 * @throws SystemException
	 *             the system exception
	 */
	public static int getForecastYearCount(final String filterText) throws SystemException {
		final String filter = StringUtils.trimToEmpty(filterText);
		LOGGER.debug("Entering getLazyPriceTypeCount method with filterText : {}", filter);
		List<Object[]> qualifierList;
		final DynamicQuery dynamicQueryForecastYearCount = FileManagementLocalServiceUtil.dynamicQuery();
		if (!filter.equals("")) {
			dynamicQueryForecastYearCount.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_YEAR, Integer.valueOf(filter)));
		}
		dynamicQueryForecastYearCount.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.FORECAST_YEAR));
		dynamicQueryForecastYearCount.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.FORECAST_YEAR));
		qualifierList = DAO.getForecastList(dynamicQueryForecastYearCount);
		foecastYearCount = Integer.parseInt(String.valueOf(qualifierList.get(0)));
		return foecastYearCount;
	}

	/**
	 * Gets the forecast year results.
	 *
	 * @param startIndex
	 *            the start index
	 * @param end
	 *            the end
	 * @param filter
	 *            the filter
	 * @return the forecast year results
	 * @throws PortalException
	 *             the portal exception
	 * @throws SystemException
	 *             the system exception
	 */
	public static List<HelperDTO> getForecastYearResults(final int startIndex, final int end, final String filter)
			throws SystemException {
		final List<HelperDTO> listOfForecastYear = new ArrayList<>();
		final String filterText = StringUtils.trimToEmpty(filter) + "%";
		LOGGER.debug("Entering getLazyPriceTypeResults method with filterText :{}", filterText);
		final DynamicQuery dynamicQueryForForecastYear = ForecastingMasterLocalServiceUtil.dynamicQuery();
		dynamicQueryForForecastYear.setLimit(startIndex, end);
		dynamicQueryForForecastYear.setProjection(
				ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORECAST_YEAR)));

		final List<Object> returnList = DAO.getForecastList(dynamicQueryForForecastYear);
		HelperDTO helperTable;
		if (startIndex == ConstantsUtils.ZERO_INT) {
			helperTable = new HelperDTO();
			helperTable.setDescription(ConstantsUtils.SELECT_ONE);
			listOfForecastYear.add(helperTable);
		}
		for (final Iterator<Object> iterator = returnList.iterator(); iterator.hasNext();) {
			final Object value = iterator.next();
			helperTable = new HelperDTO(value == null ? StringUtils.EMPTY : String.valueOf(value));
			listOfForecastYear.add(helperTable);
		}
		LOGGER.debug("Ending getLazyPriceTypeResults  return list size : {}", listOfForecastYear.size());
		return listOfForecastYear;
	}

	public List<FileMananagementResultDTO> getItemSearchtrsults(final String itemName, final String itemNo)
			throws SystemException {
		List<FileMananagementResultDTO> resultListOfItemResult = new ArrayList<>();
		final DynamicQuery dynamicQueryItemSearch = ItemMasterLocalServiceUtil.dynamicQuery();
		if (itemName.length() > 0) {
			String name = itemName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			dynamicQueryItemSearch.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NAME, name));
		}
		if (itemNo.length() > 0) {
			String number = itemNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			dynamicQueryItemSearch.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO, number));
		}
		List<ItemMaster> list = ItemMasterLocalServiceUtil.dynamicQuery(dynamicQueryItemSearch);
		for (final Iterator<ItemMaster> iterator = list.iterator(); iterator.hasNext();) {
			final ItemMaster itemMasterAtCFF = iterator.next();
			FileMananagementResultDTO dto = new FileMananagementResultDTO();
			dto.setItemName(itemMasterAtCFF.getItemName());
			dto.setItemNo(itemMasterAtCFF.getItemNo());
			resultListOfItemResult.add(dto);
		}
		return resultListOfItemResult;

	}

	public void populateAll(String clickEvent, final BeanItemContainer<FileMananagementResultDTO> detailsBean,
			ExtFilterTable detailsTableInCFF) throws SystemException {
		if (ConstantsUtils.CHECK.equalsIgnoreCase(clickEvent)) {
			final List<FileMananagementResultDTO> itemIdsForPopulateAll;
			itemIdsForPopulateAll = detailsBean.getItemIds();
			for (int i = 0; i < itemIdsForPopulateAll.size(); i++) {
				final FileMananagementResultDTO beanItem = itemIdsForPopulateAll.get(i);
				if (!beanItem.isRecordLockStatus()) {
					detailsTableInCFF.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(BooleanConstant.getTrueFlag());
				}
			}
		} else if ("uncheck".equalsIgnoreCase(clickEvent)) {
			final List<FileMananagementResultDTO> itemIdsOnUncheckItem = detailsBean.getItemIds();
			for (int i = 0; i < itemIdsOnUncheckItem.size(); i++) {
				final FileMananagementResultDTO beanItem = itemIdsOnUncheckItem.get(i);
				if (!beanItem.isRecordLockStatus()) {
					detailsTableInCFF.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(BooleanConstant.getFalseFlag());
				}
			}
		}
	}

	public String saveForecastDetails(List<FileMananagementResultDTO> itemIds, String source, String country,
			String version, String forecastName, String fileType) throws SystemException {
		LOGGER.debug("Entering Save Forecast Details with File Name= {} and File Type= {} and Source= {} and Country= {}", forecastName, fileType.equals(ConstantsUtils.EX_FACTORY_SALES), source, country);
		boolean flag = false;
                String dateString = new Date().toString();
		for (int i = 0; i < itemIds.size(); i++) {

			final FileMananagementResultDTO beanItem = itemIds.get(i);
			if (!beanItem.isRecordLockStatus()) {

				ForecastingMaster forecastMaster;
				DemandForecast demandForecast;
				AdjustedDemandForecast adjDemandForecast;
				InventoryWdProjMas invWdProjMas;
				if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                                    int create = (int) CounterLocalServiceUtil.increment();
					forecastMaster = ForecastingMasterLocalServiceUtil.createForecastingMaster(create);
					flag = true;
					forecastMaster.setForecastYear(beanItem.getYear());
					forecastMaster.setForecastMonth(beanItem.getMonth());
					forecastMaster.setNdc(beanItem.getItemNo());
					forecastMaster.setRecordLockStatus(false);
					Date date = new Date();
					forecastMaster.setForecastDate(beanItem.getStartDate());
					forecastMaster.setUnits(Double.parseDouble(beanItem.getUnits().replace("$", ConstantsUtils.EMPTY)));
					forecastMaster.setPrice(Double.parseDouble(beanItem.getPrice().replace("$", ConstantsUtils.EMPTY)));
					forecastMaster.setDollars(Double.parseDouble(beanItem.getDollars().replace("$", ConstantsUtils.EMPTY)));
					forecastMaster.setSource(source);
					forecastMaster.setCountry(country);
					forecastMaster.setForecastVer(version);
					forecastMaster.setForecastName(forecastName);
					forecastMaster.setCreatedDate(new Date());
					forecastMaster.setModifiedDate(date);
					DAO.addForecastDetails(forecastMaster);
				} else if (fileType.equals(ConstantsUtils.DEMAND)) {
                                        int create = (int) CounterLocalServiceUtil.increment();
					demandForecast = DemandForecastLocalServiceUtil.createDemandForecast(create);
					flag = true;
					demandForecast.setForecastType(beanItem.getForecastType());
					demandForecast.setForecastYear(beanItem.getForcastYear());
					demandForecast.setForecastMonth(beanItem.getForecastMonth());
					demandForecast.setItemId(beanItem.getItemId());
					demandForecast.setItemIdentifierCodeQualifier(beanItem.getItemIdentifierCodeQualifier());
					demandForecast.setItemIdentifier(beanItem.getItemIdentifier());
					demandForecast.setBrandId(beanItem.getBrandId());
					demandForecast.setSegment(beanItem.getSegment());
					demandForecast.setMarketSizeUnits(Double.parseDouble(beanItem.getMarketSizeUnits()));
					demandForecast.setMarketShareUnits(Double.parseDouble(beanItem.getMarketShareUnits()));
					demandForecast.setMarketShareRatio(beanItem.getMarketShareRatio());
					demandForecast.setUncapturedUnits(Double.parseDouble(beanItem.getUncapturedUnits()));
					demandForecast.setUncapturedUnitsRatio(beanItem.getUncapturedUnitsRatio());
					demandForecast.setTotalDemandUnits(Double.parseDouble(beanItem.getTotalDemandUnits()));
					demandForecast.setTotalDemandAmount(Double.parseDouble(beanItem.getTotalDemandAmount()));
					demandForecast.setInventoryUnitChange(Double.parseDouble(beanItem.getInventoryUnitChange()));
					demandForecast.setGrossUnits(Double.parseDouble(beanItem.getGrossUnits()));
					demandForecast.setGrossPrice(Double.parseDouble(beanItem.getGrossPrice()));
					demandForecast.setGrossAmount(Double.parseDouble(beanItem.getGrossAmount()));
					demandForecast.setNetSalesPrice(Double.parseDouble(beanItem.getNetSalesPrice()));
					demandForecast.setBatchId(beanItem.getBatchId());
					demandForecast.setOrganizationKey(beanItem.getOrganizationKey());
					Date date = new Date();
					demandForecast.setSource(source);
					demandForecast.setCountry(country);
					demandForecast.setForecastVer(version);
					demandForecast.setForecastName(forecastName);
					demandForecast.setCreatedDate(new Date());
					demandForecast.setModifiedDate(date);
					DemandForecastLocalServiceUtil.addDemandForecast(demandForecast);
				} else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
					try {
                                                int create =(int) CounterLocalServiceUtil.increment();
						adjDemandForecast = AdjustedDemandForecastLocalServiceUtil.createAdjustedDemandForecast(create);
						flag = true;
						adjDemandForecast.setForecastType(beanItem.getForecastType());
						adjDemandForecast.setYear(beanItem.getForcastYear());
						adjDemandForecast.setMonth(beanItem.getForecastMonth());
						adjDemandForecast.setItemId(beanItem.getItemId());
						adjDemandForecast.setBrandId(beanItem.getBrandId());
						adjDemandForecast.setSegment(beanItem.getSegment());
						adjDemandForecast
								.setMarketSizeUnits(Double.valueOf(beanItem.getMarketSizeUnits() == StringUtils.EMPTY
										? "0" : beanItem.getMarketSizeUnits()));
						adjDemandForecast.setMarketShareUnits(Double.valueOf((beanItem.getMarketShareUnits() == null
								|| beanItem.getMarketShareUnits().trim() == StringUtils.EMPTY) ? "0"
										: beanItem.getMarketShareUnits()));
						adjDemandForecast.setMarketShareRatio(beanItem.getMarketShareRatio() == StringUtils.EMPTY ? "0"
								: beanItem.getMarketShareRatio());
						adjDemandForecast
								.setUncapturedUnits(Double.valueOf(beanItem.getUncapturedUnits() == StringUtils.EMPTY
										? "0" : beanItem.getUncapturedUnits()));
						adjDemandForecast.setUncapturedUnitsRatio(beanItem.getUncapturedUnitsRatio());
						adjDemandForecast.setTotalDemandUnits(
								Double.valueOf(beanItem.getTotalDemandUnits().trim() == StringUtils.EMPTY ? "0"
										: beanItem.getTotalDemandUnits()));
						adjDemandForecast.setTotalDemandAmount(
								Double.valueOf(beanItem.getTotalDemandAmount() == StringUtils.EMPTY ? "0"
										: beanItem.getTotalDemandAmount()));
						adjDemandForecast.setInventoryUnitChange(
								Double.valueOf(beanItem.getInventoryUnitChange() == StringUtils.EMPTY ? "0"
										: beanItem.getInventoryUnitChange()));
						adjDemandForecast.setGrossUnits(Double.valueOf(
								beanItem.getGrossUnits() == StringUtils.EMPTY ? "0" : beanItem.getGrossUnits()));
						adjDemandForecast.setGrossPrice(Double.valueOf(
								beanItem.getGrossPrice() == StringUtils.EMPTY ? "0" : beanItem.getGrossPrice()));
						adjDemandForecast.setGrossAmount(Double.valueOf(
								beanItem.getGrossAmount() == StringUtils.EMPTY ? "0" : beanItem.getGrossAmount()));
						adjDemandForecast.setNetSalesPrice(Double.valueOf(
								beanItem.getNetSalesPrice() == StringUtils.EMPTY ? "0" : beanItem.getNetSalesPrice()));
						adjDemandForecast.setBatchId(beanItem.getBatchId());
						adjDemandForecast.setOrganizationKey(beanItem.getOrganizationKey());
						Date date = new Date();
						adjDemandForecast.setSource(source);
						adjDemandForecast.setCountry(country);
						adjDemandForecast.setForecastVer(version);
						adjDemandForecast.setForecastName(forecastName);
						adjDemandForecast.setCreatedDate(new Date());
						adjDemandForecast.setModifiedDate(date);

						AdjustedDemandForecastLocalServiceUtil.addAdjustedDemandForecast(adjDemandForecast);
					} catch (NumberFormatException e) {
						LOGGER.error(e.getMessage());
					}
				} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
                                        int create = (int) CounterLocalServiceUtil.increment();
					invWdProjMas = InventoryWdProjMasLocalServiceUtil.createInventoryWdProjMas(create);
					flag = true;
					invWdProjMas.setYear(beanItem.getYear());
					invWdProjMas.setMonth(beanItem.getMonth());
					invWdProjMas.setWeek(beanItem.getWeek());
					invWdProjMas.setDay(beanItem.getDay());
					invWdProjMas.setItemId(beanItem.getItemId());
					invWdProjMas.setItemIdentifierCodeQualifier(beanItem.getItemIdentifierCodeQualifier());
					invWdProjMas.setItemIdentifier(beanItem.getItemIdentifier());
					invWdProjMas.setUnitsWithdrawn(beanItem.getUnitsWithdrawn());
					invWdProjMas.setAmountWithdrawn(beanItem.getAmountWithdrawn());
					invWdProjMas.setBatchId(beanItem.getBatchId());
					invWdProjMas.setOrganizationKey(beanItem.getOrganizationKey());
					Date date = new Date();
					invWdProjMas.setSource(source);
					invWdProjMas.setCountry(country);
					invWdProjMas.setForecastVer(version);
					invWdProjMas.setForecastName(forecastName);
					invWdProjMas.setCreatedDate(new Date());
					invWdProjMas.setModifiedDate(date);
					InventoryWdProjMasLocalServiceUtil.addInventoryWdProjMas(invWdProjMas);
				} else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
					String query = insertQueryForInventoryDetails();
					if (beanItem.getYear() == null || "".equals(beanItem.getYear())) {
						query += "0";
					} else {
						query += "'" + beanItem.getYear() + "'";
					}
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getMonth())) ? "," + 0
							: buildQuery(beanItem.getMonth());
					query += buildQuery(beanItem.getDay());
					query += buildQuery(beanItem.getWeek());
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getCompanyId())) ? "," + 0
							: buildQuery(beanItem.getCompanyId());
					query += buildQuery(beanItem.getIdentifierCodeQualifier());
					query += buildQuery(beanItem.getCompanyIdentifier());
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getItemId())) ? "," + 0
							: buildQuery(beanItem.getItemId());
					query += buildQuery(beanItem.getItemIdentifierCodeQualifier());
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getItemIdentifier()))
							? ",'" + 0 + "'" : buildQuery(beanItem.getItemIdentifier());
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getUnitsWithdrawn())) ? "," + 0
							: buildQuery(beanItem.getUnitsWithdrawn());
					query += buildQuery(beanItem.getAmountWithdrawn());
					query += buildQuery(beanItem.getPrice());
					query += ",'" + convertStringToDate(dateString, DEFAULT_JAVA_DATE_FORMAT,
							DEFAULT_SQL_DATE_FORMAT) + "'";
					query += ",'" + convertStringToDate(dateString, DEFAULT_JAVA_DATE_FORMAT,
							DEFAULT_SQL_DATE_FORMAT) + "'";
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getBatchId())) ? ",'" + 0 + "'"
							: buildQuery(beanItem.getBatchId());
					query += ",'" + source + "'";
					query += ",'" + forecastName + "'";
					query += ",'" + version + "'";
					query += ",'" + country + "'";
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getOrganizationKey()))
							? ",'" + 0 + "'" : buildQuery(beanItem.getOrganizationKey());
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getCompanyId())) ? ",'" + 0 + "'"
							: buildQuery(beanItem.getCompanyId());
					query += StringConstantsUtil.A_NULL.equals(buildQuery(beanItem.getItemId())) ? ",'" + 0 + "'"
							: buildQuery(beanItem.getItemId());
					query += ");";
					HelperTableLocalServiceUtil.executeUpdateQuery(query);
					flag = true;

				}
			}

		}
		if (!flag) {
			AbstractNotificationUtils.getErrorNotification("Details Error", "No Difference between source version");
			return "fail";
		}
		return "success";
	}

	private String buildQuery(String value) {
		String query = "";
		if (value == null || "".equals(value)) {
			query += StringConstantsUtil.A_NULL;
		} else {
			query += ",'" + value + "'";
		}
		return query;
	}

	public void updateAutoModeProcess(final Date date) throws SystemException {
		final DynamicQuery dynamicQueryUpdateAuto = ForecastConfigLocalServiceUtil.dynamicQuery();
		List<ForecastConfig> configForAutoUpdate;
		dynamicQueryUpdateAuto.add(RestrictionsFactoryUtil.eq("processType", BooleanConstant.getTrueFlag()));
		configForAutoUpdate = ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQueryUpdateAuto);
		if (!configForAutoUpdate.isEmpty()) {
			for (ForecastConfig forecastConfig : configForAutoUpdate) {
				forecastConfig.setToDate(date);
				ForecastConfigLocalServiceUtil.updateForecastConfig(forecastConfig);
			}
		}
	}

	public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperDtoList) {

		for (int i = 0; i < helperDtoList.size(); i++) {
			final HelperDTO helperDTO = helperDtoList.get(i);
			select.addItem(helperDTO.getId());
			select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
		}
		select.select(0);
		return select;
	}

	/**
	 * Gets the item type.
	 *
	 * @param listType
	 *            the list type
	 * @return the item type
	 */
	public List<HelperDTO> getItemType(final String listType) throws SystemException {

		final List<HelperDTO> helperListOfItemType = new ArrayList<>();

		LOGGER.debug("Entering getItemType P1: {}", listType);
		final DynamicQuery helperDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
		helperDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME, listType));
		helperDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
		final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(helperDynamicQuery);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				final HelperTable helperTable = (HelperTable) list.get(i);
				helperListOfItemType.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

			}
		}
		LOGGER.debug("returns size= {} ", helperListOfItemType.size());

		return helperListOfItemType;
	}

	/**
	 * getting results for CompanyQualifierName
	 *
	 * @param start
	 * @param end
	 * @param filterText
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<HelperDTO> getItemQualifierNameResults() throws SystemException {
		final List<HelperDTO> listOfItemQualifier = new ArrayList<>();
		final DynamicQuery itemQualifierDynamicQuery = ItemQualifierLocalServiceUtil.dynamicQuery();
		final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
		projectionList.add(ProjectionFactoryUtil.property(ITEM_QUALIFIER_SID));
		projectionList.add(ProjectionFactoryUtil.property(ITEM_QUAL_NAME));
		itemQualifierDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
		itemQualifierDynamicQuery.addOrder(OrderFactoryUtil.asc(ITEM_QUAL_NAME));
		itemQualifierDynamicQuery
				.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ITEM_QUAL_NAME, StringUtils.EMPTY)));
		itemQualifierDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ITEM_QUAL_NAME));
		final List<Object[]> qualifierList = DAO.itemIrtQualifierNameList(itemQualifierDynamicQuery);

		HelperDTO dto;
		for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
			final Object[] value = iterator.next();
			dto = new HelperDTO(StringUtils.EMPTY);
			dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
			dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
			if (!StringUtils.EMPTY.equals(dto.getDescription())) {
				listOfItemQualifier.add(dto);
			}
		}
		LOGGER.debug("return CompanyQualifier size= {}", listOfItemQualifier.size());
		return listOfItemQualifier;
	}

	/**
	 * getting results for Brand
	 *
	 * @param start
	 * @param end
	 * @param filterText
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	public List<HelperDTO> getBrandResults() throws SystemException {
		List<Object[]> qualifierList;
		final List<HelperDTO> brandList = new ArrayList<>();

		final DynamicQuery brandDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();
		final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
		projectionList.add(ProjectionFactoryUtil.property("brandMasterSid"));
		projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_NAME));
		brandDynamicQuery.setProjection(projectionList);
		brandDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.BRAND_NAME));
		brandDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.BRAND_NAME));

		qualifierList = DAO.getBrandList(brandDynamicQuery);

		HelperDTO helperDto;
		for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
			final Object[] value = iterator.next();
			helperDto = new HelperDTO(StringUtils.EMPTY);
			helperDto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
			helperDto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
			brandList.add(helperDto);
		}
		LOGGER.debug("return Brand size= {}", brandList.size());
		return brandList;
	}


	/**
	 * * Gets the file history results based on country and fileType.
	 *
	 * @param fileType
	 * @param country
	 * @param startIndex
	 * @param endIndex
	 * @param sortByColumns
	 * @param filterSet
	 * @param isCountFHR
	 * @return
	 */
	public Object getFileHistoryResults(final HelperDTO fileType, final String country, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCountFHR) throws SystemException {

		LOGGER.debug("Entering getFileHistoryResults ");
		DynamicQuery projectionDynamicQuery = null;
		Object object;
		Criterion criterion = null;
		List<FileManagement> resultsList;

		projectionDynamicQuery = FileManagementLocalServiceUtil.dynamicQuery();
		Criterion criteria = RestrictionsFactoryUtil.eq(StringConstantsUtil.FILE_TYPE, fileType.getId());
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())
				&& ConstantsUtils.COUNTRY_US.equals(country)) {
			final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
			criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
		} else if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())
				&& ConstantsUtils.COUNTRY_PR.equals(country)) {
			criterion = RestrictionsFactoryUtil
					.and(RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FF_SALES), criteria);
		} else if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
			final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
			criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
		} else if (ConstantsUtils.DEMAND.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())) {
			criterion = criteria;
		} else {
			criterion = NULLCREATION;
		}
		projectionDynamicQuery.add(criterion);
		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";
					if ("file".equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.FORECAST_NAME, filterString));
					}
					if ("type".equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastSource", filterString));
					}
					if (StringConstantsUtil.VERSION.equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.VERSION, filterString));
					}

				} else if (filter instanceof Compare) {

					Compare compare = (Compare) filter;
					Compare.Operation operation = compare.getOperation();
					if (StringConstantsUtil.VERSION.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
						int value = Integer.parseInt(String.valueOf(compare.getValue()));
						if (Compare.Operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
							projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(StringConstantsUtil.VERSION, value));
						} else if (Compare.Operation.LESS.toString().equalsIgnoreCase(operation.name())) {
							projectionDynamicQuery.add(RestrictionsFactoryUtil.le(StringConstantsUtil.VERSION, value));
						} else if (Compare.Operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
							projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.VERSION, value));
						}
					}
				} else if (filter instanceof Between) {
					Between stringFilter = (Between) filter;
					Date filterString = (Date) stringFilter.getStartValue();
					Date filterString1 = (Date) stringFilter.getEndValue();

					if ("effectiveDate".equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
					}
					if (StringConstantsUtil.FROM_PERIOD.equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ge(StringConstantsUtil.FROM_PERIOD, filterString));
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.lt(StringConstantsUtil.FROM_PERIOD, filterString1));
					}
					if (StringConstantsUtil.TO_PERIOD.equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ge(StringConstantsUtil.TO_PERIOD, filterString));
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.lt(StringConstantsUtil.TO_PERIOD, filterString1));
					}

				}
			}
		}
		loadFMColumnName();
		if (!isCountFHR) {

			boolean sortOrderInFHR = false;
			String columnNameInFHR = null;
			String orderByColumnInFHR = null;

			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnNameInFHR = sortByColumn.getName();
					orderByColumnInFHR = getfileDBColumnName(columnNameInFHR);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrderInFHR = false;
					} else {
						sortOrderInFHR = true;
					}
				}
			}

			if (orderByColumnInFHR == null || StringUtils.EMPTY.equals(orderByColumnInFHR)) {
				final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
				projectionDynamicQuery.addOrder(defaultOrder);
			} else {
				if (sortOrderInFHR) {
					projectionDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumnInFHR));
				} else {
					projectionDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumnInFHR));
				}
			}

		}
		final List<FileMananagementResultDTO> resultsListInFHR = new ArrayList<>();

		if (isCountFHR) {
			object = (Integer) DAO.getFileManagementCount(projectionDynamicQuery);
		} else {
			projectionDynamicQuery.setLimit(startIndex, endIndex);
			resultsList = DAO.getFilesList(projectionDynamicQuery);
			for (final Iterator<FileManagement> iterator = resultsList.iterator(); iterator.hasNext();) {
				final FileManagement fileMgt = iterator.next();
				final FileMananagementResultDTO fileMgtDtoInFHR = new FileMananagementResultDTO();
				fileMgtDtoInFHR.setFile(fileMgt.getForecastName());
				fileMgtDtoInFHR.setEffectiveDate(fileMgt.getCreatedDate());
				fileMgtDtoInFHR.setType(fileMgt.getForecastSource());
				fileMgtDtoInFHR.setVersion(
						fileMgt.getVersion().equals(ConstantsUtils.NULL) ? ConstantsUtils.EMPTY : fileMgt.getVersion());
				fileMgtDtoInFHR.setFromPeriod(fileMgt.getFromPeriod() == null ? null : fileMgt.getFromPeriod());
				fileMgtDtoInFHR.setToPeriod(fileMgt.getToPeriod() == null ? null : fileMgt.getToPeriod());
				fileMgtDtoInFHR.setFileId(String.valueOf(fileMgt.getFileManagementSid()));
				resultsListInFHR.add(fileMgtDtoInFHR);
			}
			object = resultsListInFHR;
		}

		LOGGER.debug("getFileHistoryResults return resultsListDTO= {}", resultsListInFHR.size());
		return object;
	}

	public String getfileDBColumnName(String visibleColumnName) {
		return columnNames.get(visibleColumnName);
	}

	public HashMap<String, String> loadFMColumnName() {
		columnNames.put("file", StringConstantsUtil.FORECAST_NAME);
		columnNames.put("type", "forecastSource");
		columnNames.put(StringConstantsUtil.VERSION, StringConstantsUtil.VERSION);
		columnNames.put("effectiveDate", ConstantsUtils.CREATED_DATE);
		columnNames.put(StringConstantsUtil.FROM_PERIOD, StringConstantsUtil.FROM_PERIOD);
		columnNames.put(StringConstantsUtil.TO_PERIOD, StringConstantsUtil.TO_PERIOD);

		return columnNames;
	}

	/**
	 * To load Results table in File management look up
	 *
	 * @param resultDTO
	 * @param startIndex
	 * @param endIndex
	 * @param sortByColumns
	 * @param filterSet
	 * @param isCount
	 * @return
	 */
	public Object getFileResults(FileMananagementResultDTO resultDTO, final int startIndex, final int endIndex,
			final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
		Object object = new Object();
		try {
			List resultsListInFileRes;
			final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<>();
			String fileName = resultDTO.getFileName();
			String type = resultDTO.getType();
			String version = resultDTO.getVersion();
			String searchForecastYear = resultDTO.getSearchforcastYear();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			String query1 = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-' + Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE, "
					+ " Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,Month(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-' + Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH, ";

			String query2 = "", query = "";

			LOGGER.debug("getResults started with P1:String fileType= {} and P2:String country= {} and P3:String fileName= {} and P4:String type= {} and P5:String version= {} and P6:String forecastYear= {} and P7:Date fromDate= {} and P8: Date toDate= {}", resultDTO.getFileType(), resultDTO.getCountry(), fileName, type, version, resultDTO.getForecastYear(), resultDTO.getFromPeriod(), resultDTO.getToPeriod());
			String condition = "";
			String having = "";
			if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getFileType())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
				query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  FORECASTING_MASTER FM "
						+ " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM   FORECASTING_MASTER "
						+ "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS "
						+ "    JOIN ITEM_MASTER IM ON IM.ITEM_ID = FM.NDC AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 =  GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND  CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + "WHERE ";
				query = query1 + query2;
				condition = "(FM.SOURCE in('FORESIGHT','LE_FORESIGHT'))";
				condition = condition + " AND FM.COUNTRY ='" + resultDTO.getCountry() + "'";
				condition = condition + " AND  FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
			} else if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getHelperType())
					&& ConstantsUtils.COUNTRY_PR.equals(resultDTO.getCountry())) {
				query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  FORECASTING_MASTER FM "
						+ " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM   FORECASTING_MASTER  "
						+ "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS "
						+ "    JOIN ITEM_MASTER IM ON IM.ITEM_ID = FM.NDC AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM  ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + "WHERE ";
				query = query1 + query2;
				condition = "(FM.SOURCE in('FF_SALES'))";
				condition = condition + " AND FM.COUNTRY = '" + resultDTO.getCountry() + "'";
				condition = condition + " AND  FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
			} else if (ConstantsUtils.DEMAND.equals(resultDTO.getFileType())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
				query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  DEMAND_FORECAST FM "
						+ " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM DEMAND_FORECAST "
						+ "  WHERE   FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS"
						+ "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM  ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + " WHERE ";
				query = query1 + query2;
				condition = "FM.COUNTRY ='" + resultDTO.getCountry() + "'";
				condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
			} else if (ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getFileType())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
				query2 = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, Max(Cast(( Cast(fm.YEAR AS VARCHAR(4)) + '-' + Cast(fm.MONTH AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE, "
						+ " Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,Month(Max(Cast(( Cast(fm.YEAR AS VARCHAR(4)) + '-' + Cast(fm.MONTH AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH, "
						+ " Year(Max(Cast(( Cast(fm.YEAR AS VARCHAR(4)) + '-'+ Cast(fm.MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  ADJUSTED_DEMAND_FORECAST FM "
						+ " CROSS APPLY(SELECT Min(Cast(( Cast(YEAR AS VARCHAR(4)) + '-'+ Cast(MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM ADJUSTED_DEMAND_FORECAST "
						+ "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS"
						+ "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER GLC ON  IM.NDC8 = GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID =  GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + " WHERE ";
				query = query2;
				condition = "FM.COUNTRY='" + resultDTO.getCountry() + "'";
				condition = condition + "  AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
			} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getFileType())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
				query = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, \n"
						+ "Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE,  \n"
						+ "Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,\n"
						+ "Month(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH,  \n"
						+ "Year(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR \n"
						+ "FROM  INVENTORY_WD_PROJ_MAS FM  \n"
						+ "CROSS APPLY(SELECT Min(Cast(( Cast(\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(\"MONTH\" AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE \n"
						+ "FROM INVENTORY_WD_PROJ_MAS   \n"
						+ "WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS \n"
						+ "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID =  FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER GLC ON  IM.NDC8 = GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE  AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + " WHERE  ";
				condition = "FM.COUNTRY LIKE '" + resultDTO.getCountry() + "%'";
				condition = condition + " AND FM.BUSINESS_UNIT = '" + resultDTO.getBusinessUnit() + "'";
			} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getFileType())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
				query = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, \n"
						+ "Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE,  \n"
						+ "Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,\n"
						+ "Month(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH,  \n"
						+ "Year(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR \n"
						+ "FROM  INVENTORY_WD_PROJ_DT FM  \n"
						+ "CROSS APPLY(SELECT Min(Cast(( Cast(\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(\"MONTH\" AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE \n"
						+ "FROM INVENTORY_WD_PROJ_DT   \n"
						+ "WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS \n"
						+ "    JOIN ITEM_MASTER  IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER  GLC ON IM.NDC8 = GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + " WHERE    ";
				condition = "FM.COUNTRY LIKE '" + resultDTO.getCountry() + "%'";
				condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
			} else if (ConstantsUtils.CUSTOMERGTS.equals(resultDTO.getFileType())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
				query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  CUSTOMER_GTS_FORECAST FM "
						+ " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM CUSTOMER_GTS_FORECAST "
						+ "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS"
						+ "    JOIN ITEM_MASTER  IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
						+ "    JOIN GL_COST_CENTER_MASTER  GLC ON IM.NDC8 = GLC.NDC8 \n"
						+ "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "
						+ resultDTO.getCompany() + " " + " WHERE    ";
				query = query1 + query2;
				condition = "FM.COUNTRY='" + resultDTO.getCountry() + "'";
				condition = condition + " AND  FM.BUSINESS_UNIT = '" + resultDTO.getBusinessUnit() + "'";
			}

			if (!ConstantsUtils.EMPTY.equals(fileName)) {
				fileName = fileName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
				condition = condition + " AND FM.FORECAST_NAME like '" + fileName + "'";
			}
			if (!ConstantsUtils.EMPTY.equals(type)) {
				type = type.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
				condition = condition + " AND FM.SOURCE like '" + type + "'";
			}
			if (!ConstantsUtils.EMPTY.equals(version)) {
				version = version.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
				condition = condition + " AND FM.FORECAST_VER like '" + version + "'";
			}
			if (!ConstantsUtils.EMPTY.equals(searchForecastYear)) {
				if (resultDTO.getFileType().contains("Adjusted Demand")) {
					condition = condition + " AND fm.YEAR like '" + searchForecastYear + "'";
				} else if (!resultDTO.getFileType().contains("Inventory")) {
					condition = condition + " AND fm.FORECAST_YEAR like '" + searchForecastYear + "'";
				} else {
					condition = condition + " AND \"YEAR\" like '" + searchForecastYear + "'";
				}
			}

			if (resultDTO.getFromPeriod() != null) {
				String from = formatter.format(resultDTO.getFromPeriod());
				condition = condition + " AND FT_MIN_DATE >='" + from + "'";
			}
			if (resultDTO.getToPeriod() != null) {
				String to = formatter.format(resultDTO.getToPeriod());
				if (!ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getFileType())
						&& !ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getFileType())
						&& !ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getFileType())) {
					having = "having Max( Cast(( Cast( fm.FORECAST_YEAR AS VARCHAR( 4 ) ) + '-' + Cast( fm.FORECAST_MONTH AS VARCHAR( 2 ) ) + '-01' ) AS DATE ) ) <='"
							+ to + "'";
				} else {
					having = "having Max( Cast(( Cast( fm.YEAR AS VARCHAR( 4 ) ) + '-' + Cast( fm.MONTH AS VARCHAR( 2 ) ) + '-01' ) AS DATE ) ) <='"
							+ to + "'";
				}
			}

			HashMap<String, String> resultsColumn = new HashMap<>();
			resultsColumn.put(StringConstantsUtil.FILE_TYPE, "FM.SOURCE");
			resultsColumn.put(StringConstantsUtil.COUNTRY, "FM.COUNTRY");
			resultsColumn.put("fileName", "FM.FORECAST_NAME");
			resultsColumn.put("type", "FM.SOURCE");
			resultsColumn.put(StringConstantsUtil.VERSION, "FM.FORECAST_VER");
			resultsColumn.put(StringConstantsUtil.FROM_DATE, "FT_MIN_DATE");
			resultsColumn.put(StringConstantsUtil.TO_DATE, "FT_MAX_DATE");
			resultsColumn.put("businessUnit", "FM.BUSINESS_UNIT");
			String filterHaving = "";
			if (filterSet != null) {
				for (Container.Filter filter : filterSet) {
					if (filter instanceof SimpleStringFilter) {
						SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
						String filterString = "";
						if ((StringConstantsUtil.FROM_DATE.equals(stringFilter.getPropertyId())
								|| StringConstantsUtil.TO_DATE.equals(stringFilter.getPropertyId()))
								&& !stringFilter.equals("")) {
							DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
							Date date = (Date) formatter1.parse(stringFilter.getFilterString());
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.DATE);
							filterString = formatedDate;
						} else {
							filterString = "%" + stringFilter.getFilterString() + "%";
						}
						if (StringConstantsUtil.FROM_DATE.equals(stringFilter.getPropertyId())
								|| StringConstantsUtil.TO_DATE.equals(stringFilter.getPropertyId())) {
							condition = condition + StringConstantsUtil.AND
									+ resultsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " = '"
									+ filterString + "'";
						} else {
							condition = condition + StringConstantsUtil.AND
									+ resultsColumn.get(String.valueOf(stringFilter.getPropertyId()))
									+ StringConstantsUtil.LIKE_QUOTE + filterString + "'";
						}

					} else if (filter instanceof Between) {
						Between stringFilter = (Between) filter;
						Date filterString = (Date) stringFilter.getStartValue();
						Date filterString1 = (Date) stringFilter.getEndValue();
						if (StringConstantsUtil.FROM_DATE.equals(stringFilter.getPropertyId())) {

							condition = condition + " AND FT_MIN_DATE >= '" + formatter.format(filterString) + "' ";
							condition = condition + " AND FT_MIN_DATE <= '" + formatter.format(filterString1) + "' ";
						} else if (StringConstantsUtil.TO_DATE.equals(stringFilter.getPropertyId())) {
							filterHaving = filterHaving + " AND  FT_MAX_DATE >= '" + formatter.format(filterString)
									+ "' ";
							filterHaving = filterHaving + " AND  FT_MAX_DATE <= '" + formatter.format(filterString1)
									+ "' ";
						}
					} else if (filter instanceof Compare) {
						Compare stringFilter = (Compare) filter;
						Compare.Operation operation = stringFilter.getOperation();
						if (stringFilter.getValue() instanceof Date) {
							String filterString = formatter.format(stringFilter.getValue());

							if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
								if (StringConstantsUtil.FROM_DATE.equals(stringFilter.getPropertyId())) {
									condition = condition + " AND FT_MIN_DATE >= '" + filterString + "' ";
								} else if (StringConstantsUtil.TO_DATE.equals(stringFilter.getPropertyId())) {
									filterHaving = filterHaving + " AND  FT_MAX_DATE >= '" + filterString + "' ";
								}

							} else {
								if (StringConstantsUtil.FROM_DATE.equals(stringFilter.getPropertyId())) {
									condition = condition + " AND FT_MIN_DATE <= '" + filterString + "' ";
								} else if (StringConstantsUtil.TO_DATE.equals(stringFilter.getPropertyId())) {
									filterHaving = filterHaving + " AND  FT_MAX_DATE <= '" + filterString + "' ";
								}

							}
						}
					}
				}
			}
			String order = "";
			if (!isCount) {

				boolean sortOrder = false;
				String columnName = null;
				String orderByColumn = null;
				if (sortByColumns != null) {
					for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
						final SortByColumn sortByColumn = (SortByColumn) iterator.next();

						columnName = sortByColumn.getName();
						orderByColumn = resultsColumn.get(columnName);

						if (sortByColumn.getType() == SortByColumn.Type.ASC) {
							sortOrder = false;
						} else {
							sortOrder = true;
						}
					}
				}

				if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
					order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + " FORECAST_NAME ASC";
				} else {
					order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn + ((!sortOrder)
							? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
				}

				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + " ROWS ONLY";

			}
			String groupBy = "";
			if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getFileType())) {
				groupBy = " GROUP  BY FM.FORECAST_NAME, FM.FORECAST_VER,FM.SOURCE,FM.COUNTRY,FM.FORECAST_DATE ";
			} else if (ConstantsUtils.DEMAND.equals(resultDTO.getFileType())
					|| ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getFileType())
					|| ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getFileType())
					|| ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getFileType())
					|| ConstantsUtils.CUSTOMERGTS.equals(resultDTO.getFileType())) {
				groupBy = " GROUP BY FM.FORECAST_NAME, FM.FORECAST_VER,FM.SOURCE,FM.COUNTRY ";
			}

			String finalQuery = query + condition + groupBy + having + order;
			if (!filterHaving.isEmpty()) {
				finalQuery = query + condition + groupBy + having;
				finalQuery = " SELECT FORECAST_NAME,FORECAST_VER,SOURCE,COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE,MAX(FT_MAX_DATE),MIN_YEAR,MAX_MONTH,MAX_YEAR from ("
						+ finalQuery + ") T  WHERE  FORECAST_VER = Floor(T.FORECAST_VER)" + filterHaving
						+ "GROUP BY FORECAST_NAME, FORECAST_VER,SOURCE,COUNTRY,MIN_YEAR,MAX_MONTH,MAX_YEAR " + order;
			}

    			resultsListInFileRes = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);

			if (!isCount) {
				for (int i = 0; i < resultsListInFileRes.size(); i++) {
					final Object[] obj = (Object[]) resultsListInFileRes.get(i);
					final FileMananagementResultDTO fmDTOForFR = new FileMananagementResultDTO();
					fmDTOForFR.setFileName(String.valueOf(obj[0]));
					if (!ConstantsUtils.NULL.equals(String.valueOf(obj[1]))) {
						fmDTOForFR.setVersion(String.valueOf(obj[1]));
					}
					fmDTOForFR.setFileType(String.valueOf(obj[NumericConstants.TWO]));
					fmDTOForFR.setType(String.valueOf(obj[NumericConstants.TWO]));
					fmDTOForFR.setCountry(String.valueOf(obj[NumericConstants.THREE]));
					String from = null;
					if (String.valueOf(obj[NumericConstants.FOUR]) != null
							&& !"".equals(String.valueOf(obj[NumericConstants.FOUR]))) {
						from = dateFormat.format(dateFormatToParse.parse(String.valueOf(obj[NumericConstants.FOUR])));
						fmDTOForFR.setFromDate(dateFormat.parse(from));
					}

					String to = null;
					if (String.valueOf(obj[NumericConstants.FIVE]) != null
							&& !"".equals(String.valueOf(obj[NumericConstants.FIVE]))) {
						to = dateFormat.format(dateFormatToParse.parse(String.valueOf(obj[NumericConstants.FIVE])));
						fmDTOForFR.setToDate(dateFormat.parse(to));
					}
					fmDTOForFR.setAuditVersion(0);
					resultsListDTO.add(fmDTOForFR);
				}
				object = resultsListDTO;
				LOGGER.debug("getResults return List<FileMananagementResultDTO> resultsListDTO= {}", resultsListDTO.size());
			} else {
				object = resultsListInFileRes.size();
				LOGGER.debug("getResults return List<FileMananagementResultDTO> resultsList= {}", resultsListInFileRes.size());
			}

		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
		return object;
	}

	/**
	 * Gets the details results to load details table in File Management Lookup.
	 *
	 * @param detailsResultDTO
	 * @param startIndex
	 * @param endIndex
	 * @param sortByColumns
	 * @param filterSet
	 * @param isCount
	 * @return
	 * @throws SystemException
	 * @throws PortalException
	 * @throws Exception
	 */
	public Object getDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount) throws ParseException {
		Object detailsObj = null;
		List resultsList;
		final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                LOGGER.debug("getDetailsResults started with P1:String fileName= {}; P2:String version= {}; P3:String fileType= {}; P4:String country= {}; P5: StartIndex= {}; P6: EndIndex= {}", 
                        detailsResultDTO.getFileName(), detailsResultDTO.getVersion(), detailsResultDTO.getHelperType(), detailsResultDTO.getCountry(),
			startIndex, endIndex);
		if (detailsResultDTO.getHelperType().equals(ConstantsUtils.DEMAND)) {
			resultsList = (List) getDemandDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, false);
		} else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			resultsList = (List) getAdjustedDemandDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, false);
		} else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.EX_FACTORY_SALES)) {
			resultsList = (List) getForecastDetails(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet,
					isCount, false);
		} else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			resultsList = (List) getInventorySummaryResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, false);
		} else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			resultsList = (List) getInventoryDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, false);
		} else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.CUSTOMERGTS)) {
			resultsList = (List) getCustomerSalesResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, false);
		} else {
			resultsList = null;
		}
		if (!isCount && resultsList != null && detailsResultDTO.getHelperType().equals(ConstantsUtils.EX_FACTORY_SALES)) {
			for (int i = 0; i < resultsList.size(); i++) {
				final Object[] obj = (Object[]) resultsList.get(i);
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setItemNo(String.valueOf(obj[0]));
				fmDTO.setItemName(String.valueOf(obj[1]));
				fmDTO.setYear(String.valueOf(obj[NumericConstants.TWO]));
				fmDTO.setMonth(String.valueOf(obj[NumericConstants.THREE]));
				if (obj[NumericConstants.FOUR] != null) {
					String date = formatter.format((Date) obj[NumericConstants.FOUR]);
					Date sdate = formatter.parse(date);
					fmDTO.setStartDate(sdate);
				}
				fmDTO.setPrice(obj[NumericConstants.FIVE] == null ? " "
						: dollar + PRICE_FORMAT.format(obj[NumericConstants.FIVE]));
				fmDTO.setUnits(obj[NumericConstants.SIX] == null ? " " : unitsFormat.format(obj[NumericConstants.SIX]));
				fmDTO.setHiddenPrice(String.valueOf(obj[NumericConstants.FIVE]));
				fmDTO.setHiddenUnits(String.valueOf(obj[NumericConstants.SIX]));
				fmDTO.setDollars(obj[NumericConstants.SEVEN] == null ? " "
						: dollar + dollarFormat.format(obj[NumericConstants.SEVEN]));
				boolean recordStatus = ((Boolean) obj[NumericConstants.EIGHT]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) obj[NumericConstants.NINE]);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
			LOGGER.debug("getDetailsResults return List<FileMananagementResultDTO> resultsListDTO= {}", resultsListDTO.size());
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().equals(ConstantsUtils.DEMAND)) {
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setForecastType(obj[0] != null ? String.valueOf(obj[0]) : "");
				fmDTO.setForcastYear(obj[1] != null ? String.valueOf(obj[1]) : "");
				fmDTO.setForecastMonth(
						obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
				fmDTO.setItemId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
				fmDTO.setHiddenForecastType(obj[0] != null ? String.valueOf(obj[0]) : "");
				fmDTO.setHiddenForecastYear(obj[1] != null ? String.valueOf(obj[1]) : "");
				fmDTO.setHiddenForecastMonth(
						obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
				fmDTO.setHiddenItemId(
						obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
				fmDTO.setItemIdentifierCodeQualifier(
						obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
				fmDTO.setItemIdentifier(
						obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
				fmDTO.setBrandId(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : "");
				fmDTO.setSegment(
						obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
				fmDTO.setMarketSizeUnits(
						obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
				fmDTO.setMarketShareUnits(
						obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
				fmDTO.setMarketShareRatio(
						obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
				fmDTO.setUncapturedUnits(
						obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
				fmDTO.setUncapturedUnitsRatio(
						obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : "");
				fmDTO.setTotalDemandUnits(
						obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
				fmDTO.setTotalDemandAmount(
						obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
				fmDTO.setInventoryUnitChange(
						obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : "");
				fmDTO.setGrossUnits(
						obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : "");
				fmDTO.setGrossPrice(
						obj[NumericConstants.SEVENTEEN] != null ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : "");
				fmDTO.setGrossAmount(
						obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : "");
				fmDTO.setNetSalesPrice(
						obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN]) : "");
				fmDTO.setNetSalesAmount(
						obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY]) : "");
				fmDTO.setBatchId(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : "");
				fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_SIX] != null
						? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : "");
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_SIX] != null
						? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : "");
				boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SEVEN]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) obj[NumericConstants.TWENTY_EIGHT]);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			for (Object resultsList1 : resultsList) {
				final Object[] objForIWDS = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setYear(objForIWDS[0] != null ? String.valueOf(objForIWDS[0]) : "");
				fmDTO.setHiddenYear(objForIWDS[0] != null ? String.valueOf(objForIWDS[0]) : "");
				fmDTO.setMonth(objForIWDS[1] != null ? String.valueOf(objForIWDS[1]) : "");
				fmDTO.setHiddenMonth(objForIWDS[1] != null ? String.valueOf(objForIWDS[1]) : "");
				fmDTO.setDay(objForIWDS[NumericConstants.TWO] != null ? String.valueOf(objForIWDS[NumericConstants.TWO]) : "");
				fmDTO.setHiddenDay(objForIWDS[NumericConstants.TWO] != null ? String.valueOf(objForIWDS[NumericConstants.TWO]) : "");
				fmDTO.setWeek(objForIWDS[NumericConstants.THREE] != null ? String.valueOf(objForIWDS[NumericConstants.THREE]) : "");
				fmDTO.setHiddenWeek(
						objForIWDS[NumericConstants.THREE] != null ? String.valueOf(objForIWDS[NumericConstants.THREE]) : "");
				fmDTO.setItemId(objForIWDS[NumericConstants.FOUR] != null ? String.valueOf(objForIWDS[NumericConstants.FOUR]) : "");
				fmDTO.setItemIdentifierCodeQualifier(
						objForIWDS[NumericConstants.FIVE] != null ? String.valueOf(objForIWDS[NumericConstants.FIVE]) : "");
				fmDTO.setItemIdentifier(
						objForIWDS[NumericConstants.SIX] != null ? String.valueOf(objForIWDS[NumericConstants.SIX]) : "");
				fmDTO.setUnitsWithdrawn(
						objForIWDS[NumericConstants.SEVEN] != null ? String.valueOf(objForIWDS[NumericConstants.SEVEN]) : "");
				fmDTO.setAmountWithdrawn(
						objForIWDS[NumericConstants.EIGHT] != null ? String.valueOf(objForIWDS[NumericConstants.EIGHT]) : "");
				fmDTO.setPrice(objForIWDS[NumericConstants.NINE] == null ? " "
						: dollar + PRICE_FORMAT.format(objForIWDS[NumericConstants.NINE]));
				fmDTO.setBatchId(objForIWDS[NumericConstants.TEN] != null ? String.valueOf(objForIWDS[NumericConstants.TEN]) : "");
				fmDTO.setHiddenbatchId(
						objForIWDS[NumericConstants.TEN] != null ? String.valueOf(objForIWDS[NumericConstants.TEN]) : "");
				fmDTO.setOrganizationKey(
						objForIWDS[NumericConstants.ELEVEN] != null ? String.valueOf(objForIWDS[NumericConstants.ELEVEN]) : "");
				fmDTO.setHiddenOrganisationKey(
						objForIWDS[NumericConstants.ELEVEN] != null ? String.valueOf(objForIWDS[NumericConstants.ELEVEN]) : "");
				boolean recordStatus = ((Boolean) objForIWDS[NumericConstants.TWELVE]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) objForIWDS[NumericConstants.THIRTEEN]);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			for (Object resultsList1 : resultsList) {
				final Object[] objForIWDD = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setYear(objForIWDD[0] != null ? String.valueOf(objForIWDD[0]) : "");
				fmDTO.setHiddenYear(objForIWDD[0] != null ? String.valueOf(objForIWDD[0]) : "");
				fmDTO.setMonth(objForIWDD[1] != null ? String.valueOf(objForIWDD[1]) : "");
				fmDTO.setHiddenMonth(objForIWDD[1] != null ? String.valueOf(objForIWDD[1]) : "");
				fmDTO.setDay(objForIWDD[NumericConstants.TWO] != null ? String.valueOf(objForIWDD[NumericConstants.TWO]) : "");
				fmDTO.setHiddenDay(objForIWDD[NumericConstants.TWO] != null ? String.valueOf(objForIWDD[NumericConstants.TWO]) : "");
				fmDTO.setWeek(objForIWDD[NumericConstants.THREE] != null ? String.valueOf(objForIWDD[NumericConstants.THREE]) : "");
				fmDTO.setHiddenWeek(
						objForIWDD[NumericConstants.THREE] != null ? String.valueOf(objForIWDD[NumericConstants.THREE]) : "");
				fmDTO.setCompanyId(
						objForIWDD[NumericConstants.FOUR] != null ? String.valueOf(objForIWDD[NumericConstants.FOUR]) : "");
				fmDTO.setHiddenCompanyId(
						objForIWDD[NumericConstants.FOUR] != null ? String.valueOf(objForIWDD[NumericConstants.FOUR]) : "");
				fmDTO.setIdentifierCodeQualifier(
						objForIWDD[NumericConstants.FIVE] != null ? String.valueOf(objForIWDD[NumericConstants.FIVE]) : "");
				fmDTO.setCompanyIdentifier(
						objForIWDD[NumericConstants.SIX] != null ? String.valueOf(objForIWDD[NumericConstants.SIX]) : "");
				fmDTO.setItemId(objForIWDD[NumericConstants.SEVEN] != null ? String.valueOf(objForIWDD[NumericConstants.SEVEN]) : "");
				fmDTO.setHiddenItemId(
						objForIWDD[NumericConstants.SEVEN] != null ? String.valueOf(objForIWDD[NumericConstants.SEVEN]) : "");
				fmDTO.setItemIdentifierCodeQualifier(
						objForIWDD[NumericConstants.EIGHT] != null ? String.valueOf(objForIWDD[NumericConstants.EIGHT]) : "");
				fmDTO.setItemIdentifier(
						objForIWDD[NumericConstants.NINE] != null ? String.valueOf(objForIWDD[NumericConstants.NINE]) : "");
				fmDTO.setUnitsWithdrawn(
						objForIWDD[NumericConstants.TEN] != null ? String.valueOf(objForIWDD[NumericConstants.TEN]) : "");
				fmDTO.setAmountWithdrawn(
						objForIWDD[NumericConstants.ELEVEN] != null ? String.valueOf(objForIWDD[NumericConstants.ELEVEN]) : "");
				fmDTO.setPrice(objForIWDD[NumericConstants.TWELVE] != null
						? dollar + PRICE_FORMAT.format(objForIWDD[NumericConstants.TWELVE]) : " ");
				fmDTO.setBatchId(
						objForIWDD[NumericConstants.THIRTEEN] != null ? String.valueOf(objForIWDD[NumericConstants.THIRTEEN]) : "");
				fmDTO.setHiddenbatchId(
						objForIWDD[NumericConstants.THIRTEEN] != null ? String.valueOf(objForIWDD[NumericConstants.THIRTEEN]) : "");
				fmDTO.setOrganizationKey(
						objForIWDD[NumericConstants.FOURTEEN] != null ? String.valueOf(objForIWDD[NumericConstants.FOURTEEN]) : "");
				fmDTO.setHiddenOrganisationKey(
						objForIWDD[NumericConstants.FOURTEEN] != null ? String.valueOf(objForIWDD[NumericConstants.FOURTEEN]) : "");
				boolean recordStatus = ((Boolean) objForIWDD[NumericConstants.FIFTEEN]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) objForIWDD[NumericConstants.SIXTEEN]);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().equals(ConstantsUtils.CUSTOMERGTS)) {
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setForcastYear(obj[0] != null ? String.valueOf(obj[0]) : "");
				fmDTO.setForecastMonth(obj[1] != null ? String.valueOf(obj[1]) : "");
				fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : "");
				fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : "");
				fmDTO.setItemId(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
				fmDTO.setCompanyId(
						obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
				fmDTO.setUnits(
						obj[NumericConstants.FOUR] != null ? unitsFormat.format(obj[NumericConstants.FOUR]) : "0.0");
				fmDTO.setPriceType(
						obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
				fmDTO.setPrice(obj[NumericConstants.SIX] != null
						? dollar + PRICE_FORMAT.format(obj[NumericConstants.SIX]) : " ");
				fmDTO.setSalesAmount(
						obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
				fmDTO.setSalesInclusion(
						obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
				fmDTO.setDeductionId(
						obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
				fmDTO.setDeductionCategory(
						obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
				fmDTO.setDeductionType(
						obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
				fmDTO.setDeductionProgramType(
						obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : "");
				fmDTO.setAdjustmentCode(
						obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
				fmDTO.setDeductionRate(
						obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
				fmDTO.setDeductionAmount(
						obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : "");
				fmDTO.setDeductionInclusion(
						obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : "");
				fmDTO.setForecastValueType(
						obj[NumericConstants.SEVENTEEN] != null ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : "");
				fmDTO.setBrandId(
						obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : "");
				fmDTO.setSegment(
						obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN]) : "");
				fmDTO.setBatchId(
						obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY]) : "");
				fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : "");
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : "");
				fmDTO.setForecastName(obj[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : "");
				fmDTO.setForecastVersion(obj[NumericConstants.TWENTY_THREE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : "");
				fmDTO.setCountry(obj[NumericConstants.TWENTY_FOUR] != null
						? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : "");
				fmDTO.setForecastDate(obj[NumericConstants.TWENTY_FIVE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : "");
				boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SIX]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId(obj[NumericConstants.TWENTY_SEVEN] != null
						? Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])) : 0);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			for (Object resultsList1 : resultsList) {
				final Object[] objForAdjDemand = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setItemId(objForAdjDemand[0] != null ? String.valueOf(objForAdjDemand[0]) : ConstantsUtils.EMPTY);
				fmDTO.setItemName(objForAdjDemand[1] != null ? String.valueOf(objForAdjDemand[1]) : ConstantsUtils.EMPTY);
				fmDTO.setBrandId(objForAdjDemand[NumericConstants.TWO] != null ? String.valueOf(objForAdjDemand[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setBrandName(objForAdjDemand[NumericConstants.THREE] != null ? String.valueOf(objForAdjDemand[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setSegment(objForAdjDemand[NumericConstants.FOUR] != null ? String.valueOf(objForAdjDemand[NumericConstants.FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setForcastYear(objForAdjDemand[NumericConstants.FIVE] != null ? String.valueOf(objForAdjDemand[NumericConstants.FIVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenYear(objForAdjDemand[NumericConstants.FIVE] != null ? String.valueOf(objForAdjDemand[NumericConstants.FIVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setForecastMonth(objForAdjDemand[NumericConstants.SIX] != null ? String.valueOf(objForAdjDemand[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenMonth(objForAdjDemand[NumericConstants.SIX] != null ? String.valueOf(objForAdjDemand[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setMarketSizeUnits(objForAdjDemand[NumericConstants.SEVEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.SEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setMarketShareRatio(objForAdjDemand[NumericConstants.EIGHT] != null
						? String.valueOf(objForAdjDemand[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
				fmDTO.setMarketShareUnits(objForAdjDemand[NumericConstants.NINE] != null
						? String.valueOf(objForAdjDemand[NumericConstants.NINE]) : ConstantsUtils.EMPTY);
				fmDTO.setUncapturedUnits(objForAdjDemand[NumericConstants.TEN] != null ? String.valueOf(objForAdjDemand[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setUncapturedUnitsRatio(objForAdjDemand[NumericConstants.ELEVEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setTotalDemandUnits(objForAdjDemand[NumericConstants.TWELVE] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWELVE]) : ConstantsUtils.EMPTY);
				fmDTO.setTotalDemandAmount(objForAdjDemand[NumericConstants.THIRTEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setInventoryUnitChange(objForAdjDemand[NumericConstants.FOURTEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossUnits(objForAdjDemand[NumericConstants.FIFTEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.FIFTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossPrice(objForAdjDemand[NumericConstants.SIXTEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.SIXTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossAmount(objForAdjDemand[NumericConstants.SEVENTEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.SEVENTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setNetSalesPrice(objForAdjDemand[NumericConstants.EIGHTEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.EIGHTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setNetSalesAmount(objForAdjDemand[NumericConstants.NINETEEN] != null
						? String.valueOf(objForAdjDemand[NumericConstants.NINETEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setBatchId(objForAdjDemand[NumericConstants.TWENTY] != null ? String.valueOf(objForAdjDemand[NumericConstants.TWENTY])
						: ConstantsUtils.EMPTY);
				fmDTO.setSource(objForAdjDemand[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
				fmDTO.setOrganizationKey(objForAdjDemand[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenOrganisationKey(objForAdjDemand[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastName(objForAdjDemand[NumericConstants.TWENTY_THREE] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWENTY_THREE]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastVersion(objForAdjDemand[NumericConstants.TWENTY_FOUR] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWENTY_FOUR]) : ConstantsUtils.EMPTY);
				fmDTO.setCountry(objForAdjDemand[NumericConstants.TWENTY_FIVE] != null
						? String.valueOf(objForAdjDemand[NumericConstants.TWENTY_FIVE]) : ConstantsUtils.EMPTY);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				boolean recordStatus = ((Boolean) objForAdjDemand[NumericConstants.TWENTY_SIX]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				if (objForAdjDemand[NumericConstants.TWENTY_SEVEN] != null) {
					fmDTO.setForecastSystemId(Integer.parseInt(String.valueOf(objForAdjDemand[NumericConstants.TWENTY_SEVEN])));
				}
				fmDTO.setCheck(Boolean.FALSE);
				resultsListDTO.add(fmDTO);
			}

			detailsObj = resultsListDTO;
		} else {
                    if(resultsList != null )
                    {
                        detailsObj = resultsList.get(0);
                    }
			LOGGER.debug("getDetailsResults return List<FileMananagementResultDTO> detailsObj= {}", detailsObj);
		}
		return detailsObj;
	}

	public Object getForecastDetails(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcel) {
		LOGGER.debug("Entering getForecastDetails ");
		List list;
		String sqlString;
		          if (isCount) {
                sqlString = "SELECT count( * ) \n"
                        + " FROM FORECASTING_MASTER FM, ITEM_MASTER IM WHERE FM.NDC=IM.ITEM_ID AND \n" + "FORECAST_NAME=  ";
            } else if (isExcel) {
                sqlString = SQlUtil.getQuery("getDetailsResultsExcel");
            } else {
                sqlString = SQlUtil.getQuery("getDetailsResults");
            }
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType())
				&& "US".equals(detailsResultDTO.getCountry())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'LE_FORESIGHT')");
		} else if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType())
				&& "PR".equals(detailsResultDTO.getCountry())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_SALES')");
		} else if ("Vaccine Segmentation".equals(detailsResultDTO.getHelperType())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_VACCINE')");
		}
		sqlString = sqlString.concat(" AND FM.COUNTRY =  '").concat(detailsResultDTO.getCountry()).concat("'");

		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" (FM.FORECAST_VER='")
					.concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
		} else {
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" FM.FORECAST_VER='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}

		String filterQuery = "";
                StringBuilder filterQueryBuilder = new StringBuilder();

		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "FORECAST_YEAR");
		detailsColumn.put(StringConstantsUtil.MONTH_PROPERTY, "FORECAST_MONTH");
		detailsColumn.put(StringConstantsUtil.ITEM_NO_PROPERTY, "ITEM_NO");
		detailsColumn.put(StringConstantsUtil.ITEM_NAME_PROPERTY, StringConstantsUtil.ITEM_NAME_LABEL);
		detailsColumn.put(StringConstantsUtil.PRICE_PROPERTY, "PRICE");
		detailsColumn.put(StringConstantsUtil.UNITS_PROPERTY, "UNITS");
		detailsColumn.put("dollars", "DOLLARS");
		detailsColumn.put(StringConstantsUtil.START_DATE_PROPERTY, "FORECAST_DATE");

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQueryBuilder.append(StringConstantsUtil.AND)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');

				} else if (filter instanceof Between) {
					Between stringFilter = (Between) filter;
					SimpleDateFormat formatter = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
					String filterString = formatter.format(stringFilter.getStartValue());
					String filterString1 = formatter.format(stringFilter.getEndValue());
					if (StringConstantsUtil.START_DATE_PROPERTY.equals(stringFilter.getPropertyId())) {
						filterQueryBuilder.append(filterQuery ).append( " AND FORECAST_DATE >= '" ).append( filterString ).append( "' ");
						filterQueryBuilder.append( " AND FORECAST_DATE <= '" ).append( filterString1 ).append( "' ");
					}

				}
			}
		}
                filterQuery = filterQueryBuilder.toString();
		String order = "";
		if (!isCount) {

			boolean sortOrder = false;
			String columnName = null;
			String orderByColumn = null;
			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = detailsColumn.get(columnName);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrder = false;
					} else {
						sortOrder = true;
					}
				}
			}
			if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
				order = order + " ORDER BY FM.FORECAST_YEAR ASC, FM.FORECAST_MASTER_SID ASC";
			} else {
				order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn
						+ ((!sortOrder) ? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
			}

			if (isExcel) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;
			}

		}

		String finalQuery = "";
		if (isCount) {
			finalQuery = sqlString + filterQuery;
		} else {
			finalQuery = sqlString + filterQuery + order;
		}
		if (isExcel) {
			return finalQuery;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("getDetailsResults return List list= {}", list.size());
		return list;
	}

	public Object getItemSearchResults(final ItemSearchDTO itemSearchDTO, final int startIndex, final int endIndex,
			final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount)
			throws SystemException {
		Object itemObject;
		List<ItemSearchDTO> resultList = new ArrayList<>();
		String sql = findItemMaster(itemSearchDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
		List<Object[]> resulList = (List<Object[]>) DAO.executeSelectQuery(sql, null, null);
		if (!isCount) {
			if (resulList != null && !resulList.isEmpty()) {
				for (int i = 0; i < resulList.size(); i++) {
					Object[] obj = resulList.get(i);
					ItemSearchDTO dto = new ItemSearchDTO();
					dto.setSystemId(obj[0] != null ? String.valueOf(obj[0].toString()) : "0");
					dto.setItemName(obj[1] != null ? obj[1].toString() : "");
					dto.setItemNo(obj[NumericConstants.TWO] != null ? obj[NumericConstants.TWO].toString() : "");
					dto.setItemDesc(obj[NumericConstants.THREE] != null ? obj[NumericConstants.THREE].toString() : "");
					dto.setItemType(obj[NumericConstants.FOUR] != null
							&& !obj[NumericConstants.FOUR].toString().equals(ConstantsUtils.SELECTONE)
									? obj[NumericConstants.FOUR].toString() : "");
					dto.setItemStatus(obj[NumericConstants.FIVE] != null
							&& !obj[NumericConstants.FIVE].toString().equals(ConstantsUtils.SELECTONE)
									? obj[NumericConstants.FIVE].toString() : "");
					dto.setIdentifier(obj[NumericConstants.SIX] != null ? obj[NumericConstants.SIX].toString() : "");
					dto.setIdentifierType(
							obj[NumericConstants.ELEVEN] != null ? obj[NumericConstants.ELEVEN].toString() : "");
					dto.setTherapyClass(obj[NumericConstants.SEVEN] != null
							&& !obj[NumericConstants.SEVEN].toString().equals(ConstantsUtils.SELECTONE)
									? obj[NumericConstants.SEVEN].toString() : "");
					dto.setBrand(obj[NumericConstants.EIGHT] != null
							&& !obj[NumericConstants.EIGHT].toString().equals(ConstantsUtils.SELECTONE)
									? obj[NumericConstants.EIGHT].toString() : "");
					dto.setNdc8(obj[NumericConstants.NINE] != null ? obj[NumericConstants.NINE].toString() : "");
					dto.setNdc9(obj[NumericConstants.TEN] != null ? obj[NumericConstants.TEN].toString() : "");
					resultList.add(dto);
				}
			}
			itemObject = resultList;
		} else {
			if (resulList != null && !resulList.isEmpty()) {
				itemObject = resulList.get(0);
			} else {
				itemObject = 0;
			}
		}
		return itemObject;
	}

	public String findItemMaster(ItemSearchDTO itemSearchDTO, final int startIndex, final int endIndex,
			final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
		try {
			String sql = "";
			if (isCount) {
				sql = "select count(*) from Item_Master im, Item_Identifier irt, Brand_Master bm,ITEM_QUALIFIER iq,\n"
						+ "HELPER_TABLE tc1,HELPER_TABLE st,HELPER_TABLE it\n"
						+ " where im.item_Master_Sid = irt.item_Master_Sid and im.BRAND_MASTER_SID = bm.BRAND_MASTER_SID and im.THERAPEUTIC_CLASS=tc1.HELPER_TABLE_SID and im.item_status=st.HELPER_TABLE_SID "
						+ " and im.item_type=it.HELPER_TABLE_SID and irt.item_Qualifier_Sid=iq.item_Qualifier_Sid and bm.brand_name is not null";
			} else {
				sql = "select im.item_Master_Sid,im.item_Name,im.item_No,im.item_Desc,it.description as ItemType,\n"
						+ "st.description as itemStatus,irt.item_Identifier_Value,tc1.description as Therapy,bm.BRAND_NAME,im.ndc8,im.ndc9,iq.ITEM_QUALIFIER_NAME from Item_Master im, Item_Identifier irt, Brand_Master bm,ITEM_QUALIFIER iq,\n"
						+ "HELPER_TABLE tc1,HELPER_TABLE st,HELPER_TABLE it\n"
						+ " where im.item_Master_Sid = irt.item_Master_Sid and im.BRAND_MASTER_SID = bm.BRAND_MASTER_SID and im.THERAPEUTIC_CLASS=tc1.HELPER_TABLE_SID and im.item_status=st.HELPER_TABLE_SID "
						+ " and im.item_type=it.HELPER_TABLE_SID and irt.item_Qualifier_Sid=iq.item_Qualifier_Sid and bm.brand_name is not null";
			}
			if (itemSearchDTO.getSystemId() != null && !"null".equals(String.valueOf(itemSearchDTO.getSystemId()))
					&& !StringUtils.isBlank(itemSearchDTO.getSystemId())) {
				String sysId = itemSearchDTO.getSystemId().replace('*', '%');
				sql += " and im.item_Master_Sid like '" + sysId + "' ";
			}

			if (itemSearchDTO.getItemName() != null && !"null".equals(String.valueOf(itemSearchDTO.getItemName()))
					&& !StringUtils.isBlank(itemSearchDTO.getItemName())) {
				String itemName = itemSearchDTO.getItemName().replace('*', '%');
				sql += " and im.item_Name like '" + itemName + "' ";
			}
			if (itemSearchDTO.getHelperItemType() != null
					&& !"null".equals(String.valueOf(itemSearchDTO.getHelperItemType()))
					&& itemSearchDTO.getHelperItemType().getId() != 0) {
				sql += " and im.item_Type='" + itemSearchDTO.getHelperItemType().getId() + "' ";
			}
			if (itemSearchDTO.getHelperTherapyClass() != null
					&& !"null".equals(String.valueOf(itemSearchDTO.getHelperTherapyClass()))
					&& itemSearchDTO.getHelperTherapyClass().getId() != 0) {
				sql += " and im.THERAPEUTIC_CLASS=" + itemSearchDTO.getHelperTherapyClass().getId() + " ";
			}
			if (itemSearchDTO.getNdc9() != null && !"null".equals(String.valueOf(itemSearchDTO.getNdc9()))
					&& !StringUtils.isBlank(itemSearchDTO.getNdc9())) {
				String ndc9 = itemSearchDTO.getNdc9().replace('*', '%');
				sql += " and im.ndc9 like '" + ndc9 + "' ";
			}
			if (itemSearchDTO.getIdentifierType() != null
					&& !"null".equals(String.valueOf(itemSearchDTO.getIdentifierType()))
					&& !"".equals(String.valueOf(itemSearchDTO.getIdentifierType()))
					&& !"0".equals(itemSearchDTO.getIdentifierType())) {
				sql += " and irt.item_Qualifier_Sid=" + itemSearchDTO.getIdentifierType() + " ";
			}
			if (itemSearchDTO.getItemNo() != null && !"null".equals(String.valueOf(itemSearchDTO.getItemNo()))
					&& !StringUtils.isBlank(itemSearchDTO.getItemNo())) {
				String itemNo = itemSearchDTO.getItemNo().replace('*', '%');
				sql += " and im.item_No like '" + itemNo + "' ";
			}
			if (itemSearchDTO.getItemDesc() != null && !"null".equals(String.valueOf(itemSearchDTO.getItemDesc()))
					&& !StringUtils.isBlank(itemSearchDTO.getItemDesc())) {
				String desc = itemSearchDTO.getItemDesc().replace('*', '%');
				sql += " and im.item_Desc like '" + desc + "' ";
			}
			if (itemSearchDTO.getHelperStatus() != null
					&& !"null".equals(String.valueOf(itemSearchDTO.getHelperStatus()))
					&& itemSearchDTO.getHelperStatus().getId() != 0) {
				sql += " and im.item_Status='" + itemSearchDTO.getHelperStatus().getId() + "' ";
			}
			if (itemSearchDTO.getBrand() != null && !"null".equals(String.valueOf(itemSearchDTO.getBrand()))
					&& !StringUtils.isBlank(itemSearchDTO.getBrand()) && !"0".equals(itemSearchDTO.getBrand())) {
				sql += " and im.brand_Master_Sid = '" + itemSearchDTO.getBrand() + "' ";
			}
			if (itemSearchDTO.getIdentifier() != null && !"null".equals(String.valueOf(itemSearchDTO.getIdentifier()))
					&& !StringUtils.isBlank(itemSearchDTO.getIdentifier())) {
				String identifier = itemSearchDTO.getIdentifier().replace('*', '%');
				sql += " and irt.item_Identifier_Value like '" + identifier + "' ";
			}
			if (itemSearchDTO.getNdc8() != null && !"null".equals(String.valueOf(itemSearchDTO.getNdc8()))
					&& !StringUtils.isBlank(itemSearchDTO.getNdc8())) {
				String ndc8 = itemSearchDTO.getNdc8().replace('*', '%');
				sql += " and im.ndc8 like '" + ndc8 + "' ";
			}
			sql += " and irt.inbound_Status <> '" + "D" + "' ";

			// Query for filter in table
			StringBuilder filterQuery = new StringBuilder();
			HashMap<String, String> itemColumn = new HashMap<>();
			itemColumn.put("systemId", "im.item_Master_Sid");
			itemColumn.put(StringConstantsUtil.ITEM_NO_PROPERTY, "im.item_No");
			itemColumn.put(StringConstantsUtil.ITEM_NAME_PROPERTY, "im.item_Name");
			itemColumn.put("itemDesc", "im.item_Desc");
			itemColumn.put("itemType", "im.item_Type");
			itemColumn.put("itemStatus", "im.item_Status");
			itemColumn.put("therapyClass", "im.THERAPEUTIC_CLASS");
			itemColumn.put(StringConstantsUtil.BRAND_PROPERTY, "im.brand_Master_Sid");
			itemColumn.put("ndc9", "im.ndc9");
			itemColumn.put("ndc8", "im.ndc8");
			itemColumn.put("identifierType", "irt.item_Qualifier_Sid");
			itemColumn.put("identifier", "irt.item_Identifier_Value");

			if (filterSet != null) {
				for (Container.Filter filter : filterSet) {
					if (filter instanceof SimpleStringFilter) {
						SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
						String filterString = "%" + stringFilter.getFilterString() + "%";
						if ((StringConstantsUtil.BRAND_PROPERTY).equals(stringFilter.getPropertyId())
								|| "identifierType".equals(stringFilter.getPropertyId())) {
							if (stringFilter.getFilterString() != null
									&& !"null".equals(stringFilter.getFilterString())) {
								filterQuery.append(StringConstantsUtil.AND)
										.append( itemColumn.get(String.valueOf(stringFilter.getPropertyId())))
										.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');
							}
						} else {
							filterQuery.append(StringConstantsUtil.AND)
									.append( itemColumn.get(String.valueOf(stringFilter.getPropertyId())))
									.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');
						}
					}
				}
			}

			String order = "";
			if (!isCount) {
				boolean sortOrder = false;
				String columnName = null;
				String orderByColumn = null;
				if (sortByColumns != null) {
					for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
						final SortByColumn sortByColumn = (SortByColumn) iterator.next();

						columnName = sortByColumn.getName();
						orderByColumn = itemColumn.get(columnName);

						if (sortByColumn.getType() == SortByColumn.Type.ASC) {
							sortOrder = false;
						} else {
							sortOrder = true;
						}
					}
				}
				if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
					order = order + " ORDER BY im.item_Master_Sid ";
				} else {
					order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn + ((!sortOrder)
							? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
				}

				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;

			}

			sql = sql + filterQuery.toString() + order;

			return sql;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());

			return null;
		}
	}

	public Object getDemandDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcel) {
		LOGGER.debug("Entering Demand Details Results");
		List list;
		String sqlString = "";

		if (isCount) {
			sqlString = "SELECT Count(DF.DEMAND_FORECAST_SID) FROM DEMAND_FORECAST DF WHERE DF.FORECAST_NAME = ";
		} else {
			sqlString = "SELECT DISTINCT DF.FORECAST_TYPE,DF.FORECAST_YEAR,DF.FORECAST_MONTH,"
					+ "DF.ITEM_ID,DF.ITEM_IDENTIFIER_CODE_QUALIFIER,DF.ITEM_IDENTIFIER,"
					+ "DF.BRAND_ID,DF.SEGMENT,DF.MARKET_SIZE_UNITS,DF.MARKET_SHARE_UNITS,"
					+ "DF.MARKET_SHARE_RATIO,DF.UNCAPTURED_UNITS,"
					+ "DF.UNCAPTURED_UNITS_RATIO,DF.TOTAL_DEMAND_UNITS,DF.TOTAL_DEMAND_AMOUNT,"
					+ "DF.INVENTORY_UNIT_CHANGE,DF.GROSS_UNITS,DF.GROSS_PRICE,"
					+ "DF.GROSS_AMOUNT,DF.NET_SALES_PRICE,DF.NET_SALES_AMOUNT,DF.BATCH_ID,DF.SOURCE,"
					+ "DF.FORECAST_NAME,DF.FORECAST_VER,DF.COUNTRY,DF.ORGANIZATION_KEY,DF.RECORD_LOCK_STATUS,DF.DEMAND_FORECAST_SID "
					+ "FROM DEMAND_FORECAST DF WHERE DF.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(" AND DF.COUNTRY = '").concat(detailsResultDTO.getCountry()).concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantsUtil.AND)
					.concat(" DF.FORECAST_VER in ('" + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			int outerSize = Integer.parseInt(innerArray[0]);
			int innerSize;
			for (int i = 1; i <= outerSize; i++) {
				if (Integer.parseInt(versionArray[0]) > outerSize) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = 0; j <= innerSize; j++) {

					if (j == innerSize && j != NumericConstants.NINE) {
						sqlString = sqlString.concat(" '" + i + "." + j + "')");
					} else {
						sqlString = sqlString.concat(" '" + i + "." + j + "',");
					}
				}
			}

		} else {
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" DF.FORECAST_VER='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("forecastType", "DF.FORECAST_TYPE");
		detailsColumn.put(StringConstantsUtil.FORCAST_YEAR_PROPERTY, StringConstantsUtil.DFFORECAST_YEAR);
		detailsColumn.put(StringConstantsUtil.FORECAST_MONTH, StringConstantsUtil.DFFORECAST_MONTH);
		detailsColumn.put(StringConstantsUtil.ITEM_ID, StringConstantsUtil.DFITEM_ID);
		detailsColumn.put(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER, "DF.ITEM_IDENTIFIER_CODE_QUALIFIER");
		detailsColumn.put(StringConstantsUtil.ITEM_IDENTIFIER, StringConstantsUtil.DFITEM_IDENTIFIER);
		detailsColumn.put(StringConstantsUtil.BRAND_ID, StringConstantsUtil.DFBRAND_ID);
		detailsColumn.put(StringConstantsUtil.SEGMENT, StringConstantsUtil.DFSEGMENT);
		detailsColumn.put(StringConstantsUtil.MARKET_SIZE_UNITS, StringConstantsUtil.DFMARKET_SIZE_UNITS);
		detailsColumn.put(StringConstantsUtil.MARKET_SHARE_RATIO, StringConstantsUtil.DFMARKET_SHARE_RATIO);
		detailsColumn.put(StringConstantsUtil.MARKET_SHARE_UNITS_PROPERTY, StringConstantsUtil.DFMARKET_SHARE_UNITS);
		detailsColumn.put(StringConstantsUtil.UNCAPTURED_UNITS, StringConstantsUtil.DFUNCAPTURED_UNITS);
		detailsColumn.put(StringConstantsUtil.UNCAPTURED_UNITS_RATIO, StringConstantsUtil.DFUNCAPTURED_UNITS_RATIO);
		detailsColumn.put(StringConstantsUtil.TOTAL_DEMAND_UNITS, StringConstantsUtil.DFTOTAL_DEMAND_UNITS);
		detailsColumn.put(StringConstantsUtil.TOTAL_DEMAND_AMOUNT, StringConstantsUtil.DFTOTAL_DEMAND_AMOUNT);
		detailsColumn.put(StringConstantsUtil.INVENTORY_UNIT_CHANGE, StringConstantsUtil.DFINVENTORY_UNIT_CHANGE);
		detailsColumn.put(StringConstantsUtil.GROSS_UNITS, StringConstantsUtil.DFGROSS_UNITS);
		detailsColumn.put(StringConstantsUtil.GROSS_PRICE, StringConstantsUtil.DFGROSS_PRICE);
		detailsColumn.put(StringConstantsUtil.GROSS_AMOUNT, StringConstantsUtil.DFGROSS_AMOUNT);
		detailsColumn.put(StringConstantsUtil.NET_SALES_PRICE, StringConstantsUtil.DFNET_SALES_PRICE);
		detailsColumn.put(StringConstantsUtil.NET_SALES_AMOUNT, StringConstantsUtil.DFNET_SALES_AMOUNT);
		detailsColumn.put(StringConstantsUtil.BATCH_ID, StringConstantsUtil.DFBATCH_ID);
		detailsColumn.put(StringConstantsUtil.ORGANIZATION_KEY, StringConstantsUtil.DFORGANIZATION_KEY);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantsUtil.AND)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');

				}
			}
		}
		String finalQuery = "";
		String order = "";
		if (!isCount) {
			String sortOrder = StringConstantsUtil.SPACE_ASC_SPACE;
			String columnName = null;
			String orderByColumn = null;
			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = detailsColumn.get(columnName);

					if (sortByColumn.getType() != SortByColumn.Type.ASC) {
						sortOrder = StringConstantsUtil.SPACE_DESC_SPACE;
					}
				}
			}
			String extraOrder = "";
			String extraSortOrder = StringConstantsUtil.SPACE_ASC_SPACE;
			String extraSortOrderMonth = StringConstantsUtil.SPACE_ASC_SPACE;
			if (orderByColumn != null && !StringUtils.EMPTY.equals(orderByColumn)) {
				if (StringConstantsUtil.DFFORECAST_YEAR.equalsIgnoreCase(orderByColumn)) {
					extraSortOrder = sortOrder;
				} else if (StringConstantsUtil.DFFORECAST_MONTH.equalsIgnoreCase(orderByColumn)) {
					extraSortOrderMonth = sortOrder;
				} else {
					extraOrder = ", " + orderByColumn + sortOrder;
				}
			}
			order += " ORDER BY DF.FORECAST_YEAR  " + extraSortOrder + ",DF.FORECAST_MONTH  " + extraSortOrderMonth
					+ ", DF.DEMAND_FORECAST_SID ASC " + extraOrder;
			if (isExcel) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;
			}
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isExcel) {
			return finalQuery;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending Demand  Details Results");
		return list;
	}

	public Object getInventorySummaryResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcel) {
		LOGGER.debug("Entering getInventorySummaryResults Details Results");
		List list;
		String sqlString = "";

		if (isCount) {
			sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_MAS_SID) FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME =";
		} else {
			sqlString = "SELECT DISTINCT INW.\"YEAR\",INW.\"MONTH\",INW.\"DAY\",INW.WEEK,INW.ITEM_ID,INW.ITEM_IDENTIFIER_CODE_QUALIFIER,INW.ITEM_IDENTIFIER\n"
					+ ",INW.UNITS_WITHDRAWN,INW.AMOUNT_WITHDRAWN,INW.PRICE,INW.BATCH_ID,INW.ORGANIZATION_KEY,INW.RECORD_LOCK_STATUS,INW.INVENTORY_WD_PROJ_MAS_SID FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(" AND INW.COUNTRY LIKE'").concat(detailsResultDTO.getCountry()).concat("%'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			int x = 0, y = 0;
			String[] version2Array;
			if (versionArray[0].contains(".")) {
				String tmpString = versionArray[0].replace('.', '~');
				version2Array = tmpString.split("~");
				y = Integer.parseInt(version2Array[1]);
			} else {
				x = Integer.parseInt(versionArray[0]);
				y = 0;
			}
			sqlString = sqlString.concat(StringConstantsUtil.AND)
					.concat(" INW.FORECAST_VER in ('" + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			int outerSize = Integer.parseInt(innerArray[0]);
			int innerSize;
			for (int i = x; i <= outerSize; i++) {
				if (Integer.parseInt(versionArray[0]) > outerSize) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = y; j <= innerSize; j++) {
					if (j == innerSize && j != NumericConstants.NINE) {
						sqlString = sqlString.concat(" '" + i + "." + j + "')");
					} else {
						sqlString = sqlString.concat(" '" + i + "." + j + "',");
					}
				}
			}
		} else {
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" INW.FORECAST_VER ='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		String finalQuery = "";
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "YEAR");
		detailsColumn.put(StringConstantsUtil.MONTH_PROPERTY, StringConstantsUtil.MONTH_LABEL);
		detailsColumn.put("week", "WEEK");
		detailsColumn.put("day", "DAY");
		detailsColumn.put(StringConstantsUtil.ITEM_ID, StringConstantsUtil.ITEM_ID_COLUMN);
		detailsColumn.put(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER,
				StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_COLUMN);
		detailsColumn.put(StringConstantsUtil.ITEM_IDENTIFIER, ITEM_IDENTIFIER);
		detailsColumn.put(StringConstantsUtil.UNITS_WITHDRAWN, StringConstantsUtil.UNITS_WITHDRAWN_COLUMN);
		detailsColumn.put(StringConstantsUtil.AMOUNT_WITHDRAWN, StringConstantsUtil.AMOUNT_WITHDRAWN_COLUMN);
		detailsColumn.put(StringConstantsUtil.BATCH_ID, StringConstantsUtil.BATCH_ID_COLUMN);
		detailsColumn.put(StringConstantsUtil.ORGANIZATION_KEY, StringConstantsUtil.ORGANIZATION_KEY_COLUMN);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantsUtil.AND)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');

				}
			}
		}
		String order = "";
		if (!isCount) {
			boolean sortOrder = false;
			String columnName = null;
			String orderByColumn = null;
			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = detailsColumn.get(columnName);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrder = false;
					} else {
						sortOrder = true;
					}
				}
			}
			if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
				order = order + " ORDER BY INW.YEAR ASC ,INW.\"MONTH\" ASC ,INW.INVENTORY_WD_PROJ_MAS_SID ASC ";
			} else {
				order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn
						+ ((!sortOrder) ? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
			}
			if (isExcel) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;
			}
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isExcel) {
			return finalQuery;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending  Demand Details Results");
		return list;
	}

	public Object getInventoryDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcel) {
		LOGGER.debug("Entering getInventory Details Results");
		List list;
		String sqlString = "";

		if (isCount) {
			sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_DT_SID) FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME =";
		} else {
			sqlString = "SELECT \n" + "    DISTINCT INW.\"YEAR\",\n" + "    INW.\"MONTH\",\n" + "    INW.\"DAY\",\n"
					+ "    INW.WEEK,\n" + "    INW.COMPANY_ID,\n" + "    INW.IDENTIFIER_CODE_QUALIFIER,\n"
					+ "    INW.COMPANY_IDENTIFIER,\n" + "    INW.ITEM_ID,\n"
					+ "    INW.ITEM_IDENTIFIER_CODE_QUALIFIER,\n" + "    INW.ITEM_IDENTIFIER,\n"
					+ "    INW.UNITS_WITHDRAWN,\n" + "    INW.AMOUNT_WITHDRAWN,\n" + "    INW.PRICE,\n"
					+ "    INW.BATCH_ID,\n" + "    INW.ORGANIZATION_KEY,\n" + "    INW.RECORD_LOCK_STATUS,\n"
					+ "    INW.INVENTORY_WD_PROJ_DT_SID\n" + "FROM\n"
					+ "    INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(" AND INW.COUNTRY LIKE'").concat(detailsResultDTO.getCountry()).concat("%'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			int x = 0, y = 0;
			String[] version2Array;
			if (versionArray[0].contains(".")) {
				String tmpString = versionArray[0].replace('.', '~');
				version2Array = tmpString.split("~");
				y = Integer.parseInt(version2Array[1]);
			} else {
				x = Integer.parseInt(versionArray[0]);
				y = 0;
			}
			sqlString = sqlString.concat(StringConstantsUtil.AND)
					.concat("  INW.FORECAST_VER in ('" + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			int outerSize = Integer.parseInt(innerArray[0]);
			int innerSize;
			for (int i = x; i <= outerSize; i++) {
				if (Integer.parseInt(versionArray[0]) > outerSize) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = y; j <= innerSize; j++) {
					if (j == innerSize && j != NumericConstants.NINE) {
						sqlString = sqlString.concat(" '" + i + "." + j + "')");
					} else {
						sqlString = sqlString.concat(" '" + i + "." + j + "',");
					}
				}
			}
		} else {
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" INW.FORECAST_VER = ' ")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		String finalQuery = "";
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "YEAR");
		detailsColumn.put(StringConstantsUtil.MONTH_PROPERTY, StringConstantsUtil.MONTH_LABEL);
		detailsColumn.put("week", "WEEK");
		detailsColumn.put("day", "DAY");
		detailsColumn.put(StringConstantsUtil.ITEM_ID, StringConstantsUtil.ITEM_ID_COLUMN);
		detailsColumn.put(StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER,
				StringConstantsUtil.ITEM_IDENTIFIER_CODE_QUALIFIER_COLUMN);
		detailsColumn.put(StringConstantsUtil.ITEM_IDENTIFIER, ITEM_IDENTIFIER);
		detailsColumn.put(StringConstantsUtil.UNITS_WITHDRAWN, StringConstantsUtil.UNITS_WITHDRAWN_COLUMN);
		detailsColumn.put(StringConstantsUtil.AMOUNT_WITHDRAWN, StringConstantsUtil.AMOUNT_WITHDRAWN_COLUMN);
		detailsColumn.put(StringConstantsUtil.BATCH_ID, StringConstantsUtil.BATCH_ID_COLUMN);
		detailsColumn.put(StringConstantsUtil.ORGANIZATION_KEY, StringConstantsUtil.ORGANIZATION_KEY_COLUMN);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantsUtil.AND)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');

				}
			}
		}
		String order = "";
		if (!isCount) {
			boolean sortOrder = false;
			String columnName = null;
			String orderByColumn = null;
			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = detailsColumn.get(columnName);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrder = false;
					} else {
						sortOrder = true;
					}
				}
			}
			if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
				order = order + "  ORDER BY INW.YEAR ";
			} else {
				order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn
						+ ((!sortOrder) ? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
			}
			if (isExcel) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;
			}
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isExcel) {
			return finalQuery;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending getInventory Details Results");
		return list;
	}

	private String insertQueryForInventoryDetails() {
            String query = StringUtils.EMPTY;
		 query = query + "INSERT INTO INVENTORY_WD_PROJ_DT (\n" + "YEAR, \n" + "MONTH, \n" + "DAY, \n" + "WEEK, \n"
				+ "COMPANY_ID, \n" + "IDENTIFIER_CODE_QUALIFIER, \n" + "COMPANY_IDENTIFIER, \n" + "ITEM_ID, \n"
				+ "ITEM_IDENTIFIER_CODE_QUALIFIER, \n" + "ITEM_IDENTIFIER,\n" + "UNITS_WITHDRAWN, \n"
				+ "AMOUNT_WITHDRAWN, \n" + "PRICE, \n" + "CREATED_DATE, \n" + "MODIFIED_DATE, \n" + "BATCH_ID, \n"
				+ "\"SOURCE\", \n" + "FORECAST_NAME, \n" + "FORECAST_VER, \n" + "COUNTRY, \n" + "ORGANIZATION_KEY,"
				+ "COMPANY_MASTER_SID," + "ITEM_MASTER_SID)" + "VALUES(";
		return query;
	}

	public static List getInventoryDemandValidationQuery(final List<String> query) {
		List<FileMananagementResultDTO> resultsListDTO = new ArrayList<>();
		String sqlString = "SELECT\n" + "    DISTINCT INW.\"YEAR\",\n" + "    INW.\"MONTH\",\n" + "    INW.\"DAY\",\n"
				+ "    INW.WEEK,\n" + "    INW.COMPANY_ID,\n" + "    INW.IDENTIFIER_CODE_QUALIFIER,\n"
				+ "    INW.COMPANY_IDENTIFIER,\n" + "    INW.ITEM_ID,\n" + "    INW.ITEM_IDENTIFIER_CODE_QUALIFIER,\n"
				+ "    INW.ITEM_IDENTIFIER,\n" + "    INW.UNITS_WITHDRAWN,\n" + "    INW.AMOUNT_WITHDRAWN,\n"
				+ "    INW.PRICE,\n" + "    INW.BATCH_ID,\n" + "    INW.ORGANIZATION_KEY,\n"
				+ "    INW.RECORD_LOCK_STATUS,\n" + "    INW.INVENTORY_WD_PROJ_DT_SID\n" + "     FROM\n"
				+ "    INVENTORY_WD_PROJ_DT INW WHERE INW.SOURCE LIKE '" + query.get(0) + "' AND INW.COUNTRY ='"
				+ query.get(1) + "' AND INW.FORECAST_VER LIKE '" + query.get(NumericConstants.TWO)
				+ "' AND INW.FORECAST_NAME LIKE '" + query.get(NumericConstants.THREE) + "'";
		List result = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		for (Object resultsList1 : result) {
			final Object[] obj = (Object[]) resultsList1;
			final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
			fmDTO.setYear(obj[0] != null ? String.valueOf(obj[0]) : "");
			fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : "");
			fmDTO.setMonth(obj[1] != null ? String.valueOf(obj[1]) : "");
			fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : "");
			fmDTO.setDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
			fmDTO.setHiddenDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
			fmDTO.setWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
			fmDTO.setHiddenWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
			fmDTO.setCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
			fmDTO.setHiddenCompanyId(
					obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
			fmDTO.setIdentifierCodeQualifier(
					obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
			fmDTO.setCompanyIdentifier(
					obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : "");
			fmDTO.setItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
			fmDTO.setHiddenItemId(
					obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
			fmDTO.setItemIdentifierCodeQualifier(
					obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
			fmDTO.setItemIdentifier(
					obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
			fmDTO.setUnitsWithdrawn(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
			fmDTO.setAmountWithdrawn(
					obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
			fmDTO.setPrice(obj[NumericConstants.TWELVE] != null ? "$" + PRICE_FORMAT.format(obj[NumericConstants.TWELVE])
					: " ");
			fmDTO.setBatchId(
					obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
			fmDTO.setHiddenbatchId(
					obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
			fmDTO.setOrganizationKey(
					obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
			fmDTO.setHiddenOrganisationKey(
					obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
			boolean recordStatus = ((Boolean) obj[NumericConstants.FIFTEEN]).booleanValue();
			fmDTO.setRecordLockStatus(recordStatus);
			fmDTO.setForecastSystemId((Integer) obj[NumericConstants.SIXTEEN]);
			fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
			fmDTO.setCheck(BooleanConstant.getFalseFlag());
			resultsListDTO.add(fmDTO);
		}
		return resultsListDTO;
	}

	public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
		try {
			SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
			SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
			Date date;
			date = inputDateFormatter.parse(stringDate);
			return outputDateFormatter.format(date);
		} catch (ParseException ex) {
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	public Object getCustomerSalesResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcel) {
		LOGGER.debug("Entering Demand Details Results");
		List list;
		String sqlString = "";

		if (isCount) {
			sqlString = "SELECT Count(DF.CUSTOMER_GTS_FORECAST_SID) FROM CUSTOMER_GTS_FORECAST DF WHERE DF.FORECAST_NAME = ";
		} else {
			sqlString = "SELECT DISTINCT DF.FORECAST_YEAR,DF.FORECAST_MONTH," + "DF.ITEM_ID,DF.COMPANY_ID,DF.UNITS,"
					+ "DF.PRICE_TYPE,DF.PRICE,DF.SALES_AMOUNT,DF.SALES_INCLUSION,"
					+ "DF.DEDUCTION_ID,DF.DEDUCTION_CATEGORY,DF.DEDUCTION_TYPE,"
					+ "DF.DEDUCTION_PROGRAM_TYPE,DF.ADJUSTMENT_CODE,DF.DEDUCTION_RATE,"
					+ "DF.DEDUCTION_AMOUNT,DF.DEDUCTION_INCLUSION,DF.FORECAST_VALUE_TYPE,"
					+ "DF.BRAND,DF.SEGMENT,DF.BATCH_ID,DF.SOURCE,"
					+ "DF.FORECAST_NAME,DF.FORECAST_VER,DF.COUNTRY,DF.FORECAST_DATE,"
					+ "DF.RECORD_LOCK_STATUS,DF.CUSTOMER_GTS_FORECAST_SID "
					+ "FROM CUSTOMER_GTS_FORECAST DF WHERE DF.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(" AND DF.COUNTRY= '").concat(detailsResultDTO.getCountry()).concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantsUtil.AND)
					.concat(" DF.FORECAST_VER in ('" + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			int outerSize = Integer.parseInt(innerArray[0]);
			int innerSize;
			for (int i = 1; i <= outerSize; i++) {
				if (Integer.parseInt(versionArray[0]) > outerSize) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = 0; j <= innerSize; j++) {

					if (j == innerSize && j != NumericConstants.NINE) {
						sqlString = sqlString.concat(" '" + i + "." + j + "')");
					} else {
						sqlString = sqlString.concat(" '" + i + "." + j + "',");
					}
				}
			}

		} else {
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat("  DF.FORECAST_VER='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put(StringConstantsUtil.FORCAST_YEAR_PROPERTY, StringConstantsUtil.DFFORECAST_YEAR);
		detailsColumn.put(StringConstantsUtil.FORECAST_MONTH, StringConstantsUtil.DFFORECAST_MONTH);
		detailsColumn.put(StringConstantsUtil.ITEM_ID, StringConstantsUtil.DFITEM_ID);
		detailsColumn.put("companyId", "DF.COMPANY_ID");
		detailsColumn.put(StringConstantsUtil.UNITS_PROPERTY, "DF.UNITS");
		detailsColumn.put("priceType", "DF.PRICE_TYPE");
		detailsColumn.put(StringConstantsUtil.PRICE_PROPERTY, "DF.PRICE");
		detailsColumn.put("salesAmount", "DF.SALES_AMOUNT");
		detailsColumn.put("salesInclusion", "DF.SALES_INCLUSION");
		detailsColumn.put("deductionId", "DF.DEDUCTION_ID");
		detailsColumn.put("deductionCategory", "DF.DEDUCTION_CATEGORY");
		detailsColumn.put("deductionType", "DF.DEDUCTION_TYPE");
		detailsColumn.put("deductionProgramType", "DF.DEDUCTION_PROGRAM_TYPE");
		detailsColumn.put("adjustmentCode", "DF.ADJUSTMENT_CODE");
		detailsColumn.put("deductionRate", "DF.DEDUCTION_RATE");
		detailsColumn.put("deductionAmount", "DF.DEDUCTION_AMOUNT");
		detailsColumn.put("deductionInclusion", "DF.DEDUCTION_INCLUSION");
		detailsColumn.put("forecastValueType", "DF.FORECAST_VALUE_TYPE");
		detailsColumn.put(StringConstantsUtil.BRAND_PROPERTY, "DF.BRAND");
		detailsColumn.put(StringConstantsUtil.SEGMENT, StringConstantsUtil.DFSEGMENT);
		detailsColumn.put(StringConstantsUtil.BATCH_ID, StringConstantsUtil.DFBATCH_ID);
		detailsColumn.put(StringConstantsUtil.ORGANIZATION_KEY, StringConstantsUtil.DFSOURCE);
		detailsColumn.put(StringConstantsUtil.FORECAST_VERSION, "DF.FORECAST_VER");
		detailsColumn.put(StringConstantsUtil.COUNTRY, "DF.COUNTRY");
		detailsColumn.put(StringConstantsUtil.FORECAST_NAME, "DF.FORECAST_NAME");
		detailsColumn.put("forecastDate", "DF.FORECAST_DATE");

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantsUtil.AND)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');

				}
			}
		}
		String finalQuery = "";
		String order = "";
		if (!isCount) {
			boolean sortOrder = false;
			String columnName = null;
			String orderByColumn = null;
			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = detailsColumn.get(columnName);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrder = false;
					} else {
						sortOrder = true;
					}
				}
			}
			if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
				order = order + " ORDER BY DF.FORECAST_YEAR , DF.CUSTOMER_GTS_FORECAST_SID ASC ";
			} else {
				order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn
						+ ((!sortOrder) ? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
			}
			if (isExcel) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;
			}
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isExcel) {
			return finalQuery;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending Demand Details Results= {}", list.size());
		return list;
	}

	public List getFileCount(String fileName, String fileType) {

		String query = "SELECT top 1 FM.FROM_PERIOD,FM.TO_PERIOD FROM FILE_MANAGEMENT FM \n"
				+ "JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FM.FILE_TYPE       \n" + "WHERE FM.FORECAST_NAME='"
				+ fileName + "' AND  HT.[DESCRIPTION] ='" + fileType
				+ "' AND HT.LIST_NAME = 'FILE_TYPE' ORDER BY VERSION DESC";

		return HelperTableLocalServiceUtil.executeSelectQuery(query);
	}

	public Object getAdjustedDemandDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcel) {
		LOGGER.debug("Entering getAdjustedDemandDetailsResults Details Results");
		List list;
		String sqlString;

		if (isCount) {
			sqlString = "SELECT Count(DF.ADJUSTED_DEMAND_FORECAST_SID) ";
		} else {
			sqlString = "SELECT DISTINCT DF.ITEM_ID,IM.ITEM_NAME ,\n" + "DF.BRAND_ID,BM.BRAND_NAME ,\n"
					+ "DF.SEGMENT,DF.YEAR,DF.MONTH,DF.MARKET_SIZE_UNITS,DF.MARKET_SHARE_RATIO,DF.MARKET_SHARE_UNITS,\n"
					+ "DF.UNCAPTURED_UNITS,DF.UNCAPTURED_UNITS_RATIO,DF.TOTAL_DEMAND_UNITS,\n"
					+ "DF.TOTAL_DEMAND_AMOUNT,DF.INVENTORY_UNIT_CHANGE,DF.GROSS_UNITS,DF.GROSS_PRICE,\n"
					+ "DF.GROSS_AMOUNT,DF.NET_SALES_PRICE,DF.NET_SALES_AMOUNT,DF.BATCH_ID,DF.SOURCE,DF.ORGANIZATION_KEY,\n"
					+ "DF.FORECAST_NAME,DF.FORECAST_VER,DF.COUNTRY,DF.RECORD_LOCK_STATUS , DF.ADJUSTED_DEMAND_FORECAST_SID\n";
		}

		sqlString += " FROM ADJUSTED_DEMAND_FORECAST  DF \n"
				+ " LEFT JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = DF.ITEM_MASTER_SID\n"
				+ " LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID \n"
				+ " AND IM.BRAND_MASTER_SID = DF.BRAND_MASTER_SID WHERE DF.FORECAST_NAME = '"
				+ detailsResultDTO.getFileName() + "' \n" + " AND  DF.COUNTRY='" + detailsResultDTO.getCountry() + "' ";

		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantsUtil.AND)
					.concat(" DF.FORECAST_VER  in ('" + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			int outerSize = Integer.parseInt(innerArray[0]);
			int innerSize;
			for (int i = 1; i <= outerSize; i++) {
				if (Integer.parseInt(versionArray[0]) > outerSize) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = 0; j <= innerSize; j++) {

					if (j == innerSize && j != NumericConstants.NINE) {
						sqlString = sqlString.concat(" '" + i + "." + j + "')");
					} else {
						sqlString = sqlString.concat(" '" + i + "." + j + "',");
					}
				}
			}

		} else {
			sqlString = sqlString.concat(StringConstantsUtil.AND).concat("  DF.FORECAST_VER='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();

		detailsColumn.put(StringConstantsUtil.FORCAST_YEAR_PROPERTY, "DF.YEAR");
		detailsColumn.put(StringConstantsUtil.FORECAST_MONTH, "DF.MONTH");
		detailsColumn.put(StringConstantsUtil.ITEM_ID, StringConstantsUtil.DFITEM_ID);
		detailsColumn.put(StringConstantsUtil.ITEM_NAME_PROPERTY, "IM.ITEM_NAME");

		detailsColumn.put(StringConstantsUtil.SOURCE_PROPERTY, StringConstantsUtil.DFSOURCE);
		detailsColumn.put(StringConstantsUtil.BRAND_ID, StringConstantsUtil.DFBRAND_ID);
		detailsColumn.put("brandName", "BM.BRAND_NAME");
		detailsColumn.put(StringConstantsUtil.SEGMENT, StringConstantsUtil.DFSEGMENT);
		detailsColumn.put(StringConstantsUtil.MARKET_SIZE_UNITS, StringConstantsUtil.DFMARKET_SIZE_UNITS);
		detailsColumn.put(StringConstantsUtil.MARKET_SHARE_RATIO, StringConstantsUtil.DFMARKET_SHARE_RATIO);
		detailsColumn.put(StringConstantsUtil.MARKET_SHARE_UNITS_PROPERTY, StringConstantsUtil.DFMARKET_SHARE_UNITS);
		detailsColumn.put(StringConstantsUtil.UNCAPTURED_UNITS, StringConstantsUtil.DFUNCAPTURED_UNITS);
		detailsColumn.put(StringConstantsUtil.UNCAPTURED_UNITS_RATIO, StringConstantsUtil.DFUNCAPTURED_UNITS_RATIO);
		detailsColumn.put(StringConstantsUtil.TOTAL_DEMAND_UNITS, StringConstantsUtil.DFTOTAL_DEMAND_UNITS);
		detailsColumn.put(StringConstantsUtil.TOTAL_DEMAND_AMOUNT, StringConstantsUtil.DFTOTAL_DEMAND_AMOUNT);
		detailsColumn.put(StringConstantsUtil.INVENTORY_UNIT_CHANGE, StringConstantsUtil.DFINVENTORY_UNIT_CHANGE);
		detailsColumn.put(StringConstantsUtil.GROSS_UNITS, StringConstantsUtil.DFGROSS_UNITS);
		detailsColumn.put(StringConstantsUtil.GROSS_PRICE, StringConstantsUtil.DFGROSS_PRICE);
		detailsColumn.put(StringConstantsUtil.GROSS_AMOUNT, StringConstantsUtil.DFGROSS_AMOUNT);
		detailsColumn.put(StringConstantsUtil.NET_SALES_PRICE, StringConstantsUtil.DFNET_SALES_PRICE);
		detailsColumn.put(StringConstantsUtil.NET_SALES_AMOUNT, StringConstantsUtil.DFNET_SALES_AMOUNT);
		detailsColumn.put(StringConstantsUtil.BATCH_ID, StringConstantsUtil.DFBATCH_ID);
		detailsColumn.put(StringConstantsUtil.ORGANIZATION_KEY, StringConstantsUtil.DFORGANIZATION_KEY);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantsUtil.AND)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantsUtil.LIKE_QUOTE ).append( filterString ).append( '\'');

				}

			}
		}
		String finalQuery;
		String order = ConstantsUtils.EMPTY;
		if (!isCount) {
			boolean sortOrder = false;
			String columnName = null;
			String orderByColumn = null;
			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = detailsColumn.get(columnName);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrder = false;
					} else {
						sortOrder = true;
					}
				}
			}
			if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
				order = order + " ORDER BY DF.YEAR ";
			} else {
				order = order + StringConstantsUtil.SPACE_ORDER_BY_SPACE + orderByColumn
						+ ((!sortOrder) ? StringConstantsUtil.SPACE_ASC_SPACE : StringConstantsUtil.SPACE_DESC_SPACE);
			}
			if (isExcel) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantsUtil.OFFSET;
				order = order + startIndex;
				order = order + StringConstantsUtil.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantsUtil.ROWS_ONLY;
			}
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isExcel) {
			return finalQuery;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending Demand Details Results");
		return list;
	}

	public String getBusinessUnit() {
		String projId = String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"));
		String query = "Select BUSINESS_UNIT FROM dbo.CFF_MASTER where CFF_MASTER_SID =" + projId;
		List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
		return list == null || list.isEmpty() ? "" : String.valueOf(list.get(0));
	}

	private String appendOffsetPlaceHolder(String query) {
		String offsetQuery = query + " " + StringConstantsUtil.OFFSET;
		offsetQuery = offsetQuery + "?";
		offsetQuery = offsetQuery + StringConstantsUtil.ROWS_FETCH_NEXT + "?";
		offsetQuery = offsetQuery + StringConstantsUtil.ROWS_ONLY;
		return offsetQuery;
	}
}
