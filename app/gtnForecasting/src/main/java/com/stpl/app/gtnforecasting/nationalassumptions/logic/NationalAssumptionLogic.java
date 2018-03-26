package com.stpl.app.gtnforecasting.nationalassumptions.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.NationalAssumptionsDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonResultsDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.NationalAssumptionsDAOImpl;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.BaselinePeriodDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.NewNdcDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.PriceTypeDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.DataSelectionQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.MedicaidQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils.getQuator;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.BRAND_NAME;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SHOW_ALL;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SUCCESS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.GROWTH;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PER_OF_WAC;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PRICE_TRENDING;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.FederalNewNdc;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.MedicaidNewNdc;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.StNewNdc;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.FederalNewNdcLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.MedicaidNewNdcLocalServiceUtil;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.StNewNdcLocalServiceUtil;
import com.stpl.app.utils.CumulativeCalculationUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.util.BeanItem;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class NationalAssumptionLogic.
 *
 * @author Nadhiya
 */
public class NationalAssumptionLogic {

    
    /**
     * The current year.
     */
    private int currentYear;
    private final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    public static final String DESCRIPTION = "description";
    public static final String CALL_BRACKET = "{call ";
    /**
     * The description.
     */
    public static final String HELPER_TABLE_SID = "helperTableSid";
    /**
     * The list name.
     */
    public static final String LIST_NAME = "listName";

    private static final NationalAssumptionsDAO DAO = new NationalAssumptionsDAOImpl();
    private static int count;
    private static final Logger LOGGER = LoggerFactory.getLogger(NationalAssumptionLogic.class);
    /**
     * The Percent Two Decimal Places Format.
     */
    private final DecimalFormat perWithTwoDecimal = new DecimalFormat("#0.00%");
    /**
     * The Dollar Four Decimal Places Format.
     */
    private final DecimalFormat CUR_FOUR = new DecimalFormat("$#,##0.0000");
    private static final CommonResultsDAO commonDAO = new CommonResultsDAOImpl();

    public NationalAssumptionLogic() {
        super();
    }

    /**
     * Gets the periods.
     *
     * @param startIndex the start index
     * @param endIndex the end index
     * @return the periods
     */
    public List<BaselinePeriodDTO> getBasePeriods(int startIndex, int endIndex) {
        List<BaselinePeriodDTO> results = new ArrayList<>();
        List<BaselinePeriodDTO> totalresults = getBaselinePeriods();

        for (; startIndex <= endIndex; startIndex++) {

            results.add(totalresults.get(startIndex));

        }
        return results;
    }

    public List<BaselinePeriodDTO> getRollingPeriods(int startIndex, int endIndex) {
        List<BaselinePeriodDTO> results = new ArrayList<>();
        List<BaselinePeriodDTO> totalresults = getRollingPeriods();

        for (; startIndex <= endIndex; startIndex++) {

            results.add(totalresults.get(startIndex));

        }
        return results;
    }

    /**
     * Gets the all periods.
     *
     * @return the all periods
     */
    private List<BaselinePeriodDTO> getBaselinePeriods() {
        List<BaselinePeriodDTO> periods = new ArrayList<>();
        com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO startAndTodate = CommonUtils.getSessionDto();
        Date startDate = startAndTodate.getFromDate();
        Date endDate = startAndTodate.getToDate();
        int startYear = startDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        int endYear = endDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        Calendar now = CommonUtils.getCalendar();
        int currentYr = now.get(Calendar.YEAR);
        int histYear = currentYr - NumericConstants.THREE;
        int currentPeriod = getQuator(now.get(Calendar.MONTH) + 1);
        if (histYear >= startYear) {
            int years = (endYear - startYear) + 1;
            boolean hist = true;
            for (int i = 0; i < years; i++) {
                int year = (startYear) + i;
                for (int j = 1; j < NumericConstants.FIVE; j++) {
                    BaselinePeriodDTO period = new BaselinePeriodDTO();
                    period.setPeriod(Constant.Q + j + " " + year);
                    if (year == currentYr && j == currentPeriod) {
                        hist = false;
                    }
                    if (hist) {
                        period.setType(Constant.ACTUALS);
                    } else {
                        period.setType(Constant.FORECAST);
                    }

                    periods.add(period);
                }
            }
        } else {
            int years = (endYear - histYear) + 1;
            boolean hist = true;
            for (int i = 0; i < years; i++) {
                int year = (histYear) + i;
                for (int j = 1; j < NumericConstants.FIVE; j++) {
                    BaselinePeriodDTO period = new BaselinePeriodDTO();
                    period.setPeriod(Constant.Q + j + " " + year);
                    if (year == currentYr && j == currentPeriod) {
                        hist = false;
                    }
                    if (hist) {
                        period.setType(Constant.ACTUALS);
                    } else {
                        period.setType(Constant.FORECAST);
                    }
                    periods.add(period);
                }
            }
        }

        return periods;
    }

    private List<BaselinePeriodDTO> getRollingPeriods() {
        Calendar now = Calendar.getInstance();
        currentYear = now.get(Calendar.YEAR);
        List<BaselinePeriodDTO> periods = new ArrayList<>();
        for (int i = 0; i < NumericConstants.FIVE; i++) {
            int year = (currentYear - NumericConstants.THREE) + i;
            for (int j = 1; j < NumericConstants.FIVE; j++) {
                BaselinePeriodDTO period = new BaselinePeriodDTO();
                period.setPeriod(Constant.Q + j + " " + year);

                if (i < NumericConstants.THREE) {
                    period.setType(Constant.ACTUALS);
                } else {
                    period.setType(Constant.FORECAST);
                }
                periods.add(period);
            }
        }

        return periods;
    }

