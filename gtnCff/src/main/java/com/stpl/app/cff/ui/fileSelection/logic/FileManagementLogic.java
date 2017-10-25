
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.logic;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.dao.FileManagementLogicDAO;
import com.stpl.app.cff.dao.impl.FileManagementLogicDAOImpl;
import com.stpl.app.cff.ui.fileSelection.dto.FileManagementDTO;
import com.stpl.app.cff.ui.fileSelection.dto.FileMananagementResultDTO;
import com.stpl.app.cff.ui.fileSelection.dto.ItemSearchDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.FileManagement;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.FileManagementLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.Order;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
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
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManagementLogic.
 *
 * @author santanukumar
 */
public class FileManagementLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FileManagementLogic.class);// Logger
    // Declaration
    /**
     * The Constant NULLCREATION.
     */
    private static final Criterion NULLCREATION = null;
    /**
     * The dao.
     */
    final private static FileManagementLogicDAO DAO = new FileManagementLogicDAOImpl();
    /**
     * The foecast year count.
     */
    private static int foecastYearCount;
    /**
     * The ITEM_QUAL_NAME.
     */
    public static String ITEM_QUAL_NAME = "itemQualifierName";
    /**
     * The ITEM_QUALIFIER_SID.
     */
    public final static String ITEM_QUALIFIER_SID = "itemQualifierSid";
    
    Map<String, String> monthMap = new HashMap<String, String>();
    
    HashMap<String, String> columnNames = new HashMap<String, String>();
      String DOLLAR ="$";
     DecimalFormat dollarFormat = new DecimalFormat("#,##0.00");
     DecimalFormat unitsFormat = new DecimalFormat("#0.0");
    static DecimalFormat priceFormat = new DecimalFormat("#0.00");
    final DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
    final DateFormat dateFormatToParse = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT_TO_PARSE);
      public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
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
     * @param itemPricingQualifierNameCount the new foecast year count
     */
    public void setFoecastYearCount(final int itemPricingQualifierNameCount) {
        this.foecastYearCount = itemPricingQualifierNameCount;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public FileManagementLogicDAO getDao() {
        return DAO;
    }
    
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Gets the forecast year.
     *
     * @return the forecast year
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ForecastingMaster> getForecastYear() throws SystemException  {
        LOGGER.debug("getForecastYear started");
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class);
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORECAST_YEAR)));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.FORECAST_YEAR));
        LOGGER.debug("getForecastYear return List<ForecastingMaster> resultList");
        return DAO.getForecastList(dynamicQuery);
    }

    /**
     * Gets the details summ.
     *
     * @param fileName the file name
     * @param version the version
     * @param fileType the file type
     * @param country the country
     * @return the details summ
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public FileManagementDTO getDetailsSumm(final String fileName, final String version, final String fileType, final String country) throws SystemException {
        List<ForecastingMaster> resultsList;
        final FileManagementDTO fileMgtDTO = new FileManagementDTO();
        final DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        
        Criterion criterion;
        String sqlString = "";
        LOGGER.debug("getDetailsSumm started with P1:String fileName=" + fileName + " P2:String version=" + version + " P3:String fileType=" + fileType + " P4:String country" + country);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORECAST_NAME, fileName));
        if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType)) {
            if (ConstantsUtils.COUNTRY_US.equals(country)) {
                final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.FORE_SIGHT);
                criterion = RestrictionsFactoryUtil.or(criterion1, RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.LE_FORESIGHT));
            } else if (ConstantsUtils.COUNTRY_PR.equals(country)) {
                criterion = RestrictionsFactoryUtil.ilike(ConstantsUtils.SOURCE, ConstantsUtils.FF_SALES);
            } else {
                criterion = NULLCREATION;
            }
            dynamicQuery.add(criterion);
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.COUNTRY, country));
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
        } else if (ConstantsUtils.DEMAND.equals(fileType)) {
            sqlString = "SELECT DF.FORECAST_NAME,DF.FORECAST_VER,DF.CREATED_DATE FROM DEMAND_FORECAST DF WHERE FORECAST_NAME like '" + fileName + "' AND COUNTRY like '" + country + "' AND FORECAST_VER like '" + version + "'";
            List<ForecastingMaster> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
            if (!resultsLists.isEmpty()) {
                for (Object resultsList1 : resultsLists) {
                    final Object[] obj = (Object[]) resultsList1;
                    fileMgtDTO.setForecastName(String.valueOf(obj[0]));
                    fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
                    fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
                }
            }
        } else if (ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType)) {
            sqlString = "SELECT INW.FORECAST_NAME,INW.FORECAST_VER,INW.CREATED_DATE FROM INVENTORY_WD_PROJ_MAS INW WHERE INW.FORECAST_NAME like '" + fileName + "' AND INW.COUNTRY like '" + country + "' AND INW.FORECAST_VER like '" + version + "'";
            List<ForecastingMaster> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
            if (!resultsLists.isEmpty()) {
                for (Object resultsList1 : resultsLists) {
                    final Object[] obj = (Object[]) resultsList1;
                    fileMgtDTO.setForecastName(String.valueOf(obj[0]));
                    fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
                    fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
                }
            }
        } else if (ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType)) {
            sqlString = "SELECT INW.FORECAST_NAME,INW.FORECAST_VER,INW.CREATED_DATE FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME like '" + fileName + "' AND INW.COUNTRY like '" + country + "%' AND INW.FORECAST_VER like '" + version + "'";
            List<ForecastingMaster> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
            if (!resultsLists.isEmpty()) {
                for (Object resultsList1 : resultsLists) {
                    final Object[] obj = (Object[]) resultsList1;
                    fileMgtDTO.setForecastName(String.valueOf(obj[0]));
                    fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
                    fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
                }
            }
        } else if (ConstantsUtils.CUSTOMERGTS.equals(fileType)) {
            sqlString = "SELECT DF.FORECAST_NAME,DF.FORECAST_VER,DF.CREATED_DATE FROM CUSTOMER_GTS_FORECAST DF WHERE FORECAST_NAME like '" + fileName + "' AND COUNTRY like '" + country + "' AND FORECAST_VER like '" + version + "'";
            List<ForecastingMaster> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
            if (!resultsLists.isEmpty()) {
                for (Object resultsList1 : resultsLists) {
                    final Object[] obj = (Object[]) resultsList1;
                    fileMgtDTO.setForecastName(String.valueOf(obj[0]));
                    fileMgtDTO.setForecastVersion(String.valueOf(obj[1]));
                    fileMgtDTO.setCreatedDate(String.valueOf(dateFormat.format(obj[NumericConstants.TWO])));
                }
            }
        } else if (ConstantsUtils.ADJUSTED_DEMAND.equals(fileType)) {
            sqlString = "SELECT FORECAST_NAME, FORECAST_VER, CREATED_DATE FROM dbo.ADJUSTED_DEMAND_FORECAST WHERE FORECAST_NAME like '" + fileName + "' AND COUNTRY like '" + country + "' AND FORECAST_VER like '" + version + "'";
            List<ForecastingMaster> resultsLists = HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
            if (!resultsLists.isEmpty()) {
                for (Object resultsList1 : resultsLists) {
                    final Object[] obj = (Object[]) resultsList1;
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
     * @param fileMgtDTO the file mgt dto
     * @param fileType the file type
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public String saveFileMgtHist(final FileMananagementResultDTO fileMgtDTO, final HelperDTO fileType, final SessionDTO sessionDTO) throws SystemException {
        final String userId = (String) sessionDTO.getUserId();
        LOGGER.debug("saveFileMgtHist started with P1:FileMananagementResultDTO fileMgtDTO and P2:String fileType=" + fileType);
        FileManagement fileManagement = FileManagementLocalServiceUtil.createFileManagement(0);
        fileManagement.setForecastName(fileMgtDTO.getFileName());
        fileManagement.setForecastSource(fileMgtDTO.getType());
        fileManagement.setFileSource(fileMgtDTO.getFileType());
        fileManagement.setVersion(fileMgtDTO.getVersion());
        fileManagement.setFromPeriod(new Date());
        fileManagement.setCreatedDate(new Date());
        fileManagement.setModifiedDate(new Date());
        fileManagement.setCreatedBy(Integer.valueOf(userId));
        fileManagement.setFileType(fileType.getId());
        List<FileManagement> resultsList;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class);
        final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
        dynamicQuery.addOrder(defaultOrder);
        Criterion criterion;
        final Criterion Criteria = RestrictionsFactoryUtil.eq("fileType", fileType.getId());
        if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
            final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
            final Criterion criterion2 = RestrictionsFactoryUtil.or(criterion1, RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT));
            criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion2, RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FF_SALES)), Criteria);
        } else if (ConstantsUtils.DEMAND.equals(fileType.getDescription()) || ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription())) {
            criterion = Criteria;
        } else {
            criterion = NULLCREATION;
        }
        dynamicQuery.add(criterion);
        
        resultsList = DAO.getFilesList(dynamicQuery);
        if (!resultsList.isEmpty()) {
            final FileManagement currentActiveFile = resultsList.get(0);
            currentActiveFile.setToPeriod(new Date());
            currentActiveFile.setModifiedDate(new Date());
            currentActiveFile.setModifiedBy(Integer.valueOf(userId));
            currentActiveFile.setFileType(fileType.getId());
            
            DAO.updateFiles(currentActiveFile);
        }
        DAO.addFiles(fileManagement);
        final String msg = ConstantsUtils.SUCCESS;
        
        LOGGER.debug("saveFileMgtHist return String msg=" + msg);
        return msg;
    }

    /**
     * Gets the current file info.
     *
     * @param fileType the file type
     * @return the current file info
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public FileManagementDTO getCurrentFileInfo(final HelperDTO fileType) throws SystemException {
        
        final FileManagementDTO fileMgtDTO = new FileManagementDTO();
        LOGGER.debug("getCurrentFileInfo started with P1:String fileType=" + fileType);
        List<FileManagement> resultsList;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class);
        final Order defaultOrder = OrderFactoryUtil.desc(ConstantsUtils.CREATE_DATE);
        dynamicQuery.addOrder(defaultOrder);
        Criterion criterion;
        
        Criterion criteria = RestrictionsFactoryUtil.eq("fileType", fileType.getId());
        if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
            final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
            criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1, RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
        } else if (ConstantsUtils.DEMAND.equals(fileType.getDescription()) || ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription()) || ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())) {
            criterion = criteria;
        } else {
            criterion = NULLCREATION;
        }
        dynamicQuery.add(criterion);
        resultsList = DAO.getFilesList(dynamicQuery);
        if (!resultsList.isEmpty()) {
            final FileManagement fileMgt = (FileManagement) resultsList.get(0);
            final DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
            fileMgtDTO.setCurrentFile(fileMgt.getForecastName());
            fileMgtDTO.setForecastVersion(fileMgt.getVersion());
            fileMgtDTO.setEffectiveDate(fileMgt.getCreatedDate() == null ? ConstantsUtils.EMPTY : dateFormat.format(fileMgt.getCreatedDate()));
        }
        
        LOGGER.debug("getCurrentFileInfo return FileManagementDTO fileMgtDTO");
        return fileMgtDTO;
    }

    /**
     * Gets the forecast year count.
     *
     * @param filterText the filter text
     * @return the forecast year count
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static int getForecastYearCount(final String filterText) throws PortalException, SystemException {
        final String filter = StringUtils.trimToEmpty(filterText);
        LOGGER.debug("Entering getLazyPriceTypeCount method with filterText :" + filter);
        List<Object[]> qualifierList;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class);
        if (!filter.equals("")) {
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
     * @param startIndex the start index
     * @param end the end
     * @param filter the filter
     * @return the forecast year results
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public static List<HelperDTO> getForecastYearResults(final int startIndex, final int end, final String filter) throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        final String filterText = StringUtils.trimToEmpty(filter) + "%";
        LOGGER.debug("Entering getLazyPriceTypeResults method with filterText :" + filterText);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class);
        dynamicQuery.setLimit(startIndex, end);
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORECAST_YEAR)));
        
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
        LOGGER.debug("Ending getLazyPriceTypeResults  return list size :" + +list.size());
        return list;
    }
    
    public List<FileMananagementResultDTO> getItemSearchtrsults(final String itemName, final String itemNo) throws SystemException {
        List<FileMananagementResultDTO> resultList = new ArrayList<FileMananagementResultDTO>();
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
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
    
    public void populateAll(String clickEvent, final BeanItemContainer<FileMananagementResultDTO> detailsBean, ExtFilterTable detailsTable) throws PortalException, SystemException {
        if (ConstantsUtils.CHECK.equalsIgnoreCase(clickEvent)) {
            final List<FileMananagementResultDTO> itemIds;
            itemIds = detailsBean.getItemIds();
            for (int i = 0; i < itemIds.size(); i++) {
                final FileMananagementResultDTO beanItem = itemIds.get(i);
                if (!beanItem.isRecordLockStatus()) {
                    detailsTable.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(true);
                }
            }
        } else if ("uncheck".equalsIgnoreCase(clickEvent)) {
            final List<FileMananagementResultDTO> itemIds = detailsBean.getItemIds();
            for (int i = 0; i < itemIds.size(); i++) {
                final FileMananagementResultDTO beanItem = itemIds.get(i);
                if (!beanItem.isRecordLockStatus()) {
                    detailsTable.getContainerProperty(beanItem, ConstantsUtils.CHECK).setValue(false);
                }
            }
        }
    }
    
    public String saveForecastDetails(List<FileMananagementResultDTO> itemIds, String source, String country, String version, String forecastName, String fileType) throws SystemException {
        LOGGER.debug("Entering Save Forecast Details with File Name" + forecastName + "File Type" + fileType.equals(ConstantsUtils.EX_FACTORY_SALES)
                + "source" + source + "Country" + country);
        Boolean flag = false;
        for (int i = 0; i < itemIds.size(); i++) {
            
            final FileMananagementResultDTO beanItem = itemIds.get(i);
            if (!beanItem.isRecordLockStatus()) {
                
                ForecastingMaster master;
                DemandForecast forecast;
                AdjustedDemandForecast adjustedforecast;
                InventoryWdProjMas inventoryWdProjMas;
                if (fileType.equals(ConstantsUtils.EX_FACTORY_SALES)) {
                    master = ForecastingMasterLocalServiceUtil.createForecastingMaster(0);
                    flag = true;
                    master.setForecastYear(beanItem.getYear().toString());
                    master.setForecastMonth(beanItem.getMonth().toString());
                    master.setNdc(beanItem.getItemNo());
                    master.setRecordLockStatus(false);
                    Date date = new Date();
                    master.setForecastDate(beanItem.getStartDate());
                    master.setUnits(Double.valueOf(beanItem.getUnits().toString().replace("$", ConstantsUtils.EMPTY)));
                    master.setPrice(Double.valueOf(beanItem.getPrice().toString().replace("$", ConstantsUtils.EMPTY)));
                    master.setDollars(Double.valueOf(beanItem.getDollars().toString().replace("$", ConstantsUtils.EMPTY)));
                    master.setSource(source);
                    master.setCountry(country);
                    master.setForecastVer(version);
                    master.setForecastName(forecastName);
                    master.setCreatedDate(new Date());
                    master.setModifiedDate(date);
                    DAO.addForecastDetails(master);
                } else if (fileType.equals(ConstantsUtils.DEMAND)) {
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
                    forecast.setMarketSizeUnits(Double.valueOf(beanItem.getMarketSizeUnits()));
                    forecast.setMarketShareUnits(Double.valueOf(beanItem.getMarketShareUnits()));
                    forecast.setMarketShareRatio(beanItem.getMarketShareRatio());
                    forecast.setUncapturedUnits(Double.valueOf(beanItem.getUncapturedUnits()));
                    forecast.setUncapturedUnitsRatio(beanItem.getUncapturedUnitsRatio());
                    forecast.setTotalDemandUnits(Double.valueOf(beanItem.getTotalDemandUnits()));
                    forecast.setTotalDemandAmount(Double.valueOf(beanItem.getTotalDemandAmount()));
                    forecast.setInventoryUnitChange(Double.valueOf(beanItem.getInventoryUnitChange()));
                    forecast.setGrossUnits(Double.valueOf(beanItem.getGrossUnits()));
                    forecast.setGrossPrice(Double.valueOf(beanItem.getGrossPrice()));
                    forecast.setGrossAmount(Double.valueOf(beanItem.getGrossAmount()));
                    forecast.setNetSalesPrice(Double.valueOf(beanItem.getNetSalesPrice()));
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
                    } else if (fileType.equals(ConstantsUtils.ADJUSTED_DEMAND)) {
                        try {
                            adjustedforecast = AdjustedDemandForecastLocalServiceUtil.createAdjustedDemandForecast(0);
                            flag = true;
                            adjustedforecast.setForecastType(beanItem.getForecastType());
                            adjustedforecast.setYear(beanItem.getForcastYear());
                            adjustedforecast.setMonth(beanItem.getForecastMonth());
                            adjustedforecast.setItemId(beanItem.getItemId());
                            adjustedforecast.setBrandId(beanItem.getBrandId());
                            adjustedforecast.setSegment(beanItem.getSegment());
                            adjustedforecast.setMarketSizeUnits(Double.valueOf(beanItem.getMarketSizeUnits() == StringUtils.EMPTY ? "0" : beanItem.getMarketSizeUnits()));
                            adjustedforecast.setMarketShareUnits(Double.valueOf((beanItem.getMarketShareUnits() == null || beanItem.getMarketShareUnits().trim() == StringUtils.EMPTY) ? "0" : beanItem.getMarketShareUnits()));
                            adjustedforecast.setMarketShareRatio(beanItem.getMarketShareRatio() == StringUtils.EMPTY ? "0" : beanItem.getMarketShareRatio());
                            adjustedforecast.setUncapturedUnits(Double.valueOf(beanItem.getUncapturedUnits() == StringUtils.EMPTY ? "0" : beanItem.getUncapturedUnits()));
                            adjustedforecast.setUncapturedUnitsRatio(beanItem.getUncapturedUnitsRatio());
                            adjustedforecast.setTotalDemandUnits(Double.valueOf(beanItem.getTotalDemandUnits().trim() == StringUtils.EMPTY ? "0" : beanItem.getTotalDemandUnits()));
                            adjustedforecast.setTotalDemandAmount(Double.valueOf(beanItem.getTotalDemandAmount() == StringUtils.EMPTY ? "0" : beanItem.getTotalDemandAmount()));
                            adjustedforecast.setInventoryUnitChange(Double.valueOf(beanItem.getInventoryUnitChange() == StringUtils.EMPTY ? "0" : beanItem.getInventoryUnitChange()));
                            adjustedforecast.setGrossUnits(Double.valueOf(beanItem.getGrossUnits() == StringUtils.EMPTY ? "0" : beanItem.getGrossUnits()));
                            adjustedforecast.setGrossPrice(Double.valueOf(beanItem.getGrossPrice() == StringUtils.EMPTY ? "0" : beanItem.getGrossPrice()));
                            adjustedforecast.setGrossAmount(Double.valueOf(beanItem.getGrossAmount() == StringUtils.EMPTY ? "0" : beanItem.getGrossAmount()));
                            adjustedforecast.setNetSalesPrice(Double.valueOf(beanItem.getNetSalesPrice() == StringUtils.EMPTY ? "0" : beanItem.getNetSalesPrice()));
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
                            LOGGER.error(e);
                        }
                    } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
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
                } else if (fileType.equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
                    String query = insertQueryForInventoryDetails();
                    if (beanItem.getYear() == null || "".equals(beanItem.getYear())) {
                        query += "0";
                    } else {
                        query += "'" + beanItem.getYear() + "'";
                    }
                    query += ",NULL".equals(buildQuery(beanItem.getMonth())) ? "," + 0 : buildQuery(beanItem.getMonth());
                    query += buildQuery(beanItem.getDay());
                    query += buildQuery(beanItem.getWeek());
                    query += ",NULL".equals(buildQuery(beanItem.getCompanyId())) ? "," + 0 : buildQuery(beanItem.getCompanyId());
                    query += buildQuery(beanItem.getIdentifierCodeQualifier());
                    query += buildQuery(beanItem.getCompanyIdentifier());
                    query += ",NULL".equals(buildQuery(beanItem.getItemId())) ? "," + 0 : buildQuery(beanItem.getItemId());
                    query += buildQuery(beanItem.getItemIdentifierCodeQualifier());
                    query += ",NULL".equals(buildQuery(beanItem.getItemIdentifier())) ? ",'" + 0 + "'" : buildQuery(beanItem.getItemIdentifier());
                    query += ",NULL".equals(buildQuery(beanItem.getUnitsWithdrawn())) ? "," + 0 : buildQuery(beanItem.getUnitsWithdrawn());
                    query += buildQuery(beanItem.getAmountWithdrawn());
                    query += buildQuery(beanItem.getPrice());
                    query += ",'" + convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT) + "'";
                    query += ",'" + convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT) + "'";
                    query += ",NULL".equals(buildQuery(beanItem.getBatchId())) ? ",'" + 0 + "'" : buildQuery(beanItem.getBatchId());
                    query += ",'" + source + "'";
                    query += ",'" + forecastName + "'";
                    query += ",'" + version + "'";
                    query += ",'" + country + "'";
                    query += ",NULL".equals(buildQuery(beanItem.getOrganizationKey())) ? ",'" + 0 + "'" : buildQuery(beanItem.getOrganizationKey());
                    query += ",NULL".equals(buildQuery(beanItem.getCompanyId())) ? ",'" + 0 + "'" : buildQuery(beanItem.getCompanyId());
                    query += ",NULL".equals(buildQuery(beanItem.getItemId())) ? ",'" + 0 + "'" : buildQuery(beanItem.getItemId());
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
            query += ",NULL";
        } else {
            query += ",'" + value + "'";
        }
        return query;
    }
    
    public void updateAutoModeProcess(final Date date) throws SystemException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        List<ForecastConfig> config;
        dynamicQuery.add(RestrictionsFactoryUtil.eq("processType", true));
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
     * @param listType the list type
     * @return the item type
     */
    public List<HelperDTO> getItemType(final String listType) throws SystemException {
        
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        
        LOGGER.debug("Entering getItemType P1:" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
                
            }
        }
        LOGGER.debug("returns size " + helperList.size());
        
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
    public List<HelperDTO> getItemQualifierNameResults() throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ITEM_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ITEM_QUAL_NAME));
        ifpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        ifpDynamicQuery.addOrder(OrderFactoryUtil.asc(ITEM_QUAL_NAME));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ITEM_QUAL_NAME, StringUtils.EMPTY)));
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
        LOGGER.debug("return CompanyQualifier size -" + list.size());
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
    public List<HelperDTO> getBrandResults() throws PortalException, SystemException {
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);
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
        LOGGER.debug("return Brand size -" + list.size());
        return list;
    }
    
    private void loadMonthMap() {
        monthMap.put("1", "Jan");
        monthMap.put("2", "Feb");
        monthMap.put("3", "Mar");
        monthMap.put("4", "Apr");
        monthMap.put("5", "May");
        monthMap.put("6", "Jun");
        monthMap.put("7", "Jul");
        monthMap.put("8", "Aug");
        monthMap.put("9", "Sep");
        monthMap.put("10", "Oct");
        monthMap.put("11", "Nov");
        monthMap.put("12", "Dec");
        
    }

    /**
     * *
     * Gets the file history results based on country and fileType.
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
    public Object getFileHistoryResults(final HelperDTO fileType, final String country, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException {
        
        LOGGER.debug("Entering getFileHistoryResults ");
        DynamicQuery projectionDynamicQuery = null;
        Object object = new Object();
        Criterion criterion = null;
        List<FileManagement> resultsList;
        
        projectionDynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class);
        Criterion criteria = RestrictionsFactoryUtil.eq("fileType", fileType.getId());
        if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription()) && ConstantsUtils.COUNTRY_US.equals(country)) {
            final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
            criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1, RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
        } else if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription()) && ConstantsUtils.COUNTRY_PR.equals(country)) {
            criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FF_SALES), criteria);
        } else if (ConstantsUtils.EX_FACTORY_SALES.equals(fileType.getDescription())) {
            final Criterion criterion1 = RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.FORE_SIGHT);
            criterion = RestrictionsFactoryUtil.and(RestrictionsFactoryUtil.or(criterion1, RestrictionsFactoryUtil.ilike(ConstantsUtils.TYPE, ConstantsUtils.LE_FORESIGHT)), criteria);
        } else if (ConstantsUtils.DEMAND.equals(fileType.getDescription()) || ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(fileType.getDescription()) || ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(fileType.getDescription())) {
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
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastName", filterString));
                    }
                    if ("type".equals(stringFilter.getPropertyId())) {
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastSource", filterString));
                    }
                    if ("version".equals(stringFilter.getPropertyId())) {
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.ilike("version", filterString));
                    }
                    
                } else if (filter instanceof Compare) {
                    
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    if ("version".equalsIgnoreCase(String.valueOf(compare.getPropertyId()))) {
                        int value = Integer.valueOf(String.valueOf(compare.getValue()));
                        if (operation.GREATER.toString().equalsIgnoreCase(operation.name())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.ge("version", value));
                        } else if (operation.LESS.toString().equalsIgnoreCase(operation.name())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.le("version", value));
                        } else if (operation.EQUAL.toString().equalsIgnoreCase(operation.name())) {
                            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq("version", value));
                        }
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    
                    if ("effectiveDate".equals(stringFilter.getPropertyId())) {
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.CREATED_DATE, filterString));
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.CREATED_DATE, filterString1));
                    }
                    if ("fromPeriod".equals(stringFilter.getPropertyId())) {
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.ge("fromPeriod", filterString));
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.lt("fromPeriod", filterString1));
                    }
                    if ("toPeriod".equals(stringFilter.getPropertyId())) {
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.ge("toPeriod", filterString));
                        projectionDynamicQuery.add(RestrictionsFactoryUtil.lt("toPeriod", filterString1));
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
            } else {
                if (sortOrder) {
                    projectionDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn));
                } else {
                    projectionDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn));
                }
            }
            
        }
        final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<FileMananagementResultDTO>();
        
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
                fileMgtDTO.setVersion(fileMgt.getVersion().equals(ConstantsUtils.NULL) ? ConstantsUtils.EMPTY : fileMgt.getVersion());
                fileMgtDTO.setFromPeriod(fileMgt.getFromPeriod() == null ? null : fileMgt.getFromPeriod());
                fileMgtDTO.setToPeriod(fileMgt.getToPeriod() == null ? null : fileMgt.getToPeriod());
                fileMgtDTO.setFileId(String.valueOf(fileMgt.getFileManagementSid()));
                resultsListDTO.add(fileMgtDTO);
            }
            object = resultsListDTO;
        }
        
        LOGGER.debug("getFileHistoryResults return resultsListDTO=" + resultsListDTO.size());
        return object;
    }
    
    public String getfileDBColumnName(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }
    
    public HashMap<String, String> loadFMColumnName() {
        columnNames.put("file", "forecastName");
        columnNames.put("type", "forecastSource");
        columnNames.put("version", "version");
        columnNames.put("effectiveDate", ConstantsUtils.CREATED_DATE);
        columnNames.put("fromPeriod", "fromPeriod");
        columnNames.put("toPeriod", "toPeriod");
        
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
    public Object getFileResults(FileMananagementResultDTO resultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        Object object = new Object();
        try {
            List resultsList = new ArrayList();
            final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<FileMananagementResultDTO>();
            String fileName = resultDTO.getFileName();
            String type = resultDTO.getType();
            String version = resultDTO.getVersion();
            String searchForecastYear = resultDTO.getSearchforcastYear();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            String query1 = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-' + Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE, "
                    + " Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,Month(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-' + Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH, ";
            
            String query2 = "", query = "";
            
            LOGGER.debug("getResults started with P1:String fileType=" + resultDTO.getFileType() + " P2:String country=" + resultDTO.getCountry() + " P3:String fileName=" + fileName + " P4:String type" + type + " P5:String version"
                    + version + " P6:String forecastYear" + resultDTO.getForecastYear() + " P7:Date fromDate" + resultDTO.getFromPeriod() + " Date toDate" + resultDTO.getToPeriod());
            String condition = "";
            String having = "";
            if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getFileType()) && ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
                query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  FORECASTING_MASTER FM "
                        + " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM   FORECASTING_MASTER "
                        + "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS "
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_ID = FM.NDC AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + "WHERE ";
                query = query1 + query2;
                condition = "(FM.SOURCE in('FORESIGHT','LE_FORESIGHT'))";
                condition = condition + " AND FM.COUNTRY='" + resultDTO.getCountry() + "'";
                 condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
            } else if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getHelperType()) && ConstantsUtils.COUNTRY_PR.equals(resultDTO.getCountry())) {
                query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  FORECASTING_MASTER FM "
                        + " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM   FORECASTING_MASTER  "
                        + "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS "
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_ID = FM.NDC AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + "WHERE ";
                query = query1 + query2;
                condition = "(FM.SOURCE in('FF_SALES'))";
                condition = condition + " AND FM.COUNTRY='" + resultDTO.getCountry() + "'";
                 condition = condition + " AND FM.BUSINESS_UNIT='" +resultDTO.getBusinessUnit() + "'";
            } else if (ConstantsUtils.DEMAND.equals(resultDTO.getFileType()) && ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
                query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  DEMAND_FORECAST FM "
                        + " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM DEMAND_FORECAST "
                        + "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS"
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + " WHERE ";
                query = query1 + query2;
                condition = "FM.COUNTRY='" + resultDTO.getCountry() + "'";
                 condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
            }
            else if (ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getFileType()) && ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
                query2
                        = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, Max(Cast(( Cast(fm.YEAR AS VARCHAR(4)) + '-' + Cast(fm.MONTH AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE, "
                        + " Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,Month(Max(Cast(( Cast(fm.YEAR AS VARCHAR(4)) + '-' + Cast(fm.MONTH AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH, "
                        + " Year(Max(Cast(( Cast(fm.YEAR AS VARCHAR(4)) + '-'+ Cast(fm.MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  ADJUSTED_DEMAND_FORECAST FM "
                        + " CROSS APPLY(SELECT Min(Cast(( Cast(YEAR AS VARCHAR(4)) + '-'+ Cast(MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM ADJUSTED_DEMAND_FORECAST "
                        + "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS"
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + " WHERE ";
                query = query2;
                condition = "FM.COUNTRY='" + resultDTO.getCountry() + "'";
                 condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
            }
            else if (ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getFileType()) && ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
                query = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, \n"
                        + "Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE,  \n"
                        + "Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,\n"
                        + "Month(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH,  \n"
                        + "Year(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR \n"
                        + "FROM  INVENTORY_WD_PROJ_MAS FM  \n"
                        + "CROSS APPLY(SELECT Min(Cast(( Cast(\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(\"MONTH\" AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE \n"
                        + "FROM INVENTORY_WD_PROJ_MAS   \n"
                        + "WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS \n"
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + "WHERE ";
                condition = "FM.COUNTRY LIKE '" + resultDTO.getCountry() + "%'";
                 condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
            } else if (ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getFileType()) && ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
                query = "SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.\"SOURCE\",FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE, \n"
                        + "Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)) AS FT_MAX_DATE,  \n"
                        + "Month(Min(FT_MIN_DATE)) AS MIN_MONTH,Year(Min(FT_MIN_DATE)) AS MIN_YEAR,\n"
                        + "Month(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-' + Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE))) AS MAX_MONTH,  \n"
                        + "Year(Max(Cast(( Cast(fm.\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(fm.\"MONTH\" AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR \n"
                        + "FROM  INVENTORY_WD_PROJ_DT FM  \n"
                        + "CROSS APPLY(SELECT Min(Cast(( Cast(\"YEAR\" AS VARCHAR(4)) + '-'+ Cast(\"MONTH\" AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE \n"
                        + "FROM INVENTORY_WD_PROJ_DT   \n"
                        + "WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS \n"
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + "WHERE ";
                condition = "FM.COUNTRY LIKE '" + resultDTO.getCountry() + "%'";
                condition = condition + " AND FM.BUSINESS_UNIT='" + resultDTO.getBusinessUnit() + "'";
            } else if (ConstantsUtils.CUSTOMERGTS.equals(resultDTO.getFileType()) && ConstantsUtils.COUNTRY_US.equals(resultDTO.getCountry())) {
                query2 = " Year(Max(Cast(( Cast(fm.FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(fm.FORECAST_MONTH AS VARCHAR(2))+ '-01' ) AS DATE)))  AS MAX_YEAR FROM  CUSTOMER_GTS_FORECAST FM "
                        + " CROSS APPLY(SELECT Min(Cast(( Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'+ Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' ) AS DATE)) AS FT_MIN_DATE FROM CUSTOMER_GTS_FORECAST "
                        + "  WHERE  FORECAST_VER = Floor(FM.FORECAST_VER) AND forecast_name = fm.forecast_name) CS"
                        + "    JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = FM.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D'\n"
                        + "    JOIN GL_COST_CENTER_MASTER GLC ON IM.NDC8 = GLC.NDC8 \n"
                        + "    JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = GLC.COMPANY_CODE AND CM.INBOUND_STATUS <> 'D' AND CM.COMPANY_MASTER_SID = "+resultDTO.getCompany()+" "
                        + " WHERE ";
                query = query1 + query2;
                condition = "FM.COUNTRY='" + resultDTO.getCountry() + "'";
                condition = condition + " AND FM.BUSINESS_UNIT='" +resultDTO.getBusinessUnit() + "'";
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
                if (!ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getFileType()) && !ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getFileType())&& !ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getFileType())) {
                    having = "having Max( Cast(( Cast( fm.FORECAST_YEAR AS VARCHAR( 4 ) ) + '-' + Cast( fm.FORECAST_MONTH AS VARCHAR( 2 ) ) + '-01' ) AS DATE ) ) <='" + to + "'";
                }
                else{
                    having = "having Max( Cast(( Cast( fm.YEAR AS VARCHAR( 4 ) ) + '-' + Cast( fm.MONTH AS VARCHAR( 2 ) ) + '-01' ) AS DATE ) ) <='" + to + "'";
                }
            }
            
            
            HashMap<String, String> resultsColumn = new HashMap<String, String>();
            resultsColumn.put("fileSource", "FM.SOURCE");
            resultsColumn.put("country", "FM.COUNTRY");
            resultsColumn.put("fileName", "FM.FORECAST_NAME");
            resultsColumn.put("type", "FM.SOURCE");
            resultsColumn.put("version", "FM.FORECAST_VER");
            resultsColumn.put("fromDate", "FT_MIN_DATE");
            resultsColumn.put("toDate", "FT_MAX_DATE");
            resultsColumn.put("businessUnit","FM.BUSINESS_UNIT");
            String filterHaving="";
            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "";
                        if (("fromDate".equals(stringFilter.getPropertyId()) || "toDate".equals(stringFilter.getPropertyId())) && !stringFilter.equals("")) {
                            DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                            Date date = (Date) formatter1.parse(stringFilter.getFilterString().toString());
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
                            filterString = formatedDate;
                        } else {
                            filterString = "%" + stringFilter.getFilterString() + "%";
                        }
                        if ("fromDate".equals(stringFilter.getPropertyId()) || "toDate".equals(stringFilter.getPropertyId())) {
                            condition = condition + " AND " + resultsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " = '" + filterString + "'";
                        } else {
                            condition = condition + " AND " + resultsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                            }

                    } else if (filter instanceof Between) {
                        Between stringFilter = (Between) filter;
                        Date filterString = (Date) stringFilter.getStartValue();
                        Date filterString1 = (Date) stringFilter.getEndValue();
                        if ("fromDate".equals(stringFilter.getPropertyId())) {

                            condition = condition + " AND FT_MIN_DATE >= '" + DB_DATE.format(filterString) + "' ";
                            condition = condition + " AND FT_MIN_DATE <= '" + DB_DATE.format(filterString1) + "' ";
                        } else if ("toDate".equals(stringFilter.getPropertyId())) {
                            filterHaving = filterHaving + " AND  FT_MAX_DATE >= '" + DB_DATE.format(filterString) + "' ";
                            filterHaving = filterHaving + " AND  FT_MAX_DATE <= '" + DB_DATE.format(filterString1) + "' ";
                        }
                    }
                     else if (filter instanceof Compare) {
                        Compare stringFilter = (Compare) filter;
                        Compare.Operation operation = stringFilter.getOperation();
                        if (stringFilter.getValue() instanceof Date) {
                            String filterString = DB_DATE.format(stringFilter.getValue());

                            if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                                if ("fromDate".equals(stringFilter.getPropertyId())) {
                                    condition = condition + " AND FT_MIN_DATE >= '" + filterString + "' ";
                                } else if ("toDate".equals(stringFilter.getPropertyId())) {
                                    filterHaving = filterHaving + " AND  FT_MAX_DATE >= '" + filterString + "' ";
                                }

                            } else {
                                if ("fromDate".equals(stringFilter.getPropertyId())) {
                                    condition = condition + " AND FT_MIN_DATE <= '" + filterString+ "' ";
                                } else if ("toDate".equals(stringFilter.getPropertyId())) {
                                    filterHaving = filterHaving + " AND  FT_MAX_DATE <= '" +filterString + "' ";
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
                    order = order + " ORDER BY " + " FORECAST_NAME ASC";
                } else {
                    order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
                }
                
                order = order + " " + "OFFSET ";
                order = order + startIndex;
                order = order + " ROWS FETCH NEXT " + endIndex;
                order = order + " ROWS ONLY";
                
            }
            String GroupBy = "";
            if (ConstantsUtils.EX_FACTORY_SALES.equals(resultDTO.getFileType())) {
                GroupBy = " GROUP  BY FM.FORECAST_NAME, FM.FORECAST_VER,FM.SOURCE,FM.COUNTRY,FM.FORECAST_DATE ";
            } else if (ConstantsUtils.DEMAND.equals(resultDTO.getFileType())
                    || ConstantsUtils.ADJUSTED_DEMAND.equals(resultDTO.getFileType())
                    || ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY.equals(resultDTO.getFileType())
                    || ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL.equals(resultDTO.getFileType()) || ConstantsUtils.CUSTOMERGTS.equals(resultDTO.getFileType())) {
                GroupBy = " GROUP BY FM.FORECAST_NAME, FM.FORECAST_VER,FM.SOURCE,FM.COUNTRY ";
            }
           
            String finalQuery = query + condition + GroupBy + having + order;
            if (!filterHaving.isEmpty()) {
                finalQuery = query + condition + GroupBy + having;
                finalQuery=" SELECT FM.FORECAST_NAME,FM.FORECAST_VER,FM.SOURCE,FM.COUNTRY, Min(FT_MIN_DATE) AS FT_MIN_DATE,MAX(FT_MAX_DATE),MIN_YEAR,MAX_MONTH,MAX_YEAR from ("+finalQuery+") T  WHERE  FORECAST_VER = Floor(T.FORECAST_VER)"+filterHaving+
                        "GROUP BY FM.FORECAST_NAME, FM.FORECAST_VER,FM.SOURCE,FM.COUNTRY,MIN_YEAR,MAX_MONTH,MAX_YEAR "+order;
            }
            
            resultsList = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
           
            if (!isCount) {
                loadMonthMap();
                for (int i = 0; i < resultsList.size(); i++) {
                    final Object obj[] = (Object[]) resultsList.get(i);
                    final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
                    fmDTO.setFileName(String.valueOf(obj[0]));
                    if (!ConstantsUtils.NULL.equals(String.valueOf(obj[1]))) {
                        fmDTO.setVersion(String.valueOf(obj[1]));
                    }
                    fmDTO.setFileType(String.valueOf(obj[NumericConstants.TWO]));
                    fmDTO.setType(String.valueOf(obj[NumericConstants.TWO]));
                    fmDTO.setCountry(String.valueOf(obj[NumericConstants.THREE]));
                    String from = null;
                    if (String.valueOf(obj[NumericConstants.FOUR]) != null && !"".equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                        from = dateFormat.format(dateFormatToParse.parse(String.valueOf(obj[NumericConstants.FOUR])));
                        fmDTO.setFromDate(dateFormat.parse(from));
                    } 
                    
                    String to = null;
                    if (String.valueOf(obj[NumericConstants.FIVE]) != null && !"".equals(String.valueOf(obj[NumericConstants.FIVE]))) {
                        to = dateFormat.format(dateFormatToParse.parse(String.valueOf(obj[NumericConstants.FIVE])));
                        fmDTO.setToDate(dateFormat.parse(to));
                    } 
                    fmDTO.setAuditVersion(0);
                    resultsListDTO.add(fmDTO);
                }
                object = resultsListDTO;
                LOGGER.debug("getResults return List<FileMananagementResultDTO> resultsListDTO=" + resultsListDTO.size());
            } else {
                object = resultsList.size();
                LOGGER.debug("getResults return List<FileMananagementResultDTO> resultsList=" + resultsList.size());
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
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
    public Object getDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws ParseException {
        Object detailsObj = null;
        List resultsList;
        final List<FileMananagementResultDTO> resultsListDTO = new ArrayList<FileMananagementResultDTO>();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        LOGGER.debug("getDetailsResults started with P1:String fileName=" + detailsResultDTO.getFileName() + " P2:String version=" + detailsResultDTO.getVersion() + " P3:String fileType=" + detailsResultDTO.getHelperType() + " P4:String country=" + detailsResultDTO.getCountry() + " startIndex=" + startIndex + " endIndex=" + endIndex);
        if (detailsResultDTO.getHelperType().equals(ConstantsUtils.DEMAND)) {
            resultsList = getDemandDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
        }else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
            resultsList = getAdjustedDemandDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
        } else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.EX_FACTORY_SALES)) {
            resultsList = getForecastDetails(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
        } else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
            resultsList = getInventorySummaryResults(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
        } else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
            resultsList = getInventoryDetailsResults(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
        } else if (detailsResultDTO.getHelperType().equals(ConstantsUtils.CUSTOMERGTS)) {
            resultsList = getCustomerSalesResults(detailsResultDTO, startIndex, endIndex, sortByColumns, filterSet, isCount);
        } else {
            resultsList = null;
        }
        if (!isCount && detailsResultDTO.getHelperType().equals(ConstantsUtils.EX_FACTORY_SALES)) {
            for (int i = 0; i < resultsList.size(); i++) {
                final Object[] obj = (Object[]) resultsList.get(i);
                final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
                fmDTO.setItemNo(String.valueOf(obj[0]));
                fmDTO.setItemName(String.valueOf(obj[1]));
                fmDTO.setYear(String.valueOf(obj[NumericConstants.TWO]));
                fmDTO.setMonth(String.valueOf(obj[NumericConstants.THREE]));
                if (obj[NumericConstants.FOUR] != null) {
                    String date = formatter.format((Date)obj[NumericConstants.FOUR]);
                    Date sdate = formatter.parse(date);
                    fmDTO.setStartDate(sdate);
                }
                fmDTO.setPrice(obj[NumericConstants.FIVE] == null ? " " :DOLLAR+priceFormat.format(obj[NumericConstants.FIVE]));
                fmDTO.setUnits(obj[NumericConstants.SIX] == null ? " " : unitsFormat.format(obj[NumericConstants.SIX]));
                fmDTO.setHiddenPrice(String.valueOf(obj[NumericConstants.FIVE]));
                fmDTO.setHiddenUnits(String.valueOf(obj[NumericConstants.SIX]));
                fmDTO.setDollars(obj[NumericConstants.SEVEN]==null ? " " : DOLLAR+dollarFormat.format(obj[NumericConstants.SEVEN]));
                boolean recordStatus = ((Boolean) obj[NumericConstants.EIGHT]).booleanValue();
                fmDTO.setRecordLockStatus(recordStatus);
                fmDTO.setForecastSystemId((Integer) obj[NumericConstants.NINE]);
                fmDTO.setCheck(false);
                fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
                resultsListDTO.add(fmDTO);
            }
            detailsObj = resultsListDTO;
            LOGGER.debug("getDetailsResults return List<FileMananagementResultDTO> resultsListDTO=" + resultsListDTO.size());
        } else if (!isCount && detailsResultDTO.getHelperType().equals(ConstantsUtils.DEMAND)) {
            for (Object resultsList1 : resultsList) {
                final Object[] obj = (Object[]) resultsList1;
                final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
                fmDTO.setForecastType(obj[0] != null ? String.valueOf(obj[0]) : "");
                fmDTO.setForcastYear(obj[1] != null ? String.valueOf(obj[1]) : "");
                fmDTO.setForecastMonth(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
                fmDTO.setItemId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
                fmDTO.setHiddenForecastType(obj[0] != null ? String.valueOf(obj[0]) : "");
                fmDTO.setHiddenForecastYear(obj[1] != null ? String.valueOf(obj[1]) : "");
                fmDTO.setHiddenForecastMonth(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
                fmDTO.setHiddenItemId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
                fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
                fmDTO.setItemIdentifier(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
                fmDTO.setBrandId(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : "");
                fmDTO.setSegment(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
                fmDTO.setMarketSizeUnits(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
                fmDTO.setMarketShareUnits(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
                fmDTO.setMarketShareRatio(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
                fmDTO.setUncapturedUnits(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
                fmDTO.setUncapturedUnitsRatio(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : "");
                fmDTO.setTotalDemandUnits(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
                fmDTO.setTotalDemandAmount(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
                fmDTO.setInventoryUnitChange(obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : "");
                fmDTO.setGrossUnits(obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : "");
                fmDTO.setGrossPrice(obj[NumericConstants.SEVENTEEN] != null ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : "");
                fmDTO.setGrossAmount(obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : "");
                fmDTO.setNetSalesPrice(obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN]) : "");
                fmDTO.setNetSalesAmount(obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY]) : "");
                fmDTO.setBatchId(obj[NumericConstants.TWENTY_ONE] != null ? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : "");
                fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_SIX] != null ? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : "");
                fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_SIX] != null ? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : "");
                boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SEVEN]).booleanValue();
                fmDTO.setRecordLockStatus(recordStatus);
                fmDTO.setForecastSystemId((Integer) obj[NumericConstants.TWENTY_EIGHT]);
                fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
                fmDTO.setCheck(false);
                resultsListDTO.add(fmDTO);
            }
            detailsObj = resultsListDTO;
        } else if (!isCount && detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_SUMMARY)) {
            for (Object resultsList1 : resultsList) {
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
                fmDTO.setItemId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
                fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
                fmDTO.setItemIdentifier(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : "");
                fmDTO.setUnitsWithdrawn(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
                fmDTO.setAmountWithdrawn(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
                fmDTO.setPrice(obj[NumericConstants.FIVE] == null ? " " : DOLLAR+priceFormat.format(obj[NumericConstants.FIVE]));
                fmDTO.setBatchId(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
                fmDTO.setHiddenbatchId(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
                fmDTO.setOrganizationKey(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
                fmDTO.setHiddenOrganisationKey(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
                boolean recordStatus = ((Boolean) obj[NumericConstants.TWELVE]).booleanValue();
                fmDTO.setRecordLockStatus(recordStatus);
                fmDTO.setForecastSystemId((Integer) obj[NumericConstants.THIRTEEN]);
                fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
                fmDTO.setCheck(false);
                resultsListDTO.add(fmDTO);
            }
            detailsObj = resultsListDTO;
        } else if (!isCount && detailsResultDTO.getHelperType().equals(ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL)) {
            for (Object resultsList1 : resultsList) {
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
                fmDTO.setHiddenCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
                fmDTO.setIdentifierCodeQualifier(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
                fmDTO.setCompanyIdentifier(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : "");
                fmDTO.setItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
                fmDTO.setHiddenItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
                fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
                fmDTO.setItemIdentifier(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
                fmDTO.setUnitsWithdrawn(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
                fmDTO.setAmountWithdrawn(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
                fmDTO.setPrice(obj[NumericConstants.TWELVE] != null ?  DOLLAR+priceFormat.format(obj[NumericConstants.TWELVE]): " " );
                fmDTO.setBatchId(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
                fmDTO.setHiddenbatchId(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
                fmDTO.setOrganizationKey(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
                fmDTO.setHiddenOrganisationKey(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
                boolean recordStatus = ((Boolean) obj[NumericConstants.FIFTEEN]).booleanValue();
                fmDTO.setRecordLockStatus(recordStatus);
                fmDTO.setForecastSystemId((Integer) obj[NumericConstants.SIXTEEN]);
                fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
                fmDTO.setCheck(false);
                resultsListDTO.add(fmDTO);
            }
            detailsObj = resultsListDTO;
        } else if (!isCount && detailsResultDTO.getHelperType().equals(ConstantsUtils.CUSTOMERGTS)) {
            for (Object resultsList1 : resultsList) {
                final Object[] obj = (Object[]) resultsList1;
                final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
                fmDTO.setForcastYear(obj[0] != null ? String.valueOf(obj[0]) : "");
                fmDTO.setForecastMonth(obj[1] != null ? String.valueOf(obj[1]) : "");
                fmDTO.setHiddenYear(obj[0] != null ? String.valueOf(obj[0]) : "");
                fmDTO.setHiddenMonth(obj[1] != null ? String.valueOf(obj[1]) : "");
                fmDTO.setItemId(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : "");
                fmDTO.setCompanyId(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : "");
                fmDTO.setUnits(obj[NumericConstants.FOUR] != null ? unitsFormat.format(obj[NumericConstants.FOUR]) :"0.0" );
                fmDTO.setPriceType(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
                fmDTO.setPrice(obj[NumericConstants.SIX] != null ? DOLLAR+priceFormat.format(obj[NumericConstants.SIX]): " " );
                fmDTO.setSalesAmount(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
                fmDTO.setSalesInclusion(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
                fmDTO.setDeductionId(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
                fmDTO.setDeductionCategory(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
                fmDTO.setDeductionType(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
                fmDTO.setDeductionProgramType(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : "");
                fmDTO.setAdjustmentCode(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
                fmDTO.setDeductionRate(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
                fmDTO.setDeductionAmount(obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : "");
                fmDTO.setDeductionInclusion(obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : "");
                fmDTO.setForecastValueType(obj[NumericConstants.SEVENTEEN] != null ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : "");
                fmDTO.setBrandId(obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : "");
                fmDTO.setSegment(obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN]) : "");
                fmDTO.setBatchId(obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY]) : "");
                fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_ONE] != null ? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : "");
                fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_ONE] != null ? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : "");
                fmDTO.setForecastName(obj[NumericConstants.TWENTY_TWO] != null ? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : "");
                fmDTO.setForecastVersion(obj[NumericConstants.TWENTY_THREE] != null ? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : "");
                fmDTO.setCountry(obj[NumericConstants.TWENTY_FOUR] != null ? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : "");
                fmDTO.setForecastDate(obj[NumericConstants.TWENTY_FIVE] != null ? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : "");
                boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SIX]).booleanValue();
                fmDTO.setRecordLockStatus(recordStatus);
                fmDTO.setForecastSystemId(obj[NumericConstants.TWENTY_SEVEN] != null ? Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])) : 0);
                fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
                fmDTO.setCheck(false);
                resultsListDTO.add(fmDTO);
            }
            detailsObj = resultsListDTO;
        } else if (!isCount && detailsResultDTO.getHelperType().equals(ConstantsUtils.ADJUSTED_DEMAND)) {
            for (Object resultsList1 : resultsList) {
                final Object[] obj = (Object[]) resultsList1;
                final FileMananagementResultDTO fmDTO = new FileMananagementResultDTO();
                fmDTO.setItemId(obj[0] != null ? String.valueOf(obj[0]) : ConstantsUtils.EMPTY);
                fmDTO.setItemName(obj[1] != null ? String.valueOf(obj[1]) : ConstantsUtils.EMPTY);
                fmDTO.setBrandId(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : ConstantsUtils.EMPTY);
                fmDTO.setBrandName(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : ConstantsUtils.EMPTY);
                fmDTO.setSegment(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : ConstantsUtils.EMPTY);
                fmDTO.setForcastYear(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : ConstantsUtils.EMPTY);
                fmDTO.setHiddenYear(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : ConstantsUtils.EMPTY);
                fmDTO.setForecastMonth(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : ConstantsUtils.EMPTY);
                fmDTO.setHiddenMonth(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : ConstantsUtils.EMPTY);
                fmDTO.setMarketSizeUnits(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : ConstantsUtils.EMPTY);
                fmDTO.setMarketShareRatio(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : ConstantsUtils.EMPTY);
                fmDTO.setMarketShareUnits(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : ConstantsUtils.EMPTY);
                fmDTO.setUncapturedUnits(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : ConstantsUtils.EMPTY);
                fmDTO.setUncapturedUnitsRatio(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : ConstantsUtils.EMPTY);
                fmDTO.setTotalDemandUnits(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : ConstantsUtils.EMPTY);
                fmDTO.setTotalDemandAmount(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setInventoryUnitChange(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setGrossUnits(obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setGrossPrice(obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setGrossAmount(obj[NumericConstants.SEVENTEEN] != null ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setNetSalesPrice(obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setNetSalesAmount(obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN]) : ConstantsUtils.EMPTY);
                fmDTO.setBatchId(obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY]) : ConstantsUtils.EMPTY);
                fmDTO.setSource(obj[NumericConstants.TWENTY_ONE] != null ? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : ConstantsUtils.EMPTY);
                fmDTO.setOrganizationKey(obj[NumericConstants.TWENTY_TWO] != null ? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
                fmDTO.setHiddenOrganisationKey(obj[NumericConstants.TWENTY_TWO] != null ? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : ConstantsUtils.EMPTY);
                fmDTO.setForecastName(obj[NumericConstants.TWENTY_THREE] != null ? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : ConstantsUtils.EMPTY);
                fmDTO.setForecastVersion(obj[NumericConstants.TWENTY_FOUR] != null ? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : ConstantsUtils.EMPTY);
                fmDTO.setCountry(obj[NumericConstants.TWENTY_FIVE] != null ? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : ConstantsUtils.EMPTY);
                fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
                boolean recordStatus = ((Boolean) obj[NumericConstants.TWENTY_SIX]).booleanValue();
                fmDTO.setRecordLockStatus(recordStatus);
                if (obj[NumericConstants.TWENTY_SEVEN] != null) {
                    fmDTO.setForecastSystemId(Integer.valueOf(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])));
                }
                fmDTO.setCheck(Boolean.FALSE);
                resultsListDTO.add(fmDTO);
            }

            detailsObj = resultsListDTO;
        }else {
            detailsObj = resultsList.get(0);
            LOGGER.debug("getDetailsResults return List<FileMananagementResultDTO> detailsObj=" + detailsObj);
        }
        return detailsObj;
    }
    
    public List getForecastDetails(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering getForecastDetails ");
        List list = new ArrayList();
        String sqlString;
        if (isCount) {
            sqlString = "SELECT count( * ) \n"
                    + " FROM FORECASTING_MASTER FM, ITEM_MASTER IM WHERE FM.NDC=IM.ITEM_ID AND \n"
                    + "FORECAST_NAME=  ";
        } else {
            sqlString = CustomSQLUtil.get("getDetailsResults");
        }
        sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
        if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType()) && "US".equals(detailsResultDTO.getCountry())) {
            sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'LE_FORESIGHT')");
        } else if (ConstantsUtils.EX_FACTORY_SALES.equals(detailsResultDTO.getHelperType()) && "PR".equals(detailsResultDTO.getCountry())) {
            sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_SALES')");
        } else if ("Vaccine Segmentation".equals(detailsResultDTO.getHelperType())) {
            sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_VACCINE')");
        }
        sqlString = sqlString.concat(" AND FM.COUNTRY='").concat(detailsResultDTO.getCountry()).concat("'");
        
        if (detailsResultDTO.getVersion().contains("~")) {
            String[] versionArray = detailsResultDTO.getVersion().split("~");
            sqlString = sqlString.concat(" AND ").concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
        } else {
            sqlString = sqlString.concat(" AND ").concat(" FM.FORECAST_VER='").concat(detailsResultDTO.getVersion()).concat("'");
        }
        
        String filterQuery = "";
        
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("year", "FORECAST_YEAR");
        detailsColumn.put("month", "FORECAST_MONTH");
        detailsColumn.put("itemNo", "ITEM_NO");
        detailsColumn.put("itemName", "ITEM_NAME");
        detailsColumn.put("price", "PRICE");
        detailsColumn.put("units", "UNITS");
        detailsColumn.put("dollars", "DOLLARS");
        detailsColumn.put("startDate", "FORECAST_DATE");
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    
                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String filterString = formatter.format(stringFilter.getStartValue());
                    String filterString1 = formatter.format(stringFilter.getEndValue());
                    if ("startDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND FORECAST_DATE >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND FORECAST_DATE <= '" + filterString1 + "' ";
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
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            
            order = order + " " + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
            
        }
        
        String finalQuery = "";
        if (isCount) {
            finalQuery = sqlString + filterQuery;
        } else {
            finalQuery = sqlString + filterQuery + order;
        }
        list = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
        LOGGER.debug("getDetailsResults return List list=" + list.size());
        return list;
    }
    
    public Object getItemSearchResults(final ItemSearchDTO itemSearchDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) throws SystemException {
        Object itemObject;
        List<ItemSearchDTO> resultList = new ArrayList<ItemSearchDTO>();
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
                    dto.setItemType(obj[NumericConstants.FOUR] != null && !obj[NumericConstants.FOUR].toString().equals(ConstantsUtils.SELECTONE) ? obj[NumericConstants.FOUR].toString() : "");
                    dto.setItemStatus(obj[NumericConstants.FIVE] != null && !obj[NumericConstants.FIVE].toString().equals(ConstantsUtils.SELECTONE) ? obj[NumericConstants.FIVE].toString() : "");
                    dto.setIdentifier(obj[NumericConstants.SIX] != null ? obj[NumericConstants.SIX].toString() : "");
                    dto.setIdentifierType(obj[NumericConstants.ELEVEN] != null ? obj[NumericConstants.ELEVEN].toString() : "");
                    dto.setTherapyClass(obj[NumericConstants.SEVEN] != null && !obj[NumericConstants.SEVEN].toString().equals(ConstantsUtils.SELECTONE) ? obj[NumericConstants.SEVEN].toString() : "");
                    dto.setBrand(obj[NumericConstants.EIGHT] != null && !obj[NumericConstants.EIGHT].toString().equals(ConstantsUtils.SELECTONE) ? obj[NumericConstants.EIGHT].toString() : "");
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
    
    public String findItemMaster(ItemSearchDTO itemSearchDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
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
            if (itemSearchDTO.getSystemId() != null && !"null".equals(String.valueOf(itemSearchDTO.getSystemId())) && !StringUtils.isBlank(itemSearchDTO.getSystemId())) {
                String sysId = itemSearchDTO.getSystemId().replace('*', '%');
                sql += " and im.item_Master_Sid like '" + sysId + "' ";
            }
            
            if (itemSearchDTO.getItemName() != null && !"null".equals(String.valueOf(itemSearchDTO.getItemName())) && !StringUtils.isBlank(itemSearchDTO.getItemName())) {
                String itemName = itemSearchDTO.getItemName().replace('*', '%');
                sql += " and im.item_Name like '" + itemName + "' ";
            }
            if (itemSearchDTO.getHelperItemType() != null && !"null".equals(String.valueOf(itemSearchDTO.getHelperItemType())) && itemSearchDTO.getHelperItemType().getId() != 0) {
                sql += " and im.item_Type='" + itemSearchDTO.getHelperItemType().getId() + "' ";
            }
            if (itemSearchDTO.getHelperTherapyClass() != null && !"null".equals(String.valueOf(itemSearchDTO.getHelperTherapyClass())) && itemSearchDTO.getHelperTherapyClass().getId() != 0) {
                sql += " and im.THERAPEUTIC_CLASS="
                        + itemSearchDTO.getHelperTherapyClass().getId() + " ";
            }
            if (itemSearchDTO.getNdc9() != null && !"null".equals(String.valueOf(itemSearchDTO.getNdc9())) && !StringUtils.isBlank(itemSearchDTO.getNdc9())) {
                String ndc9 = itemSearchDTO.getNdc9().replace('*', '%');
                sql += " and im.ndc9 like '" + ndc9 + "' ";
            }
            if (itemSearchDTO.getIdentifierType() != null && !"null".equals(String.valueOf(itemSearchDTO.getIdentifierType())) && !"".equals(String.valueOf(itemSearchDTO.getIdentifierType())) && !"0".equals(itemSearchDTO.getIdentifierType())) {
                sql += " and irt.item_Qualifier_Sid="
                        + itemSearchDTO.getIdentifierType() + " ";
            }
            if (itemSearchDTO.getItemNo() != null && !"null".equals(String.valueOf(itemSearchDTO.getItemNo())) && !StringUtils.isBlank(itemSearchDTO.getItemNo())) {
                String itemNo = itemSearchDTO.getItemNo().replace('*', '%');
                sql += " and im.item_No like '" + itemNo + "' ";
            }
            if (itemSearchDTO.getItemDesc() != null && !"null".equals(String.valueOf(itemSearchDTO.getItemDesc())) && !StringUtils.isBlank(itemSearchDTO.getItemDesc())) {
                String desc = itemSearchDTO.getItemDesc().replace('*', '%');
                sql += " and im.item_Desc like '" + desc + "' ";
            }
            if (itemSearchDTO.getHelperStatus() != null && !"null".equals(String.valueOf(itemSearchDTO.getHelperStatus())) && itemSearchDTO.getHelperStatus().getId() != 0) {
                sql += " and im.item_Status='" + itemSearchDTO.getHelperStatus().getId() + "' ";
            }
            if (itemSearchDTO.getBrand() != null && !"null".equals(String.valueOf(itemSearchDTO.getBrand())) && !StringUtils.isBlank(itemSearchDTO.getBrand()) && !"0".equals(itemSearchDTO.getBrand())) {
                sql += " and im.brand_Master_Sid = '" + itemSearchDTO.getBrand()
                        + "' ";
            }
            if (itemSearchDTO.getIdentifier() != null && !"null".equals(String.valueOf(itemSearchDTO.getIdentifier())) && !StringUtils.isBlank(itemSearchDTO.getIdentifier())) {
                String identifier = itemSearchDTO.getIdentifier().replace('*', '%');
                sql += " and irt.item_Identifier_Value like '"
                        + identifier + "' ";
            }
            if (itemSearchDTO.getNdc8() != null && !"null".equals(String.valueOf(itemSearchDTO.getNdc8())) && !StringUtils.isBlank(itemSearchDTO.getNdc8())) {
                String ndc8 = itemSearchDTO.getNdc8().replace('*', '%');
                sql += " and im.ndc8 like '" + ndc8 + "' ";
            }
            sql += " and irt.inbound_Status <> '" + "D" + "' ";

//    Query for filter in table
            String filterQuery = "";
            HashMap<String, String> itemColumn = new HashMap<String, String>();
            itemColumn.put("systemId", "im.item_Master_Sid");
            itemColumn.put("itemNo", "im.item_No");
            itemColumn.put("itemName", "im.item_Name");
            itemColumn.put("itemDesc", "im.item_Desc");
            itemColumn.put("itemType", "im.item_Type");
            itemColumn.put("itemStatus", "im.item_Status");
            itemColumn.put("therapyClass", "im.THERAPEUTIC_CLASS");
            itemColumn.put("brand", "im.brand_Master_Sid");
            itemColumn.put("ndc9", "im.ndc9");
            itemColumn.put("ndc8", "im.ndc8");
            itemColumn.put("identifierType", "irt.item_Qualifier_Sid");
            itemColumn.put("identifier", "irt.item_Identifier_Value");
            
            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if (("brand").equals(stringFilter.getPropertyId()) || "identifierType".equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getFilterString() != null && !"null".equals(stringFilter.getFilterString())) {
                                filterQuery = filterQuery + " AND " + itemColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                            }
                        } else {
                            filterQuery = filterQuery + " AND " + itemColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
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
                    order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
                }
                
                order = order + " " + "OFFSET ";
                order = order + startIndex;
                order = order + " ROWS FETCH NEXT " + endIndex;
                order = order + " ROWS ONLY;";
                
            }
            
            sql = sql + filterQuery + order;
            
            return sql;
        } catch (Exception e) {
            LOGGER.error(e);
            
            return null;
        }
    }
    
    public List getDemandDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering Demand Details Results");
        List list = new ArrayList();
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
        sqlString = sqlString.concat(" AND DF.COUNTRY='").concat(detailsResultDTO.getCountry()).concat("'");
        if (detailsResultDTO.getVersion().contains("~")) {
            String[] versionArray = detailsResultDTO.getVersion().split("~");
            sqlString = sqlString.concat(" AND ").concat(" DF.FORECAST_VER in ('" + versionArray[0] + "',");
            String tempversionArray = String.valueOf(versionArray[1].toString()).replace(".", "~").trim();
            String[] InnerArray = tempversionArray.split("~");
            int outerSize = Integer.parseInt(InnerArray[0]);
            int innerSize;
            for (int i = 1; i <= outerSize; i++) {
                if (Integer.parseInt(versionArray[0]) > outerSize) {
                    innerSize = NumericConstants.NINE;
                } else {
                    innerSize = Integer.parseInt(InnerArray[1]);
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
            sqlString = sqlString.concat(" AND ").concat(" DF.FORECAST_VER='").concat(detailsResultDTO.getVersion()).concat("'");
        }
        String filterQuery = "";
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("forecastType", "DF.FORECAST_TYPE");
        detailsColumn.put("forcastYear", "DF.FORECAST_YEAR");
        detailsColumn.put("forecastMonth", "DF.FORECAST_MONTH");
        detailsColumn.put("itemId", "DF.ITEM_ID");
        detailsColumn.put("itemIdentifierCodeQualifier", "DF.ITEM_IDENTIFIER_CODE_QUALIFIER");
        detailsColumn.put("itemIdentifier", "DF.ITEM_IDENTIFIER");
        detailsColumn.put("brandId", "DF.BRAND_ID");
        detailsColumn.put("segment", "DF.SEGMENT");
        detailsColumn.put("marketSizeUnits", "DF.MARKET_SIZE_UNITS");
        detailsColumn.put("marketShareRatio", "DF.MARKET_SHARE_RATIO");
        detailsColumn.put("marketShareUnits", "DF.MARKET_SHARE_UNITS");
        detailsColumn.put("uncapturedUnits", "DF.UNCAPTURED_UNITS");
        detailsColumn.put("uncapturedUnitsRatio", "DF.UNCAPTURED_UNITS_RATIO");
        detailsColumn.put("totalDemandUnits", "DF.TOTAL_DEMAND_UNITS");
        detailsColumn.put("totalDemandAmount", "DF.TOTAL_DEMAND_AMOUNT");
        detailsColumn.put("inventoryUnitChange", "DF.INVENTORY_UNIT_CHANGE");
        detailsColumn.put("grossUnits", "DF.GROSS_UNITS");
        detailsColumn.put("grossPrice", "DF.GROSS_PRICE");
        detailsColumn.put("grossAmount", "DF.GROSS_AMOUNT");
        detailsColumn.put("netSalesPrice", "DF.NET_SALES_PRICE");
        detailsColumn.put("netSalesAmount", "DF.NET_SALES_AMOUNT");
        detailsColumn.put("batchId", "DF.BATCH_ID");
        detailsColumn.put("organizationKey", "DF.ORGANIZATION_KEY");
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    
                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    
                }
            }
        }
        String finalQuery = "";
        String order = "";
        if (!isCount) {
            String sortOrder=" ASC ";
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                    
                    columnName = sortByColumn.getName();
                    orderByColumn = detailsColumn.get(columnName);
                    
                    if (sortByColumn.getType() != SortByColumn.Type.ASC) {
                        sortOrder = " DESC ";
                    }
                }
            }
            String extraOrder="";
            String extraSortOrder=" ASC ";
            String extraSortOrderMonth=" ASC ";
            if (orderByColumn != null && !StringUtils.EMPTY.equals(orderByColumn)) {
                if("DF.FORECAST_YEAR".equalsIgnoreCase(orderByColumn)){
                   extraSortOrder=sortOrder;
                }else if("DF.FORECAST_MONTH".equalsIgnoreCase(orderByColumn)){
                   extraSortOrderMonth=sortOrder;
                }else{
                  extraOrder=", "+ orderByColumn + sortOrder;
                }               
            }
            order += " ORDER BY DF.FORECAST_YEAR "+extraSortOrder+ ",DF.FORECAST_MONTH  "+extraSortOrderMonth+", DF.DEMAND_FORECAST_SID ASC "+extraOrder;
            order += " " + "OFFSET ";
            order += startIndex;
            order += " ROWS FETCH NEXT " + endIndex;
            order += " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlString + filterQuery;
        } else {
            finalQuery = sqlString + filterQuery + order;
        }
        list = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
        LOGGER.debug("Ending Demand Details Results");
        return list;
    }
    
    public List getInventorySummaryResults(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering Demand Details Results");
        List list = new ArrayList();
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
                String tmpString = versionArray[0].replace(".", "~");
                version2Array = tmpString.split("~");
                y = Integer.valueOf(version2Array[1]);
            } else {
                x = Integer.valueOf(versionArray[0]);
                y = 0;
            }
            sqlString = sqlString.concat(" AND ").concat(" INW.FORECAST_VER in ('" + versionArray[0] + "',");
            String tempversionArray = String.valueOf(versionArray[1].toString()).replace(".", "~").trim();
            String[] InnerArray = tempversionArray.split("~");
            int outerSize = Integer.parseInt(InnerArray[0]);
            int innerSize;
            for (int i = x; i <= outerSize; i++) {
                if (Integer.parseInt(versionArray[0]) > outerSize) {
                    innerSize = NumericConstants.NINE;
                } else {
                    innerSize = Integer.parseInt(InnerArray[1]);
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
            sqlString = sqlString.concat(" AND ").concat(" INW.FORECAST_VER='").concat(detailsResultDTO.getVersion()).concat("'");
        }
        String filterQuery = "";
        String finalQuery = "";
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("year", "YEAR");
        detailsColumn.put("month", "MONTH");
        detailsColumn.put("week", "WEEK");
        detailsColumn.put("day", "DAY");
        detailsColumn.put("itemId", "ITEM_ID");
        detailsColumn.put("itemIdentifierCodeQualifier", "ITEM_IDENTIFIER_CODE_QUALIFIER");
        detailsColumn.put("itemIdentifier", "ITEM_IDENTIFIER");
        detailsColumn.put("unitsWithdrawn", "UNITS_WITHDRAWN");
        detailsColumn.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
        detailsColumn.put("batchId", "BATCH_ID");
        detailsColumn.put("organizationKey", "ORGANIZATION_KEY");
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    
                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    
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
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlString + filterQuery;
        } else {
            finalQuery = sqlString + filterQuery + order;
        }
        list = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
        LOGGER.debug("Ending Demand Details Results");
        return list;
    }
    
    public List getInventoryDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering getInventory Details Results");
        List list = new ArrayList();
        String sqlString = "";
        
        if (isCount) {
            sqlString = "SELECT COUNT(INW.INVENTORY_WD_PROJ_DT_SID) FROM INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME =";
        } else {
            sqlString = "SELECT\n"
                    + "    DISTINCT INW.\"YEAR\",\n"
                    + "    INW.\"MONTH\",\n"
                    + "    INW.\"DAY\",\n"
                    + "    INW.WEEK,\n"
                    + "    INW.COMPANY_ID,\n"
                    + "    INW.IDENTIFIER_CODE_QUALIFIER,\n"
                    + "    INW.COMPANY_IDENTIFIER,\n"
                    + "    INW.ITEM_ID,\n"
                    + "    INW.ITEM_IDENTIFIER_CODE_QUALIFIER,\n"
                    + "    INW.ITEM_IDENTIFIER,\n"
                    + "    INW.UNITS_WITHDRAWN,\n"
                    + "    INW.AMOUNT_WITHDRAWN,\n"
                    + "    INW.PRICE,\n"
                    + "    INW.BATCH_ID,\n"
                    + "    INW.ORGANIZATION_KEY,\n"
                    + "    INW.RECORD_LOCK_STATUS,\n"
                    + "    INW.INVENTORY_WD_PROJ_DT_SID\n"
                    + "FROM\n"
                    + "    INVENTORY_WD_PROJ_DT INW WHERE INW.FORECAST_NAME =";
        }
        sqlString = sqlString.concat("'").concat(detailsResultDTO.getFileName()).concat("'");
        sqlString = sqlString.concat(" AND INW.COUNTRY LIKE'").concat(detailsResultDTO.getCountry()).concat("%'");
        if (detailsResultDTO.getVersion().contains("~")) {
            String[] versionArray = detailsResultDTO.getVersion().split("~");
            int x = 0, y = 0;
            String[] version2Array;
            if (versionArray[0].contains(".")) {
                String tmpString = versionArray[0].replace(".", "~");
                version2Array = tmpString.split("~");
                y = Integer.valueOf(version2Array[1]);
            } else {
                x = Integer.valueOf(versionArray[0]);
                y = 0;
            }
            sqlString = sqlString.concat(" AND ").concat(" INW.FORECAST_VER in ('" + versionArray[0] + "',");
            String tempversionArray = String.valueOf(versionArray[1].toString()).replace(".", "~").trim();
            String[] InnerArray = tempversionArray.split("~");
            int outerSize = Integer.parseInt(InnerArray[0]);
            int innerSize;
            for (int i = x; i <= outerSize; i++) {
                if (Integer.parseInt(versionArray[0]) > outerSize) {
                    innerSize = NumericConstants.NINE;
                } else {
                    innerSize = Integer.parseInt(InnerArray[1]);
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
            sqlString = sqlString.concat(" AND ").concat(" INW.FORECAST_VER='").concat(detailsResultDTO.getVersion()).concat("'");
        }
        String filterQuery = "";
        String finalQuery = "";
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("year", "YEAR");
        detailsColumn.put("month", "MONTH");
        detailsColumn.put("week", "WEEK");
        detailsColumn.put("day", "DAY");
        detailsColumn.put("itemId", "ITEM_ID");
        detailsColumn.put("itemIdentifierCodeQualifier", "ITEM_IDENTIFIER_CODE_QUALIFIER");
        detailsColumn.put("itemIdentifier", "ITEM_IDENTIFIER");
        detailsColumn.put("unitsWithdrawn", "UNITS_WITHDRAWN");
        detailsColumn.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
        detailsColumn.put("batchId", "BATCH_ID");
        detailsColumn.put("organizationKey", "ORGANIZATION_KEY");
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    
                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    
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
                order = order + " ORDER BY INW.YEAR ";
            } else {
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlString + filterQuery;
        } else {
            finalQuery = sqlString + filterQuery + order;
        }
        list = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
        LOGGER.debug("Ending getInventory Details Results");
        return list;
    }
    
    private String insertQueryForInventoryDetails() {
        String query = "INSERT INTO INVENTORY_WD_PROJ_DT (\n"
                + "YEAR, \n"
                + "MONTH, \n"
                + "DAY, \n"
                + "WEEK, \n"
                + "COMPANY_ID, \n"
                + "IDENTIFIER_CODE_QUALIFIER, \n"
                + "COMPANY_IDENTIFIER, \n"
                + "ITEM_ID, \n"
                + "ITEM_IDENTIFIER_CODE_QUALIFIER, \n"
                + "ITEM_IDENTIFIER,\n"
                + "UNITS_WITHDRAWN, \n"
                + "AMOUNT_WITHDRAWN, \n"
                + "PRICE, \n"
                + "CREATED_DATE, \n"
                + "MODIFIED_DATE, \n"
                + "BATCH_ID, \n"
                + "\"SOURCE\", \n"
                + "FORECAST_NAME, \n"
                + "FORECAST_VER, \n"
                + "COUNTRY, \n"
                + "ORGANIZATION_KEY,"
                + "COMPANY_MASTER_SID,"
                + "ITEM_MASTER_SID)"
                + "VALUES(";
        return query;
    }
    
    public static List getInventoryDemandValidationQuery(final List<String> query) {
        List<FileMananagementResultDTO> resultsListDTO = new ArrayList<FileMananagementResultDTO>();
        String sqlString = "SELECT\n"
                + "    DISTINCT INW.\"YEAR\",\n"
                + "    INW.\"MONTH\",\n"
                + "    INW.\"DAY\",\n"
                + "    INW.WEEK,\n"
                + "    INW.COMPANY_ID,\n"
                + "    INW.IDENTIFIER_CODE_QUALIFIER,\n"
                + "    INW.COMPANY_IDENTIFIER,\n"
                + "    INW.ITEM_ID,\n"
                + "    INW.ITEM_IDENTIFIER_CODE_QUALIFIER,\n"
                + "    INW.ITEM_IDENTIFIER,\n"
                + "    INW.UNITS_WITHDRAWN,\n"
                + "    INW.AMOUNT_WITHDRAWN,\n"
                + "    INW.PRICE,\n"
                + "    INW.BATCH_ID,\n"
                + "    INW.ORGANIZATION_KEY,\n"
                + "    INW.RECORD_LOCK_STATUS,\n"
                + "    INW.INVENTORY_WD_PROJ_DT_SID\n"
                + "     FROM\n"
                + "    INVENTORY_WD_PROJ_DT INW WHERE INW.SOURCE LIKE '" + query.get(0) + "' AND INW.COUNTRY ='" + query.get(1) + "' AND INW.FORECAST_VER LIKE '" + query.get(NumericConstants.TWO) + "' AND INW.FORECAST_NAME LIKE '" + query.get(NumericConstants.THREE) + "'";
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
            fmDTO.setHiddenCompanyId(obj[NumericConstants.FOUR] != null ? String.valueOf(obj[NumericConstants.FOUR]) : "");
            fmDTO.setIdentifierCodeQualifier(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : "");
            fmDTO.setCompanyIdentifier(obj[NumericConstants.SIX] != null ? String.valueOf(obj[NumericConstants.SIX]) : "");
            fmDTO.setItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
            fmDTO.setHiddenItemId(obj[NumericConstants.SEVEN] != null ? String.valueOf(obj[NumericConstants.SEVEN]) : "");
            fmDTO.setItemIdentifierCodeQualifier(obj[NumericConstants.EIGHT] != null ? String.valueOf(obj[NumericConstants.EIGHT]) : "");
            fmDTO.setItemIdentifier(obj[NumericConstants.NINE] != null ? String.valueOf(obj[NumericConstants.NINE]) : "");
            fmDTO.setUnitsWithdrawn(obj[NumericConstants.TEN] != null ? String.valueOf(obj[NumericConstants.TEN]) : "");
            fmDTO.setAmountWithdrawn(obj[NumericConstants.ELEVEN] != null ? String.valueOf(obj[NumericConstants.ELEVEN]) : "");
            fmDTO.setPrice(obj[NumericConstants.TWELVE] != null ? "$"+priceFormat.format(obj[NumericConstants.TWELVE]): " " );
            fmDTO.setBatchId(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
            fmDTO.setHiddenbatchId(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : "");
            fmDTO.setOrganizationKey(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
            fmDTO.setHiddenOrganisationKey(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : "");
            boolean recordStatus = ((Boolean) obj[NumericConstants.FIFTEEN]).booleanValue();
            fmDTO.setRecordLockStatus(recordStatus);
            fmDTO.setForecastSystemId((Integer) obj[NumericConstants.SIXTEEN]);
            fmDTO.setInterfaceFlag(ConstantsUtils.CHAR_N);
            fmDTO.setCheck(false);
            resultsListDTO.add(fmDTO);
        }
        return resultsListDTO;
    }
    
    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
    
    public List getCustomerSalesResults(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering Demand Details Results");
        List list = new ArrayList();
        String sqlString = "";
        
        if (isCount) {
            sqlString = "SELECT Count(DF.CUSTOMER_GTS_FORECAST_SID) FROM CUSTOMER_GTS_FORECAST DF WHERE DF.FORECAST_NAME = ";
        } else {
            sqlString = "SELECT DISTINCT DF.FORECAST_YEAR,DF.FORECAST_MONTH,"
                    + "DF.ITEM_ID,DF.COMPANY_ID,DF.UNITS,"
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
        sqlString = sqlString.concat(" AND DF.COUNTRY='").concat(detailsResultDTO.getCountry()).concat("'");
        if (detailsResultDTO.getVersion().contains("~")) {
            String[] versionArray = detailsResultDTO.getVersion().split("~");
            sqlString = sqlString.concat(" AND ").concat(" DF.FORECAST_VER in ('" + versionArray[0] + "',");
            String tempversionArray = String.valueOf(versionArray[1].toString()).replace(".", "~").trim();
            String[] InnerArray = tempversionArray.split("~");
            int outerSize = Integer.parseInt(InnerArray[0]);
            int innerSize;
            for (int i = 1; i <= outerSize; i++) {
                if (Integer.parseInt(versionArray[0]) > outerSize) {
                    innerSize = NumericConstants.NINE;
                } else {
                    innerSize = Integer.parseInt(InnerArray[1]);
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
            sqlString = sqlString.concat(" AND ").concat(" DF.FORECAST_VER='").concat(detailsResultDTO.getVersion()).concat("'");
        }
        String filterQuery = "";
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("forcastYear", "DF.FORECAST_YEAR");
        detailsColumn.put("forecastMonth", "DF.FORECAST_MONTH");
        detailsColumn.put("itemId", "DF.ITEM_ID");
        detailsColumn.put("companyId", "DF.COMPANY_ID");
        detailsColumn.put("units", "DF.UNITS");
        detailsColumn.put("priceType", "DF.PRICE_TYPE");
        detailsColumn.put("price", "DF.PRICE");
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
        detailsColumn.put("brand", "DF.BRAND");
        detailsColumn.put("segment", "DF.SEGMENT");
        detailsColumn.put("batchId", "DF.BATCH_ID");
        detailsColumn.put("organizationKey", "DF.SOURCE");
        detailsColumn.put("forecastVersion", "DF.FORECAST_VER");
        detailsColumn.put("country", "DF.COUNTRY");
        detailsColumn.put("forecastName", "DF.FORECAST_NAME");
        detailsColumn.put("forecastDate", "DF.FORECAST_DATE");
        
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    
                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    
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
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlString + filterQuery;
        } else {
            finalQuery = sqlString + filterQuery + order;
        }
        list = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
        LOGGER.debug("Ending Demand Details Results" + list.size());
        return list;
    }
    
    public List getFileCount(String fileName, String fileType) {
        
        String query = "SELECT top 1 FM.FROM_PERIOD,FM.TO_PERIOD FROM FILE_MANAGEMENT FM \n"
                + "JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FM.FILE_TYPE       \n"
                + "WHERE FM.FORECAST_NAME='" + fileName + "' AND  HT.[DESCRIPTION] ='"
                + fileType + "' AND HT.LIST_NAME = 'FILE_TYPE' ORDER BY VERSION DESC";
        
        List result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result;
    }
    public List getAdjustedDemandDetailsResults(FileMananagementResultDTO detailsResultDTO, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        LOGGER.debug("Entering Demand Details Results");
        List list = new ArrayList();
        String sqlString = ConstantsUtils.EMPTY;

        if (isCount) {
            sqlString = "SELECT Count(DF.ADJUSTED_DEMAND_FORECAST_SID) FROM ADJUSTED_DEMAND_FORECAST DF WHERE DF.FORECAST_NAME = ";
        } else {
            sqlString = "SELECT DISTINCT DF.ITEM_ID,IM.ITEM_NAME ,\n"
                    + "DF.BRAND_ID,BM.BRAND_NAME ,\n"
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
        sqlString = sqlString.concat(" AND DF.COUNTRY='").concat(detailsResultDTO.getCountry()).concat("'");
        if (detailsResultDTO.getVersion().contains("~")) {
            String[] versionArray = detailsResultDTO.getVersion().split("~");
            sqlString = sqlString.concat(" AND ").concat(" DF.FORECAST_VER in ('" + versionArray[0] + "',");
            String tempversionArray = String.valueOf(versionArray[1].toString()).replace(".", "~").trim();
            String[] InnerArray = tempversionArray.split("~");
            int outerSize = Integer.parseInt(InnerArray[0]);
            int innerSize;
            for (int i = 1; i <= outerSize; i++) {
                if (Integer.parseInt(versionArray[0]) > outerSize) {
                    innerSize = NumericConstants.NINE;
                } else {
                    innerSize = Integer.parseInt(InnerArray[1]);
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
            sqlString = sqlString.concat(" AND ").concat(" DF.FORECAST_VER='").concat(detailsResultDTO.getVersion()).concat("'");
        }
        String filterQuery = ConstantsUtils.EMPTY;
        HashMap<String, String> detailsColumn = new HashMap<String, String>();

        detailsColumn.put("Year", "DF.YEAR");
        detailsColumn.put("Month", "DF.MONTH");
        detailsColumn.put("itemId", "DF.ITEM_ID");
        detailsColumn.put("itemName", "IM.ITEM_NAME");

        detailsColumn.put("source", "DF.SOURCE");
        detailsColumn.put("brandId", "DF.BRAND_ID");
        detailsColumn.put("brandName", "BM.BRAND_NAME");
        detailsColumn.put("segment", "DF.SEGMENT");
        detailsColumn.put("marketSizeUnits", "DF.MARKET_SIZE_UNITS");
        detailsColumn.put("marketShareRatio", "DF.MARKET_SHARE_RATIO");
        detailsColumn.put("marketShareUnits", "DF.MARKET_SHARE_UNITS");
        detailsColumn.put("uncapturedUnits", "DF.UNCAPTURED_UNITS");
        detailsColumn.put("uncapturedUnitsRatio", "DF.UNCAPTURED_UNITS_RATIO");
        detailsColumn.put("totalDemandUnits", "DF.TOTAL_DEMAND_UNITS");
        detailsColumn.put("totalDemandAmount", "DF.TOTAL_DEMAND_AMOUNT");
        detailsColumn.put("inventoryUnitChange", "DF.INVENTORY_UNIT_CHANGE");
        detailsColumn.put("grossUnits", "DF.GROSS_UNITS");
        detailsColumn.put("grossPrice", "DF.GROSS_PRICE");
        detailsColumn.put("grossAmount", "DF.GROSS_AMOUNT");
        detailsColumn.put("netSalesPrice", "DF.NET_SALES_PRICE");
        detailsColumn.put("netSalesAmount", "DF.NET_SALES_AMOUNT");
        detailsColumn.put("batchId", "DF.BATCH_ID");
        detailsColumn.put("organizationKey", "DF.ORGANIZATION_KEY");

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";

                    filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";

                }

            }
        }
        String finalQuery = ConstantsUtils.EMPTY;
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
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + startIndex;
            order = order + " ROWS FETCH NEXT " + endIndex;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            finalQuery = sqlString + filterQuery;
        } else {
            finalQuery = sqlString + filterQuery + order;
        }
        list = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
        LOGGER.debug("Ending Demand Details Results");
        return list;
    }
    
   public  String getBusinessUnit(){
        String projId = String.valueOf(VaadinSession.getCurrent().getAttribute("projectionId"));
          String query="Select BUSINESS_UNIT FROM dbo.CFF_MASTER where CFF_MASTER_SID ="+projId;
          List list=HelperTableLocalServiceUtil.executeSelectQuery(query);
           return  list==null||list.isEmpty() ?"":String.valueOf(list.get(0));
    }
}
