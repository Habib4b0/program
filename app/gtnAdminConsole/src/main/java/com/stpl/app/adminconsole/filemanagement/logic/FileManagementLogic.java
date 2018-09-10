package com.stpl.app.adminconsole.filemanagement.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.FileManagementLogicDAO;
import com.stpl.app.adminconsole.dao.impl.FileManagementLogicDAOImpl;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementDTO;
import com.stpl.app.adminconsole.filemanagement.dto.FileMananagementResultDTO;
import com.stpl.app.adminconsole.filemanagement.dto.ItemSearchDTO;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.FileManagement;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.CustomerGtsForecastLocalServiceUtil;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.FileManagementLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.model.CustomerGtsForecast;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.ComboBox;

 
/**
 * The Class FileManagementLogic.
 *
 * @author santanukumar
 */
public class FileManagementLogic {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementLogic.class);

	private static final Criterion NULLCREATION = null;

	private static final FileManagementLogicDAO DAO = new FileManagementLogicDAOImpl();

	private static int foecastYearCount;

	public static final String ITEM_QUAL_NAME = "itemQualifierName";

	public static final  String ITEM_QUALIFIER_SID = "itemQualifierSid";

	public static final String COMPANY_NAME = "companyName";
	public static final String SELECT_ONE = "-Select One-";

	private HashMap<String, String> columnNames = new HashMap<>();

	public int getFoecastYearCount() {
		return foecastYearCount;
	}

	public static void setFoecastYearCount(final int itemPricingQualifierNameCount) {
		FileManagementLogic.foecastYearCount = itemPricingQualifierNameCount;
	}

	public FileManagementLogicDAO getDao() {
		return DAO;
	}

	public static final String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";

	public static final String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat javaDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        
        

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
	public FileManagementDTO getDetailsSumm(final String fileName, final String version, final HelperDTO fileType,
			final String country) throws SystemException {
		List<ForecastingMaster> resultsList;
		final FileManagementDTO fileMgtDTO = new FileManagementDTO();
		final DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

		Criterion criterion;
		String sqlString;
		LOGGER.debug("getDetailsSumm started with P1:String fileName= {}, P2:String version= {}, P3:String fileType= {}, P4:String country= {} " , fileName, version
				, fileType, country);
		final DynamicQuery dynamicQuery = ForecastingMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORECAST_NAME, fileName));
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
			if (ConstantsUtils.COUNTRY_US.equals(country)) {
				final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE,
						ConstantsUtils.FORE_SIGHT);
				criterion = RestrictionsFactoryUtil.or(criterion1,
						RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.LE_FORESIGHT));
			} else if (ConstantsUtils.COUNTRY_PR.equals(country)) {
				criterion = RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.FF_SALES);
			} else {
				criterion = NULLCREATION;
			}
			dynamicQuery.add(criterion);
			dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country));
			// Fix to resolve GTN-1003
			dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastVer", version));
			resultsList = DAO.getForecastList(dynamicQuery);
			if (!resultsList.isEmpty()) {
				final ForecastingMaster fmMaster = (ForecastingMaster) resultsList.get(0);
				fileMgtDTO.setForecastName(fmMaster.getForecastName());
				fileMgtDTO.setForecastVersion(fmMaster.getForecastVer());
				if (fmMaster.getForecastDate() != null) {
					fileMgtDTO.setForecastDate(dateFormat.format(fmMaster.getForecastDate()));
				}
				if (fmMaster.getCreatedDate() != null) {
					fileMgtDTO.setCreatedDate(dateFormat.format(fmMaster.getCreatedDate()));
				}
			}
		} else if (ConstantsUtils.DEMAND.equals(fileType.getDescription())) {
			sqlString = "SELECT DF.FORECAST_NAME,DF.FORECAST_VER,DF.CREATED_DATE FROM DEMAND_FORECAST DF WHERE FORECAST_NAME like '"
					+ fileName + StringConstantUtils.AND_COUNTRY_LIKE + country
					+ StringConstantUtils.AND_FORECAST_VER_LIKE + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription())) {
			sqlString = "SELECT INW.FORECAST_NAME,INW.FORECAST_VER,INW.CREATED_DATE FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME like '"
					+ fileName + StringConstantUtils.AND_INW_COUNTRY_LIKE + country + "' AND INW.FORECAST_VER like '"
					+ version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())) {
			sqlString = "SELECT INW.FORECAST_NAME,INW.FORECAST_VER,INW.CREATED_DATE FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME like '"
					+ fileName + StringConstantUtils.AND_INW_COUNTRY_LIKE + country + "%' AND INW.FORECAST_VER like '"
					+ version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.CUSTOMERGTS.equals(fileType.getDescription())) {
			sqlString = "SELECT DF.FORECAST_NAME,DF.FORECAST_VER,DF.CREATED_DATE FROM CUSTOMER_GTS_FORECAST DF WHERE FORECAST_NAME like '"
					+ fileName + StringConstantUtils.AND_COUNTRY_LIKE + country
					+ StringConstantUtils.AND_FORECAST_VER_LIKE + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
				}
			}
		} else if (ConstantsUtils.ADJUSTED_DEMAND.equals(fileType.getDescription())) {
			sqlString = "SELECT FORECAST_NAME, FORECAST_VER, CREATED_DATE FROM dbo.ADJUSTED_DEMAND_FORECAST WHERE FORECAST_NAME like '"
					+ fileName + StringConstantUtils.AND_COUNTRY_LIKE + country
					+ StringConstantUtils.AND_FORECAST_VER_LIKE + version + "'";
			List<Object[]> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
			if (!resultsLists.isEmpty()) {
				for (Object[] resultsList1 : resultsLists) {
					final Object[] obj = resultsList1;
					fileMgtDTO.setForecastName(String.valueOf(obj[0]));
					fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
					fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
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
		LOGGER.debug("saveFileMgtHist started with P1:FileMananagementResultDTO fileMgtDTO and P2:String fileType= {}",
				fileType);
		FileManagement fileManagement = FileManagementLocalServiceUtil.createFileManagement(0);
		fileManagement.setForecastName(fileMgtDTO.getFileName());
		fileManagement.setForecastSource(fileMgtDTO.getType());
		fileManagement.setFileSource(fileMgtDTO.getFileType());
		fileManagement.setVersion(fileMgtDTO.getVersion());
		fileManagement.setFromPeriod(new Date());
		fileManagement.setCreatedDate(new Date());
		fileManagement.setModifiedDate(new Date());
		fileManagement.setCreatedBy(Integer.parseInt(userId));
		fileManagement.setFileType(fileType.getId());
		fileManagement.setBusinessUnit(String.valueOf(fileMgtDTO.getBusinessUnitSysId()));
		fileManagement.setCompany(fileMgtDTO.getCompanyMasterSystemId());
		List<FileManagement> resultsList;
		final DynamicQuery dynamicQuery = FileManagementLocalServiceUtil.dynamicQuery();
		final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
		dynamicQuery.addOrder(defaultOrder);
		Criterion criterion;
		final Criterion criteria = RestrictionsFactoryUtil.eq(StringConstantUtils.FILE_TYPE, fileType.getId());
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
			final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
			final Criterion criterion2 = RestrictionsFactoryUtil.or(criterion1,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT));
			criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion2,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FF_SALES)), criteria);
			dynamicQuery.add(criterion);
		} else {
			dynamicQuery.add(criteria);
		}
		if ((fileMgtDTO.getBusinessUnitSysId()) != 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantUtils.BUSINESS_UNIT,
					String.valueOf(fileMgtDTO.getBusinessUnitSysId())));
		}

		if (fileMgtDTO.getCompanyMasterSystemId() != 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.eq(StringConstantUtils.COMPANY, fileMgtDTO.getCompanyMasterSystemId()));
		}

		resultsList = DAO.getFilesList(dynamicQuery);
		if (!resultsList.isEmpty()) {
			final FileManagement currentActiveFile = resultsList.get(0);
			currentActiveFile.setToPeriod(new Date());
			currentActiveFile.setModifiedDate(new Date());
			currentActiveFile.setModifiedBy(Integer.parseInt(userId));
			currentActiveFile.setFileType(fileType.getId());
			currentActiveFile.setVersionNo(currentActiveFile.getVersionNo() + 1);
			DAO.updateFiles(currentActiveFile);
		}
		DAO.addFiles(fileManagement);
		final String msg = ConstantsUtils.SUCCESS;

		LOGGER.debug("saveFileMgtHist return String msg= {}" , msg);
		return msg;
	}

	/**
	 * Gets the current file info.
	 *
	 * @param fileType
	 *            the file type
	 * @param businessUnit
	 * @param companyMasterSystemId
	 * @return the current file info
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public FileManagementDTO getCurrentFileInfo(final HelperDTO fileType, String businessUnit,
			Object companyMasterSystemId) throws SystemException {

		if(fileType==null) {
			return null;
		}
		final FileManagementDTO fileMgtDTO = new FileManagementDTO();
		LOGGER.debug("getCurrentFileInfo started with P1:String fileType= {}, Business Unit= {}" , fileType, businessUnit);
		List<FileManagement> resultsList;
		final DynamicQuery dynamicQuery = FileManagementLocalServiceUtil.dynamicQuery();
		final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
		dynamicQuery.addOrder(defaultOrder);
		Criterion criterion;

		Criterion criteria = RestrictionsFactoryUtil.eq(StringConstantUtils.FILE_TYPE, fileType.getId());
		if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
			final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
			criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1,
					RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
		} else if (ConstantsUtils.DEMAND.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription())
				|| ConstantsUtils.ADJUSTED_DEMAND.equals(fileType.getDescription())
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())) {
			criterion = criteria;
		} else if (ConstantsUtils.CUSTOMERGTS.equals(fileType.getDescription())) {
			criterion = criteria;
		} else {
			criterion = NULLCREATION;
		}
		dynamicQuery.add(criterion);
		if (businessUnit != null && !businessUnit.isEmpty() && !businessUnit.equals("null")) {

			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantUtils.BUSINESS_UNIT, businessUnit));
		}

		if (companyMasterSystemId != null && (Integer) companyMasterSystemId != 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantUtils.COMPANY, companyMasterSystemId));
		}

		resultsList = DAO.getFilesList(dynamicQuery);
		if (!resultsList.isEmpty()) {
			final FileManagement fileMgt = (FileManagement) resultsList.get(0);
			final DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
			fileMgtDTO.setCurrentFile(fileMgt.getForecastName());
			fileMgtDTO.setForecastVersion(fileMgt.getVersion());
			fileMgtDTO.setEffectiveDate(fileMgt.getCreatedDate() == null ? ConstantsUtils.EMPTY
					: dateFormat.format(fileMgt.getCreatedDate()));
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
		LOGGER.debug("Entering getLazyPriceTypeCount method with filterText= {}" , filter);
		List<Object[]> qualifierList;
		final DynamicQuery dynamicQuery = ForecastingMasterLocalServiceUtil.dynamicQuery();
		if (!filter.equals(ConstantsUtils.EMPTY)) {
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_YEAR, Integer.valueOf(filter)));
		}
		dynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.FORECAST_YEAR));
		dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.FORECAST_YEAR));

		qualifierList = DAO.getForecastList(dynamicQuery);

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
		final List<HelperDTO> list = new ArrayList<>();
		final String filterText = StringUtils.trimToEmpty(filter) + "%";
		LOGGER.debug("Entering getLazyPriceTypeResults method with filterText= {}" , filterText);
		final DynamicQuery dynamicQuery = ForecastingMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.setLimit(startIndex, end);
		dynamicQuery.setProjection(
				ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORECAST_YEAR)));
		final List<Object> returnList = DAO.getForecastList(dynamicQuery);
		HelperDTO helperTable;
		if (startIndex == ConstantsUtils.ZERO_INT) {
			helperTable = new HelperDTO();
			helperTable.setDescription(ConstantsUtils.SELECT_ONE);
			list.add(helperTable);
		}
		for (final Iterator<Object> iterator = returnList.iterator(); iterator.hasNext();) {
			final Object value = iterator.next();
			helperTable = new HelperDTO(value == null ? StringUtils.EMPTY : String.valueOf(value));
			list.add(helperTable);
		}
		LOGGER.debug("Ending getLazyPriceTypeResults  return list size= {}" , list.size());
		return list;
	}

	public List<FileMananagementResultDTO> getItemSearchtrsults(final String itemName, final String itemNo)
			throws SystemException {
		List<FileMananagementResultDTO> resultList = new ArrayList<>();
		final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
		if (itemName.length() > 0) {
			String name = itemName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NAME, name));
		}
		if (itemNo.length() > 0) {
			String number = itemNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_NO, number));
		}
		List<ItemMaster> list = ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
		for (final Iterator<ItemMaster> iterator = list.iterator(); iterator.hasNext();) {
			final ItemMaster itemMaster = iterator.next();
			FileMananagementResultDTO dto = new FileMananagementResultDTO();
			dto.setItemName(itemMaster.getItemName());
			dto.setItemNo(itemMaster.getItemNo());
			resultList.add(dto);
		}
		return resultList;

	}

	public void populateAll(String clickEvent, final BeanItemContainer<FileMananagementResultDTO> detailsBean,
			ExtFilterTable detailsTable) throws PortalException {
		if (ConstantsUtils.CHECK.equalsIgnoreCase(clickEvent)) {
			final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
			for (int i = 0; i < itemIds.size(); i++) {
				final FileMananagementResultDTO beanItem = itemIds.get(i);
				if (!beanItem.isRecordLockStatus()) {
					detailsTable.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(BooleanConstant.getTrueFlag());
				}
			}
		} else if ("uncheck".equalsIgnoreCase(clickEvent)) {
			final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
			for (int i = 0; i < itemIds.size(); i++) {
				final FileMananagementResultDTO beanItem = itemIds.get(i);
				if (!beanItem.isRecordLockStatus()) {
					detailsTable.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(BooleanConstant.getFalseFlag());
				}
			}
		}
	}

	public String getLatestVersion(String country, String forecastName, HelperDTO fileType, String version,
			String selectedFile) throws SystemException {
		String latestVersion = ConstantsUtils.EMPTY;
		String forecastVersion = version + "%";
		List<String> versions = new ArrayList<>();
		List<String> versionList = new ArrayList<>();
		final DynamicQuery dynamicQuery = ForecastingMasterLocalServiceUtil.dynamicQuery();
		if (fileType.getDescription().equals(ConstantsUtils.EX_FACTORY_SALES)) {
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, selectedFile));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country));
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_VER, forecastVersion));
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.FORECAST_NAME, forecastName));
			dynamicQuery.setProjection(
					ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORECAST_VER)));
			versions = ForecastingMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
		} else if (fileType.getDescription().equals(ConstantsUtils.DEMAND)) {
			String sqlString = "SELECT DISTINCT DF.FORECAST_VER FROM DEMAND_FORECAST DF WHERE FORECAST_NAME like '"
					+ forecastName + StringConstantUtils.AND_COUNTRY_LIKE + country
					+ StringConstantUtils.AND_FORECAST_VER_LIKE + forecastVersion + "'";
			versions = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		} else if (fileType.getDescription().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			String sqlString = "SELECT DISTINCT DF.FORECAST_VER FROM ADJUSTED_DEMAND_FORECAST DF WHERE FORECAST_NAME like '"
					+ forecastName + StringConstantUtils.AND_COUNTRY_LIKE + country
					+ StringConstantUtils.AND_FORECAST_VER_LIKE + forecastVersion + "'";
			versions = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		} else if (fileType.getDescription().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			String sqlString = "SELECT DISTINCT INW.FORECAST_VER FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME like '"
					+ forecastName + StringConstantUtils.AND_INW_COUNTRY_LIKE + country
					+ "' AND INW.FORECAST_VER like '" + forecastVersion + "'";
			versions = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		} else if (fileType.getDescription().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			String sqlString = "SELECT DISTINCT INW.FORECAST_VER FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME like '"
					+ forecastName + StringConstantUtils.AND_INW_COUNTRY_LIKE + country
					+ "%' AND INW.FORECAST_VER like '" + forecastVersion + "'";
			versions = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		} else if (fileType.getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {
			String sqlString = "SELECT DISTINCT DF.FORECAST_VER FROM CUSTOMER_GTS_FORECAST DF WHERE FORECAST_NAME like '"
					+ forecastName + StringConstantUtils.AND_COUNTRY_LIKE + country
					+ StringConstantUtils.AND_FORECAST_VER_LIKE + forecastVersion + "'";
			versions = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		}
		versionList.addAll(versions);
		if (!versionList.isEmpty()) {
			List<Integer> tmpList = new ArrayList<>();
			String etlVer = ConstantsUtils.EMPTY;
			for (int i = 0; i < versionList.size(); i++) {
				String ver = versionList.get(i);
				if (ver.contains(".")) {
					String[] array = ver.split("\\.");
					int vers = Integer.parseInt(String.valueOf(array[1]));
					etlVer = String.valueOf(array[0]);
					tmpList.add(vers);
				}
			}
			if (!tmpList.isEmpty()) {
				Collections.sort(tmpList);
				int listSize = tmpList.size();
				int minorVersion = tmpList.get(listSize - 1);
				latestVersion = etlVer + "." + minorVersion;
			} else {
				Collections.sort(versionList);
				int listSize = versionList.size();
				latestVersion = versionList.get(listSize - 1);
			}

		}
		return latestVersion;
	}

	public String saveForecastDetails(List<FileMananagementResultDTO> itemIds, String source, String country,
			String version, String forecastName, HelperDTO fileType, String businessUnit) {
		LOGGER.debug("Entering Save Forecast Details with File Name forecastName= {}, File Type= {}, Country={}, source={} " , forecastName, 
				fileType.getDescription().equals(ConstantsUtils.EX_FACTORY_SALES), source, country);
		boolean flag = false;
		String item;
		String totalDemand;
		String inventoryUnit;
		String netSalesPrice;
		String totalDemandadj;
		String grossAmount;
		String netSalesPriceadj;
                String dateString = new Date().toString();
		try {
			for (int i = 0; i < itemIds.size(); i++) {

				final FileMananagementResultDTO beanItem = itemIds.get(i);
				if (!beanItem.isRecordLockStatus()) {

					ForecastingMaster master;
					DemandForecast forecast;
					AdjustedDemandForecast adjustedforecast;
					CustomerGtsForecast custForecast;
					InventoryWdProjMas inventoryWdProjMas;
					if (fileType.getDescription().equals(ConstantsUtils.EX_FACTORY_SALES)) {
						master = ForecastingMasterLocalServiceUtil.createForecastingMaster(0);
						flag = true;

						master.setForecastYear(beanItem.getYear());
						master.setForecastMonth(beanItem.getMonth());

						master.setNdc(beanItem.getItemNo());
						master.setRecordLockStatus(false);
						Date date = new Date();

						master.setForecastDate(beanItem.getStartDate());
						master.setUnits(
								Double.parseDouble(beanItem.getUnits().replace("$", ConstantsUtils.EMPTY)));
						master.setPrice(
								Double.parseDouble(beanItem.getPrice().replace("$", ConstantsUtils.EMPTY)));
						master.setDollars(
								Double.parseDouble(beanItem.getDollars().replace("$", ConstantsUtils.EMPTY)));
						master.setSource(source);
						master.setCountry(country);
						master.setBusinessUnit(Integer.parseInt(businessUnit));
						master.setForecastVer(version);
						master.setForecastName(forecastName);
						master.setCreatedDate(new Date());
						master.setModifiedDate(date);
						DAO.addForecastDetails(master);
					} else if (fileType.getDescription().equals(ConstantsUtils.DEMAND)) {
						forecast = DemandForecastLocalServiceUtil.createDemandForecast(0);
						flag = true;
						forecast.setForecastType(beanItem.getForecastType());
						forecast.setForecastYear(beanItem.getForcastYear());
						forecast.setForecastMonth(beanItem.getForecastMonth());
						forecast.setItemId(beanItem.getItemId());
						forecast.setItemIdentifierCodeQualifier(beanItem.getItemIdentifierCodeQualifier());
						forecast.setItemIdentifier(beanItem.getItemIdentifier());
						forecast.setBrandId(beanItem.getBrandId());
						forecast.setSegment(beanItem.getSegment());
						forecast.setMarketSizeUnits(Double.valueOf(StringUtils.isBlank(beanItem.getMarketSizeUnits())
								? "0" : beanItem.getMarketSizeUnits()));
						forecast.setMarketShareUnits(Double.valueOf(StringUtils.isBlank(beanItem.getMarketShareUnits())
								? "0" : beanItem.getMarketShareUnits()));
						forecast.setMarketShareRatio(StringUtils.isBlank(beanItem.getMarketShareRatio()) ? "0"
								: beanItem.getMarketShareRatio());
						forecast.setUncapturedUnits(Double.valueOf(StringUtils.isBlank(beanItem.getUncapturedUnits())
								? "0" : beanItem.getUncapturedUnits()));
						forecast.setUncapturedUnitsRatio(StringUtils.isBlank(beanItem.getUncapturedUnitsRatio()) ? "0"
								: beanItem.getUncapturedUnitsRatio());
						forecast.setTotalDemandUnits(Double.valueOf(StringUtils.isBlank(beanItem.getTotalDemandUnits())
								? "0" : beanItem.getTotalDemandUnits()));
						if (beanItem.getTotalDemandAmount().contains("$")) {
							totalDemand = beanItem.getTotalDemandAmount().replace("$", "");
						} else {
							totalDemand = beanItem.getTotalDemandAmount();
						}
						forecast.setTotalDemandAmount(
								Double.valueOf(StringUtils.isNotBlank(totalDemand) ? totalDemand : "0"));
						if (beanItem.getInventoryUnitChange().contains("$")) {
							inventoryUnit = beanItem.getInventoryUnitChange().replace("$", "");
						} else {
							inventoryUnit = beanItem.getInventoryUnitChange();
						}
						forecast.setInventoryUnitChange(
								Double.valueOf(StringUtils.isNotBlank(inventoryUnit) ? inventoryUnit : "0"));
						forecast.setGrossUnits(Double.valueOf(
								StringUtils.isNotBlank(beanItem.getGrossUnits()) ? beanItem.getGrossUnits() : "0"));
						forecast.setGrossPrice(Double.valueOf(
								StringUtils.isNotBlank(beanItem.getGrossPrice()) ? beanItem.getGrossPrice() : "0"));
						if (beanItem.getGrossAmount().contains("$")) {
							item = beanItem.getGrossAmount().replace("$", "");
						} else {
							item = beanItem.getGrossAmount();
						}
						forecast.setGrossAmount(Double.valueOf(StringUtils.isNotBlank(item) ? item : "0"));
						if (beanItem.getNetSalesPrice().contains("$")) {
							netSalesPrice = beanItem.getNetSalesPrice().replace("$", "");
						} else {
							netSalesPrice = beanItem.getNetSalesPrice();
						}
						forecast.setNetSalesPrice(
								Double.valueOf(StringUtils.isNotBlank(netSalesPrice) ? netSalesPrice : "0"));
						forecast.setBatchId(beanItem.getBatchId());
						forecast.setOrganizationKey(beanItem.getOrganizationKey());
						Date date = new Date();
						forecast.setSource(source);
						forecast.setCountry(country);
						forecast.setForecastVer(version);
						forecast.setForecastName(forecastName);
						forecast.setCreatedDate(new Date());
						forecast.setModifiedDate(date);

						DemandForecastLocalServiceUtil.addDemandForecast(forecast);
					} else if (fileType.getDescription().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
						try {
							adjustedforecast = AdjustedDemandForecastLocalServiceUtil.createAdjustedDemandForecast(0);
							flag = true;
							adjustedforecast.setForecastType(beanItem.getForecastType());
							adjustedforecast.setYear(beanItem.getForcastYear());
							adjustedforecast.setMonth(beanItem.getForecastMonth());
							adjustedforecast.setItemId(beanItem.getItemId());
							adjustedforecast.setItemMasterSid(beanItem.getItemMasterSid());
							adjustedforecast.setBrandId(beanItem.getBrandId());
							adjustedforecast.setSegment(beanItem.getSegment());
							adjustedforecast.setMarketSizeUnits(
									Double.valueOf(StringUtils.isBlank(beanItem.getMarketSizeUnits()) ? "0"
											: beanItem.getMarketSizeUnits()));
							adjustedforecast.setMarketShareUnits(Double.valueOf((beanItem.getMarketShareUnits() == null
									|| beanItem.getMarketShareUnits().trim().equals(StringUtils.EMPTY)) ? "0"
											: beanItem.getMarketShareUnits()));
							adjustedforecast.setMarketShareRatio(StringUtils.isBlank(beanItem.getMarketShareRatio())
									? "0" : beanItem.getMarketShareRatio());
							adjustedforecast.setUncapturedUnits(
									Double.valueOf(StringUtils.isBlank(beanItem.getUncapturedUnits()) ? "0"
											: beanItem.getUncapturedUnits()));
							adjustedforecast.setUncapturedUnitsRatio(beanItem.getUncapturedUnitsRatio());
							adjustedforecast.setTotalDemandUnits(
									Double.valueOf(beanItem.getTotalDemandUnits().trim() == StringUtils.EMPTY ? "0"
											: beanItem.getTotalDemandUnits()));
							if (beanItem.getTotalDemandAmount().contains("$")) {
								totalDemandadj = beanItem.getTotalDemandAmount().replace("$", "");
							} else {
								totalDemandadj = beanItem.getTotalDemandAmount();
							}
							adjustedforecast.setTotalDemandAmount(
									Double.valueOf(StringUtils.isNotBlank(totalDemandadj) ? totalDemandadj : "0"));
							adjustedforecast.setInventoryUnitChange(
									Double.valueOf(StringUtils.isNotBlank(beanItem.getInventoryUnitChange())
											? beanItem.getInventoryUnitChange() : "0"));
							adjustedforecast.setGrossUnits(Double.valueOf(
									StringUtils.isNotBlank(beanItem.getGrossUnits()) ? beanItem.getGrossUnits() : "0"));
							adjustedforecast.setGrossPrice(Double.valueOf(
									StringUtils.isNotBlank(beanItem.getGrossPrice()) ? beanItem.getGrossPrice() : "0"));
							if (beanItem.getGrossAmount().contains("$")) {
								grossAmount = beanItem.getGrossAmount().replace("$", "");
							} else {
								grossAmount = beanItem.getGrossAmount();
							}
							adjustedforecast.setGrossAmount(
									Double.valueOf(StringUtils.isNotBlank(grossAmount) ? grossAmount : "0"));
							if (beanItem.getNetSalesPrice().contains("$")) {
								netSalesPriceadj = beanItem.getNetSalesPrice().replace("$", "");
							} else {
								netSalesPriceadj = beanItem.getNetSalesPrice();
							}
							adjustedforecast.setNetSalesPrice(
									Double.valueOf(StringUtils.isNotBlank(netSalesPriceadj) ? netSalesPriceadj : "0"));
							adjustedforecast.setBatchId(beanItem.getBatchId());
							adjustedforecast.setOrganizationKey(beanItem.getOrganizationKey());
							Date date = new Date();
							adjustedforecast.setSource(source);
							adjustedforecast.setCountry(country);
							adjustedforecast.setForecastVer(version);
							adjustedforecast.setForecastName(forecastName);
							adjustedforecast.setCreatedDate(new Date());
							adjustedforecast.setModifiedDate(date);

							AdjustedDemandForecastLocalServiceUtil.addAdjustedDemandForecast(adjustedforecast);
						} catch (Exception e) {
							LOGGER.error(e.getMessage());
						}
					} else if (fileType.getDescription().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
						inventoryWdProjMas = InventoryWdProjMasLocalServiceUtil.createInventoryWdProjMas(0);
						flag = true;
						inventoryWdProjMas.setYear(beanItem.getYear());
						inventoryWdProjMas.setMonth(beanItem.getMonth());
						inventoryWdProjMas.setWeek(beanItem.getWeek());
						inventoryWdProjMas.setDay(beanItem.getDay());
						inventoryWdProjMas.setItemId(beanItem.getItemId());
						inventoryWdProjMas.setItemIdentifierCodeQualifier(beanItem.getItemIdentifierCodeQualifier());
						inventoryWdProjMas.setItemIdentifier(beanItem.getItemIdentifier());
						inventoryWdProjMas.setUnitsWithdrawn(beanItem.getUnitsWithdrawn());
						inventoryWdProjMas.setAmountWithdrawn(beanItem.getAmountWithdrawn());
						inventoryWdProjMas.setBatchId(beanItem.getBatchId());
						inventoryWdProjMas.setOrganizationKey(beanItem.getOrganizationKey());
						Date date = new Date();
						inventoryWdProjMas.setSource(source);
						inventoryWdProjMas.setCountry(country);
						inventoryWdProjMas.setForecastVer(version);
						inventoryWdProjMas.setForecastName(forecastName);
						inventoryWdProjMas.setCreatedDate(new Date());
						inventoryWdProjMas.setModifiedDate(date);
						InventoryWdProjMasLocalServiceUtil.addInventoryWdProjMas(inventoryWdProjMas);
					} else if (fileType.getDescription().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
						String query = insertQueryForInventoryDetails();
                                               
						if (beanItem.getYear() == null || ConstantsUtils.EMPTY.equals(beanItem.getYear())) {
							query += "0";
						} else {
							query += "'" + beanItem.getYear() + "'";
						}

						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getMonth())) ? "," + 0
								: buildQuery(beanItem.getMonth());
						query += buildQuery(beanItem.getDay());
						query += buildQuery(beanItem.getWeek());
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getCompanyId())) ? "," + 0
								: buildQuery(beanItem.getCompanyId());
						query += buildQuery(beanItem.getIdentifierCodeQualifier());
						query += buildQuery(beanItem.getCompanyIdentifier());
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getItemId())) ? "," + 0
								: buildQuery(beanItem.getItemId());
						query += buildQuery(beanItem.getItemIdentifierCodeQualifier());
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getItemIdentifier()))
								? ",'" + 0 + "'" : buildQuery(beanItem.getItemIdentifier());
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getUnitsWithdrawn())) ? "," + 0
								: buildQuery(beanItem.getUnitsWithdrawn());
						query += buildQuery(beanItem.getAmountWithdrawn());
						query += buildQuery(beanItem.getPrice());
						query += ",'" + convertStringToDate(dateString, DEFAULT_JAVA_DATE_FORMAT,
								DEFAULT_SQL_DATE_FORMAT) + "'";
						query += ",'" + convertStringToDate(dateString, DEFAULT_JAVA_DATE_FORMAT,
								DEFAULT_SQL_DATE_FORMAT) + "'";
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getBatchId())) ? ",'" + 0 + "'"
								: buildQuery(beanItem.getBatchId());
						query += ",'" + source + "'";
						query += ",'" + forecastName + "'";
						query += ",'" + version + "'";
						query += ",'" + country + "'";
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getOrganizationKey()))
								? ",'" + 0 + "'" : buildQuery(beanItem.getOrganizationKey());
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getCompanyId())) ? ",'" + 0 + "'"
								: buildQuery(beanItem.getCompanyId());
						query += StringConstantUtils.A_NULL.equals(buildQuery(beanItem.getItemId())) ? ",'" + 0 + "'"
								: buildQuery(beanItem.getItemId());
						query += ");";
						HelperTableLocalServiceUtil.executeUpdateQuery(query);
						flag = true;

					} else if (fileType.getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {
						try {
							custForecast = CustomerGtsForecastLocalServiceUtil.createCustomerGtsForecast(0);
							flag = true;
							custForecast.setForecastYear(beanItem.getForcastYear());
							custForecast.setForecastMonth(beanItem.getForecastMonth());
							custForecast.setItemId(beanItem.getItemId());
							custForecast.setItemMasterSid(1);
							custForecast.setCompanyIdString(beanItem.getCompanyId());
							custForecast.setCompanyMasterSid(1);
							custForecast.setUnits(beanItem.getUnits());
							custForecast.setPriceType(beanItem.getPriceType());
							custForecast.setPrice(beanItem.getPrice());
							custForecast.setSalesAmount(beanItem.getSalesAmount());
							custForecast.setSalesInclusion(beanItem.getSalesInclusion());
							custForecast.setDeductionId(beanItem.getDeductionId());
							custForecast.setDeductionCategory(beanItem.getDeductionCategory());
							custForecast.setDeductionType(beanItem.getDeductionType());
							custForecast.setDeductionProgramType(beanItem.getDeductionProgramType());
							custForecast.setAdjustmentCode(beanItem.getAdjustmentCode());
							custForecast.setDeductionRate(beanItem.getDeductionRate());
							custForecast.setDeductionAmount(beanItem.getDeductionAmount());
							custForecast.setDeductionInclusion(beanItem.getDeductionInclusion());
							custForecast.setForecastValueType(beanItem.getForecastValueType());
							custForecast.setBrand(beanItem.getBrandId());
							custForecast.setBrandMasterSid(NumericConstants.FOUR);
							custForecast.setBatchId(beanItem.getBatchId());
							custForecast.setSegment(beanItem.getSegment());
							Date date = new Date();
							custForecast.setSource(beanItem.getSource());
							custForecast.setCountry(beanItem.getCountry());
							custForecast.setForecastVer(beanItem.getForecastVersion());
							custForecast.setForecastName(beanItem.getForecastName());
							custForecast.setAddChgDelIndicator("A");
							custForecast.setCreatedBy("13330");
							custForecast.setCreatedDate(new Date());
							custForecast.setModifiedBy("13330");
							custForecast.setModifiedDate(date);
							custForecast.setRecordLockStatus(true);

							CustomerGtsForecastLocalServiceUtil.addCustomerGtsForecast(custForecast);
						} catch (Exception e) {
							LOGGER.error(e.getMessage());
						}
					}
				}

			}
			if (!flag) {
				AbstractNotificationUtils.getErrorNotification("Details Error", "No Difference between source version");
				return "fail";
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return "success";
	}

	private String buildQuery(String value) {
		String query = ConstantsUtils.EMPTY;
		if (value == null || ConstantsUtils.EMPTY.equals(value)) {
			query += StringConstantUtils.A_NULL;
		} else {
			query += ",'" + value + "'";
		}
		return query;
	}

	public void updateAutoModeProcess(final Date date) throws SystemException {
		final DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
		List<ForecastConfig> config;
		dynamicQuery.add(RestrictionsFactoryUtil.eq("processType", BooleanConstant.getTrueFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.isNull("activeEndDate"));
		config = ForecastConfigLocalServiceUtil.dynamicQuery(dynamicQuery);
		if (!config.isEmpty()) {
			for (ForecastConfig forecastConfig : config) {
				forecastConfig.setToDate(date);
				ForecastConfigLocalServiceUtil.updateForecastConfig(forecastConfig);
			}
		}
	}

	public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {

		for (int i = 0; i < helperList.size(); i++) {
			final HelperDTO helperDTO = helperList.get(i);
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

		final List<HelperDTO> helperList = new ArrayList<>();

		LOGGER.debug("Entering getItemType P1= {}" , listType);
		final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
		cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME, listType));
		cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
		final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(cfpDynamicQuery);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				final HelperTable helperTable = (HelperTable) list.get(i);
				helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

			}
		}
		LOGGER.debug("returns size= {}" , helperList.size());

		return helperList;
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
	public List<HelperDTO> getItemQualifierNameResults() throws PortalException {
		final List<HelperDTO> list = new ArrayList<>();
		final DynamicQuery ifpDynamicQuery = ItemQualifierLocalServiceUtil.dynamicQuery();
		final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
		projectionList.add(ProjectionFactoryUtil.property(ITEM_QUALIFIER_SID));
		projectionList.add(ProjectionFactoryUtil.property(ITEM_QUAL_NAME));
		ifpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
		ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ITEM_QUAL_NAME));
		ifpDynamicQuery
				.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ITEM_QUAL_NAME, StringUtils.EMPTY)));
		ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ITEM_QUAL_NAME));
		final List<Object[]> qualifierList = DAO.itemIrtQualifierNameList(ifpDynamicQuery);

		HelperDTO dto;
		for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
			final Object[] value = iterator.next();
			dto = new HelperDTO(StringUtils.EMPTY);
			dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
			dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
			if (!StringUtils.EMPTY.equals(dto.getDescription())) {
				list.add(dto);
			}
		}
		LOGGER.debug("return CompanyQualifier size= {}" , list.size());
		return list;
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
	public List<HelperDTO> getBrandResults() throws PortalException {
		List<Object[]> qualifierList;
		final List<HelperDTO> list = new ArrayList<>();

		final DynamicQuery ifpDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();
		final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
		projectionList.add(ProjectionFactoryUtil.property("brandMasterSid"));
		projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.BRAND_NAME));
		ifpDynamicQuery.setProjection(projectionList);
		ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.BRAND_NAME));
		ifpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.BRAND_NAME));

		qualifierList = DAO.getBrandList(ifpDynamicQuery);

		HelperDTO dto;
		for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
			final Object[] value = iterator.next();
			dto = new HelperDTO(StringUtils.EMPTY);
			dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
			dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
			list.add(dto);
		}
		LOGGER.debug("return Brand size= {}" , list.size());
		return list;
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
	 * @param isCount
	 * @return
	 */
	public Object getFileHistoryResults(final HelperDTO fileType, final String country, final String businessUnit,
			final Object company, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns,
			final Set<Container.Filter> filterSet, boolean isCount) throws SystemException {

		LOGGER.debug("Entering getFileHistoryResults ");
		DynamicQuery projectionDynamicQuery = null;
		Object object;
		Criterion criterion = null;
		List<FileManagement> resultsList;

		projectionDynamicQuery = FileManagementLocalServiceUtil.dynamicQuery();
		Criterion criteria = RestrictionsFactoryUtil.eq(StringConstantUtils.FILE_TYPE, fileType.getId());
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
				|| ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())
				|| ConstantsUtils.ADJUSTED_DEMAND.equals(fileType.getDescription())) {
			criterion = criteria;
		} else if (ConstantsUtils.CUSTOMERGTS.equals(fileType.getDescription()) && ConstantsUtils.COUNTRY_US.equals(country)) {
			criterion = criteria;

		} else {
			criterion = NULLCREATION;
		}
                projectionDynamicQuery.add(criterion);
		if (businessUnit != null && !businessUnit.isEmpty() && !businessUnit.equalsIgnoreCase("null")
				&& !businessUnit.equalsIgnoreCase("0")) {
			projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantUtils.BUSINESS_UNIT, businessUnit));
		}

		if (company != null) {
			projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantUtils.COMPANY, company));
		}

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";
					if ("file".equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ilike(CommonUtils.FORECAST_NAME, filterString));
					}
					if ("type".equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastSource", filterString));
					}
					if (CommonUtils.VERSION.equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike(CommonUtils.VERSION, filterString));
					}
					if (StringConstantUtils.BUSINESS_UNIT.equals(stringFilter.getPropertyId())
							&& stringFilter.getFilterString() != null && !stringFilter.getFilterString().isEmpty()
							&& !stringFilter.getFilterString().equalsIgnoreCase("null")
							&& !stringFilter.getFilterString().equalsIgnoreCase("0")) {
						projectionDynamicQuery
								.add(RestrictionsFactoryUtil.ilike(StringConstantUtils.BUSINESS_UNIT, filterString));
					}
					if (COMPANY_NAME.equals(stringFilter.getPropertyId()) && stringFilter.getFilterString() != null
							&& !stringFilter.getFilterString().isEmpty()
							&& !stringFilter.getFilterString().equalsIgnoreCase("null")
							&& !stringFilter.getFilterString().equalsIgnoreCase("0")) {
						projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantUtils.COMPANY,
								Integer.valueOf(stringFilter.getFilterString())));
					}

				} else if (filter instanceof Compare) {

					Compare compare = (Compare) filter;
					Compare.Operation operation = compare.getOperation();
					if (CommonUtils.VERSION.equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
						int value = Integer.parseInt(String.valueOf(compare.getValue()));
						if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
							projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(CommonUtils.VERSION, value));
						} else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
							projectionDynamicQuery.add(RestrictionsFactoryUtil.le(CommonUtils.VERSION, value));
						} else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
							projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.VERSION, value));
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
					if (CommonUtils.FROM_PERIOD.equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(CommonUtils.FROM_PERIOD, filterString));
						projectionDynamicQuery.add(RestrictionsFactoryUtil.lt(CommonUtils.FROM_PERIOD, filterString1));
					}
					if (CommonUtils.TO_PERIOD.equals(stringFilter.getPropertyId())) {
						projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(CommonUtils.TO_PERIOD, filterString));
						projectionDynamicQuery.add(RestrictionsFactoryUtil.lt(CommonUtils.TO_PERIOD, filterString1));
					}

				}
			}
		}
		loadFMColumnName();
		if (!isCount) {

			boolean sortOrder = false;
			String columnName = null;
			String orderByColumn = null;

			if (sortByColumns != null) {
				for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
					final SortByColumn sortByColumn = (SortByColumn) iterator.next();

					columnName = sortByColumn.getName();
					orderByColumn = getfileDBColumnName(columnName);

					if (sortByColumn.getType() == SortByColumn.Type.ASC) {
						sortOrder = false;
					} else {
						sortOrder = true;
					}
				}
			}

			if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
				final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
				projectionDynamicQuery.addOrder(defaultOrder);
			} else if (sortOrder) {
				projectionDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn));
			} else {
				projectionDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn));
			}

		}
		final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<>();

		if (isCount) {
			object = (Integer) DAO.getFileManagementCount(projectionDynamicQuery);
		} else {
			projectionDynamicQuery.setLimit(startIndex, endIndex);
			resultsList = DAO.getFilesList(projectionDynamicQuery);
			for (final Iterator<FileManagement> iterator = resultsList.iterator(); iterator.hasNext();) {
				final FileManagement fileMgt = iterator.next();
				final FileMananagementResultDTO fileMgtDTO = new FileMananagementResultDTO();
				fileMgtDTO.setFile(fileMgt.getForecastName());
				fileMgtDTO.setEffectiveDate(fileMgt.getCreatedDate());
				fileMgtDTO.setType(fileMgt.getForecastSource());
				fileMgtDTO.setVersion(
						fileMgt.getVersion().equals(ConstantsUtils.NULL) ? ConstantsUtils.EMPTY : fileMgt.getVersion());
				fileMgtDTO.setFromPeriod(fileMgt.getFromPeriod() == null ? null : fileMgt.getFromPeriod());
				fileMgtDTO.setToPeriod(fileMgt.getToPeriod() == null ? null : fileMgt.getToPeriod());
				fileMgtDTO.setFileId(String.valueOf(fileMgt.getFileManagementSid()));
				fileMgtDTO.setBusinessUnit(getCompanyMasterName(fileMgt.getBusinessUnit()));
				fileMgtDTO.setCompanyName(getCompanyMasterName(String.valueOf(fileMgt.getCompany())));
				fileMgtDTO.setCompanyMasterSystemId(fileMgt.getCompany());
				fileMgtDTO.setSelectedFileVersion(fileMgt.getVersionNo());
				resultsListDTO.add(fileMgtDTO);
			}
			object = resultsListDTO;
		}

		LOGGER.debug("getFileHistoryResults return resultsListDTO= {}" , resultsListDTO.size());
		return object;
	}

	public String getfileDBColumnName(String visibleColumnName) {
		return columnNames.get(visibleColumnName);
	}

	public HashMap<String, String> loadFMColumnName() {
		columnNames.put("file", CommonUtils.FORECAST_NAME);
		columnNames.put("type", "forecastSource");
		columnNames.put(CommonUtils.VERSION, CommonUtils.VERSION);
		columnNames.put("effectiveDate", ConstantsUtils.CREATED_DATE);
		columnNames.put(CommonUtils.FROM_PERIOD, CommonUtils.FROM_PERIOD);
		columnNames.put(CommonUtils.TO_PERIOD, CommonUtils.TO_PERIOD);
		columnNames.put(StringConstantUtils.BUSINESS_UNIT, StringConstantUtils.BUSINESS_UNIT);
		columnNames.put(StringConstantUtils.COMPANY, StringConstantUtils.COMPANY);
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

			String searchQuery = StringUtils.EMPTY;

			List resultsList;
			final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<>();
			String fileName = resultDTO.getFileName();
			String type = resultDTO.getType();
			String version = resultDTO.getVersion();

			LOGGER.debug("getResults started with P1:String fileType= {}, P2:String country= {}, P3:String fileName= {}, P4:String type= {}, P5:String version= {}, P6:String forecastYear= {}, P7:Date fromDate= {}, Date toDate= {} ", resultDTO.getHelperType().getDescription(), resultDTO.getCountry(), fileName, 
					type, version, resultDTO.getForecastYear(), resultDTO.getFromPeriod(), resultDTO.getToPeriod());

			if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil.getQuery(isCount ? "ex-factory-count-query" : "ex-factory-search-query");
				searchQuery = searchQuery.replace("[?SOURCE]", "'FORESIGHT','LE_FORESIGHT'");

			} else if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_PR.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil.getQuery(isCount ? "ex-factory-count-query" : "ex-factory-search-query");
				searchQuery = searchQuery.replace("[?SOURCE]", "'FF_SALES'");

			} else if (ConstantsUtils.DEMAND.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil.getQuery(isCount ? "demand-count-query" : "demand-search-query");

			} else if (ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil
						.getQuery(isCount ? "adjusted-demand-count-query" : "adjusted-demand-search-query");

			} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil.getQuery(isCount ? "inventory-withdrawl-summary-count-query"
						: "inventory-withdrawl-summary-search-query");

			} else if (ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil
						.getQuery(isCount ? "inventory-with-det-count-query" : "inventory-with-det-search-query");

			} else if (ConstantsUtils.CUSTOMERGTS.equals(resultDTO.getHelperType().getDescription())
					&& ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {

				searchQuery = SQlUtil.getQuery(isCount ? "customer-gts-count-query" : "customer-gts-search-query");

			}

			String condition = StringUtils.EMPTY;
			searchQuery = searchQuery.replace("[?COMPANY_MASTER_SID]",
					StringUtils.EMPTY + resultDTO.getCompanyMasterSystemId());
			searchQuery = searchQuery.replace("[?COUNTRY]", resultDTO.getCountry());
			searchQuery = searchQuery.replace("[?BUSINESS_UNIT]", resultDTO.getBusinessUnit());

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
			String dateWhereCondition = StringUtils.EMPTY;
			if (resultDTO.getFromPeriod() != null && resultDTO.getToPeriod() != null) {
				searchQuery = searchQuery.replace(StringConstantUtils.DATEQUERY, "WHERE FT_MIN_DATE  = " + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getFromPeriod())))
						+ "'" + " AND FT_MAX_DATE  =" + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getToPeriod())))
						+ "'");
				dateWhereCondition = "WHERE FT_MIN_DATE  = " + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getFromPeriod())))
						+ "'" + " AND FT_MAX_DATE  =" + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getToPeriod())))
						+ "'";
			} else if (resultDTO.getFromPeriod() != null) {
				searchQuery = searchQuery
						.replace(StringConstantUtils.DATEQUERY,
								"WHERE FT_MIN_DATE  =" + "'"
										+ dateFormat.format(
												javaDateFormat.parse(String.valueOf(resultDTO.getFromPeriod())))
										+ "'");
				dateWhereCondition = "WHERE FT_MIN_DATE  =" + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getFromPeriod())))
						+ "'";
			} else if (resultDTO.getToPeriod() != null) {
				searchQuery = searchQuery.replace(StringConstantUtils.DATEQUERY, "WHERE FT_MAX_DATE  =" + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getToPeriod())))
						+ "'");
				dateWhereCondition = "WHERE FT_MAX_DATE  =" + "'"
						+ dateFormat.format(javaDateFormat.parse(String.valueOf(resultDTO.getToPeriod())))
						+ "'";
			}

			HashMap<String, String> resultsColumn = new HashMap<>();
			resultsColumn.put("fileSource", "FM.SOURCE");
			resultsColumn.put(CommonUtils.COUNTRY, "FM.COUNTRY");
			resultsColumn.put("fileName", "FM.FORECAST_NAME");
			resultsColumn.put(StringConstantUtils.FILE_TYPE, "FM.SOURCE");
			resultsColumn.put(CommonUtils.VERSION, "FM.FORECAST_VER");
			resultsColumn.put(StringConstantUtils.FROM_DATE, StringConstantUtils.FMFORECAST_DATE);
			resultsColumn.put(StringConstantUtils.TO_DATE, StringConstantUtils.FMFORECAST_DATE);
			resultsColumn.put(StringConstantUtils.BUSINESS_UNIT, "FM.BUSINESS_UNIT");
			resultsColumn.put(COMPANY_NAME, "CM.COMPANY_MASTER_SID");

			HashMap<String, String> orderColumn = new HashMap<>();
			orderColumn.put(StringConstantUtils.FILE_TYPE, StringConstantUtils.SOURCE);
			orderColumn.put("fileName", StringConstantUtils.FORECAST_NAME_LIST);
			orderColumn.put(CommonUtils.COUNTRY, COUNTRY);
			orderColumn.put(StringConstantUtils.BUSINESS_UNIT, "BUSINESS_UNIT");
			orderColumn.put(COMPANY_NAME, "COMPANY_MASTER_SID");
			orderColumn.put(StringConstantUtils.FROM_DATE, "FT_MIN_DATE");
			orderColumn.put(StringConstantUtils.TO_DATE, "FT_MAX_DATE");

			if (filterSet != null) {
				for (Container.Filter filter : filterSet) {
					if (filter instanceof SimpleStringFilter) {
						SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
						String filterString = "%" + stringFilter.getFilterString() + "%";
						if (StringConstantUtils.BUSINESS_UNIT.equals(stringFilter.getPropertyId())
								|| COMPANY_NAME.equals(stringFilter.getPropertyId())) {
							if (stringFilter.getFilterString() != null && !stringFilter.getFilterString().isEmpty()
									&& !stringFilter.getFilterString().equalsIgnoreCase("null")
									&& !stringFilter.getFilterString().equalsIgnoreCase("0")) {
								condition = condition + StringConstantUtils.AND_SPACE
										+ resultsColumn.get(String.valueOf(stringFilter.getPropertyId()))
										+ StringConstantUtils.LIKE_SPACE + filterString + "'";
							}
						} else {
							condition = condition + StringConstantUtils.AND_SPACE
									+ resultsColumn.get(String.valueOf(stringFilter.getPropertyId()))
									+ StringConstantUtils.LIKE_SPACE + filterString + "'";
						}

					} else if (filter instanceof Between) {
						Between stringFilter = (Between) filter;
						Date filterString = (Date) stringFilter.getStartValue();
						Date filterString1 = (Date) stringFilter.getEndValue();
						if (StringConstantUtils.FROM_DATE.equals(stringFilter.getPropertyId())) {
							if (!dateWhereCondition.isEmpty()) {
								dateWhereCondition = dateWhereCondition + " AND FT_MIN_DATE >= '"
										+ dateFormat.format(filterString) + "' ";
							} else {
								dateWhereCondition = dateWhereCondition + " WHERE FT_MIN_DATE >= '"
										+ dateFormat.format(filterString) + "' ";
							}
							dateWhereCondition = dateWhereCondition + " AND FT_MIN_DATE <= '"
									+ dateFormat.format(filterString1) + "' ";
						}
						if (StringConstantUtils.TO_DATE.equals(stringFilter.getPropertyId())) {
							if (!dateWhereCondition.isEmpty()) {
								dateWhereCondition = dateWhereCondition + " AND FT_MAX_DATE >= '" + filterString + "' ";
							} else {
								dateWhereCondition = dateWhereCondition + " WHERE FT_MAX_DATE >= '" + filterString
										+ "' ";
							}

							dateWhereCondition = dateWhereCondition + " AND FT_MAX_DATE <= '" + filterString1 + "' ";
						}

					}
				}
			}

			if (!isCount) {

				boolean sortOrder = false;
				String columnName = null;
				String orderByColumn = null;
				if (sortByColumns != null) {
					for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
						final SortByColumn sortByColumn = (SortByColumn) iterator.next();

						columnName = sortByColumn.getName();
						orderByColumn = orderColumn.get(columnName);

						if (sortByColumn.getType() == SortByColumn.Type.ASC) {
							sortOrder = false;
						} else {
							sortOrder = true;
						}
					}
				}

				if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {

					searchQuery = searchQuery.replace("[?ORDER_BY_COLUMN]", "FORECAST_NAME ASC");
				} else {
					searchQuery = searchQuery.replace("[?ORDER_BY_COLUMN]",
							orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE));

				}

				searchQuery = searchQuery.replace("[?START]", StringUtils.EMPTY + startIndex);
				searchQuery = searchQuery.replace("[?END]", StringUtils.EMPTY + endIndex);

			}

			searchQuery = searchQuery.replace("[?CONDITION]", condition);
			searchQuery = searchQuery.replace(StringConstantUtils.DATEQUERY, dateWhereCondition);

			resultsList = HelperTableLocalServiceUtil.executeSelectQuery(searchQuery);
			if (!isCount) {
				for (int i = 0; i < resultsList.size(); i++) {
					final Object[] obj = (Object[]) resultsList.get(i);
					final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
					fmDTO.setFileName(String.valueOf(obj[0]));
					if (!ConstantsUtils.NULL.equals(String.valueOf(obj[1]))) {
						fmDTO.setVersion(String.valueOf(obj[1]));
					}
					fmDTO.setFileType(String.valueOf(obj[NumericConstants.TWO]));
					fmDTO.setType(String.valueOf(obj[NumericConstants.TWO]));
					fmDTO.setCountry(String.valueOf(obj[NumericConstants.THREE]));
					fmDTO.setBusinessUnit(resultDTO.getBusinessUnitName());
					fmDTO.setFromDate(checkAndReturnDate(obj[NumericConstants.FOUR]));
					fmDTO.setToDate(checkAndReturnDate(obj[NumericConstants.FIVE]));
					fmDTO.setAuditVersion(0);
					fmDTO.setCompanyName(resultDTO.getCompanyName());
					resultsListDTO.add(fmDTO);
				}
				object = resultsListDTO;
				LOGGER.debug(
						"getResults return List<FileMananagementResultDTO> resultsListDTO= {}" , resultsListDTO.size());
			} else {
				object = resultsList.get(0);
				LOGGER.debug("getResults return List<FileMananagementResultDTO> resultsList= {}" , resultsList.size());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return object;
	}

	public static final String DESC_SPACE = " DESC ";
	public static final String ASC_SPACE = " ASC ";
	public static final String COUNTRY = "COUNTRY";

	private Date checkAndReturnDate(Object source) {
		final DateFormat dateFormatToParse = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT_TO_PARSE);
		if (source != null) {
			try {
				return dateFormatToParse.parse((String) source);
			} catch (ParseException ex) {
				LOGGER.error(ex.getMessage());
			}
		}
		return null;
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
			boolean isCount, boolean isRecordLock) throws SystemException, ParseException {
		Object detailsObj = null;
		List resultsList;
		final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		LOGGER.debug("getDetailsResults started with P1:String fileName= {}, P2:String version= {}, P3:String fileType= {}, P4:String country= {}, startIndex= {}, endIndex={} " , detailsResultDTO.getFileName()
				, detailsResultDTO.getVersion(), detailsResultDTO.getHelperType(), detailsResultDTO.getCountry(),
				startIndex, endIndex);
		if (detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.DEMAND)) {
			resultsList = (List) getDemandDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, isRecordLock);
		} else if (detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			resultsList = (List) getAdjustedDemandDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, isRecordLock);
		} else if (detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.EX_FACTORY_SALES)) {
			resultsList = (List) getForecastDetails(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet,
					isCount, isRecordLock);
		} else if (detailsResultDTO.getHelperType().getDescription()
				.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			resultsList = (List) getInventorySummaryResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount, isRecordLock);
		} else if (detailsResultDTO.getHelperType().getDescription()
				.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			resultsList = (List) getInventoryDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount);
		} else if (detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {
			resultsList = (List) getCustomerSalesResults(detailsResultDTO, startIndex, endIndex, sortByColumns,
					filterSet, isCount);
		} else {
			resultsList = null;
		}
		if (!isCount && resultsList != null && detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.EX_FACTORY_SALES)) {
			for (int i = 0; i < resultsList.size(); i++) {
				final Object[] obj = (Object[]) resultsList.get(i);
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setItemNo(String.valueOf(obj[0]));
				fmDTO.setItemName(String.valueOf(obj[1]));
				fmDTO.setYear(String.valueOf(obj[NumericConstants.TWO]));
				fmDTO.setMonth(String.valueOf(obj[NumericConstants.THREE]));
				if (obj[NumericConstants.FOUR] != null) {
					String date = formatter.format(obj[NumericConstants.FOUR]);
					Date sdate = formatter.parse(date);
					fmDTO.setStartDate(sdate);
				}
				fmDTO.setPrice(String.valueOf(obj[NumericConstants.FIVE]));
				fmDTO.setUnits(String.valueOf(obj[NumericConstants.SIX]));
				fmDTO.setHiddenPrice(String.valueOf(obj[NumericConstants.FIVE]));
				fmDTO.setHiddenUnits(String.valueOf(obj[NumericConstants.SIX]));
				fmDTO.setDollars(String.valueOf(obj[NumericConstants.SEVEN]));
				boolean recordStatus = ((Boolean) obj[NumericConstants.EIGHT]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) obj[NumericConstants.NINE]);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
			LOGGER.debug(
					"getDetailsResults return List<FileMananagementResultDTO> resultsListDTO= {}" , resultsListDTO.size());
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.DEMAND)) {
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setForecastType(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setForcastYear(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastMonth(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setItemId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenForecastType(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenForecastYear(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenForecastMonth(obj[NumericConstants.TWO] != null
						? String.valueOf(obj[NumericConstants.TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenItemId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.FOUR] != null
						? String.valueOf(obj[NumericConstants.FOUR]) : ConstantsUtils.EMPTY);
				fmDTO.setItemIdentifier(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setBrandId(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setSegment(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setMarketSizeUnits(obj[NumericConstants.EIGHT] != null
						? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
				fmDTO.setMarketShareUnits(obj[NumericConstants.NINE] != null
						? String.valueOf(obj[NumericConstants.NINE]) : ConstantsUtils.EMPTY);
				fmDTO.setMarketShareRatio(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setUncapturedUnits(obj[NumericConstants.ELEVEN] != null
						? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setUncapturedUnitsRatio(obj[NumericConstants.TWELVE] != null
						? String.valueOf(obj[NumericConstants.TWELVE]) : ConstantsUtils.EMPTY);
				fmDTO.setTotalDemandUnits(obj[NumericConstants.THIRTEEN] != null
						? String.valueOf(obj[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setTotalDemandAmount(obj[NumericConstants.FOURTEEN] != null
						? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setInventoryUnitChange(obj[NumericConstants.FIFTEEN] != null
						? String.valueOf(obj[NumericConstants.FIFTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossUnits(obj[NumericConstants.SIXTEEN] != null
						? String.valueOf(obj[NumericConstants.SIXTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossPrice(obj[NumericConstants.SEVENTEEN] != null
						? String.valueOf(obj[NumericConstants.SEVENTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossAmount(obj[NumericConstants.EIGHTEEN] != null
						? String.valueOf(obj[NumericConstants.EIGHTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setNetSalesPrice(obj[NumericConstants.NINETEEN] != null
						? String.valueOf(obj[NumericConstants.NINETEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setNetSalesAmount(obj[NumericConstants.TWENTY] != null
						? String.valueOf(obj[NumericConstants.TWENTY]) : ConstantsUtils.EMPTY);
				fmDTO.setBatchId(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
				fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_SIX] != null
						? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_SIX] != null
						? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : ConstantsUtils.EMPTY);
				boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SEVEN]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) obj[NumericConstants.TWENTY_EIGHT]);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList!= null && detailsResultDTO.getHelperType().getDescription()
				.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setItemId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.FIVE] != null
						? String.valueOf(obj[NumericConstants.FIVE]) : ConstantsUtils.EMPTY);
				fmDTO.setItemIdentifier(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setUnitsWithdrawn(obj[NumericConstants.SEVEN] != null
						? String.valueOf(obj[NumericConstants.SEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setAmountWithdrawn(obj[NumericConstants.EIGHT] != null
						? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
				fmDTO.setPrice(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE])
						: ConstantsUtils.EMPTY);
				fmDTO.setBatchId(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenbatchId(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setOrganizationKey(obj[NumericConstants.ELEVEN] != null
						? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.ELEVEN] != null
						? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				boolean recordStatus = ((Boolean) obj[NumericConstants.TWELVE]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) obj[NumericConstants.THIRTEEN]);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().getDescription()
				.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
			Map<String, String> userMap = CommonUtil.getCreatedByUser();
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setIdentifierCodeQualifier(obj[NumericConstants.FIVE] != null
						? String.valueOf(obj[NumericConstants.FIVE]) : ConstantsUtils.EMPTY);
				fmDTO.setCompanyIdentifier(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.EIGHT] != null
						? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
				fmDTO.setItemIdentifier(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE])
						: ConstantsUtils.EMPTY);
				fmDTO.setUnitsWithdrawn(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setAmountWithdrawn(obj[NumericConstants.ELEVEN] != null
						? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setPrice(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setBatchId(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenbatchId(obj[NumericConstants.THIRTEEN] != null
						? String.valueOf(obj[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setOrganizationKey(obj[NumericConstants.FOURTEEN] != null
						? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.FOURTEEN] != null
						? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
				boolean recordStatus = ((Boolean) obj[NumericConstants.FIFTEEN]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId((Integer) obj[NumericConstants.SIXTEEN]);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				fmDTO.setCompanyName(obj[NumericConstants.SEVENTEEN] != null
						? String.valueOf(obj[NumericConstants.SEVENTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setItemName(obj[NumericConstants.EIGHTEEN] != null
						? String.valueOf(obj[NumericConstants.EIGHTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setSource(obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setForecastName(obj[NumericConstants.TWENTY] != null
						? String.valueOf(obj[NumericConstants.TWENTY]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastVersion(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
				fmDTO.setCountry(obj[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setCreatedBy(obj[NumericConstants.TWENTY_THREE] != null
						? (userMap.get(String.valueOf(obj[NumericConstants.TWENTY_THREE])) == null ? ""
								: userMap.get(String.valueOf(obj[NumericConstants.TWENTY_THREE])))
						: ConstantsUtils.EMPTY);
				fmDTO.setCreatedDate(obj[NumericConstants.TWENTY_FOUR] != null
						? CommonUtils.convertDateToString((Date) obj[NumericConstants.TWENTY_FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setModifiedBy(obj[NumericConstants.TWENTY_FIVE] != null
						? (userMap.get(String.valueOf(obj[NumericConstants.TWENTY_FIVE])) == null ? ""
								: userMap.get(String.valueOf(obj[NumericConstants.TWENTY_FIVE])))
						: ConstantsUtils.EMPTY);
				fmDTO.setModifiedDate(obj[NumericConstants.TWENTY_SIX] != null
						? CommonUtils.convertDateToString((Date) obj[NumericConstants.TWENTY_SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setInboundStatus(obj[NumericConstants.TWENTY_SEVEN] != null
						? String.valueOf(obj[NumericConstants.TWENTY_SEVEN]) : ConstantsUtils.EMPTY);
				resultsListDTO.add(fmDTO);
			}
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null && detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.CUSTOMERGTS)) {
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setForcastYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setItemId(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setCompanyId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setUnits(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setPriceType(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setPrice(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setSalesAmount(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setSalesInclusion(obj[NumericConstants.EIGHT] != null
						? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
				fmDTO.setDeductionId(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE])
						: ConstantsUtils.EMPTY);
				fmDTO.setDeductionCategory(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setDeductionType(obj[NumericConstants.ELEVEN] != null
						? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setDeductionProgramType(obj[NumericConstants.TWELVE] != null
						? String.valueOf(obj[NumericConstants.TWELVE]) : ConstantsUtils.EMPTY);
				fmDTO.setAdjustmentCode(obj[NumericConstants.THIRTEEN] != null
						? String.valueOf(obj[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setDeductionRate(obj[NumericConstants.FOURTEEN] != null
						? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setDeductionAmount(obj[NumericConstants.FIFTEEN] != null
						? String.valueOf(obj[NumericConstants.FIFTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setDeductionInclusion(obj[NumericConstants.SIXTEEN] != null
						? String.valueOf(obj[NumericConstants.SIXTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastValueType(obj[NumericConstants.SEVENTEEN] != null
						? String.valueOf(obj[NumericConstants.SEVENTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setBrandId(obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setSegment(obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setBatchId(obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY])
						: ConstantsUtils.EMPTY);
				fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastName(obj[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastVersion(obj[NumericConstants.TWENTY_THREE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : ConstantsUtils.EMPTY);
				fmDTO.setCountry(obj[NumericConstants.TWENTY_FOUR] != null
						? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastDate(obj[NumericConstants.TWENTY_FIVE] != null
						? String.valueOf(formatter.format(obj[NumericConstants.TWENTY_FIVE])) : ConstantsUtils.EMPTY);
				boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SIX]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				fmDTO.setForecastSystemId(obj[NumericConstants.TWENTY_SEVEN] != null
						? Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])) : 0);
				fmDTO.setCustomerGtsForecastIntfId(obj[NumericConstants.TWENTY_EIGHT] != null
						? Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_EIGHT])) : 0);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				fmDTO.setCheck(BooleanConstant.getFalseFlag());
				resultsListDTO.add(fmDTO);
			}
			LOGGER.debug("resultsListDTO= {}" , resultsListDTO.size());
			detailsObj = resultsListDTO;
		} else if (!isCount && resultsList != null
				&& detailsResultDTO.getHelperType().getDescription().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
			for (Object resultsList1 : resultsList) {
				final Object[] obj = (Object[]) resultsList1;
				final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
				fmDTO.setItemId(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
				fmDTO.setItemName(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
				fmDTO.setBrandId(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
						: ConstantsUtils.EMPTY);
				fmDTO.setBrandName(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
						: ConstantsUtils.EMPTY);
				fmDTO.setSegment(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
						: ConstantsUtils.EMPTY);
				fmDTO.setForcastYear(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenYear(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE])
						: ConstantsUtils.EMPTY);
				fmDTO.setForecastMonth(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setHiddenMonth(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
						: ConstantsUtils.EMPTY);
				fmDTO.setMarketSizeUnits(obj[NumericConstants.SEVEN] != null
						? String.valueOf(obj[NumericConstants.SEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setMarketShareRatio(obj[NumericConstants.EIGHT] != null
						? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
				fmDTO.setMarketShareUnits(obj[NumericConstants.NINE] != null
						? String.valueOf(obj[NumericConstants.NINE]) : ConstantsUtils.EMPTY);
				fmDTO.setUncapturedUnits(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
						: ConstantsUtils.EMPTY);
				fmDTO.setUncapturedUnitsRatio(obj[NumericConstants.ELEVEN] != null
						? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
				fmDTO.setTotalDemandUnits(obj[NumericConstants.TWELVE] != null
						? String.valueOf(obj[NumericConstants.TWELVE]) : ConstantsUtils.EMPTY);
				fmDTO.setTotalDemandAmount(obj[NumericConstants.THIRTEEN] != null
						? String.valueOf(obj[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setInventoryUnitChange(obj[NumericConstants.FOURTEEN] != null
						? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossUnits(obj[NumericConstants.FIFTEEN] != null
						? String.valueOf(obj[NumericConstants.FIFTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossPrice(obj[NumericConstants.SIXTEEN] != null
						? String.valueOf(obj[NumericConstants.SIXTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setGrossAmount(obj[NumericConstants.SEVENTEEN] != null
						? String.valueOf(obj[NumericConstants.SEVENTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setNetSalesPrice(obj[NumericConstants.EIGHTEEN] != null
						? String.valueOf(obj[NumericConstants.EIGHTEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setNetSalesAmount(obj[NumericConstants.NINETEEN] != null
						? String.valueOf(obj[NumericConstants.NINETEEN]) : ConstantsUtils.EMPTY);
				fmDTO.setBatchId(obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY])
						: ConstantsUtils.EMPTY);
				fmDTO.setSource(obj[NumericConstants.TWENTY_ONE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
				fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_TWO] != null
						? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastName(obj[NumericConstants.TWENTY_THREE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : ConstantsUtils.EMPTY);
				fmDTO.setForecastVersion(obj[NumericConstants.TWENTY_FOUR] != null
						? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : ConstantsUtils.EMPTY);
				fmDTO.setCountry(obj[NumericConstants.TWENTY_FIVE] != null
						? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : ConstantsUtils.EMPTY);
				fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
				boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SIX]).booleanValue();
				fmDTO.setRecordLockStatus(recordStatus);
				if (obj[NumericConstants.TWENTY_SEVEN] != null) {
					fmDTO.setForecastSystemId(Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])));
				}
				fmDTO.setCheck(Boolean.FALSE);
				resultsListDTO.add(fmDTO);
			}

			detailsObj = resultsListDTO;
		} else {
                    if(resultsList != null)
                    {
			detailsObj = resultsList.get(0);
                    }
                        LOGGER.debug("getDetailsResults return List<FileMananagementResultDTO> detailsObj= {}" , detailsObj);
		}
		return detailsObj;
	}

	public Object getForecastDetails(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isRecordLock) {
		LOGGER.debug("Entering getForecastDetails ");
		List list;
		String sqlString;
		if (isCount) {
			sqlString = "SELECT count( * ) \n"
					+ " FROM FORECASTING_MASTER FM, ITEM_MASTER IM WHERE FM.NDC=IM.ITEM_ID AND \n" + "FORECAST_NAME=  ";
		} else {
			sqlString = SQlUtil.getQuery("getDetailsResults");
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType().getDescription())
				&& "US".equals(detailsResultDTO.getCountry())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'LE_FORESIGHT')");
		} else if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType().getDescription())
				&& "PR".equals(detailsResultDTO.getCountry())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_SALES')");
		} else if ("Vaccine Segmentation".equals(detailsResultDTO.getHelperType().getDescription())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_VACCINE')");
		}
		sqlString = sqlString.concat(" AND FM.COUNTRY='").concat(detailsResultDTO.getCountry()).concat("'");

		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(" (FM.FORECAST_VER='")
					.concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
		} else {
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(" FM.FORECAST_VER='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "FORECAST_YEAR");
		detailsColumn.put(StringConstantUtils.MONTH_PROPERTY, "FORECAST_MONTH");
		detailsColumn.put(StringConstantUtils.ITEM_NO_PROPERTY, "ITEM_NO");
		detailsColumn.put(StringConstantUtils.ITEM_NAME, StringConstantUtils.ITEM_NAME_LIST);
		detailsColumn.put(StringConstantUtils.PRICE_PROPERTY, "PRICE");
		detailsColumn.put(StringConstantUtils.UNITS, "UNITS");
		detailsColumn.put("dollars", "DOLLARS");
		detailsColumn.put(StringConstantUtils.START_DATE, "FORECAST_DATE");

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

				} else if (filter instanceof Between) {
					Between stringFilter = (Between) filter;
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					String filterString = formatter.format(stringFilter.getStartValue());
					String filterString1 = formatter.format(stringFilter.getEndValue());
					if (StringConstantUtils.START_DATE.equals(stringFilter.getPropertyId())) {
						filterQuery.append(" AND FORECAST_DATE >= '" ).append( filterString ).append( "' ");
						filterQuery.append( " AND FORECAST_DATE <= '" ).append( filterString1 ).append( "' ");
					}

				}
			}
		}

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
				order = order + " ORDER BY FM.FORECAST_YEAR ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}

			order = order + " " + StringConstantUtils.OFFSET_SPACE;
			order = order + startIndex;
			order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
			order = order + StringConstantUtils.ROWS_ONLY;

		}

		String finalQuery;
		String lockStatus = " AND FM.RECORD_LOCK_STATUS=0";
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("getDetailsResults return List list= {}" , list.size());
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
					dto.setItemName(obj[1] != null ? obj[1].toString() : ConstantsUtils.EMPTY);
					dto.setItemNo(obj[NumericConstants.TWO] != null ? obj[NumericConstants.TWO].toString()
							: ConstantsUtils.EMPTY);
					dto.setItemDesc(obj[NumericConstants.THREE] != null ? obj[NumericConstants.THREE].toString()
							: ConstantsUtils.EMPTY);
					dto.setItemType(obj[NumericConstants.FOUR] != null
							&& !obj[NumericConstants.FOUR].toString().equals(ConstantsUtils.SELECT_ONE)
									? obj[NumericConstants.FOUR].toString() : ConstantsUtils.EMPTY);
					dto.setItemStatus(obj[NumericConstants.FIVE] != null
							&& !obj[NumericConstants.FIVE].toString().equals(ConstantsUtils.SELECT_ONE)
									? obj[NumericConstants.FIVE].toString() : ConstantsUtils.EMPTY);
					dto.setIdentifier(obj[NumericConstants.SIX] != null ? obj[NumericConstants.SIX].toString()
							: ConstantsUtils.EMPTY);
					dto.setIdentifierType(obj[NumericConstants.ELEVEN] != null ? obj[NumericConstants.ELEVEN].toString()
							: ConstantsUtils.EMPTY);
					dto.setTherapyClass(obj[NumericConstants.SEVEN] != null
							&& !obj[NumericConstants.SEVEN].toString().equals(ConstantsUtils.SELECT_ONE)
									? obj[NumericConstants.SEVEN].toString() : ConstantsUtils.EMPTY);
					dto.setBrand(obj[NumericConstants.EIGHT] != null
							&& !obj[NumericConstants.EIGHT].toString().equals(ConstantsUtils.SELECT_ONE)
									? obj[NumericConstants.EIGHT].toString() : ConstantsUtils.EMPTY);
					dto.setNdc8(obj[NumericConstants.NINE] != null ? obj[NumericConstants.NINE].toString()
							: ConstantsUtils.EMPTY);
					dto.setNdc9(obj[NumericConstants.TEN] != null ? obj[NumericConstants.TEN].toString()
							: ConstantsUtils.EMPTY);
					dto.setItemId(obj[NumericConstants.TWELVE] != null ? obj[NumericConstants.TWELVE].toString()
							: ConstantsUtils.EMPTY);
					resultList.add(dto);
				}
			}
			itemObject = resultList;
		} else if (resulList != null && !resulList.isEmpty()) {
			itemObject = resulList.get(0);
		} else {
			itemObject = 0;
		}
		return itemObject;
	}

	public String findItemMaster(ItemSearchDTO itemSearchDTO, final int startIndex, final int endIndex,
			final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
		try {
			String sql;
			if (isCount) {
				sql = "select count(*) from Item_Master im, Item_Identifier irt, Brand_Master bm,ITEM_QUALIFIER iq,\n"
						+ "HELPER_TABLE tc1,HELPER_TABLE st,HELPER_TABLE it\n"
						+ " where im.item_Master_Sid = irt.item_Master_Sid and im.BRAND_MASTER_SID = bm.BRAND_MASTER_SID and im.THERAPEUTIC_CLASS=tc1.HELPER_TABLE_SID and im.item_status=st.HELPER_TABLE_SID "
						+ " and im.item_type=it.HELPER_TABLE_SID and irt.item_Qualifier_Sid=iq.item_Qualifier_Sid and bm.brand_name is not null";
			} else {
				sql = "select im.item_Master_Sid,im.item_Name,im.item_No,im.item_Desc,it.description as ItemType,\n"
						+ "st.description as itemStatus,irt.item_Identifier_Value,tc1.description as Therapy,bm.BRAND_NAME,im.ndc8,im.ndc9,iq.ITEM_QUALIFIER_NAME,im.item_Id from Item_Master im, Item_Identifier irt, Brand_Master bm,ITEM_QUALIFIER iq,\n"
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
					&& !ConstantsUtils.EMPTY.equals(String.valueOf(itemSearchDTO.getIdentifierType()))
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
			itemColumn.put(StringConstantUtils.ITEM_NO_PROPERTY, "im.item_No");
			itemColumn.put(StringConstantUtils.ITEM_NAME, "im.item_Name");
			itemColumn.put("itemDesc", "im.item_Desc");
			itemColumn.put("itemType", "im.item_Type");
			itemColumn.put("itemStatus", "im.item_Status");
			itemColumn.put("therapyClass", "im.THERAPEUTIC_CLASS");
			itemColumn.put(StringConstantUtils.BRAND, "im.brand_Master_Sid");
			itemColumn.put("ndc9", "im.ndc9");
			itemColumn.put("ndc8", "im.ndc8");
			itemColumn.put("identifierType", "irt.item_Qualifier_Sid");
			itemColumn.put("identifier", "irt.item_Identifier_Value");

			if (filterSet != null) {
				for (Container.Filter filter : filterSet) {
					if (filter instanceof SimpleStringFilter) {
						SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
						String filterString = "%" + stringFilter.getFilterString() + "%";
						if ((StringConstantUtils.BRAND).equals(stringFilter.getPropertyId())
								|| "identifierType".equals(stringFilter.getPropertyId())) {
							if (stringFilter.getFilterString() != null
									&& !"null".equals(stringFilter.getFilterString())) {
								filterQuery.append(StringConstantUtils.AND_SPACE)
										.append( itemColumn.get(String.valueOf(stringFilter.getPropertyId())))
										.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');
							}
						} else {
							filterQuery.append(StringConstantUtils.AND_SPACE)
									.append( itemColumn.get(String.valueOf(stringFilter.getPropertyId())))
									.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');
						}
					}
				}
			}

			String order = ConstantsUtils.EMPTY;
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
					order = order + StringConstantUtils.ORDER_BY + orderByColumn
							+ ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
				}

				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;

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
			boolean isCount, boolean isRecordLock) {
		LOGGER.debug("Entering getDemandDetailsResults Details Results ");
		List list;
		String sqlString;

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
		sqlString = sqlString.concat(StringConstantUtils.AND_DFCOUNTRY).concat(detailsResultDTO.getCountry())
				.concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(StringConstantUtils.DFFORECAST_VER_IN + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			String outerSize = String.valueOf(innerArray[0]);
			int innerSize;
			for (int i = 1; i <= Integer.parseInt(outerSize); i++) {
				if (Integer.parseInt(versionArray[0]) > Integer.parseInt(outerSize)) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = 0; j <= innerSize; j++) {
					String version = String.valueOf(i);
					if (outerSize.contains("0")) {
						version = "0" + String.valueOf(i);
					}
					sqlString = sqlString.concat(" '" + version + "." + j + "',");
				}
			}
			if (sqlString.endsWith(",")) {
				sqlString = sqlString.substring(0, sqlString.length() - 1);
			}
			sqlString += ")";
		} else {
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.DFFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("forecastType", "DF.FORECAST_TYPE");
		detailsColumn.put(StringConstantUtils.FORCAST_YEAR, StringConstantUtils.DFFORECAST_YEAR);
		detailsColumn.put(StringConstantUtils.FORECAST_MONTH, StringConstantUtils.DFFORECAST_MONTH);
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.DFITEM_ID);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER, "DF.ITEM_IDENTIFIER_CODE_QUALIFIER");
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER, "DF.ITEM_IDENTIFIER");
		detailsColumn.put(StringConstantUtils.BRAND_ID, StringConstantUtils.DFBRAND_ID);
		detailsColumn.put(StringConstantUtils.SEGMENT, StringConstantUtils.DFSEGMENT);
		detailsColumn.put(StringConstantUtils.MARKET_SIZE_UNITS, StringConstantUtils.DFMARKET_SIZE_UNITS);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_RATIO, StringConstantUtils.DFMARKET_SHARE_RATIO);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_UNITS, StringConstantUtils.DFMARKET_SHARE_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS, StringConstantUtils.DFUNCAPTURED_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS_RATIO, StringConstantUtils.DFUNCAPTURED_UNITS_RATIO);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_UNITS, StringConstantUtils.DFTOTAL_DEMAND_UNITS);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_AMOUNT, StringConstantUtils.DFTOTAL_DEMAND_AMOUNT);
		detailsColumn.put(StringConstantUtils.INVENTORY_UNIT_CHANGE, StringConstantUtils.DFINVENTORY_UNIT_CHANGE);
		detailsColumn.put(StringConstantUtils.GROSS_UNITS, StringConstantUtils.DFGROSS_UNITS);
		detailsColumn.put(StringConstantUtils.GROSS_PRICE, StringConstantUtils.DFGROSS_PRICE);
		detailsColumn.put(StringConstantUtils.GROSS_AMOUNT, StringConstantUtils.DFGROSS_AMOUNT);
		detailsColumn.put(StringConstantUtils.NET_SALES_PRICE, StringConstantUtils.DFNET_SALES_PRICE);
		detailsColumn.put(StringConstantUtils.NET_SALES_AMOUNT, StringConstantUtils.DFNET_SALES_AMOUNT);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.DFBATCH_ID);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.DFORGANIZATION_KEY);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

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
				order = order + StringConstantUtils.ORDER_BY_DFFORECAST_YEAR;
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			order = order + " " + StringConstantUtils.OFFSET_SPACE;
			order = order + startIndex;
			order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
			order = order + StringConstantUtils.ROWS_ONLY;
		}
		String lockStatus = StringConstantUtils.AND_DFRECORD_LOCK_STATUS;
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}

		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending Demand ForecastingMasterLocalServiceUtil Results");
		return list;
	}

	public Object getAdjustedDemandDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isRecordLock) {
		LOGGER.debug("Entering getAdjustedDemandDetailsResults Details Results");
		List list;
		String sqlString;

		if (isCount) {
			sqlString = "SELECT Count(DF.ADJUSTED_DEMAND_FORECAST_SID) FROM ADJUSTED_DEMAND_FORECAST DF "
					+ "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = DF.ITEM_MASTER_SID \n"
					+ "JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID \n"
					+ "AND IM.BRAND_MASTER_SID = DF.BRAND_MASTER_SID \n" + "WHERE DF.FORECAST_NAME = ";
		} else {
			sqlString = "SELECT DISTINCT DF.ITEM_ID,IM.ITEM_NAME ,\n" + "DF.BRAND_ID,BM.BRAND_NAME ,\n"
					+ "DF.SEGMENT,DF.YEAR,DF.MONTH,DF.MARKET_SIZE_UNITS,DF.MARKET_SHARE_RATIO,DF.MARKET_SHARE_UNITS,\n"
					+ "DF.UNCAPTURED_UNITS,DF.UNCAPTURED_UNITS_RATIO,DF.TOTAL_DEMAND_UNITS,\n"
					+ "DF.TOTAL_DEMAND_AMOUNT,DF.INVENTORY_UNIT_CHANGE,DF.GROSS_UNITS,DF.GROSS_PRICE,\n"
					+ "DF.GROSS_AMOUNT,DF.NET_SALES_PRICE,DF.NET_SALES_AMOUNT,DF.BATCH_ID,DF.SOURCE,DF.ORGANIZATION_KEY,\n"
					+ "DF.FORECAST_NAME,DF.FORECAST_VER,DF.COUNTRY,DF.RECORD_LOCK_STATUS , DF.ADJUSTED_DEMAND_FORECAST_SID\n"
					+ "FROM ADJUSTED_DEMAND_FORECAST  DF \n"
					+ " LEFT JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = DF.ITEM_MASTER_SID\n"
					+ " LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID \n"
					+ "AND IM.BRAND_MASTER_SID = DF.BRAND_MASTER_SID WHERE DF.FORECAST_NAME =  ";

		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_DFCOUNTRY).concat(detailsResultDTO.getCountry())
				.concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(StringConstantUtils.DFFORECAST_VER_IN + versionArray[0] + "',");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.DFFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();

		detailsColumn.put(StringConstantUtils.FORCAST_YEAR, "DF.YEAR");
		detailsColumn.put(StringConstantUtils.FORECAST_MONTH, "DF.MONTH");
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.DFITEM_ID);
		detailsColumn.put(StringConstantUtils.ITEM_NAME, "IM.ITEM_NAME");

		detailsColumn.put(StringConstantUtils.SOURCE1, StringConstantUtils.DFSOURCE);
		detailsColumn.put(StringConstantUtils.BRAND_ID, StringConstantUtils.DFBRAND_ID);
		detailsColumn.put("brandName", "BM.BRAND_NAME");
		detailsColumn.put(StringConstantUtils.SEGMENT, StringConstantUtils.DFSEGMENT);
		detailsColumn.put(StringConstantUtils.MARKET_SIZE_UNITS, StringConstantUtils.DFMARKET_SIZE_UNITS);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_RATIO, StringConstantUtils.DFMARKET_SHARE_RATIO);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_UNITS, StringConstantUtils.DFMARKET_SHARE_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS, StringConstantUtils.DFUNCAPTURED_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS_RATIO, StringConstantUtils.DFUNCAPTURED_UNITS_RATIO);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_UNITS, StringConstantUtils.DFTOTAL_DEMAND_UNITS);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_AMOUNT, StringConstantUtils.DFTOTAL_DEMAND_AMOUNT);
		detailsColumn.put(StringConstantUtils.INVENTORY_UNIT_CHANGE, StringConstantUtils.DFINVENTORY_UNIT_CHANGE);
		detailsColumn.put(StringConstantUtils.GROSS_UNITS, StringConstantUtils.DFGROSS_UNITS);
		detailsColumn.put(StringConstantUtils.GROSS_PRICE, StringConstantUtils.DFGROSS_PRICE);
		detailsColumn.put(StringConstantUtils.GROSS_AMOUNT, StringConstantUtils.DFGROSS_AMOUNT);
		detailsColumn.put(StringConstantUtils.NET_SALES_PRICE, StringConstantUtils.DFNET_SALES_PRICE);
		detailsColumn.put(StringConstantUtils.NET_SALES_AMOUNT, StringConstantUtils.DFNET_SALES_AMOUNT);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.DFBATCH_ID);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.DFORGANIZATION_KEY);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

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
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			order = order + " " + StringConstantUtils.OFFSET_SPACE;
			order = order + startIndex;
			order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
			order = order + StringConstantUtils.ROWS_ONLY;
		}
		String lockStatus = StringConstantUtils.AND_DFRECORD_LOCK_STATUS;
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending getAdjustedDemandDetailsResults Details Results");
		return list;
	}

	public Object getInventorySummaryResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isRecordLock) {
		LOGGER.debug("Entering getInventorySummaryResults Details Results");
		List list;
		String sqlString;

		if (isCount) {
			sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_MAS_SID) FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME =";
		} else {
			sqlString = "SELECT DISTINCT INW.\"YEAR\",INW.\"MONTH\",INW.\"DAY\",INW.WEEK,INW.ITEM_ID,INW.ITEM_IDENTIFIER_CODE_QUALIFIER,INW.ITEM_IDENTIFIER\n"
					+ ",INW.UNITS_WITHDRAWN,INW.AMOUNT_WITHDRAWN,INW.PRICE,INW.BATCH_ID,INW.ORGANIZATION_KEY,INW.RECORD_LOCK_STATUS,INW.INVENTORY_WD_PROJ_MAS_SID FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_INWCOUNTRY_LIKE).concat(detailsResultDTO.getCountry())
				.concat("%'");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(" INW.FORECAST_VER in  ('" + versionArray[0] + "',");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.INWFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		String finalQuery;
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "YEAR");
		detailsColumn.put(StringConstantUtils.MONTH_PROPERTY, StringConstantUtils.MONTH_COLUMN);
		detailsColumn.put("week", "WEEK");
		detailsColumn.put("day", "DAY");
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.ITEM_ID_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER,
				StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER, StringConstantUtils.ITEM_IDENTIFIER_LIST);
		detailsColumn.put(StringConstantUtils.UNITS_WITHDRAWN, StringConstantUtils.UNITS_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.AMOUNT_WITHDRAWN, StringConstantUtils.AMOUNT_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.BATCH_ID_LIST);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.ORGANIZATION_KEY_LIST);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

				}

			}
		}
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
				order = order + " ORDER BY INW.YEAR  ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			order = order + " " + StringConstantUtils.OFFSET_SPACE;
			order = order + startIndex;
			order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
			order = order + StringConstantUtils.ROWS_ONLY;
		}
		String lockStatus = " AND INW.RECORD_LOCK_STATUS=0";
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending getInventorySummaryResults Details Results");
		return list;
	}

	public Object getInventoryDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount) {
		LOGGER.debug("Entering getInventory Details Results");
		List list;
		String sqlString;

		if (isCount) {
			sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_DT_SID) FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME =";
		} else {
			sqlString = "SELECT \n " + "    DISTINCT INW.\"YEAR\" ,\n" + "    INW.\"MONTH\" ,\n" + "    INW.\"DAY\" ,\n"
					+ "    INW.WEEK, \n" + "    INW.COMPANY_ID, \n" + "    INW.IDENTIFIER_CODE_QUALIFIER,\n"
					+ "    INW.COMPANY_IDENTIFIER,\n" + "    INW.ITEM_ID, \n"
					+ "    INW.ITEM_IDENTIFIER_CODE_QUALIFIER,\n" + "    INW.ITEM_IDENTIFIER,\n"
					+ "    INW.UNITS_WITHDRAWN, \n" + "    INW.AMOUNT_WITHDRAWN, \n" + "    INW.PRICE, \n"
					+ "    INW.BATCH_ID, \n" + "    INW.ORGANIZATION_KEY,\n" + "    INW.RECORD_LOCK_STATUS,\n"
					+ "    INW.INVENTORY_WD_PROJ_DT_SID,\n" + "    Cm.COMPANY_NAME,\n " + "    im.ITEM_NAME,\n"
					+ "    INW.SOURCE,\n" + "    INW.FORECAST_NAME,\n" + "    INW.FORECAST_VER,\n"
					+ "    INW.COUNTRY,\n " + "    INW.CREATED_BY,\n" + "    INW.CREATED_DATE,\n"
					+ "    INW.MODIFIED_BY,\n" + "    INW.MODIFIED_DATE,\n" + "    INW.INBOUND_STATUS " + " FROM\n"
					+ "    INVENTORY_WD_PROJ_DT INW "
					+ "    LEFT JOIN dbo.COMPANY_MASTER Cm On cm.COMPANY_MASTER_SID=inw.COMPANY_MASTER_SID "
					+ "    LEFT JOIN dbo.ITEM_MASTER im On im.ITEM_MASTER_SID=inw.ITEM_MASTER_SID "
					+ "     WHERE INW.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_INWCOUNTRY_LIKE).concat(detailsResultDTO.getCountry())
				.concat("%'");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(" INW.FORECAST_VER in  ('" + versionArray[0] + "',");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.INWFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		String finalQuery;
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "YEAR");
		detailsColumn.put(StringConstantUtils.MONTH_PROPERTY, StringConstantUtils.MONTH_COLUMN);
		detailsColumn.put("week", "WEEK");
		detailsColumn.put("day", "DAY");
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.ITEM_ID_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER,
				StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER, StringConstantUtils.ITEM_IDENTIFIER_LIST);
		detailsColumn.put(StringConstantUtils.UNITS_WITHDRAWN, StringConstantUtils.UNITS_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.AMOUNT_WITHDRAWN, StringConstantUtils.AMOUNT_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.BATCH_ID_LIST);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.ORGANIZATION_KEY_LIST);
		detailsColumn.put(CommonUtils.COUNTRY, COUNTRY);
		detailsColumn.put(StringConstantUtils.FORECAST_VERSION, "FORECAST_VER");
		detailsColumn.put(CommonUtils.FORECAST_NAME, StringConstantUtils.FORECAST_NAME_LIST);
		detailsColumn.put(StringConstantUtils.SOURCE1, StringConstantUtils.SOURCE);
		detailsColumn.put("inboundStatus", "INBOUND_STATUS");
		detailsColumn.put("modifiedDate", "MODIFIED_DATE");
		detailsColumn.put("createdDate", "CREATED_DATE");
		detailsColumn.put(COMPANY_NAME, "COMPANY_NAME");
		detailsColumn.put(StringConstantUtils.ITEM_NAME, StringConstantUtils.ITEM_NAME_LIST);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

				}

			}
		}
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
				order = order + " ORDER BY INW.YEAR  ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			order = order + " " + StringConstantUtils.OFFSET_SPACE;
			order = order + startIndex;
			order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
			order = order + StringConstantUtils.ROWS_ONLY;
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
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
				+ query.get(NumericConstants.ONE) + "' AND INW.FORECAST_VER LIKE '" + query.get(NumericConstants.TWO)
				+ "' AND INW.FORECAST_NAME LIKE '" + query.get(NumericConstants.THREE) + "'";
		List result = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
		for (Object resultsList1 : result) {
			final Object[] obj = (Object[]) resultsList1;
			final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
			fmDTO.setYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
			fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
			fmDTO.setMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
			fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
			fmDTO.setDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
					: ConstantsUtils.EMPTY);
			fmDTO.setHiddenDay(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO])
					: ConstantsUtils.EMPTY);
			fmDTO.setWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
					: ConstantsUtils.EMPTY);
			fmDTO.setHiddenWeek(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE])
					: ConstantsUtils.EMPTY);
			fmDTO.setCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
					: ConstantsUtils.EMPTY);
			fmDTO.setHiddenCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR])
					: ConstantsUtils.EMPTY);
			fmDTO.setIdentifierCodeQualifier(obj[NumericConstants.FIVE] != null
					? String.valueOf(obj[NumericConstants.FIVE]) : ConstantsUtils.EMPTY);
			fmDTO.setCompanyIdentifier(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX])
					: ConstantsUtils.EMPTY);
			fmDTO.setItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN])
					: ConstantsUtils.EMPTY);
			fmDTO.setHiddenItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN])
					: ConstantsUtils.EMPTY);
			fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.EIGHT] != null
					? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
			fmDTO.setItemIdentifier(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE])
					: ConstantsUtils.EMPTY);
			fmDTO.setUnitsWithdrawn(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN])
					: ConstantsUtils.EMPTY);
			fmDTO.setAmountWithdrawn(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN])
					: ConstantsUtils.EMPTY);
			fmDTO.setPrice(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE])
					: ConstantsUtils.EMPTY);
			fmDTO.setBatchId(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN])
					: ConstantsUtils.EMPTY);
			fmDTO.setHiddenbatchId(obj[NumericConstants.THIRTEEN] != null
					? String.valueOf(obj[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
			fmDTO.setOrganizationKey(obj[NumericConstants.FOURTEEN] != null
					? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
			fmDTO.setHiddenOrganisationKey(obj[NumericConstants.FOURTEEN] != null
					? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
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
			boolean isCount) {
		LOGGER.debug("Entering getCustomerSalesResults Details Results");
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
					+ "DF.RECORD_LOCK_STATUS,DF.CUSTOMER_GTS_FORECAST_SID," + "DF.CUSTOMER_GTS_FORECAST_INTF_ID "
					+ "FROM CUSTOMER_GTS_FORECAST DF WHERE DF.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_DFCOUNTRY).concat(detailsResultDTO.getCountry())
				.concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(StringConstantUtils.DFFORECAST_VER_IN + versionArray[0] + "',");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.DFFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put(StringConstantUtils.FORCAST_YEAR, StringConstantUtils.DFFORECAST_YEAR);
		detailsColumn.put(StringConstantUtils.FORECAST_MONTH, StringConstantUtils.DFFORECAST_MONTH);
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.DFITEM_ID);
		detailsColumn.put("companyId", "DF.COMPANY_ID");
		detailsColumn.put(StringConstantUtils.UNITS, "DF.UNITS");
		detailsColumn.put("priceType", "DF.PRICE_TYPE");
		detailsColumn.put(StringConstantUtils.PRICE_PROPERTY, "DF.PRICE");
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
		detailsColumn.put(StringConstantUtils.BRAND, "DF.BRAND");
		detailsColumn.put(StringConstantUtils.SEGMENT, StringConstantUtils.DFSEGMENT);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.DFBATCH_ID);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.DFSOURCE);
		detailsColumn.put(StringConstantUtils.FORECAST_VERSION, "DF.FORECAST_VER");
		detailsColumn.put(CommonUtils.COUNTRY, "DF.COUNTRY");
		detailsColumn.put(CommonUtils.FORECAST_NAME, "DF.FORECAST_NAME");
		detailsColumn.put("forecastDate", "DF.FORECAST_DATE");
		detailsColumn.put("customerGtsForecastIntfId", "DF.CUSTOMER_GTS_FORECAST_INTF_ID");

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

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
				order = order + " ORDER BY DF.FORECAST_YEAR ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			order = order + " " + StringConstantUtils.OFFSET_SPACE;
			order = order + startIndex;
			order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
			order = order + StringConstantUtils.ROWS_ONLY;
		}
		String whereQuery = ConstantsUtils.EMPTY;
		if (detailsResultDTO.getItemId() != null && !StringUtils.isEmpty(detailsResultDTO.getItemId())) {
			whereQuery = "AND DF.ITEM_ID like '" + detailsResultDTO.getItemId().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getCompanyId() != null && !StringUtils.isEmpty(detailsResultDTO.getCompanyId())) {
			whereQuery = "AND DF.COMPANY_ID like '" + detailsResultDTO.getCompanyId().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getForcastYear() != null && !StringUtils.isEmpty(detailsResultDTO.getForcastYear())) {
			whereQuery = "AND DF.FORECAST_YEAR like '" + detailsResultDTO.getForcastYear().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getForecastMonth() != null && !StringUtils.isEmpty(detailsResultDTO.getForecastMonth())) {
			whereQuery = "AND DF.FORECAST_MONTH like '" + detailsResultDTO.getForecastMonth().replace('*', '%') + "'";
		}

		if (detailsResultDTO.getDeductionId() != null && !StringUtils.isEmpty(detailsResultDTO.getDeductionId())) {
			whereQuery = "AND DF.DEDUCTION_ID like '" + detailsResultDTO.getDeductionId().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getDeductionCategory() != null
				&& !StringUtils.isEmpty(detailsResultDTO.getDeductionCategory())
				&& !SELECT_ONE.equals(detailsResultDTO.getDeductionCategory())) {
			whereQuery = "AND DF.DEDUCTION_CATEGORY like '" + detailsResultDTO.getDeductionCategory().replace('*', '%')
					+ "'";
		}
		if (detailsResultDTO.getDeductionType() != null && !StringUtils.isEmpty(detailsResultDTO.getDeductionType())
				&& !SELECT_ONE.equals(detailsResultDTO.getDeductionType())) {
			whereQuery = "AND DF.DEDUCTION_TYPE like '" + detailsResultDTO.getDeductionType().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getDeductionProgramType() != null
				&& !StringUtils.isEmpty(detailsResultDTO.getDeductionProgramType())
				&& !SELECT_ONE.equals(detailsResultDTO.getDeductionProgramType())) {
			whereQuery = "AND DF.DEDUCTION_PROGRAM_TYPE like '"
					+ detailsResultDTO.getDeductionProgramType().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getBatchId() != null && !StringUtils.isEmpty(detailsResultDTO.getBatchId())) {
			whereQuery = "AND DF.BATCH_ID like '" + detailsResultDTO.getBatchId().replace('*', '%') + "'";
		}

		if (isCount) {
			finalQuery = sqlString + whereQuery + filterQuery.toString();
		} else {
			finalQuery = sqlString + whereQuery + filterQuery.toString() + order;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		return list;
	}

	public List getCustomerValues(String systemId) {

		String query = "SELECT vv.FORECAST_DATE,vv.COUNTRY,vv.FORECAST_NAME,vv.BATCH_ID,\n"
				+ "                 vv.BRAND,vv.ITEM_ID,vv.COMPANY_ID,vv.SEGMENT,vv.DEDUCTION_CATEGORY,vv.DEDUCTION_TYPE,vv.DEDUCTION_PROGRAM_TYPE,vv.DEDUCTION_ID,\n"
				+ "                 vv.UNITS,vv.PRICE,vv.SALES_AMOUNT,vv.SALES_INCLUSION,vv.ADJUSTMENT_CODE,vv.DEDUCTION_RATE,vv.DEDUCTION_AMOUNT,vv.DEDUCTION_INCLUSION,\n"
				+ "                 vv.PRICE_TYPE,vv.FORECAST_VALUE_TYPE,vv.FORECAST_YEAR,vv.FORECAST_MONTH,SOURCE,vv.CREATED_BY,vv.CREATED_DATE,vv.MODIFIED_BY,vv.MODIFIED_DATE,\n"
				+ "                 ht1.DESCRIPTION as udc1,ht2.DESCRIPTION as udc2,ht3.DESCRIPTION,ht4.DESCRIPTION,ht5.DESCRIPTION,ht6.DESCRIPTION,vv.CUSTOMER_GTS_FORECAST_INTF_ID \n"
				+ "                  from VW_CUSTOMER_GTS_FORECAST vv\n"
				+ "                  join HELPER_TABLE ht1 on vv.UDC1 = ht1.HELPER_TABLE_SID \n"
				+ "                  join HELPER_TABLE ht2 on vv.UDC2 = ht2.HELPER_TABLE_SID\n"
				+ "                  join HELPER_TABLE ht3 on vv.UDC2 = ht3.HELPER_TABLE_SID\n"
				+ "                  join HELPER_TABLE ht4 on vv.UDC2 = ht4.HELPER_TABLE_SID\n"
				+ "                  join HELPER_TABLE ht5 on vv.UDC2 = ht5.HELPER_TABLE_SID\n"
				+ "                  join HELPER_TABLE ht6 on vv.UDC2 = ht6.HELPER_TABLE_SID\n"
				+ "                  where CUSTOMER_GTS_FORECAST_SID = '" + systemId + "'";

		return HelperTableLocalServiceUtil.executeSelectQuery(query);
	}

	public String getMaxVersion(String forecastName, String fileType) {
		String query = StringUtils.EMPTY;

		if (null != fileType) {
			switch (fileType) {
			case ConstantsUtils.DEMAND:
				query = "SELECT MAX(FORECAST_VER) FROM dbo.DEMAND_FORECAST WHERE FORECAST_NAME = '" + forecastName
						+ "'";
				break;
			case ConstantsUtils.ADJUSTED_DEMAND:
				query = "SELECT MAX(FORECAST_VER) FROM dbo.ADJUSTED_DEMAND_FORECAST WHERE FORECAST_NAME = '"
						+ forecastName + "'";
				break;
			case ConstantsUtils.EX_FACTORY_SALES:
				query = "SELECT MAX(FORECAST_VER) FROM dbo.FORECASTING_MASTER WHERE FORECAST_NAME = '" + forecastName
						+ "'";
				break;
			case ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY:
				query = "SELECT MAX(FORECAST_VER) FROM dbo.INVENTORY_WD_PROJ_MAS WHERE FORECAST_NAME = '" + forecastName
						+ "'";
				break;
			case ConstantsUtils.CUSTOMERGTS:
				query = "SELECT MAX(FORECAST_VER) FROM dbo.CUSTOMER_GTS_FORECAST WHERE FORECAST_NAME = '" + forecastName
						+ "'";
				break;
			default:
				break;
			}
		}
		if (StringUtils.isNotBlank(query)) {
			List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
			return (String) list.get(0);
		} else {
			return StringUtils.EMPTY;
		}
	}

	public void loadBusinessUnitDdlb(ComboBox businessUnit, boolean isFilter) {
		String defaultValue = isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE;
		businessUnit.addItem(0);
		businessUnit.setNullSelectionAllowed(true);
		businessUnit.setNullSelectionItemId(0);
		businessUnit.select(0);
		businessUnit.setItemCaption(0, defaultValue);
		businessUnit.setImmediate(true);
		String query = "select Company_Master_Sid,Company_Name from dbo.company_master cm\n"
				+ "Join dbo.HELPER_TABLE ht ON Cm.COmpany_Type=ht.HELPER_TABLE_SID  \n"
				+ "where ht.List_Name like 'Company_type' and ht.description like '%Business Unit%' and cm.INBOUND_STATUS <> 'D'";
		List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
		for (Object[] object : list) {
			if (object != null && !String.valueOf(object).isEmpty()) {
				businessUnit.addItem((Integer) object[0]);
				businessUnit.setItemCaption((Integer) object[0], String.valueOf(object[1]));
			}
		}
	}

	public String getCompanyMasteridFromName(String name) {
		String query = "select COmpany_master_sid FROM Company_Master WHERE company_Name='" + name + "'";
		List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
		return list == null || list.isEmpty() ? "" : String.valueOf(list.get(0));
	}

	public String getCompanyMasterName(String id) {
		String query = "select company_Name FROM Company_Master WHERE COmpany_master_sid=" + id;
		List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
		return list == null || list.isEmpty() ? "" : String.valueOf(list.get(0));
	}

	/**
	 * To check whether a file contains more than one business Unit or not
	 */
	public boolean checkBusinessUnits(FileMananagementResultDTO dto) {
		try {
			int currentBUCount = (Integer) getFileResults(dto, 0, 0, null, null, true);
			int allBUCount = (Integer) getFileResults(dto, 0, 0, null, null, true);
			return allBUCount > currentBUCount ? true : false;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return true;

		}
	}

	/**
	 * Method return a list of companies.
	 *
	 * @param companyId
	 * @return
	 */
	public List getCompanies(int companyId) {

		String query = SQlUtil.getQuery("get-companies");
		if (companyId == 0) {
			query = query.replace("AND CM.COMPANY_MASTER_SID = @GLCOMP", StringUtils.EMPTY);
		} else {
			query = query.replace("@GLCOMP", StringUtils.EMPTY + companyId);
		}

		return HelperTableLocalServiceUtil.executeSelectQuery(query);
	}

	public Object getForecastDetailsExcel(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isRecordLock, boolean isExcelFlag) {
		LOGGER.debug("Entering getForecastDetails ");
		List list;
		String sqlString;
		if (isCount) {
			sqlString = "SELECT count( * ) \n"
					+ " FROM FORECASTING_MASTER FM, ITEM_MASTER IM WHERE FM.NDC=IM.ITEM_ID AND \n" + "FORECAST_NAME=  ";
		} else {
			sqlString = SQlUtil.getQuery("getDetailsResultsExcel");
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType().getDescription())
				&& "US".equals(detailsResultDTO.getCountry())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'LE_FORESIGHT')");
		} else if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType().getDescription())
				&& "PR".equals(detailsResultDTO.getCountry())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_SALES')");
		} else if ("Vaccine Segmentation".equals(detailsResultDTO.getHelperType().getDescription())) {
			sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_VACCINE')");
		}
		sqlString = sqlString.concat(" AND FM.COUNTRY='").concat(detailsResultDTO.getCountry()).concat("'");

		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(" (FM.FORECAST_VER='")
					.concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
		} else {
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(" FM.FORECAST_VER='")
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "FORECAST_YEAR");
		detailsColumn.put(StringConstantUtils.MONTH_PROPERTY, "FORECAST_MONTH");
		detailsColumn.put(StringConstantUtils.ITEM_NO_PROPERTY, "ITEM_NO");
		detailsColumn.put(StringConstantUtils.ITEM_NAME, StringConstantUtils.ITEM_NAME_LIST);
		detailsColumn.put(StringConstantUtils.PRICE_PROPERTY, "PRICE");
		detailsColumn.put(StringConstantUtils.UNITS, "UNITS");
		detailsColumn.put("dollars", "DOLLARS");
		detailsColumn.put(StringConstantUtils.START_DATE, "FORECAST_DATE");

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append('\'');

				} else if (filter instanceof Between) {
					Between stringFilter = (Between) filter;
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					String filterString = formatter.format(stringFilter.getStartValue());
					String filterString1 = formatter.format(stringFilter.getEndValue());
					if (StringConstantUtils.START_DATE.equals(stringFilter.getPropertyId())) {
						filterQuery.append(" AND FORECAST_DATE >= '" ).append( filterString ).append( "' ");
						filterQuery.append(" AND FORECAST_DATE <= '" ).append( filterString1 ).append( "' ");
					}

				}
			}
		}

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
				order = order + " ORDER BY FM.FORECAST_YEAR ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			if (isExcelFlag) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;
			}

		}

		String finalQuery;
		String lockStatus = " AND FM.RECORD_LOCK_STATUS=0";
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("getDetailsResults return List list= {}" , list.size());
		if (isExcelFlag) {
			return finalQuery;
		} else {
			return list;
		}
	}

	public Object getDemandDetailsResultsExcel(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isRecordLock, boolean isExcelFlag) {
		LOGGER.debug("Entering getDemandDetailsResults_Excel Details Results");
		List list;
		String sqlString;

		if (isCount) {
			sqlString = "SELECT Count(DF.DEMAND_FORECAST_SID) FROM DEMAND_FORECAST DF WHERE DF.FORECAST_NAME = ";
		} else {
			sqlString = "SELECT DISTINCT DF.FORECAST_TYPE,DF.FORECAST_YEAR,DF.FORECAST_MONTH,"
					+ "DF.ITEM_ID,DF.ITEM_IDENTIFIER_CODE_QUALIFIER,DF.ITEM_IDENTIFIER,"
					+ "DF.BRAND_ID,DF.SEGMENT,DF.MARKET_SIZE_UNITS,DF.MARKET_SHARE_UNITS,"
					+ "DF.MARKET_SHARE_RATIO,DF.UNCAPTURED_UNITS,"
					+ "DF.UNCAPTURED_UNITS_RATIO,DF.TOTAL_DEMAND_UNITS,DF.TOTAL_DEMAND_AMOUNT,"
					+ "DF.INVENTORY_UNIT_CHANGE,DF.GROSS_UNITS,DF.GROSS_PRICE,"
					+ "DF.GROSS_AMOUNT,DF.NET_SALES_PRICE,DF.NET_SALES_AMOUNT,DF.BATCH_ID," + "DF.ORGANIZATION_KEY "
					+ "FROM DEMAND_FORECAST DF WHERE DF.FORECAST_NAME =";

		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_DFCOUNTRY).concat(detailsResultDTO.getCountry())
				.concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(StringConstantUtils.DFFORECAST_VER_IN + versionArray[0] + "',");
			String tempversionArray = (versionArray[1]).replace('.', '~').trim();
			String[] innerArray = tempversionArray.split("~");
			String outerSize = String.valueOf(innerArray[0]);
			int innerSize;
			for (int i = 1; i <= Integer.parseInt(outerSize); i++) {
				if (Integer.parseInt(versionArray[0]) > Integer.parseInt(outerSize)) {
					innerSize = NumericConstants.NINE;
				} else {
					innerSize = Integer.parseInt(innerArray[1]);
				}
				for (int j = 0; j <= innerSize; j++) {
					String version = String.valueOf(i);
					if (outerSize.contains("0")) {
						version = "0" + String.valueOf(i);
					}
                                        sqlString = sqlString.concat(" '" + version + "." + j + "',");
				}
			}
			if (sqlString.endsWith(",")) {
				sqlString = sqlString.substring(0, sqlString.length() - 1);
			}
			sqlString += ")";
		} else {
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.DFFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("forecastType", "DF.FORECAST_TYPE");
		detailsColumn.put(StringConstantUtils.FORCAST_YEAR, StringConstantUtils.DFFORECAST_YEAR);
		detailsColumn.put(StringConstantUtils.FORECAST_MONTH, StringConstantUtils.DFFORECAST_MONTH);
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.DFITEM_ID);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER, "DF.ITEM_IDENTIFIER_CODE_QUALIFIER");
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER, "DF.ITEM_IDENTIFIER");
		detailsColumn.put(StringConstantUtils.BRAND_ID, StringConstantUtils.DFBRAND_ID);
		detailsColumn.put(StringConstantUtils.SEGMENT, StringConstantUtils.DFSEGMENT);
		detailsColumn.put(StringConstantUtils.MARKET_SIZE_UNITS, StringConstantUtils.DFMARKET_SIZE_UNITS);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_RATIO, StringConstantUtils.DFMARKET_SHARE_RATIO);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_UNITS, StringConstantUtils.DFMARKET_SHARE_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS, StringConstantUtils.DFUNCAPTURED_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS_RATIO, StringConstantUtils.DFUNCAPTURED_UNITS_RATIO);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_UNITS, StringConstantUtils.DFTOTAL_DEMAND_UNITS);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_AMOUNT, StringConstantUtils.DFTOTAL_DEMAND_AMOUNT);
		detailsColumn.put(StringConstantUtils.INVENTORY_UNIT_CHANGE, StringConstantUtils.DFINVENTORY_UNIT_CHANGE);
		detailsColumn.put(StringConstantUtils.GROSS_UNITS, StringConstantUtils.DFGROSS_UNITS);
		detailsColumn.put(StringConstantUtils.GROSS_PRICE, StringConstantUtils.DFGROSS_PRICE);
		detailsColumn.put(StringConstantUtils.GROSS_AMOUNT, StringConstantUtils.DFGROSS_AMOUNT);
		detailsColumn.put(StringConstantUtils.NET_SALES_PRICE, StringConstantUtils.DFNET_SALES_PRICE);
		detailsColumn.put(StringConstantUtils.NET_SALES_AMOUNT, StringConstantUtils.DFNET_SALES_AMOUNT);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.DFBATCH_ID);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.DFORGANIZATION_KEY);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

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
				order = order + " ORDER BY DF.FORECAST_YEAR ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			if (isExcelFlag) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;
			}
		}
		String lockStatus = StringConstantUtils.AND_DFRECORD_LOCK_STATUS;
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}

		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending Demand Details Results");
		if (isExcelFlag) {
			return finalQuery;
		} else {
			return list;
		}
	}

	public Object getAdjustedDemandDetailsResultsExcel(FileMananagementResultDTO detailsResultDTO,
			final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns,
			final Set<Container.Filter> filterSet, boolean isCount, boolean isRecordLock, boolean isExcelflag) {
		LOGGER.debug("Entering getAdjustedDemandDetailsResults_Excel Details Results");
		List list;
		String sqlString;

		if (isCount) {
			sqlString = "SELECT Count(DF.ADJUSTED_DEMAND_FORECAST_SID) FROM ADJUSTED_DEMAND_FORECAST DF WHERE DF.FORECAST_NAME = ";
		} else {
			sqlString = "SELECT DISTINCT DF.ITEM_ID,IM.ITEM_NAME ,\n" + "DF.BRAND_ID,BM.BRAND_NAME ,\n"
					+ "DF.SEGMENT,DF.YEAR,DF.MONTH,DF.MARKET_SIZE_UNITS,DF.MARKET_SHARE_RATIO,DF.MARKET_SHARE_UNITS,\n"
					+ "DF.UNCAPTURED_UNITS,DF.UNCAPTURED_UNITS_RATIO,DF.TOTAL_DEMAND_UNITS,\n"
					+ "DF.TOTAL_DEMAND_AMOUNT,DF.INVENTORY_UNIT_CHANGE,DF.GROSS_UNITS,DF.GROSS_PRICE,\n"
					+ "DF.GROSS_AMOUNT,DF.NET_SALES_PRICE,DF.NET_SALES_AMOUNT,DF.BATCH_ID,DF.SOURCE,DF.ORGANIZATION_KEY\n"
					+ "FROM ADJUSTED_DEMAND_FORECAST  DF \n"
					+ " LEFT JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = DF.ITEM_MASTER_SID\n"
					+ " LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID \n"
					+ "AND IM.BRAND_MASTER_SID = DF.BRAND_MASTER_SID WHERE DF.FORECAST_NAME =  ";

		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_DFCOUNTRY).concat(detailsResultDTO.getCountry())
				.concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(StringConstantUtils.DFFORECAST_VER_IN + versionArray[0] + "',");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.DFFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();

		detailsColumn.put("Year", "DF.YEAR");
		detailsColumn.put("Month", "DF.MONTH");
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.DFITEM_ID);
		detailsColumn.put(StringConstantUtils.ITEM_NAME, "IM.ITEM_NAME");

		detailsColumn.put(StringConstantUtils.SOURCE1, StringConstantUtils.DFSOURCE);
		detailsColumn.put(StringConstantUtils.BRAND_ID, StringConstantUtils.DFBRAND_ID);
		detailsColumn.put("brandName", "BM.BRAND_NAME");
		detailsColumn.put(StringConstantUtils.SEGMENT, StringConstantUtils.DFSEGMENT);
		detailsColumn.put(StringConstantUtils.MARKET_SIZE_UNITS, StringConstantUtils.DFMARKET_SIZE_UNITS);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_RATIO, StringConstantUtils.DFMARKET_SHARE_RATIO);
		detailsColumn.put(StringConstantUtils.MARKET_SHARE_UNITS, StringConstantUtils.DFMARKET_SHARE_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS, StringConstantUtils.DFUNCAPTURED_UNITS);
		detailsColumn.put(StringConstantUtils.UNCAPTURED_UNITS_RATIO, StringConstantUtils.DFUNCAPTURED_UNITS_RATIO);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_UNITS, StringConstantUtils.DFTOTAL_DEMAND_UNITS);
		detailsColumn.put(StringConstantUtils.TOTAL_DEMAND_AMOUNT, StringConstantUtils.DFTOTAL_DEMAND_AMOUNT);
		detailsColumn.put(StringConstantUtils.INVENTORY_UNIT_CHANGE, StringConstantUtils.DFINVENTORY_UNIT_CHANGE);
		detailsColumn.put(StringConstantUtils.GROSS_UNITS, StringConstantUtils.DFGROSS_UNITS);
		detailsColumn.put(StringConstantUtils.GROSS_PRICE, StringConstantUtils.DFGROSS_PRICE);
		detailsColumn.put(StringConstantUtils.GROSS_AMOUNT, StringConstantUtils.DFGROSS_AMOUNT);
		detailsColumn.put(StringConstantUtils.NET_SALES_PRICE, StringConstantUtils.DFNET_SALES_PRICE);
		detailsColumn.put(StringConstantUtils.NET_SALES_AMOUNT, StringConstantUtils.DFNET_SALES_AMOUNT);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.DFBATCH_ID);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.DFORGANIZATION_KEY);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

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
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			if (isExcelflag) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;
			}
		}
		String lockStatus = StringConstantUtils.AND_DFRECORD_LOCK_STATUS;
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending getAdjustedDemandDetailsResults_Excel Details Results");
		if (isExcelflag) {
			return finalQuery;
		} else {
			return list;
		}
	}

	public Object getInventorySummaryResultsExcel(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isRecordLock, boolean isExcelflag) {
		LOGGER.debug("Entering getInventorySummaryResults_Excel Details Results");
		String sqlString;

		if (isCount) {
			sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_MAS_SID) FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME =";
		} else {
			sqlString = "SELECT DISTINCT INW.\"YEAR\",INW.\"MONTH\",INW.\"DAY\",INW.WEEK,INW.ITEM_ID,INW.ITEM_IDENTIFIER_CODE_QUALIFIER,INW.ITEM_IDENTIFIER\n"
					+ ",INW.UNITS_WITHDRAWN,INW.AMOUNT_WITHDRAWN,INW.PRICE,INW.BATCH_ID,INW.ORGANIZATION_KEY FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME =";

		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_INWCOUNTRY_LIKE).concat(detailsResultDTO.getCountry())
				.concat("%'");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.INWFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		String finalQuery;
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "YEAR");
		detailsColumn.put(StringConstantUtils.MONTH_PROPERTY, StringConstantUtils.MONTH_COLUMN);
		detailsColumn.put("week", "WEEK");
		detailsColumn.put("day", "DAY");
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.ITEM_ID_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER,
				StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER, StringConstantUtils.ITEM_IDENTIFIER_LIST);
		detailsColumn.put(StringConstantUtils.UNITS_WITHDRAWN, StringConstantUtils.UNITS_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.AMOUNT_WITHDRAWN, StringConstantUtils.AMOUNT_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.BATCH_ID_LIST);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.ORGANIZATION_KEY_LIST);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

				}

			}
		}
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
				order = order + " ORDER BY INW.YEAR ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			if (isExcelflag) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;
			}
		}
		String lockStatus = " AND INW.RECORD_LOCK_STATUS=0";
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		if (isRecordLock) {
			finalQuery = finalQuery + lockStatus;
		}
		LOGGER.debug("Ending getInventorySummaryResults_Excel Details Results");
		if (isExcelflag) {
			return finalQuery;
		} else {
			return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		}
	}

	public Object getInventoryDetailsResultsExcel(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcelflag) throws Exception {

		LOGGER.debug("Entering getInventory Details Results");
		List list;
		String sqlString;
		if (isCount) {
			sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_DT_SID) FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME =";
		} else {
			sqlString = "SELECT\n" + "    DISTINCT INW.\"YEAR\",\n" + "    INW.\"MONTH\",\n" + "    INW.\"DAY\",\n"
					+ "    INW.WEEK,\n" + "    INW.COMPANY_ID,\n" + "    Cm.COMPANY_NAME,\n " + "    INW.ITEM_ID,\n"
					+ "    im.ITEM_NAME,\n" + "    INW.UNITS_WITHDRAWN,\n" + "    INW.AMOUNT_WITHDRAWN,\n"
					+ "    INW.PRICE,\n" + "    INW.CREATED_BY,\n"
					+ "    CONVERT(VARCHAR(10),INW.CREATED_DATE,101)  AS CREATED_DATE,\n" + "    INW.MODIFIED_BY,\n"
					+ "    CONVERT(VARCHAR(10),INW.MODIFIED_DATE,101)  AS MODIFIED_DATE,\n"
					+ "    INW.INBOUND_STATUS,\n" + "    INW.BATCH_ID,\n" + "    INW.SOURCE,\n"
					+ "    INW.FORECAST_NAME,\n" + "    INW.FORECAST_VER,\n" + "    INW.COUNTRY,\n "
					+ "    INW.ORGANIZATION_KEY\n" + " FROM\n" + "    INVENTORY_WD_PROJ_DT INW "
					+ "    LEFT JOIN dbo.COMPANY_MASTER Cm On cm.COMPANY_MASTER_SID=inw.COMPANY_MASTER_SID "
					+ "    LEFT JOIN dbo.ITEM_MASTER im On im.ITEM_MASTER_SID=inw.ITEM_MASTER_SID "
					+ "    WHERE INW.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_INWCOUNTRY_LIKE).concat(detailsResultDTO.getCountry())
				.concat("%'");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.INWFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		String finalQuery;
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put("year", "YEAR");
		detailsColumn.put(StringConstantUtils.MONTH_PROPERTY, StringConstantUtils.MONTH_COLUMN);
		detailsColumn.put("week", "WEEK");
		detailsColumn.put("day", "DAY");
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.ITEM_ID_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER,
				StringConstantUtils.ITEM_IDENTIFIER_CODE_QUALIFIER_LIST);
		detailsColumn.put(StringConstantUtils.ITEM_IDENTIFIER, StringConstantUtils.ITEM_IDENTIFIER_LIST);
		detailsColumn.put(StringConstantUtils.UNITS_WITHDRAWN, StringConstantUtils.UNITS_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.AMOUNT_WITHDRAWN, StringConstantUtils.AMOUNT_WITHDRAWN_LIST);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.BATCH_ID_LIST);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.ORGANIZATION_KEY_LIST);
		detailsColumn.put(CommonUtils.COUNTRY, COUNTRY);
		detailsColumn.put(StringConstantUtils.FORECAST_VERSION, "FORECAST_VER");
		detailsColumn.put(CommonUtils.FORECAST_NAME, StringConstantUtils.FORECAST_NAME_LIST);
		detailsColumn.put(StringConstantUtils.SOURCE1, StringConstantUtils.SOURCE);
		detailsColumn.put("inboundStatus", "INBOUND_STATUS");
		detailsColumn.put("modifiedDate", "MODIFIED_DATE");
		detailsColumn.put("createdDate", "CREATED_DATE");
		detailsColumn.put(COMPANY_NAME, "COMPANY_NAME");
		detailsColumn.put(StringConstantUtils.ITEM_NAME, StringConstantUtils.ITEM_NAME_LIST);

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

				}

			}
		}
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
				order = order + " ORDER BY INW.YEAR ";
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			if (isExcelflag) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;
			}
		}
		if (isCount) {
			finalQuery = sqlString + filterQuery.toString();
		} else {
			finalQuery = sqlString + filterQuery.toString() + order;
		}
		list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		LOGGER.debug("Ending getInventory Details Results");
		if (isExcelflag) {
			return finalQuery;
		} else {
			return list;
		}
	}

	public Object getCustomerSalesResultsExcel(FileMananagementResultDTO detailsResultDTO, final int startIndex,
			final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet,
			boolean isCount, boolean isExcelflag) {
		LOGGER.debug("Entering getCustomerSalesResults_Excel Details Results");
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
					+ "DF.FORECAST_VER,DF.COUNTRY,DF.FORECAST_NAME,CONVERT(VARCHAR(10),DF.FORECAST_DATE,101)  AS FORECAST_DATE,"
					+ "DF.CUSTOMER_GTS_FORECAST_INTF_ID " + "FROM CUSTOMER_GTS_FORECAST DF WHERE DF.FORECAST_NAME =";
		}
		sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
		sqlString = sqlString.concat(StringConstantUtils.AND_DFCOUNTRY).concat(detailsResultDTO.getCountry())
				.concat("'");
		if (detailsResultDTO.getVersion().contains("~")) {
			String[] versionArray = detailsResultDTO.getVersion().split("~");
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE)
					.concat(StringConstantUtils.DFFORECAST_VER_IN + versionArray[0] + "',");
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
			sqlString = sqlString.concat(StringConstantUtils.AND_SPACE).concat(StringConstantUtils.DFFORECAST_VER)
					.concat(detailsResultDTO.getVersion()).concat("'");
		}
		StringBuilder filterQuery = new StringBuilder();
		HashMap<String, String> detailsColumn = new HashMap<>();
		detailsColumn.put(StringConstantUtils.FORCAST_YEAR, StringConstantUtils.DFFORECAST_YEAR);
		detailsColumn.put(StringConstantUtils.FORECAST_MONTH, StringConstantUtils.DFFORECAST_MONTH);
		detailsColumn.put(StringConstantUtils.ITEM_ID, StringConstantUtils.DFITEM_ID);
		detailsColumn.put("companyId", "DF.COMPANY_ID");
		detailsColumn.put(StringConstantUtils.UNITS, "DF.UNITS");
		detailsColumn.put("priceType", "DF.PRICE_TYPE");
		detailsColumn.put(StringConstantUtils.PRICE_PROPERTY, "DF.PRICE");
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
		detailsColumn.put(StringConstantUtils.BRAND, "DF.BRAND");
		detailsColumn.put(StringConstantUtils.SEGMENT, StringConstantUtils.DFSEGMENT);
		detailsColumn.put(StringConstantUtils.BATCH_ID, StringConstantUtils.DFBATCH_ID);
		detailsColumn.put(StringConstantUtils.ORGANIZATION_KEY, StringConstantUtils.DFSOURCE);
		detailsColumn.put(StringConstantUtils.FORECAST_VERSION, "DF.FORECAST_VER");
		detailsColumn.put(CommonUtils.COUNTRY, "DF.COUNTRY");
		detailsColumn.put(CommonUtils.FORECAST_NAME, "DF.FORECAST_NAME");
		detailsColumn.put("forecastDate", "DF.FORECAST_DATE");
		detailsColumn.put("customerGtsForecastIntfId", "DF.CUSTOMER_GTS_FORECAST_INTF_ID");

		if (filterSet != null) {
			for (Container.Filter filter : filterSet) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";

					filterQuery.append(StringConstantUtils.AND_SPACE)
							.append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())))
							.append( StringConstantUtils.LIKE_SPACE ).append( filterString ).append( '\'');

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
				order = order + StringConstantUtils.ORDER_BY_DFFORECAST_YEAR;
			} else {
				order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? ASC_SPACE : DESC_SPACE);
			}
			if (isExcelflag) {
				order = appendOffsetPlaceHolder(order);
			} else {
				order = order + " " + StringConstantUtils.OFFSET_SPACE;
				order = order + startIndex;
				order = order + StringConstantUtils.ROWS_FETCH_NEXT + endIndex;
				order = order + StringConstantUtils.ROWS_ONLY;
			}
		}
		String whereQuery = ConstantsUtils.EMPTY;
		if (detailsResultDTO.getItemId() != null && !StringUtils.isEmpty(detailsResultDTO.getItemId())) {
			whereQuery = "AND DF.ITEM_ID like '" + detailsResultDTO.getItemId().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getCompanyId() != null && !StringUtils.isEmpty(detailsResultDTO.getCompanyId())) {
			whereQuery = "AND DF.COMPANY_ID like '" + detailsResultDTO.getCompanyId().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getForcastYear() != null && !StringUtils.isEmpty(detailsResultDTO.getForcastYear())) {
			whereQuery = "AND DF.FORECAST_YEAR like '" + detailsResultDTO.getForcastYear().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getForecastMonth() != null && !StringUtils.isEmpty(detailsResultDTO.getForecastMonth())) {
			whereQuery = "AND DF.FORECAST_MONTH like '" + detailsResultDTO.getForecastMonth().replace('*', '%') + "'";
		}

		if (detailsResultDTO.getDeductionId() != null && !StringUtils.isEmpty(detailsResultDTO.getDeductionId())) {
			whereQuery = "AND DF.DEDUCTION_ID like '" + detailsResultDTO.getDeductionId().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getDeductionCategory() != null
				&& !StringUtils.isEmpty(detailsResultDTO.getDeductionCategory())
				&& !SELECT_ONE.equals(detailsResultDTO.getDeductionCategory())) {
			whereQuery = "AND DF.DEDUCTION_CATEGORY like '" + detailsResultDTO.getDeductionCategory().replace('*', '%')
					+ "'";
		}
		if (detailsResultDTO.getDeductionType() != null && !StringUtils.isEmpty(detailsResultDTO.getDeductionType())
				&& !SELECT_ONE.equals(detailsResultDTO.getDeductionType())) {
			whereQuery = "AND DF.DEDUCTION_TYPE like '" + detailsResultDTO.getDeductionType().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getDeductionProgramType() != null
				&& !StringUtils.isEmpty(detailsResultDTO.getDeductionProgramType())
				&& !SELECT_ONE.equals(detailsResultDTO.getDeductionProgramType())) {
			whereQuery = "AND DF.DEDUCTION_PROGRAM_TYPE like '"
					+ detailsResultDTO.getDeductionProgramType().replace('*', '%') + "'";
		}
		if (detailsResultDTO.getBatchId() != null && !StringUtils.isEmpty(detailsResultDTO.getBatchId())) {
			whereQuery = "AND DF.BATCH_ID like '" + detailsResultDTO.getBatchId().replace('*', '%') + "'";
		}

		if (isCount) {
			finalQuery = sqlString + whereQuery + filterQuery.toString();
		} else {
			finalQuery = sqlString + whereQuery + filterQuery.toString() + order;
		}
		if (isExcelflag) {
			return finalQuery;
		} else {
			return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
		}
	}

	public List<FileManagementDTO> getSearchResult(ComboBox fileType) {
		LOGGER.info("Entering getSearchResult");
		List<FileManagementDTO> processList = new ArrayList<>();
		try {
			String query = "SELECT WP.PROCESS_SID, WP.PROCESS_DISPLAY_NAME \n"
					+ ",WP.EMAIL_NOTIFICATION_SUCCESS_TO ,WP.EMAIL_NOTIFICATION_SUCCESS_CC"
					+ " ,WP.EMAIL_NOTIFICATION_FAILURE_TO,WP.EMAIL_NOTIFICATION_FAILURE_CC"
					+ " ,WP.SUCCESS_MAIL_SUBJECT,WP.SUCCESS_MAIL_BODY "
					+ " ,WP.FAILURE_MAIL_SUBJECT,WP.FAILURE_MAIL_BODY "
					+ " FROM WORKFLOW_PROFILE WP WHERE WP.PROCESS_NAME like  '"
					+ CommonUtil.getSelectedFileType(fileType) + "' \n";
			List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					FileManagementDTO dto = new FileManagementDTO();
					dto.setProcessSid(Integer.valueOf(String.valueOf(obj[0])));
					dto.setProcessName(String.valueOf(obj[1]));
					dto.setSuccessTo(String.valueOf(obj[NumericConstants.TWO]));
					dto.setSuccessCC(String.valueOf(obj[NumericConstants.THREE]));
					dto.setFailTo(String.valueOf(obj[NumericConstants.FOUR]));
					dto.setFailCC(String.valueOf(obj[NumericConstants.FIVE]));
					dto.setSuccessSubject(String.valueOf(obj[NumericConstants.SIX]));
					dto.setSuccessText(String.valueOf(obj[NumericConstants.SEVEN]));
					dto.setFailSubject(String.valueOf(obj[NumericConstants.EIGHT]));
					dto.setFailText(String.valueOf(obj[NumericConstants.NINE]));
					processList.add(dto);
				}

			}

			return processList;

		} catch (Exception ex) {

			LOGGER.error(ex.getMessage());
			return Collections.emptyList();
		}
	}

	private String appendOffsetPlaceHolder(String query) {
		String offsetQuery = query + " " + StringConstantUtils.OFFSET_SPACE;
		offsetQuery = offsetQuery + "?";
		offsetQuery = offsetQuery + StringConstantUtils.ROWS_FETCH_NEXT + "?";
		offsetQuery = offsetQuery + StringConstantUtils.ROWS_ONLY;
		return offsetQuery;
	}

}