    public BaselinePeriodDTO getBeanFromId(Object id) {
        BeanItem<?> targetItem = null;
        if (id instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) id;
        } else if (id instanceof BaselinePeriodDTO) {
            targetItem = new BeanItem<>(
                    (BaselinePeriodDTO) id);
        }
        return (BaselinePeriodDTO) targetItem.getBean();
    }

    public String removePriceType(PriceTypeDTO priceType, SessionDTO session) {
        try {

            int priceTypeCount = 0;
            String customSql = null;
            customSql = "SELECT count(*) FROM dbo.ST_NATIONAL_ASSUMPTIONS WHERE NA_PROJ_MASTER_SID = " + priceType.getNaProjMasterSid()
                    + " AND PRICE_TYPE ='" + priceType.getPriceType() + "'" + " AND  START_PERIOD = '" + priceType.getStartPeriod() + "'"
                    + " AND END_PERIOD = '" + priceType.getEndPeriod() + "'";
            List countObj = (List) commonDAO.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
            if (countObj != null && !countObj.isEmpty()) {
                priceTypeCount = (int) countObj.get(0);
            }

            if (priceTypeCount > 0) {
                customSql = "DELETE FROM dbo.ST_NATIONAL_ASSUMPTIONS WHERE NA_PROJ_MASTER_SID = " + priceType.getNaProjMasterSid()
                        + " AND PRICE_TYPE ='" + priceType.getPriceType() + "'" + " AND  START_PERIOD = '" + priceType.getStartPeriod() + "'"
                        + " AND END_PERIOD = '" + priceType.getEndPeriod() + "'";

                commonDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return Constant.SUCCESS;
    }

    public List<PriceTypeDTO> saveNationalAssumptions(List<PriceTypeDTO> list, boolean saveFlag, SessionDTO session) {
        int projectionId = (Integer) VaadinSession.getCurrent()
                .getAttribute(Constant.PROJECTION_ID);
        if (saveFlag) {

            StringBuilder queryBuilder = new StringBuilder();
            try {
                for (PriceTypeDTO priceType : list) {
                    if (priceType.getNaProjMasterSid() == 0) {
                        queryBuilder.append("INSERT INTO ST_NATIONAL_ASSUMPTIONS (NA_PROJ_MASTER_SID, PRICE_TYPE, BASELINE_METHODOLOGY, FORECAST_METHODOLOGY, BASELINE_PERIOD, ROLLING_PERIOD, START_PERIOD, END_PERIOD, GROWTH_RATE, FREQUENCY, PRICE_BASIS,CPI_COMPOUNDING) \n"
                                + "	VALUES  ");
                        queryBuilder.append("('").append(projectionId).append("',");

                        queryBuilder.append('\'').append(priceType.getPriceType() != null ? priceType.getPriceType().equals(Constant.ANNUAL_FSS) ? "AFSS" : priceType.getPriceType() : StringUtils.EMPTY).append("',");
                        queryBuilder.append('\'').append(priceType.getBaselineMethodology() != null ? priceType.getBaselineMethodology() : StringUtils.EMPTY).append("',");
                        queryBuilder.append('\'').append(priceType
                                .getForecastMethodology() != null ? priceType.getForecastMethodology() : StringUtils.EMPTY).append("',");

                        queryBuilder.append('\'').append(priceType.getBasePeriod() != null ? priceType.getBasePeriod() : StringUtils.EMPTY).append("',");

                        queryBuilder.append('\'').append(priceType.getRollingPeriod() != null ? priceType.getRollingPeriod() : StringUtils.EMPTY).append("',");
                        queryBuilder.append('\'').append(priceType.getStartPeriod() != null ? priceType.getStartPeriod() : StringUtils.EMPTY).append("',");
                        queryBuilder.append('\'').append(priceType.getEndPeriod() != null ? priceType.getEndPeriod() : StringUtils.EMPTY).append("',");

                        if (GROWTH.getConstant().equalsIgnoreCase(priceType.getForecastMethodology())||PER_OF_WAC.getConstant().equalsIgnoreCase(priceType.getForecastMethodology())) {
                            String growthString = priceType.getGrowthRate();
                            growthString = StringUtils.isNotBlank(growthString) ? growthString.trim().replace(Constant.PERCENT, StringUtils.EMPTY) : Constant.DASH;

                            queryBuilder.append('\'').append(Double.valueOf(growthString)).append("',");
                            if (priceType.getFrequency() != null && !ANNUAL.getConstant().equals(priceType.getCpiCompounding())) {
                                queryBuilder.append('\'').append(priceType.getFrequency()).append("',");
                            } else {
                                queryBuilder.append(" NULL ,");
                            }

                        } else {
                            queryBuilder.append('\'').append(0).append("',");
                            queryBuilder.append(" NULL ,");
                        }

                        if (PRICE_TRENDING.getConstant().equalsIgnoreCase(priceType.getForecastMethodology())||PER_OF_WAC.getConstant().equalsIgnoreCase(priceType.getForecastMethodology())) {
                            String priceBasis = priceType.getPriceBasis();

                            switch (priceBasis) {
                                case "Average Quarter WAC":
                                    priceBasis = "AVGQWAC";
                                    break;
                                case "Beginning Quarter WAC":
                                    priceBasis = "BQWAC";
                                    break;
                                case "Ending Quarter WAC":
                                    priceBasis = "EQWAC";
                                    break;
                                case "Mid-Quarter WAC":
                                    priceBasis = "MQWAC";
                                    break;
                                default:
                                    break;
                            }
                            queryBuilder.append('\'').append(priceBasis).append("',");
                        } else {
                            queryBuilder.append('\'').append(StringUtils.EMPTY).append("',");
                        }
                        if (priceType.getCpiCompounding() != null) {
                            queryBuilder.append('\'').append(priceType.getCpiCompounding()).append('\'');
                        } else {
                            queryBuilder.append(" NULL ");
                        }

                        queryBuilder.append(") ; \n");

                    }
                }
                if (StringUtils.isNotBlank(queryBuilder.toString())) {
                    commonDAO.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder.toString(), session.getCurrentTableNames()));
                }
            } catch (PortalException | SystemException | NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }
            return getSavedPriceTypes(session);
        } else {
            return getSavedPriceTypes(session);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PriceTypeDTO> getSavedPriceTypes(SessionDTO session) {
        List<PriceTypeDTO> priceTypesDTOList = new ArrayList<>();
        try {
            DataSelectionQueryUtils dsQueryUtils = new DataSelectionQueryUtils();
            List<Object[]> priceTypes = dsQueryUtils.getPriceTypesList(session);
            priceTypesDTOList = getCustomizedPriceTypeResults(priceTypes);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }

        return priceTypesDTOList;
    }

    public List<PriceTypeDTO> getCustomizedPriceTypeResults(
            List<Object[]> priceTypes) {
        List<PriceTypeDTO> priceTypesResults = new ArrayList<>();
        PriceTypeDTO priceTypeResult = null;
        for (Object priceType : priceTypes) {
            priceTypeResult = new PriceTypeDTO();
            final Object[] obj = (Object[]) priceType;
            priceTypeResult.setNaProjMasterSid(Integer.parseInt(String.valueOf(obj[0])));
            priceTypeResult.setPriceType(String.valueOf(obj[1]));
            priceTypeResult.setBaselineMethodology(String.valueOf(obj[NumericConstants.TWO]));
            priceTypeResult.setForecastMethodology(String.valueOf(obj[NumericConstants.THREE]));
            if (GROWTH.getConstant().equalsIgnoreCase(priceTypeResult.getForecastMethodology())||PER_OF_WAC.getConstant().equalsIgnoreCase(priceTypeResult.getForecastMethodology())) {
                priceTypeResult.setGrowthRate(getFormattedGrowth(String.valueOf(obj[NumericConstants.FOUR]), GROWTH.getConstant().equalsIgnoreCase(priceTypeResult.getForecastMethodology())));
                priceTypeResult.setFrequency(String.valueOf(obj[NumericConstants.NINE]));
            }
            priceTypeResult.setStartPeriod(String.valueOf(obj[NumericConstants.FIVE]));
            priceTypeResult.setEndPeriod(String.valueOf(obj[NumericConstants.SIX]));
            priceTypeResult.setBasePeriod(String.valueOf(obj[NumericConstants.SEVEN]));
            priceTypeResult.setRollingPeriod(String.valueOf(obj[NumericConstants.EIGHT]));
            if (PRICE_TRENDING.getConstant().equalsIgnoreCase(priceTypeResult.getForecastMethodology())||PER_OF_WAC.getConstant().equalsIgnoreCase(priceTypeResult.getForecastMethodology())) {
                String priceBasis = String.valueOf(obj[NumericConstants.TEN] != null ? obj[NumericConstants.TEN] : StringUtils.EMPTY);
                if (priceBasis.equals("AVGQWAC")) {
                    priceBasis = "Average Quarter WAC";
                } else if (priceBasis.equals("BQWAC")) {
                    priceBasis = "Beginning Quarter WAC";
                } else if (priceBasis.equals("EQWAC")) {
                    priceBasis = "Ending Quarter WAC";
                } else if (priceBasis.equals("MQWAC")) {
                    priceBasis = "Mid-Quarter WAC";
                }
                priceTypeResult.setPriceBasis(priceBasis);
            }
            priceTypeResult.setCpiCompounding(String.valueOf(obj[NumericConstants.ELEVEN]));
            priceTypesResults.add(priceTypeResult);
        }
        return priceTypesResults;
    }

    public StNewNdc getItemNo(String itemNo) {

        StNewNdc newNDC = null;
        try {
            DynamicQuery naDynamicQuery = StNewNdcLocalServiceUtil.dynamicQuery();
            naDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.ITEM_MASTER_SID, itemNo));
            @SuppressWarnings("unchecked")
            List<StNewNdc> resultList = StNewNdcLocalServiceUtil.dynamicQuery(naDynamicQuery);
            if (resultList != null && !resultList.isEmpty()) {
                newNDC = resultList.get(0);
            }

        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
        }
        return newNDC;
    }

    public List<Object[]> NewNDCSetupCook(int projectionId) throws NamingException, SQLException {
        DataSource datasource = null;
        ResultSet resultSet = null;
        List<Object[]> objectList = new ArrayList<>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex)
        {
            LOGGER.error(ex.getMessage());
        }
            if (datasource != null) {
                try (Connection connection = datasource.getConnection();
                        CallableStatement statement = connection.prepareCall(CALL_BRACKET + "PRC_NEW_NDC_POPUP" + "(?)}"))
                { 
                    statement.setInt(1, projectionId);
                    resultSet = statement.executeQuery();
                    objectList = convertResultSetToList(resultSet);
                    LOGGER.debug("After Converting objectList size= {}" , objectList.size());
                }
             catch (NumberFormatException | SQLException ex)
                    {
                        LOGGER.error(ex.getMessage());
                    }
            }
       return objectList;
    }

    private List<Object[]> convertResultSetToList(ResultSet rs) throws SQLException {
        List<Object[]> objList = new ArrayList<>();

        try {
            while (rs.next()) {
                ResultSetMetaData metadata = rs.getMetaData();
                int numberOfColumns = metadata.getColumnCount();
                Object[] obj = new Object[numberOfColumns];
                for (int i = 1; i <= numberOfColumns; i++) {
                    obj[i - 1] = rs.getObject(i);
                }
                objList.add(obj);
            }

        } finally {

            rs.close();

        }
        return objList;
    }

    public static Object[] getBrandDynamicQuery(HelperDTO theraupticsid) throws SystemException, PortalException {

        int projectionId = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
        final DynamicQuery projDetailsQuery = NaProjDetailsLocalServiceUtil.dynamicQuery();
        projDetailsQuery.add(RestrictionsFactoryUtil.eq(Constant.NA_PROJ_MASTER_SID, projectionId));

        List<NaProjDetails> naProjDetailsList = DAO.getNaProjDetails(projDetailsQuery);
        Object[] itemMasterSid = new Object[naProjDetailsList.size() + 1];

        for (int i = 0; i < naProjDetailsList.size(); i++) {
            itemMasterSid[i] = naProjDetailsList.get(i).getItemMasterSid();
        }
        final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, itemMasterSid));
        if (theraupticsid != null && theraupticsid.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.THERAPEUTIC_CLASS, theraupticsid.getId()));
        }

        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(Constant.BRAND_MASTER_SID));
        List<ItemMaster> list = DAO.getItemMaster(dynamicQuery);

        Object[] brandSid = new Object[list.size() + 1];
        for (int i = 0; i < list.size(); i++) {
            brandSid[i] = list.get(i).getBrandMasterSid();
        }
        if (brandSid.length == 0) {
            brandSid[0] = 0;
        }

        return brandSid;
    }

    /**
     * getting count for Brand
     *
     * @param filterText
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyBrandCount(String filterText, final HelperDTO theraupticSid) throws PortalException, SystemException {

        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<Object[]> qualifierList;

        Object[] brandSid = getBrandDynamicQuery(theraupticSid);

        final DynamicQuery brandQuery = BrandMasterLocalServiceUtil.dynamicQuery();

        if (brandSid.length != 0) {
            brandQuery.add(RestrictionsFactoryUtil.in(Constant.BRAND_MASTER_SID, brandSid));
        }

        brandQuery.add(RestrictionsFactoryUtil.ilike(BRAND_NAME.getConstant(), filterText));
        brandQuery.setProjection(ProjectionFactoryUtil.countDistinct(BRAND_NAME.getConstant()));
        qualifierList = DAO.getBrandList(brandQuery);
        count = Integer.parseInt(String.valueOf(qualifierList.get(0)));

        return count;
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
    public static List<HelperDTO> getLazyBrandResults(final int start, final int end, String filterText, final HelperDTO theraupticSid, final HelperDTO preBrandVal) throws PortalException, SystemException {

        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<>();
        int startValue;
        int endValue;
        if (start == 0) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }

        Object[] brandSid = getBrandDynamicQuery(theraupticSid);

        DynamicQuery brandQuery = BrandMasterLocalServiceUtil.dynamicQuery();
        brandQuery.setLimit(startValue, endValue);
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(Constant.BRAND_MASTER_SID));
        projectionList.add(ProjectionFactoryUtil.property(BRAND_NAME.getConstant()));
        brandQuery.setProjection(projectionList);
        brandQuery.addOrder(OrderFactoryUtil.asc(BRAND_NAME.getConstant()));
        brandQuery.add(RestrictionsFactoryUtil.ilike(BRAND_NAME.getConstant(), filterText));

        if (brandSid.length != 0) {
            brandQuery.add(RestrictionsFactoryUtil.in(Constant.BRAND_MASTER_SID, brandSid));
        }

        qualifierList = DAO.getBrandList(brandQuery);
        HelperDTO dto;
        if (start == 0) {
            dto = new HelperDTO(SELECT_ONE.getConstant());
            dto.setDescription(SELECT_ONE.getConstant());
            list.add(dto);
        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            list.add(dto);
        }
        if (preBrandVal != null && list.contains(preBrandVal)) {
            list.remove(preBrandVal);
        }
        if (preBrandVal != null && start == 0) {
            dto = new HelperDTO();
            dto.setId(preBrandVal.getId());
            dto.setDescription(preBrandVal.getDescription());
            list.add(1, dto);
        }
        return list;
    }

    public static DynamicQuery getTheraupeuticDynamicQuery(String filterText) throws SystemException, PortalException {

        int projectionId = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
        final DynamicQuery projDetailsQuery = NaProjDetailsLocalServiceUtil.dynamicQuery();
        projDetailsQuery.add(RestrictionsFactoryUtil.eq(Constant.NA_PROJ_MASTER_SID, projectionId));
        List<NaProjDetails> naProjDetailsList = DAO.getNaProjDetails(projDetailsQuery);
        Object[] itemMasterSid = new Object[naProjDetailsList.size() + 1];

        for (int i = 0; i < naProjDetailsList.size(); i++) {
            itemMasterSid[i] = naProjDetailsList.get(i).getItemMasterSid();
        }
        final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, itemMasterSid));
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(Constant.THERAPEUTIC_CLASS));
        List<ItemMaster> list = DAO.getItemMaster(dynamicQuery);

        Object[] therapeuticId = new Object[list.size() + 1];
        for (int i = 0; i < list.size(); i++) {
            therapeuticId[i] = list.get(i).getTherapeuticClass();
        }
        if (therapeuticId.length == 0) {
            therapeuticId[0] = 0;
        }

        return getHelperTableByListTypeAndDescription(therapeuticId, filterText);
    }

    public static int getLazyTherapeuticClassCount(String filterText) throws PortalException, SystemException {
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;

        DynamicQuery tcDynamicQuery = getTheraupeuticDynamicQuery(filterText);
        tcDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(DESCRIPTION));
        List<HelperTable> list = DAO.getHelperTable(tcDynamicQuery);
        return Integer.parseInt(String.valueOf(list.get(0)));
    }

    public static List<HelperDTO> getLazyTherapeuticClassResults(final int start, final int end, String filterText) throws PortalException, SystemException {

        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<HelperTable> list;
        final List<HelperDTO> helperDtoList = new ArrayList<>();

        int startValue = start;
        int endValue;
        if (start == 0) {
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }

        final DynamicQuery tcDynamicQuery = getTheraupeuticDynamicQuery(filterText);
        tcDynamicQuery.setLimit(startValue, endValue);
        tcDynamicQuery.addOrder(OrderFactoryUtil.asc(HELPER_TABLE_SID));
        list = DAO.getHelperTable(tcDynamicQuery);
        HelperDTO helperTable;
        if (start == 0) {
            helperTable = new HelperDTO(SELECT_ONE.getConstant());
            helperDtoList.add(helperTable);

        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getHelperTableSid() != 0) {
                    helperTable = new HelperDTO(list.get(i).getHelperTableSid(), list.get(i).getDescription());
                    helperDtoList.add(helperTable);
                }
            }
        }
        return helperDtoList;
    }

    public static DynamicQuery getHelperTableByListTypeAndDescription(final Object[] id, final String description) {
        final DynamicQuery htDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        htDynamicQuery.add(RestrictionsFactoryUtil.in(HELPER_TABLE_SID, id));
        if (description.contains(Constant.PERCENT)) {
            htDynamicQuery.add(RestrictionsFactoryUtil.ilike(DESCRIPTION, description));
        } else {
            htDynamicQuery.add(RestrictionsFactoryUtil.eq(DESCRIPTION, description));
        }
        return htDynamicQuery;
    }

    /**
     * getting count for Brand
     *
     * @param filterText
     * @param brandMasterSid
     * @param itemFlag
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyNdcCount(String filterText, final HelperDTO brandMasterSid, boolean itemFlag) throws PortalException, SystemException {

        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<ItemMaster> qualifierList;
        DynamicQuery ndcQuery = getNdcDynamicQuery(brandMasterSid, itemFlag);
        if (itemFlag) {
            ndcQuery.add(RestrictionsFactoryUtil.ilike(Constant.ITEM_NO, filterText));
            ndcQuery.setProjection(ProjectionFactoryUtil.count(Constant.ITEM_NO));
        } else {
            ndcQuery.add(RestrictionsFactoryUtil.ilike("ndc9", filterText));
            ndcQuery.setProjection(ProjectionFactoryUtil.countDistinct("ndc9"));
        }

        qualifierList = DAO.getItemMaster(ndcQuery);
        count = Integer.parseInt(String.valueOf(qualifierList.get(0)));

        return count;
    }

    /**
     * getting results for Brand
     *
     * @param start
     * @param end
     * @param filterText
     * @param brandMasterSid
     * @param itemFlag
     * @param medicaidNdc9
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<HelperDTO> getLazyNdcResults(final int start, final int end, String filterText, final HelperDTO brandMasterSid, boolean itemFlag, final HelperDTO medicaidNdc9) throws PortalException, SystemException {
        int naProjMasterSid = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<>();
        int startValue;
        int endValue;
        if (start == 0) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }
        if (itemFlag) {
            final DynamicQuery ndcQuery = getNdcDynamicQuery(brandMasterSid, itemFlag);
            ndcQuery.setLimit(startValue, endValue);
            final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

            projectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
            projectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_NO));
            projectionList.add(ProjectionFactoryUtil.property(ITEM_DESC));
            ndcQuery.setProjection(projectionList);
            ndcQuery.addOrder(OrderFactoryUtil.asc(Constant.ITEM_NO));
            ndcQuery.add(RestrictionsFactoryUtil.ilike(Constant.ITEM_NO, filterText));

            qualifierList = DAO.getItemList(ndcQuery);
            boolean wsflag = true;
            HelperDTO dto;
            if (start == 0) {
                dto = new HelperDTO(SELECT_ONE.getConstant());
                dto.setDescription(SELECT_ONE.getConstant());
                list.add(dto);
            }
            for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
                final Object[] value = iterator.next();
                dto = new HelperDTO(StringUtils.EMPTY);
                dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
                String ndcDescription = value[NumericConstants.TWO] == null ? StringUtils.EMPTY : StringUtils.EMPTY + value[NumericConstants.TWO];
                String ndc = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(ndcDescription)) {
                    ndc += ndcDescription + Constant.COMMA;
                }
                ndc += StringUtils.EMPTY + value[1] != null ? value[1] : StringUtils.EMPTY;
                dto.setDescription(ndc);
                if (ndc.equals(medicaidNdc9.getDescription())) { // To check and select in Worksheet NDC ddlb
                    wsflag = false;
                }
                list.add(dto);
            }
            if (wsflag && start == 0 && Constant.PERCENT.equals(filterText)) { // To check and select in Worksheet NDC ddlb
                list.add(medicaidNdc9);
            }

            if (!wsflag && start != 0 && Constant.PERCENT.equals(filterText)) { // To check and remove if already selected
                list.remove(medicaidNdc9);
            }
        } else {
            MedicaidQueryUtils queryUtils = new MedicaidQueryUtils();
            HelperDTO dto;
            List<Object[]> ndc9List = queryUtils.loadMedicaidDdlb(naProjMasterSid, brandMasterSid.getId(), 0, filterText, startValue, endValue);
            if (start == 0) {
                dto = new HelperDTO(SELECT_ONE.getConstant());
                dto.setDescription(SELECT_ONE.getConstant());
                list.add(dto);
            }
            boolean mediflag = true;
            for (final Iterator<Object[]> iterator = ndc9List.iterator(); iterator.hasNext();) {
                final Object[] value = iterator.next();
                dto = new HelperDTO(Integer.parseInt(String.valueOf(value[2])),StringUtils.EMPTY);
                String itemDesc = value[1] == null ? StringUtils.EMPTY : StringUtils.EMPTY + value[1];
                String ndc9 = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(itemDesc)) {
                    ndc9 += itemDesc + Constant.COMMA;
                }
                ndc9 += value[0];
                dto.setDescription(ndc9);

                if (ndc9.equals(medicaidNdc9.getDescription())) { // To check and select in Medicaid Worksheet NDC ddlb
                    mediflag = false;
                }
                list.add(dto);
            }
            //  To check and select in Medicaid Worksheet NDC ddlb 
            if (mediflag && start == 0 && Constant.PERCENT.equals(filterText)) {
                list.add(medicaidNdc9);
            }

            if (!mediflag && start != 0 && Constant.PERCENT.equals(filterText)) { // To check and remove if already selected
                list.remove(medicaidNdc9);
            }

        }
        return list;
    }
    public static final String ITEM_DESC = "itemDesc";

    public static DynamicQuery getNdcDynamicQuery(HelperDTO brandMasterSid, boolean itemFlag) throws SystemException, PortalException {
        int naProjMasterSid = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));

        final DynamicQuery projDetailsQuery = NaProjDetailsLocalServiceUtil.dynamicQuery();
        projDetailsQuery.add(RestrictionsFactoryUtil.eq(Constant.NA_PROJ_MASTER_SID, naProjMasterSid));

        List<NaProjDetails> naProjDetailsList = DAO.getNaProjDetails(projDetailsQuery);
        Object[] itemMasterSid = new Object[naProjDetailsList.size() + 1];

        for (int i = 0; i < naProjDetailsList.size(); i++) {
            itemMasterSid[i] = naProjDetailsList.get(i).getItemMasterSid();
        }
        final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, itemMasterSid));
        if (brandMasterSid != null && brandMasterSid.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.BRAND_MASTER_SID, brandMasterSid.getId()));
        }
        if (itemFlag) {
            dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(Constant.ITEM_NO));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.isNotNull("ndc9"));
        }

        return dynamicQuery;
    }

    public String nationalAssumptionsCook(SessionDTO session) throws NamingException, SQLException {
        LOGGER.debug("Procedure nationalAssumptionsCook starts");
        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex)
        {
               LOGGER.error(ex.getMessage());
        }
            if (datasource != null) {
                try (Connection connection = datasource.getConnection();
                      CallableStatement statement = connection.prepareCall(CALL_BRACKET + "PRC_NATIONAL_ASSUMPTIONS" + "(?,?,?)}"))
                {
                statement.setInt(1, session.getProjectionId());
                statement.setInt(NumericConstants.TWO, Integer.parseInt(session.getUserId()));
                statement.setObject(NumericConstants.THREE, session.getSessionId());
                statement.execute();
            } catch (NumberFormatException | SQLException ex)
            {
                    LOGGER.error(ex.getMessage());
            }   
        } 
        LOGGER.debug("Procedure nationalAssumptionsCook ends");
        return SUCCESS.getConstant();
    }

    public List<NewNdcDTO> getNdcTable(SessionDTO session) throws SystemException, PortalException {

        List<NewNdcDTO> priceTypesDTOList = new ArrayList<>();
        DataSelectionQueryUtils queryUtils = new DataSelectionQueryUtils();
        List<Object[]> list = queryUtils.getNdcList(session);
        if (list != null && !list.isEmpty()) {
            priceTypesDTOList = getCustomizedNdcResults(list);
        }
        return priceTypesDTOList;
    }

    public List<NewNdcDTO> getCustomizedNdcResults(
            List<Object[]> priceTypes) {
        List<NewNdcDTO> priceTypesResults = new ArrayList<>();
        NewNdcDTO ndcDto = null;
        for (Object priceType : priceTypes) {
            ndcDto = new NewNdcDTO();
            final Object[] obj = (Object[]) priceType;
            ndcDto.setNdc9(String.valueOf(obj[0]));
            ndcDto.setWac(CUR_FOUR.format(obj[1]));
            ndcDto.setBaseYearAMP(CUR_FOUR.format(obj[NumericConstants.TWO]));
            ndcDto.setBaseYearCPI(CUR_FOUR.format(obj[NumericConstants.THREE]));
            ndcDto.setForecastAMP(CUR_FOUR.format(obj[NumericConstants.FOUR]));
            ndcDto.setForecastBestPrice(CUR_FOUR.format(obj[NumericConstants.FIVE]));
            priceTypesResults.add(ndcDto);
        }
        return priceTypesResults;
    }

    /**
     * getting count for Ndc Filter
     *
     * @param filterText
     * @param brandMasterSid
     * @param itemFlag
     * @param therapeuticSid
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getLazyNdcFilterCount(String filterText, HelperDTO brandMasterSid, boolean itemFlag, HelperDTO therapeuticSid) throws PortalException, SystemException {

        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<ItemMaster> qualifierList;
        final DynamicQuery ndcQuery = getNdcFilterDynamicQuery(brandMasterSid, itemFlag, therapeuticSid);
        if (itemFlag) {
            ndcQuery.add(RestrictionsFactoryUtil.ilike(Constant.ITEM_NO, filterText));
            ndcQuery.setProjection(ProjectionFactoryUtil.count(Constant.ITEM_NO));
        } else {
            ndcQuery.add(RestrictionsFactoryUtil.ilike("ndc9", filterText));
            ndcQuery.setProjection(ProjectionFactoryUtil.countDistinct("ndc9"));
        }

        qualifierList = DAO.getItemMaster(ndcQuery);
        count = Integer.parseInt(String.valueOf(qualifierList.get(0)));
        return count;
    }

    /**
     * getting results for Ndc Filter
     *
     * @param start
     * @param end
     * @param filterText
     * @param brandMasterSid
     * @param itemFlag
     * @param therapeuticSid
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static List<HelperDTO> getLazyNdcFilterResults(final int start, final int end, String filterText, boolean itemFlag, HelperDTO brandMasterSid, HelperDTO therapeuticSid, final boolean isFilter) throws PortalException, SystemException {
        int naProjMasterSid = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<>();
        int startValue;
        int endValue;
        if (start == 0) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }
        if (itemFlag) {
            final DynamicQuery ndcQuery = getNdcFilterDynamicQuery(brandMasterSid, itemFlag, therapeuticSid);
            ndcQuery.setLimit(startValue, endValue);
            final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

            projectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
            projectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_NO));
            projectionList.add(ProjectionFactoryUtil.property(ITEM_DESC));
            ndcQuery.setProjection(projectionList);

            ndcQuery.addOrder(OrderFactoryUtil.asc(ITEM_DESC));
            ndcQuery.add(RestrictionsFactoryUtil.ilike(Constant.ITEM_NO, filterText));

            qualifierList = DAO.getItemList(ndcQuery);

            HelperDTO dto;
            if (start == 0) {
                dto = new HelperDTO(isFilter ? SHOW_ALL.getConstant() : SELECT_ONE.getConstant());
                dto.setDescription(isFilter ? SHOW_ALL.getConstant() : SELECT_ONE.getConstant());
                list.add(dto);
            }
            for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
                final Object[] value = iterator.next();
                dto = new HelperDTO(StringUtils.EMPTY);
                dto.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
                String ndcDescription = value[NumericConstants.TWO] == null ? StringUtils.EMPTY : StringUtils.EMPTY + value[NumericConstants.TWO];
                String ndc = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(ndcDescription)) {
                    ndc += ndcDescription + Constant.COMMA;
                }
                ndc += StringUtils.EMPTY + value[1] != null ? value[1] : StringUtils.EMPTY;
                dto.setDescription(ndc);
                list.add(dto);
            }
        } else {
            MedicaidQueryUtils queryUtils = new MedicaidQueryUtils();
            HelperDTO dto;
            List<Object[]> ndc9List = queryUtils.loadMedicaidDdlb(naProjMasterSid, brandMasterSid.getId(), therapeuticSid.getId(), filterText, startValue, endValue);
            if (start == 0) {
                dto = new HelperDTO(isFilter ? SHOW_ALL.getConstant() : SELECT_ONE.getConstant());
                dto.setDescription(isFilter ? SHOW_ALL.getConstant() : SELECT_ONE.getConstant());
                list.add(dto);
            }

            for (final Iterator<Object[]> iterator = ndc9List.iterator(); iterator.hasNext();) {
                final Object[] value = iterator.next();
                dto = new HelperDTO(StringUtils.EMPTY);
                String itemDesc = value[1] == null ? StringUtils.EMPTY : StringUtils.EMPTY + value[1];
                String ndc9 = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(itemDesc)) {
                    ndc9 += itemDesc + Constant.COMMA;
                }
                ndc9 += value[0];
                dto.setDescription(ndc9);
                list.add(dto);

            }
        }

        return list;
    }

    public static DynamicQuery getNdcFilterDynamicQuery(HelperDTO brandMasterSid, boolean itemFlag, HelperDTO therapeuticSid) throws SystemException, PortalException {
        int naProjMasterSid = (Integer) (VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID) == null ? 0 : VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID));

        final DynamicQuery projDetailsQuery = NaProjDetailsLocalServiceUtil.dynamicQuery();
        projDetailsQuery.add(RestrictionsFactoryUtil.eq(Constant.NA_PROJ_MASTER_SID, naProjMasterSid));

        List<NaProjDetails> naProjDetailsList = DAO.getNaProjDetails(projDetailsQuery);
        Object[] itemMasterSid = new Object[naProjDetailsList.size() + 1];

        for (int i = 0; i < naProjDetailsList.size(); i++) {
            itemMasterSid[i] = naProjDetailsList.get(i).getItemMasterSid();
        }
        final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, itemMasterSid));
        if (brandMasterSid != null && brandMasterSid.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.BRAND_MASTER_SID, brandMasterSid.getId()));
        }
        if (therapeuticSid != null && therapeuticSid.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.THERAPEUTIC_CLASS, therapeuticSid.getId()));
        }
        if (itemFlag) {
            dynamicQuery.add(RestrictionsFactoryUtil.isNotNull(Constant.ITEM_NO));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.isNotNull("ndc9"));
        }

        return dynamicQuery;
    }

    public String deletePriceTypeMain(PriceTypeDTO priceType) {
        try {
            LOGGER.debug("inside deletePriceTypeMain");
            int intCount = 0;
            String customSql = null;
            customSql = "SELECT count(*) FROM dbo.NATIONAL_ASSUMPTIONS WHERE NA_PROJ_MASTER_SID = " + priceType.getNaProjMasterSid()
                    + " AND PRICE_TYPE = '" + priceType.getPriceType() + "'" + " AND START_PERIOD = '" + priceType.getStartPeriod() + "'"
                    + " AND END_PERIOD ='" + priceType.getEndPeriod() + "'";
            List countObj = (List) commonDAO.executeSelectQuery(customSql);
            if (countObj != null && !countObj.isEmpty()) {
                intCount = (int) countObj.get(0);
            }

            if (intCount > 0) {
                customSql = "DELETE FROM dbo.NATIONAL_ASSUMPTIONS WHERE NA_PROJ_MASTER_SID = " + priceType.getNaProjMasterSid()
                        + " AND PRICE_TYPE = '" + priceType.getPriceType() + "'" + " AND START_PERIOD = '" + priceType.getStartPeriod() + "'"
                        + " AND END_PERIOD ='" + priceType.getEndPeriod() + "'";

                commonDAO.executeBulkUpdateQuery(customSql);
            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return Constant.SUCCESS;
    }

    public String getFormattedGrowth(String value, boolean isGrowth) {
        if (value.contains(Constant.NULL)) {
            value = StringUtils.EMPTY;
        } else {
            Double newValue = Double.valueOf(value.trim().replace(Constant.PERCENT, StringUtils.EMPTY));
            newValue = newValue / NumericConstants.HUNDRED;
            value = isGrowth ? perWithTwoDecimal.format(newValue) : perWithTwoDecimal.format(newValue) ;
        }
        return value;
    }

    public List<NewNdcDTO> getFederalTable(SessionDTO session) throws SystemException, PortalException {

        List<NewNdcDTO> priceTypesDTOList = new ArrayList<>();
        DataSelectionQueryUtils queryUtils = new DataSelectionQueryUtils();
        List<Object[]> list = queryUtils.getFederalList(session);
        if (list != null && !list.isEmpty()) {
            priceTypesDTOList = getCustomizedFederalResults(list);
        }
        return priceTypesDTOList;
    }

    public List<NewNdcDTO> getCustomizedFederalResults(
            List<Object[]> priceTypes) {
        List<NewNdcDTO> priceTypesResults = new ArrayList<>();
        NewNdcDTO ndcDto = null;
        for (Object priceType : priceTypes) {
            ndcDto = new NewNdcDTO();
            final Object[] obj = (Object[]) priceType;
            ndcDto.setItemMasterSid(Integer.parseInt(String.valueOf(obj[0])));
            ndcDto.setItemNo(String.valueOf(obj[1]));
            ndcDto.setWac(CUR_FOUR.format(obj[NumericConstants.TWO]));
            ndcDto.setNonFamp(CUR_FOUR.format(obj[NumericConstants.THREE]));
            ndcDto.setFssOGA(CUR_FOUR.format(obj[NumericConstants.FOUR]));

            priceTypesResults.add(ndcDto);
        }
        return priceTypesResults;
    }

    public void medicaidDeleteLogic(String ndc9, SessionDTO session) {

        try {
            String customSql = "DELETE FROM dbo.ST_MEDICAID_NEW_NDC WHERE NDC9 = '" + ndc9 + "'";

            commonDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public List listCount(String ndc9, SessionDTO session) {
        String customSql = "Select *  FROM dbo.ST_MEDICAID_NEW_NDC WHERE NDC9 = '" + ndc9 + "'";
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        return list;
    }

    public void federalDeleteLogic(int itemMasterSid, SessionDTO session) {

        try {

            String customSql = "DELETE FROM dbo.ST_FEDERAL_NEW_NDC WHERE ITEM_MASTER_SID = " + itemMasterSid;

            commonDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public List federalListCount(int itemMasterSid, SessionDTO session) {
        String customSql = "DELETE FROM dbo.ST_FEDERAL_NEW_NDC WHERE ITEM_MASTER_SID = " + itemMasterSid;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
        return list;
    }

    public void federalMainDelete(int itemMasterSid) {
        try {
            LOGGER.debug("federalMainDelete Starts");
            DynamicQuery naDynamicQuery = FederalNewNdcLocalServiceUtil.dynamicQuery();
            List<FederalNewNdc> list;
            naDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.ITEM_MASTER_SID, itemMasterSid));
            list = FederalNewNdcLocalServiceUtil.dynamicQuery(naDynamicQuery);
            if (!list.isEmpty()) {
                FederalNewNdcLocalServiceUtil
                        .deleteFederalNewNdc(itemMasterSid);
            }
            LOGGER.debug("federalMainDelete ends");
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public void medicaidMainDelete(String ndc9) {
        try {
            LOGGER.debug("medicaidMainDelete Starts");
            DynamicQuery naDynamicQuery = MedicaidNewNdcLocalServiceUtil.dynamicQuery();
            List<MedicaidNewNdc> list;
            naDynamicQuery.add(RestrictionsFactoryUtil.eq("ndc9", ndc9));
            list = MedicaidNewNdcLocalServiceUtil.dynamicQuery(naDynamicQuery);
            if (!list.isEmpty()) {
                MedicaidNewNdcLocalServiceUtil
                        .deleteMedicaidNewNdc(ndc9);
                LOGGER.debug("medicaidMainDelete ends");
            }
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public List getFederalNdcDesc(int itemId) throws PortalException, SystemException {

        List federalList;
        Map<String, Object> input = new HashMap<>();

        input.put("?IMID", itemId);
        String customSql = SQlUtil.getQuery(getClass(),"getFederalNdcDesc");

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }
        if (!input.isEmpty()) {
            input.clear();
        }

        federalList = (List) commonDAO.executeSelectQuery(customSql);

        return federalList;
    }

    public int getNewNdcCount(int projectionId) throws PortalException, SystemException {
        List federalList;
        Map<String, Object> input = new HashMap<>();

        input.put("?PID", projectionId);
        String customSql = SQlUtil.getQuery(getClass(),"getNewNdcCount");

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }
        if (!input.isEmpty()) {
            input.clear();

        }
        federalList = (List) commonDAO.executeSelectQuery(customSql);

        return federalList.size();
    }

    public String newNdcCook(SessionDTO session) throws NamingException, SQLException {
        LOGGER.debug("Procedure newNdcCook starts");
        DataSource datasource = null;
        
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex)
        {
            LOGGER.error(ex.getMessage());
        }
            if (datasource != null) {
                try (Connection connection = datasource.getConnection();
                                       CallableStatement statement = connection.prepareCall(CALL_BRACKET + "PRC_NEW_NDC" + "(?,?,?)}"))
                {
                statement.setInt(1, session.getProjectionId());
                statement.setInt(NumericConstants.TWO, Integer.parseInt(session.getUserId()));
                statement.setObject(NumericConstants.THREE, session.getSessionId());
                statement.execute();
            } catch (NumberFormatException | SQLException ex)
            {
                LOGGER.error(ex.getMessage());
            }
        } 
        LOGGER.debug("Procedure newNdcCook ends");
        return SUCCESS.getConstant();
    }

    public void saveFedralNdcPopUp(SessionDTO session, List list, NewNdcDTO newNdcDTO) throws PortalException, SystemException {
        String customSql = null;
        String replaceDollarWac = newNdcDTO.getWac();
        replaceDollarWac = replaceDollarWac.replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarWac = replaceDollarWac.trim();

        String replaceDollarNonFamp = replaceDollarWac.trim();

        String replaceDollarfss = newNdcDTO.getFssOGA();
        replaceDollarfss = replaceDollarfss.replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarfss = replaceDollarfss.trim();

        if (list.isEmpty()) {
            customSql = "INSERT INTO dbo.ST_FEDERAL_NEW_NDC (ITEM_MASTER_SID, WAC_PRICE, NON_FAMP, FSS) VALUES ("
                    + newNdcDTO.getItemMasterSid() + ","
                    + replaceDollarWac + ","
                    + replaceDollarNonFamp + ","
                    + replaceDollarfss + ")";

        } else {
            customSql = "UPDATE dbo.ST_FEDERAL_NEW_NDC SET WAC_PRICE =" + newNdcDTO.getWac() + ", NON_FAMP =" + newNdcDTO.getNonFamp() + ",FSS =" + newNdcDTO.getFssOGA()
                    + " WHERE ITEM_MASTER_SID = " + newNdcDTO.getItemMasterSid();

        }
        commonDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
    }

    public void saveMedicaidNdcPopUp(SessionDTO session, List list, NewNdcDTO newNdcDTO) throws PortalException, SystemException {
        String customSql = null;
        String replaceDollarAmp = newNdcDTO.getBaseYearAMP();
        replaceDollarAmp = replaceDollarAmp.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarAmp = replaceDollarAmp.trim();

        String replaceDollarWac = newNdcDTO.getWac();
        replaceDollarWac = replaceDollarWac.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarWac = replaceDollarWac.trim();

        String replaceDollarBasecpi = newNdcDTO.getBaseYearCPI();
        replaceDollarBasecpi = replaceDollarBasecpi.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarBasecpi = replaceDollarBasecpi.trim();

        String replaceDollarForecastAmp = newNdcDTO.getForecastAMP();
        replaceDollarForecastAmp = replaceDollarForecastAmp.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarForecastAmp = replaceDollarForecastAmp.trim();

        String replaceDollarBestPrice = newNdcDTO.getForecastBestPrice();
        replaceDollarBestPrice = replaceDollarBestPrice.replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY);
        replaceDollarBestPrice = replaceDollarBestPrice.trim();

        if (list.isEmpty()) {
            customSql = "INSERT INTO dbo.ST_MEDICAID_NEW_NDC (NDC9, WAC_PRICE, BASE_YEAR_AMP, BASE_YEAR_CPI, FORECAST_AMP, FORECAST_BESTPRICE) VALUES ('"
                    + newNdcDTO.getNdc9() + "'" + ","
                    + replaceDollarWac + ","
                    + replaceDollarAmp + ","
                    + replaceDollarBasecpi + ","
                    + replaceDollarForecastAmp + ","
                    + replaceDollarBestPrice + ")";

        } else {
            customSql = "UPDATE dbo.ST_MEDICAID_NEW_NDC SET NDC9 ='" + newNdcDTO.getNdc9() + "'" + ", WAC_PRICE =" + replaceDollarWac + ",BASE_YEAR_AMP =" + replaceDollarAmp
                    + ", BASE_YEAR_CPI =" + replaceDollarBasecpi + ",FORECAST_AMP =" + replaceDollarForecastAmp + ",FORECAST_BESTPRICE =" + replaceDollarBestPrice
                    + " WHERE NDC9 = '" + newNdcDTO.getNdc9() + "'";

        }

        commonDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()));
    }

    public void cumulativeCalculation(String methodology, SessionDTO session) {
        try {
            Object[] procedureInputs = null;
            CommonUtil.getInstance().waitsForOtherThreadsToComplete(session.getFutureValue(Constant.NA_FILE_INSERT));
            LOGGER.debug("PRC_GROWTH_CALCULATION--------------------------------------- ");
            procedureInputs = new Object[]{session.getProjectionId(), session.getUserId(), session.getSessionId(), NATIONAL_ASSUMPTIONS.getConstant(), StringUtils.EMPTY, Constant.QUARTERLY, UiUtils.getDate(), "ndc9"};
			// Procedure calling part moved to Webservice
			new CumulativeCalculationUtils(procedureInputs, session.getUserId(), session.getSessionId(), methodology,
					NATIONAL_ASSUMPTIONS.getConstant(), "ST_NA_NDC9_GROWTH_FACTOR_");
            procedureInputs = new Object[]{session.getProjectionId(), session.getUserId(), session.getSessionId(), NATIONAL_ASSUMPTIONS.getConstant(), StringUtils.EMPTY, Constant.QUARTERLY, UiUtils.getDate(), "ndc11"};
			// Procedure calling part moved to Webservice
			new CumulativeCalculationUtils(procedureInputs, session.getUserId(), session.getSessionId(), methodology,
					NATIONAL_ASSUMPTIONS.getConstant(), "ST_NA_NDC11_GROWTH_FACTOR_");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    public boolean isAFSSPriceTypeAvailable(String projectionId) {
        try {
            String sql = SQlUtil.getQuery("isAFSS_PriceType_Available").replace("?", projectionId);
            List<Object[]> resultsList = (List<Object[]>) commonDAO.executeSelectQuery(sql);
            int count = getCount(resultsList);
            return count == 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return BooleanConstant.getFalseFlag();
    }

    public int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int listCount = obj == null ? 0 : (Integer) obj;
            return listCount;
        }
        return 0;
    }
}
