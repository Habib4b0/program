/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.gtnforecasting.dao.ProjectionVarianceDAO;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.gtnforecasting.dao.impl.ProjectionVarianceDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.discountProjection.logic.SupplementalDiscountProjectionLogic;
import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.dto.PMPYCalculatorDTO;
import com.stpl.app.gtnforecasting.dto.PMPYTradingPartnerDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AlternateLookupSource;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.DataSourceConnection;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.gtnforecasting.workflow.logic.WorkflowLogic;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.utils.Constants.CommonConstants;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.ComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class NonMandatedLogic.
 *
 * @author lokeshwari
 */
public class NonMandatedLogic {

	private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
        /**
	 * The SALES_SMALL projection dao.
	 */
	private final SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

	/**
	 * The p pa projection dao.
	 */
	private final PPAProjectionDao pPAProjectionDao = new PPAProjectionDaoImpl();

	/**
	 * The projection variance dao.
	 */
	private final ProjectionVarianceDAO projectionVarianceDAO = new ProjectionVarianceDAOImpl();
	/**
	 * The data selection.
	 */
	public static final DataSelectionDAO dataSelection = new DataSelectionDAOImpl();
	/**
	 * The formatter.
	 */

	/**
	 * The Constant LOGGER.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(NonMandatedLogic.class);

	/**
	 * the SALES_SMALL dao.
	 */
	private final SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();

	/**
	 * Searh view.
	 *
	 * @param viewName
	 *            the view name
	 * @param forecastType
	 *            the forecast type
	 * @param viewType
	 *            the view type
	 * @return the list< view dto>
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public static List<ViewDTO> searhView(final String viewName, final String forecastType, final String viewType)
			throws SystemException, PortalException, ParseException {
		LOGGER.debug("Entering searchView method");
		List list = null;
		final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		String viewValue = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(viewName)) {
			viewValue = viewName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		}
		list = dataSelection.findViewByName(viewValue, forecastType, userId, viewType);
		LOGGER.debug("End of searchView method");
		return Converters.getCustomizedViews(list, false);
	}

	public static List<ViewDTO> searhViewARP(final String viewName, final String forecastType, final String viewType)
			throws SystemException, PortalException, ParseException {
		LOGGER.debug("Entering searchView method");
		List list = null;
		final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		String viewValue = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(viewName)) {
			viewValue = viewName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		}
		list = getARPView(viewValue, forecastType, userId, viewType);
		LOGGER.debug("End of searchView method");
		return Converters.getCustomizedViews(list, true);
	}

	/**
	 * Delete view.
	 *
	 * @param viewId
	 *            the view id
	 * @return the string
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public static String deleteView(final int viewId) throws SystemException, PortalException {
		LOGGER.debug("Entering deleteView method with viewId= {} " , viewId);
		final ForecastingViewMaster fvm = dataSelection.deleteForecastingViewMaster(viewId);
		LOGGER.debug("End of deleteView method");
		return fvm.getViewName();
	}

	/**
	 * Update projection.
	 *
	 * @param dataSelectionDTO
	 *            the data selection dto
	 * @param projectionId
	 * @param markAsSaved
	 * @param screenName
	 * @return the string
	 * @throws PortalException
	 *             the portal exception
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public int updateProjection(final DataSelectionDTO dataSelectionDTO, int projectionId, final boolean markAsSaved,
			final String screenName) throws PortalException, SystemException {

		final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
		LOGGER.debug("Entering updateProjection ");
		if (!StringUtils.isEmpty(String.valueOf(projectionId))
				&& !CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
			projectionMaster = dataSelection.getProjectionMaster(projectionId);
		}
		projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
		projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
		projectionMaster.setForecastingType(screenName);
		projectionMaster.setModifiedBy(Integer.parseInt(userId));
		projectionMaster.setModifiedDate(new Date());
		projectionMaster.setBrandType(true);
		projectionMaster.setCustomerHierarchySid(dataSelectionDTO.getCustomerHierSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getCustomerHierSid()));
		projectionMaster.setProductHierarchySid(dataSelectionDTO.getProdHierSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getProdHierSid()));
		projectionMaster.setCustomerHierarchyLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyLevel()));
		projectionMaster.setProductHierarchyLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyLevel()));

		projectionMaster
				.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
		projectionMaster
				.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));

		projectionMaster.setCustomerHierVersionNo(dataSelectionDTO.getCustomerHierVersionNo());
		projectionMaster.setProductHierVersionNo(dataSelectionDTO.getProductHierVersionNo());
		projectionMaster.setCompanyGroupSid(dataSelectionDTO.getCustomerGrpSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
		projectionMaster.setItemGroupSid(dataSelectionDTO.getProdGrpSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getProdGrpSid()));
		projectionMaster.setCompanyMasterSid(dataSelectionDTO.getCompanySid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getCompanySid()));
		projectionMaster.setFromDate(dataSelectionDTO.getFromDate());
		if (projectionMaster.getProjectionMasterSid() == 0) {
			projectionMaster.setToDate(dataSelectionDTO.getToDate());
		}
		projectionMaster.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid().equals(DASH)
				? null : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
		projectionMaster.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid().equals(DASH)
				? null : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
		if (markAsSaved) {
			projectionMaster.setSaveFlag(true);
		}
                projectionMaster.setForecastEligibleDate(dataSelectionDTO.getForecastEligibleDate());
		projectionMaster = dataSelection.updateProjectionMaster(projectionMaster);
		return projectionMaster.getProjectionMasterSid();
	}

	/**
	 * Update customers and contracts.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param selectedCustomersList
	 *            the selected customers
	 * @return the string
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */

	/**
	 * Update products.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param selectedProducts
	 *            the selected products
	 * @return the string
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */

	/**
	 * Search alternate customer and brand.
	 *
	 * @param searchBinder
	 *            the search binder
	 * @param searchType
	 *            the search type
	 * @return the alternate lookup source
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public AlternateLookupSource searchAlternateCustomerAndBrand(final CustomFieldGroup searchBinder,
			final String searchType, boolean flag) throws SystemException {

		List<CompanyMaster> resultTPList;
		List<BrandMaster> resultBrandList;
		final AlternateLookupSource alternate = new AlternateLookupSource();

		LOGGER.debug("Entering searchAlternateBrand  ::::");

		if (Constant.TP.equalsIgnoreCase(searchType)) {
			final DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			if (!flag) {
				if (String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()) != null
						&& !StringUtils.EMPTY
								.equals(String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()))
						&& !CommonUtils.STRING_NULL
								.equals(String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()))) {

					String contractHolderName = String
							.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue());
					contractHolderName = contractHolderName.replace(CommonUtils.CHAR_ASTERISK,
							CommonUtils.CHAR_PERCENT);

					dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, contractHolderName));

				}

				if (String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()) != null
						&& !StringUtils.EMPTY
								.equals(String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()))
						&& !CommonUtils.STRING_NULL
								.equals(String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()))) {
					String customerId = String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue());

					customerId = customerId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

					dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NO, customerId));
				}
			} else {
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, Constant.PERCENT));
				dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NO, Constant.PERCENT));

			}
			dynamicQuery.add(PropertyFactoryUtil.forName(Constant.COMPANYMASTERSID)
					.in(ContractMasterLocalServiceUtil.dynamicQuery()
							.setProjection(ProjectionFactoryUtil.property("contHoldCompanyMasterSid"))));
			resultTPList = dataSelection.getCompanyMasterList(dynamicQuery);
			LOGGER.debug("Size of resultTPList= {} " , resultTPList.size());

			List<AlternateHistoryDTO> temp;
			temp = getAlternateTP(resultTPList);
			alternate.setCustomersList(temp);
		}

		if (CommonUtils.BRAND.equalsIgnoreCase(searchType)) {
			final DynamicQuery brandDynamicQuery = BrandMasterLocalServiceUtil.dynamicQuery();

			String brandName = String.valueOf(searchBinder.getField(Constant.BRAND_SEARCH).getValue()) == null
					|| CommonUtils.STRING_NULL
							.equals(String.valueOf(searchBinder.getField(Constant.BRAND_SEARCH).getValue()))
									? StringUtils.EMPTY
									: String.valueOf(searchBinder.getField(Constant.BRAND_SEARCH).getValue());
			brandName = brandName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

			brandDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.BRAND_NAME, brandName));

			resultBrandList = dataSelection.getBrandMasterList(brandDynamicQuery);

			LOGGER.debug("Size of resultBrandList= {} " , resultBrandList.size());

			List<AlternateHistoryDTO> brand;
			brand = getAlternateBrand(resultBrandList);
			alternate.setBrandList(brand);
		}

		LOGGER.debug("Ending searchAlternateBrand    ::::");
		return alternate;
	}

	/**
	 * Gets the alternate tp.
	 *
	 * @param list
	 *            the list
	 * @return the alternate tp
	 */
	public List<AlternateHistoryDTO> getAlternateTP(final List<CompanyMaster> list) {
		final List<AlternateHistoryDTO> resultList = new ArrayList<>();

		LOGGER.debug("Entering getAlternateTP  ::::");

		final int index = list.size();

		for (int i = 0; i < index; i++) {
			final CompanyMaster comapny = list.get(i);
			final AlternateHistoryDTO alternateTP = new AlternateHistoryDTO();
			alternateTP.setCustomerId(comapny.getCompanyNo());
			alternateTP.setContractHolder(comapny.getCompanyName());
			alternateTP.setContractSid(comapny.getCompanyMasterSid());
			resultList.add(alternateTP);
		}

		LOGGER.debug("Ending getAlternateTP return  size ::::= {}" , resultList.size());
		return resultList;
	}

	/**
	 * Gets the alternate brand.
	 *
	 * @param list
	 *            the list
	 * @return the alternate brand
	 */
	public List<AlternateHistoryDTO> getAlternateBrand(final List<BrandMaster> list) {
		final List<AlternateHistoryDTO> resultList = new ArrayList<>();

		LOGGER.debug("Entering getAlternateBrand  ::::");
		final int index = list.size();
		for (int i = 0; i < index; i++) {
			final BrandMaster brand = list.get(i);
			final AlternateHistoryDTO alternateBrand = new AlternateHistoryDTO();
			alternateBrand.setBrandSearch(brand.getBrandName());
			alternateBrand.setBrandSid(brand.getBrandMasterSid());
			resultList.add(alternateBrand);
		}

		LOGGER.debug("Ending getAlternateBrand return  size ::::= {}" , resultList.size());

		return resultList;
	}

	/**
	 * Trading partner look up.
	 *
	 * @param searchBinder
	 *            the search binder
	 * @return the list
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public List<PMPYTradingPartnerDTO> tradingPartnerLookUp(String tpNo, String tpName, Object contractHolder) {
		LOGGER.debug("Entering tradingPartnerLookUp  ::::");
		List input = new ArrayList<>();
		String queryName;
		if (contractHolder != null) {
			input.add(contractHolder);
			queryName = "PMPY-Load TP with CH";

		} else {
			queryName = "PMPY-Load TP without CH";
		}
		if (!tpName.isEmpty()) {
			String tradingPartnerName = tpName;
			tradingPartnerName = tradingPartnerName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			input.add(tradingPartnerName);
		} else {
			input.add(Constant.PERCENT);
		}
		if (!tpNo.isEmpty()) {
			String tradingPartnerNo = tpNo;
			tradingPartnerNo = tradingPartnerNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			input.add(tradingPartnerNo);
		} else {
			input.add(Constant.PERCENT);
		}
		List resultTPList = PPAQuerys.getPPAData(input, queryName, null);
		List<PMPYTradingPartnerDTO> tradingpartner;
		tradingpartner = getTPLookedUp(resultTPList);
		LOGGER.debug("Ending tradingPartnerLookUp    ::::");
		return tradingpartner;
	}

	/**
	 * Gets the TP looked up.
	 *
	 * @param list
	 *            the list
	 * @return the TP looked up
	 */
	public List<PMPYTradingPartnerDTO> getTPLookedUp(final List<Object[]> list) {
		final List<PMPYTradingPartnerDTO> resultList = new ArrayList<>();

		LOGGER.debug("Entering getTPLookedUp  ::::");
		final int index = list.size();
		for (int i = 0; i < index; i++) {
			Object[] obj = list.get(i);
			final PMPYTradingPartnerDTO lookedUpTP = new PMPYTradingPartnerDTO();
			lookedUpTP.setTradingPartnerNo(String.valueOf(obj[0]));
			lookedUpTP.setTradingPartnerName(String.valueOf(obj[1]));
			lookedUpTP.setCompanySysId(Integer.parseInt(String.valueOf(obj[NumericConstants.TWO])));
			resultList.add(lookedUpTP);
		}

		LOGGER.debug("Ending getTPLookedUp return  size ::::= {}" , resultList.size());

		return resultList;
	}

	/**
	 * Pmpy generate logic.
	 *
	 * @param contract
	 *            the contract
	 * @param tpName
	 *            the tp name
	 * @return the pmpy calculator dto
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             ,Exception
	 * @throws Exception
	 *             the exception
	 */
	public PMPYCalculatorDTO pmpyGenerateLogic(final String tpName) throws SystemException, PortalException {
		final PMPYCalculatorDTO bean = new PMPYCalculatorDTO();
		String tpName1;

		if (tpName == null || CommonUtils.STRING_NULL.equals(tpName)) {
			tpName1 = StringUtils.EMPTY;
		} else {
			tpName1 = tpName;
		}

		LOGGER.debug("Entering pmpyGenerateLogic  ::::");
		final List chList = new ArrayList();

		final List<PMPYCalculatorDTO> chListFinal = getChList(chList);
		bean.setContrachHolderList(chListFinal);

		final List tpList = salesDAO.getPMPYTradingPartnerList(tpName1);

		final List<PMPYCalculatorDTO> tpListFinal = getTpList(tpList);

		bean.setTradingPartnerList(tpListFinal);

		LOGGER.debug("Ending pmpyGenerateLogic    ::::");

		return bean;
	}

	/**
	 * Load pmpy contract holders.
	 *
	 * @return the list
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public ComboBox loadPMPYContractHolders(ComboBox contract) {

		LOGGER.debug("Entering loadPmpyContractHolders  ::::");

		List list = PPAQuerys.getPPAData(new ArrayList(), "PMPY-Load CH", null);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {

				Object[] obj = (Object[]) list.get(i);

				if (obj[0] != null) {
					contract.addItem(String.valueOf(obj[0]));
				}
				if (obj[1] != null) {
					contract.setItemCaption(String.valueOf(obj[0]), String.valueOf(obj[1]));
				}

			}
		}
		return contract;

	}

	/**
	 * Manual entrysales calculation.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param salesOrUnit
	 *            the SALES_SMALL or UNIT
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public void manualEntrysalesCalculation(final int projectionId, final String salesOrUnit) throws SystemException {
		final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
		Connection connection = null;

		CallableStatement statement = null;
		try {
			connection = dataSourceConnection.getConnection();

			LOGGER.debug("Entering manualEntrysalesCalculation  ::::");

			if (connection != null) {

				statement = connection.prepareCall("{call PRC_NM_SALES_PROJECTION_MANUAL(?,?)}");
				statement.setInt(1, projectionId);
				statement.setString(NumericConstants.TWO, salesOrUnit);
				statement.execute();
			}

			LOGGER.debug("Ending manualEntrysalesCalculation    ::::");

		} catch (SQLException | NamingException ex) {
			LOGGER.error("{}, {}",new Date() , ex.getMessage());
			throw new SystemException(ex);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

	/**
	 * Gets the ch list.
	 *
	 * @param chList
	 *            the ch list
	 * @return the ch list
	 * @throws Exception
	 *             the exception
	 */
	private List<PMPYCalculatorDTO> getChList(final List chList) {
		final List lives = new ArrayList();
		final List sales = new ArrayList();
		final List units = new ArrayList();

		final List<PMPYCalculatorDTO> resultList = new ArrayList<>();

		LOGGER.debug("Entering getChList  ::::");
		final int index = chList.size();

		for (int i = 0; i < index; i++) {
			final Object[] obj = (Object[]) chList.get(i);

			if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0]))
					&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
					&& Constant.C_LIVES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

				lives.add(Constant.LIVES);
				lives.add(emptyChecker(obj[1]));
				lives.add(emptyChecker(obj[NumericConstants.TWO]));
				lives.add(emptyChecker(obj[NumericConstants.THREE]));
				lives.add(emptyChecker(obj[NumericConstants.FOUR]));
				lives.add(emptyChecker(obj[NumericConstants.FIVE]));
				lives.add(emptyChecker(obj[NumericConstants.SIX]));
				lives.add(emptyChecker(obj[NumericConstants.SEVEN]));
				lives.add(emptyChecker(obj[NumericConstants.EIGHT]));
				lives.add(emptyChecker(obj[NumericConstants.NINE]));
				lives.add(emptyChecker(obj[NumericConstants.TEN]));
				lives.add(emptyChecker(obj[NumericConstants.ELEVEN]));
				lives.add(emptyChecker(obj[NumericConstants.TWELVE]));
			}

			if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0]))
					&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
					&& Constant.A_SALES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

				sales.add(Constant.ACTUAL_SALES);
				sales.add(emptyChecker(obj[1]));
				sales.add(emptyChecker(obj[NumericConstants.TWO]));
				sales.add(emptyChecker(obj[NumericConstants.THREE]));
				sales.add(emptyChecker(obj[NumericConstants.FOUR]));
				sales.add(emptyChecker(obj[NumericConstants.FIVE]));
				sales.add(emptyChecker(obj[NumericConstants.SIX]));
				sales.add(emptyChecker(obj[NumericConstants.SEVEN]));
				sales.add(emptyChecker(obj[NumericConstants.EIGHT]));
				sales.add(emptyChecker(obj[NumericConstants.NINE]));
				sales.add(emptyChecker(obj[NumericConstants.TEN]));
				sales.add(emptyChecker(obj[NumericConstants.ELEVEN]));
				sales.add(emptyChecker(obj[NumericConstants.TWELVE]));
			}
			if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0]))
					&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
					&& Constant.B_UNITS.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

				units.add(Constant.UNIT_VOLUME);
				units.add(emptyChecker(obj[1]));
				units.add(emptyChecker(obj[NumericConstants.TWO]));
				units.add(emptyChecker(obj[NumericConstants.THREE]));
				units.add(emptyChecker(obj[NumericConstants.FOUR]));
				units.add(emptyChecker(obj[NumericConstants.FIVE]));
				units.add(emptyChecker(obj[NumericConstants.SIX]));
				units.add(emptyChecker(obj[NumericConstants.SEVEN]));
				units.add(emptyChecker(obj[NumericConstants.EIGHT]));
				units.add(emptyChecker(obj[NumericConstants.NINE]));
				units.add(emptyChecker(obj[NumericConstants.TEN]));
				units.add(emptyChecker(obj[NumericConstants.ELEVEN]));
				units.add(emptyChecker(obj[NumericConstants.TWELVE]));
			}
		}
		if (sales != null && !sales.isEmpty()) {

			for (int is = 0; is < 1; is++) { // Setting SALES_SMALL
				final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

				dto.setType(String.valueOf(sales.get(0)));
				dto.setQ1y1Ch(getSales(sales.get(1)));
				dto.setQ2y1Ch(getSales(sales.get(NumericConstants.TWO)));
				dto.setQ3y1Ch(getSales(sales.get(NumericConstants.THREE)));
				dto.setQ4y1Ch(getSales(sales.get(NumericConstants.FOUR)));
				dto.setQ1y2Ch(getSales(sales.get(NumericConstants.FIVE)));
				dto.setQ2y2Ch(getSales(sales.get(NumericConstants.SIX)));
				dto.setQ3y2Ch(getSales(sales.get(NumericConstants.SEVEN)));
				dto.setQ4y2Ch(getSales(sales.get(NumericConstants.EIGHT)));
				dto.setQ1y3Ch(getSales(sales.get(NumericConstants.NINE)));
				dto.setQ2y3Ch(getSales(sales.get(NumericConstants.TEN)));
				dto.setQ3y3Ch(getSales(sales.get(NumericConstants.ELEVEN)));
				dto.setQ4y3Ch(getSales(sales.get(NumericConstants.TWELVE)));

				// Yearly
				final Double yearOne = getSales(sales.get(1)) + getSales(sales.get(NumericConstants.TWO))
						+ getSales(sales.get(NumericConstants.THREE)) + getSales(sales.get(NumericConstants.FOUR));
				final Double yearTwo = getSales(sales.get(NumericConstants.FIVE))
						+ getSales(sales.get(NumericConstants.SIX)) + getSales(sales.get(NumericConstants.SEVEN))
						+ getSales(sales.get(NumericConstants.EIGHT));
				final Double yearThree = getSales(sales.get(NumericConstants.NINE))
						+ getSales(sales.get(NumericConstants.TEN)) + getSales(sales.get(NumericConstants.ELEVEN))
						+ getSales(sales.get(NumericConstants.TWELVE));

				dto.setY1Ch(yearThree);
				dto.setY2Ch(yearTwo);
				dto.setY3Ch(yearOne);

				// semi-annuals
				final Double s1Y1 = getSales(sales.get(1)) + getSales(sales.get(NumericConstants.TWO));
				final Double s2Y1 = getSales(sales.get(NumericConstants.THREE))
						+ getSales(sales.get(NumericConstants.FOUR));
				final Double s1Y2 = getSales(sales.get(NumericConstants.FIVE))
						+ getSales(sales.get(NumericConstants.SIX));
				final Double s2Y2 = getSales(sales.get(NumericConstants.SEVEN))
						+ getSales(sales.get(NumericConstants.EIGHT));
				final Double s1y3 = getSales(sales.get(NumericConstants.NINE))
						+ getSales(sales.get(NumericConstants.TEN));
				final Double s2y3 = getSales(sales.get(NumericConstants.ELEVEN))
						+ getSales(sales.get(NumericConstants.TWELVE));

				dto.setS1y1Ch(s1Y1);
				dto.setS2y1Ch(s2Y1);
				dto.setS1y2Ch(s1Y2);
				dto.setS2y2Ch(s2Y2);
				dto.setS1y3Ch(s1y3);
				dto.setS2y3Ch(s2y3);

				resultList.add(dto);
			}
		}
		if (units != null && !units.isEmpty()) {

			for (int iu = 0; iu < 1; iu++) { // Setting UNITS_SMALL
				final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

				dto.setType(String.valueOf(units.get(0)));
				dto.setQ1y1Ch(getUnits(units.get(1))); // q1 of current year - 3
				dto.setQ2y1Ch(getUnits(units.get(NumericConstants.TWO)));
				dto.setQ3y1Ch(getUnits(units.get(NumericConstants.THREE)));
				dto.setQ4y1Ch(getUnits(units.get(NumericConstants.FOUR)));
				dto.setQ1y2Ch(getUnits(units.get(NumericConstants.FIVE))); // q1
																			// of
																			// current
																			// year
																			// -
																			// 2
				dto.setQ2y2Ch(getUnits(units.get(NumericConstants.SIX)));
				dto.setQ3y2Ch(getUnits(units.get(NumericConstants.SEVEN)));
				dto.setQ4y2Ch(getUnits(units.get(NumericConstants.EIGHT)));
				dto.setQ1y3Ch(getUnits(units.get(NumericConstants.NINE))); // q1
																			// of
																			// current
																			// year
																			// -
																			// 1
				dto.setQ2y3Ch(getUnits(units.get(NumericConstants.TEN)));
				dto.setQ3y3Ch(getUnits(units.get(NumericConstants.ELEVEN)));
				dto.setQ4y3Ch(getUnits(units.get(NumericConstants.TWELVE)));

				// Yearly
				final Double yearOne = getUnits(units.get(1)) // current year -
						// 3
						+ getUnits(units.get(NumericConstants.TWO)) + getUnits(units.get(NumericConstants.THREE))
						+ getUnits(units.get(NumericConstants.FOUR));
				final Double yearTwo = getUnits(units.get(NumericConstants.FIVE)) // current
																					// year
																					// -
						// 2
						+ getUnits(units.get(NumericConstants.SIX)) + getUnits(units.get(NumericConstants.SEVEN))
						+ getUnits(units.get(NumericConstants.EIGHT));
				final Double yearThree = getUnits(units.get(NumericConstants.NINE)) // current
																					// year
						// - 1
						+ getUnits(units.get(NumericConstants.TEN)) + getUnits(units.get(NumericConstants.ELEVEN))
						+ getUnits(units.get(NumericConstants.TWELVE));

				dto.setY1Ch(yearOne); // current year - 3
				dto.setY2Ch(yearTwo); // current year - 2
				dto.setY3Ch(yearThree); // current year - 1

				// semi-annuals
				final Double s1Y1 = getUnits(units.get(1)) // q1, q2 current
						// year - 3
						+ getUnits(units.get(NumericConstants.TWO));
				final Double s2Y1 = getUnits(units.get(NumericConstants.THREE)) // q3,
																				// q4
																				// current
						// year - 3
						+ getUnits(units.get(NumericConstants.FOUR));
				final Double s1Y2 = getUnits(units.get(NumericConstants.FIVE)) // q1,
																				// q2
																				// current
						// year - 2
						+ getUnits(units.get(NumericConstants.SIX));
				final Double s2Y2 = getUnits(units.get(NumericConstants.SEVEN)) // q3,
																				// q4
																				// current
						// year - 2
						+ getUnits(units.get(NumericConstants.EIGHT));
				final Double s1y3 = getUnits(units.get(NumericConstants.NINE)) // q1,
																				// q2
																				// current
						// year - 1
						+ getUnits(units.get(NumericConstants.TEN));
				final Double s2y3 = getUnits(units.get(NumericConstants.ELEVEN)) // q3,
																					// q4
																					// current
						// year - 1
						+ getUnits(units.get(NumericConstants.TWELVE));

				dto.setS1y1Ch(s1Y1); // s1 current year - 3
				dto.setS2y1Ch(s2Y1); // s2 current year - 3
				dto.setS1y2Ch(s1Y2); // s1 current year - 2
				dto.setS2y2Ch(s2Y2); // s2 current year - 2
				dto.setS1y3Ch(s1y3); // s1 current year - 1
				dto.setS2y3Ch(s2y3); // s2 current year - 1

				resultList.add(dto);
			}
		}
		if (sales != null && !sales.isEmpty() || units != null && !units.isEmpty()) { // Lives

			for (int il = 0; il < 1; il++) { // Setting lives
				final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

				dto.setType(String.valueOf(lives.get(0)));
				dto.setQ1y1Ch(Double.valueOf(String.valueOf(lives.get(1))));
				dto.setQ2y1Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.TWO))));
				dto.setQ3y1Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.THREE))));
				dto.setQ4y1Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.FOUR))));
				dto.setQ1y2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.FIVE))));
				dto.setQ2y2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.SIX))));
				dto.setQ3y2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.SEVEN))));
				dto.setQ4y2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.EIGHT))));
				dto.setQ1y3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.NINE))));
				dto.setQ2y3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.TEN))));
				dto.setQ3y3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.ELEVEN))));
				dto.setQ4y3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.TWELVE))));

				dto.setY1Ch(Double.valueOf(String.valueOf(lives.get(1))));
				dto.setY2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.TWO))));
				dto.setY3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.THREE))));

				dto.setS1y1Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.FOUR))));
				dto.setS2y1Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.FIVE))));
				dto.setS1y2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.SIX))));
				dto.setS2y2Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.SEVEN))));
				dto.setS1y3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.EIGHT))));
				dto.setS2y3Ch(Double.valueOf(String.valueOf(lives.get(NumericConstants.NINE))));

				resultList.add(dto);
			}
		}

		LOGGER.debug("Ending getChList return  size ::::= {}" , resultList.size());
		return resultList;
	}

	/**
	 * Gets the tp list.
	 *
	 * @param tpList
	 *            the tp list
	 * @return the tp list
	 * @throws Exception
	 *             the exception
	 */
	private List<PMPYCalculatorDTO> getTpList(final List tpList) {
		final List lives = new ArrayList();
		final List sales = new ArrayList();
		final List units = new ArrayList();

		final List<PMPYCalculatorDTO> resultList = new ArrayList<>();

		LOGGER.debug("Entering getTpList  ::::");
		final int index = tpList.size();
		for (int i = 0; i < index; i++) {
			final Object[] obj = (Object[]) tpList.get(i);
			if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0]))
					&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
					&& Constant.C_LIVES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

				lives.add(Constant.LIVES);
				lives.add(emptyChecker(obj[1]));
				lives.add(emptyChecker(obj[NumericConstants.TWO]));
				lives.add(emptyChecker(obj[NumericConstants.THREE]));
				lives.add(emptyChecker(obj[NumericConstants.FOUR]));
				lives.add(emptyChecker(obj[NumericConstants.FIVE]));
				lives.add(emptyChecker(obj[NumericConstants.SIX]));
				lives.add(emptyChecker(obj[NumericConstants.SEVEN]));
				lives.add(emptyChecker(obj[NumericConstants.EIGHT]));
				lives.add(emptyChecker(obj[NumericConstants.NINE]));
				lives.add(emptyChecker(obj[NumericConstants.TEN]));
				lives.add(emptyChecker(obj[NumericConstants.ELEVEN]));
				lives.add(emptyChecker(obj[NumericConstants.TWELVE]));
			}

			if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0]))
					&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
					&& Constant.A_SALES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

				sales.add(Constant.ACTUAL_SALES);
				sales.add(emptyChecker(obj[1]));
				sales.add(emptyChecker(obj[NumericConstants.TWO]));
				sales.add(emptyChecker(obj[NumericConstants.THREE]));
				sales.add(emptyChecker(obj[NumericConstants.FOUR]));
				sales.add(emptyChecker(obj[NumericConstants.FIVE]));
				sales.add(emptyChecker(obj[NumericConstants.SIX]));
				sales.add(emptyChecker(obj[NumericConstants.SEVEN]));
				sales.add(emptyChecker(obj[NumericConstants.EIGHT]));
				sales.add(emptyChecker(obj[NumericConstants.NINE]));
				sales.add(emptyChecker(obj[NumericConstants.TEN]));
				sales.add(emptyChecker(obj[NumericConstants.ELEVEN]));
				sales.add(emptyChecker(obj[NumericConstants.TWELVE]));
			}
			if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0]))
					&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
					&& Constant.B_UNITS.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

				units.add(Constant.UNIT_VOLUME);
				units.add(emptyChecker(obj[1]));
				units.add(emptyChecker(obj[NumericConstants.TWO]));
				units.add(emptyChecker(obj[NumericConstants.THREE]));
				units.add(emptyChecker(obj[NumericConstants.FOUR]));
				units.add(emptyChecker(obj[NumericConstants.FIVE]));
				units.add(emptyChecker(obj[NumericConstants.SIX]));
				units.add(emptyChecker(obj[NumericConstants.SEVEN]));
				units.add(emptyChecker(obj[NumericConstants.EIGHT]));
				units.add(emptyChecker(obj[NumericConstants.NINE]));
				units.add(emptyChecker(obj[NumericConstants.TEN]));
				units.add(emptyChecker(obj[NumericConstants.ELEVEN]));
				units.add(emptyChecker(obj[NumericConstants.TWELVE]));
			}
		}
		if (sales != null && !sales.isEmpty()) {

			for (int is = 0; is < 1; is++) { // Setting SALES_SMALL
				final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

				dto.setType(String.valueOf(sales.get(0)));
				dto.setQ1y1Tp(getSales(sales.get(1)));
				dto.setQ2y1Tp(getSales(sales.get(NumericConstants.TWO)));
				dto.setQ3y1Tp(getSales(sales.get(NumericConstants.THREE)));
				dto.setQ4y1Tp(getSales(sales.get(NumericConstants.FOUR)));
				dto.setQ1y2Tp(getSales(sales.get(NumericConstants.FIVE)));
				dto.setQ2y2Tp(getSales(sales.get(NumericConstants.SIX)));
				dto.setQ3y2Tp(getSales(sales.get(NumericConstants.SEVEN)));
				dto.setQ4y2Tp(getSales(sales.get(NumericConstants.EIGHT)));
				dto.setQ1y3Tp(getSales(sales.get(NumericConstants.NINE)));
				dto.setQ2y3Tp(getSales(sales.get(NumericConstants.TEN)));
				dto.setQ3y3Tp(getSales(sales.get(NumericConstants.ELEVEN)));
				dto.setQ4y3Tp(getSales(sales.get(NumericConstants.TWELVE)));

				// Yearly
				final Double yearOne = getSales(sales.get(1)) + getSales(sales.get(NumericConstants.TWO))
						+ getSales(sales.get(NumericConstants.THREE)) + getSales(sales.get(NumericConstants.FOUR));
				final Double yearTwo = getSales(sales.get(NumericConstants.FIVE))
						+ getSales(sales.get(NumericConstants.SIX)) + getSales(sales.get(NumericConstants.SEVEN))
						+ getSales(sales.get(NumericConstants.EIGHT));
				final Double yearThree = getSales(sales.get(NumericConstants.NINE))
						+ getSales(sales.get(NumericConstants.TEN)) + getSales(sales.get(NumericConstants.ELEVEN))
						+ getSales(sales.get(NumericConstants.TWELVE));

				dto.setY1Tp(yearOne);
				dto.setY2Tp(yearTwo);
				dto.setY3Tp(yearThree);

				// semi-annuals
				final Double s1Y1 = getSales(sales.get(1)) + getSales(sales.get(NumericConstants.TWO));
				final Double s2Y1 = getSales(sales.get(NumericConstants.THREE))
						+ getSales(sales.get(NumericConstants.FOUR));
				final Double s1Y2 = getSales(sales.get(NumericConstants.FIVE))
						+ getSales(sales.get(NumericConstants.SIX));
				final Double s2Y2 = getSales(sales.get(NumericConstants.SEVEN))
						+ getSales(sales.get(NumericConstants.EIGHT));
				final Double s1y3 = getSales(sales.get(NumericConstants.NINE))
						+ getSales(sales.get(NumericConstants.TEN));
				final Double s2y3 = getSales(sales.get(NumericConstants.ELEVEN))
						+ getSales(sales.get(NumericConstants.TWELVE));

				dto.setS1y1Tp(s1Y1);
				dto.setS2y1Tp(s2Y1);
				dto.setS1y2Tp(s1Y2);
				dto.setS2y2Tp(s2Y2);
				dto.setS1y3Tp(s1y3);
				dto.setS2y3Tp(s2y3);

				resultList.add(dto);
			}
		}
		if (units != null && !units.isEmpty()) {

			for (int iu = 0; iu < 1; iu++) { // Setting UNITS_SMALL
				final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

				dto.setType(String.valueOf(units.get(0)));
				dto.setQ1y1Tp(getUnits(units.get(1)));
				dto.setQ2y1Tp(getUnits(units.get(NumericConstants.TWO)));
				dto.setQ3y1Tp(getUnits(units.get(NumericConstants.THREE)));
				dto.setQ4y1Tp(getUnits(units.get(NumericConstants.FOUR)));
				dto.setQ1y2Tp(getUnits(units.get(NumericConstants.FIVE)));
				dto.setQ2y2Tp(getUnits(units.get(NumericConstants.SIX)));
				dto.setQ3y2Tp(getUnits(units.get(NumericConstants.SEVEN)));
				dto.setQ4y2Tp(getUnits(units.get(NumericConstants.EIGHT)));
				dto.setQ1y3Tp(getUnits(units.get(NumericConstants.NINE)));
				dto.setQ2y3Tp(getUnits(units.get(NumericConstants.TEN)));
				dto.setQ3y3Tp(getUnits(units.get(NumericConstants.ELEVEN)));
				dto.setQ4y3Tp(getUnits(units.get(NumericConstants.TWELVE)));

				// Yearly
				final Double yearOne = getUnits(units.get(1)) + getUnits(units.get(NumericConstants.TWO))
						+ getUnits(units.get(NumericConstants.THREE)) + getUnits(units.get(NumericConstants.FOUR));
				final Double yearTwo = getUnits(units.get(NumericConstants.FIVE))
						+ getUnits(units.get(NumericConstants.SIX)) + getUnits(units.get(NumericConstants.SEVEN))
						+ getUnits(units.get(NumericConstants.EIGHT));
				final Double yearThree = getUnits(units.get(NumericConstants.NINE))
						+ getUnits(units.get(NumericConstants.TEN)) + getUnits(units.get(NumericConstants.ELEVEN))
						+ getUnits(units.get(NumericConstants.TWELVE));

				dto.setY1Tp(yearOne);
				dto.setY2Tp(yearTwo);
				dto.setY3Tp(yearThree);

				// semi-annuals
				final Double s1Y1 = getUnits(units.get(1)) + getUnits(units.get(NumericConstants.TWO));
				final Double s2Y1 = getUnits(units.get(NumericConstants.THREE))
						+ getUnits(units.get(NumericConstants.FOUR));
				final Double s1Y2 = getUnits(units.get(NumericConstants.FIVE))
						+ getUnits(units.get(NumericConstants.SIX));
				final Double s2Y2 = getUnits(units.get(NumericConstants.SEVEN))
						+ getUnits(units.get(NumericConstants.EIGHT));
				final Double s1y3 = getUnits(units.get(NumericConstants.NINE))
						+ getUnits(units.get(NumericConstants.TEN));
				final Double s2y3 = getUnits(units.get(NumericConstants.ELEVEN))
						+ getUnits(units.get(NumericConstants.TWELVE));

				dto.setS1y1Tp(s1Y1);
				dto.setS2y1Tp(s2Y1);
				dto.setS1y2Tp(s1Y2);
				dto.setS2y2Tp(s2Y2);
				dto.setS1y3Tp(s1y3);
				dto.setS2y3Tp(s2y3);

				resultList.add(dto);
			}
		}
		if (sales != null && !sales.isEmpty() || units != null && !units.isEmpty()) { // Lives

			for (int il = 0; il < 1; il++) { // Setting
				// lives
				final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

				dto.setType(String.valueOf(lives.get(0)));
				dto.setQ1y1Tp(Double.valueOf(String.valueOf(lives.get(1))));
				dto.setQ2y1Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.TWO))));
				dto.setQ3y1Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.THREE))));
				dto.setQ4y1Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.FOUR))));
				dto.setQ1y2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.FIVE))));
				dto.setQ2y2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.SIX))));
				dto.setQ3y2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.SEVEN))));
				dto.setQ4y2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.EIGHT))));
				dto.setQ1y3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.NINE))));
				dto.setQ2y3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.TEN))));
				dto.setQ3y3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.ELEVEN))));
				dto.setQ4y3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.TWELVE))));

				dto.setY1Tp(Double.valueOf(String.valueOf(lives.get(1))));
				dto.setY2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.TWO))));
				dto.setY3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.THREE))));
				dto.setS1y1Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.FOUR))));
				dto.setS2y1Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.FIVE))));
				dto.setS1y2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.SIX))));
				dto.setS2y2Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.SEVEN))));
				dto.setS1y3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.EIGHT))));
				dto.setS2y3Tp(Double.valueOf(String.valueOf(lives.get(NumericConstants.NINE))));

				resultList.add(dto);

			}
		}

		LOGGER.debug("Ending getTpList return  size ::::= {}" , resultList.size());

		return resultList;
	}

	/**
	 * Empty checker.
	 *
	 * @param obj
	 *            the obj
	 * @return the string
	 */
	public String emptyChecker(final Object obj) {
		String value = "0.0";

		LOGGER.debug("Entering emptyChecker  ::::");

		if (String.valueOf(obj) != null && !StringUtils.EMPTY.equals(String.valueOf(obj))
				&& !CommonUtils.STRING_NULL.equals(String.valueOf(obj))) {
			value = String.valueOf(obj);
		}

		LOGGER.debug("Ending emptyChecker return  STRING ::::= {}" , value);

		return value;
	}

	/**
	 * Gets the SALES_SMALL.
	 *
	 * @param obj
	 *            the obj
	 * @return the SALES_SMALL
	 */
	public Double getSales(final Object obj) {
		Double value;
		final DecimalFormat format = new DecimalFormat("#");

		LOGGER.debug("Entering getSales  ::::");

		value = Double.valueOf(String.valueOf(obj));
		format.format(value);

		LOGGER.debug("Ending getSales return  DOUBLE ::::= {}" , value);

		return value;
	}

	/**
	 * Gets the UNITS_SMALL.
	 *
	 * @param obj
	 *            the obj
	 * @return the UNITS_SMALL
	 */
	public Double getUnits(final Object obj) {
		Double value;
		final DecimalFormat format = new DecimalFormat("#.0");

		LOGGER.debug("Entering getUnits  ::::");

		value = Double.valueOf(String.valueOf(obj));
		format.format(value);

		LOGGER.debug("Ending getUnits return  DOUBLE ::::= {}" , value);

		return value;
	}

	/**
	 * Generate projection variance for Pivot.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param indicator
	 *            the indicator
	 * @param comparisonProjections
	 *            the up to 5 projections for comparison
	 * @param frequency
	 *            the selected frequency
	 * @param level
	 *            the level
	 * @param discountLevel
	 *            the discountLevel
	 * @return the list of projection data
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public List<ProjectionVarianceDTO> generatePivotProjectionVariance(final int projectionId, final String indicator,
			final List<Integer> comparisonProjections, final String frequency, final String level,
			final String discountLevel) throws SystemException, PortalException {
		LOGGER.debug("Generate Pivot Projection Variance");

		return projectionVarianceDAO.generatePivotProjectionVariance(projectionId, indicator, comparisonProjections,
				frequency, level, discountLevel);
	}

	/**
	 * Generate PPA Group for Projection variance.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param indicator
	 *            the indicator
	 * @param comparisonProjections
	 *            the up to 5 projections for comparison
	 * @param frequency
	 *            the selected frequency
	 * @param level
	 *            the level
	 * @param discountLevel
	 *            the discount level
	 * @return the list of projection data
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public List<ProjectionVarianceDTO> getVariancePPAGroup(final int projectionId, final String indicator,
			final List<Integer> comparisonProjections, final String frequency, final String level,
			final String discountLevel) throws SystemException, PortalException {
		LOGGER.debug("Generate PPA Group for Projection Variance");

		return pPAProjectionDao.getContractHolderSummary(projectionId, indicator, comparisonProjections, frequency,
				level, discountLevel);
	}

	/**
	 * Generate Sales Group for Projection variance.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param indicator
	 *            the indicator
	 * @param comparisonProjections
	 *            the up to 5 projections for comparison
	 * @param frequency
	 *            the selected frequency
	 * @param level
	 *            the level
	 * @param discountLevel
	 *            the discount level
	 * @return the list of projection data
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public List<ProjectionVarianceDTO> getVarianceSalesGroup(final int projectionId, final String indicator,
			final List<Integer> comparisonProjections, final String frequency, final String level,
			final String discountLevel) throws SystemException, PortalException {
		LOGGER.debug("Generate PPA Group for Projection Variance");

		return salesProjectionDAO.getContractHolderSummary(projectionId, indicator, comparisonProjections, frequency,
				level, discountLevel);
	}

	/**
	 * Generate Discount Group for Projection variance.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param indicator
	 *            the indicator
	 * @param comparisonProjections
	 *            the up to 5 projections for comparison
	 * @param frequency
	 *            the selected frequency
	 * @param level
	 *            the level
	 * @param discountLevel
	 *            the discount level
	 * @return the list of projection data
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public List<ProjectionVarianceDTO> getVarianceDiscountGroup(final int projectionId, final String indicator,
			final List<Integer> comparisonProjections, final String frequency, final String level,
			final String discountLevel) throws SystemException, PortalException {
		LOGGER.debug("Generate PPA Group for  Projection Variance");

		return projectionVarianceDAO.getContractHolderSummary(projectionId, indicator, comparisonProjections, frequency,
				level, discountLevel);
	}

	/**
	 * Search for projections in comparison lookup.
	 *
	 * @param workflowStatus
	 *            the workflow status
	 * @param marketType
	 *            the market type
	 * @param brand
	 *            the brand
	 * @param projName
	 *            the proj name
	 * @param contHldr
	 *            the cont hldr
	 * @param ndcNo
	 *            the ndc no
	 * @param ndcName
	 *            the ndc name
	 * @param desc
	 *            the desc
	 * @param contract
	 *            the contract
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @return the list
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public List<DataSelectionDTO> searchForProjections(String workflowStatus, String marketType, String brand,
			String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract, String from,
			String to) throws SystemException, PortalException {

		return projectionVarianceDAO.searchForProjections(workflowStatus, marketType, brand, projName, contHldr, ndcNo,
				ndcName, desc, contract, from, to);
	}

	/**
	 * Save or updates projection variance custom view hierarchies.
	 *
	 * @param indicator
	 *            to indicate whether action is SAVE/UPDATE
	 * @param projectionId
	 *            the projection id
	 */
	public void saveProjectionVarianceView(String indicator, int projectionId) {

		projectionVarianceDAO.saveProjectionVarianceView(indicator, projectionId);

	}

	/**
	 * Gets the hierarchy data.
	 *
	 * @param indicator
	 *            the indicator whether Customer hierarchy or Product hierarchy
	 * @param hierarchyName
	 *            the hierarchy name
	 * @param hierarchyType
	 *            the hierarchy type
	 * @return the hierarchy data
	 */
	public List<HierarchyLookupDTO> getHierarchyData(final String indicator, final String hierarchyName,
			final String hierarchyType) {
		return dataSelection.getHierarchyData(indicator, hierarchyName, hierarchyType);
	}

	/**
	 * Gets the customer group.
	 *
	 * @param customerName
	 *            the customer group name search criteria
	 * @param customerNo
	 *            the customer group no search criteria
	 * @param companySids
	 * @return the customer group result list
	 * @throws java.lang.Exception
	 */
	public List<GroupDTO> getCustomerGroup(String customerName, String customerNo, List<String> companySids)
			throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		customerName = customerName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		parameters.put(Constant.CUSTOMER_NO, customerNo);
		parameters.put(Constant.CUSTOMER_NAME, customerName);
		parameters.put("companySids", companySids);
		parameters.put(Constant.INDICATOR, "CustomerGroup");

		List resultList = dataSelection.getCustomerGroup(parameters);
		return Converters.convertCustomerGroupList(resultList);

	}

	/**
	 * Gets the Product group.
	 *
	 * @param productName
	 *            the Product group name search criteria
	 * @param productNo
	 *            the Product group no search criteria
	 * @return the Product group result list
	 * @throws java.lang.Exception
	 */
	public List<GroupDTO> getProductGroup(String productName, String productNo, List<String> itemSids)
			throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		productName = productName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		productNo = productNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

		parameters.put(Constant.PRODUCT_NAME, productName);
		parameters.put(Constant.PRODUCT_NO, productNo);
		parameters.put("itemSids", itemSids);
		parameters.put(Constant.INDICATOR, "ProductGroup");
		return Converters.convertItemGroupList(dataSelection.getProductGroup(parameters));
	}

	/**
	 * Generates Saves a projection.
	 *
	 * @param dataSelectionDTO
	 *            the data selection dto
	 * @return the projection ID
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public int saveProjection(final DataSelectionDTO dataSelectionDTO, String screenName) throws SystemException {
		String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
		projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
		projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
		projectionMaster.setForecastingType(screenName);
		projectionMaster.setCreatedBy(UiUtils.parseStringToInteger(userId));
		projectionMaster.setCreatedDate(new Date());
		projectionMaster.setBrandType(true);
		projectionMaster.setCustomerHierarchySid(dataSelectionDTO.getCustomerHierSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getCustomerHierSid()));
		projectionMaster.setProductHierarchySid(dataSelectionDTO.getProdHierSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getProdHierSid()));
		projectionMaster.setCustomerHierarchyLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyLevel()));
		projectionMaster.setProductHierarchyLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyLevel()));
		projectionMaster
				.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
		projectionMaster
				.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));
		projectionMaster.setCustomerHierVersionNo(dataSelectionDTO.getCustomerHierVersionNo());
		projectionMaster.setProductHierVersionNo(dataSelectionDTO.getProductHierVersionNo());
		projectionMaster.setCompanyGroupSid(dataSelectionDTO.getCustomerGrpSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
		projectionMaster.setItemGroupSid(dataSelectionDTO.getProdGrpSid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getProdGrpSid()));
		projectionMaster.setCompanyMasterSid(dataSelectionDTO.getCompanySid().equals(DASH) ? null
				: String.valueOf(dataSelectionDTO.getCompanySid()));
		projectionMaster.setFromDate(dataSelectionDTO.getFromDate()); // Obtain
																		// from
																		// Admin
																		// Console
		projectionMaster.setToDate(dataSelectionDTO.getToDate());
		projectionMaster.setSaveFlag(false);
		projectionMaster.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid().equals(DASH)
				? null : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
		projectionMaster.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid().equals(DASH)
				? null : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
		projectionMaster.setDiscountType(dataSelectionDTO.getDiscountSid());
		projectionMaster.setForecastingType(screenName);
		projectionMaster.setBusinessUnit(dataSelectionDTO.getBusinessUnitSystemId());
		projectionMaster.setProjectionCustVersionNo(dataSelectionDTO.getCustomerRelationShipVersionNo());
		projectionMaster.setProjectionProdVersionNo(dataSelectionDTO.getProductRelationShipVersionNo());
		Object[] obj = null;
		if (CommonUtil.isValueEligibleForLoading()) {
			obj = deductionRelationBuilderId(dataSelectionDTO.getProdRelationshipBuilderSid());
		}
		projectionMaster
				.setDedRelationshipBuilderSid(CommonUtil.isValueEligibleForLoading() ? obj[0].toString() : null);
		projectionMaster.setDeductionHierarchySid(CommonUtil.isValueEligibleForLoading() ? obj[1].toString() : null);
                projectionMaster.setForecastEligibleDate(dataSelectionDTO.getForecastEligibleDate());
		projectionMaster = dataSelection.addProjectionMaster(projectionMaster);
		return projectionMaster.getProjectionMasterSid();

	}

	public Object[] deductionRelationBuilderId(String prdRelSid) {
		List<String> input = new ArrayList<>();
		input.add(prdRelSid);
		String sql = QueryUtils.getQuery(input, "DeductionRelationshipId");
		List list = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
		return list == null || list.isEmpty() ? null : (Object[]) list.get(0);
	}

	/**
	 * Search for projections.
	 *
	 * @param dataSelectionDTO
	 *            the dataSelectionDTO
	 * @return the projection master result list
	 * @throws com.liferay.portal.kernel.exception.SystemException
	 */
	public List<DataSelectionDTO> searchDSProjections(final DataSelectionDTO dataSelectionDTO)
			throws SystemException, ParseException {
		Map<String, Object> parameters = new HashMap<>();
		DynamicQuery dynamicQuery = ProjectionMasterLocalServiceUtil.dynamicQuery();
		List resultList;
		List<DataSelectionDTO> returnList = null;

		if (isValidCriteria(dataSelectionDTO.getProjectionName())) {
			String projectionName = dataSelectionDTO.getProjectionName();
			projectionName = projectionName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROJECTION_NAME, projectionName);
		}
		if (isValidCriteria(dataSelectionDTO.getDescription())) {
			String projectionDescription = dataSelectionDTO.getDescription();
			projectionDescription = projectionDescription.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROJECTIONDESCRIPTION, projectionDescription);
		}
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECASTING_TYPE, CommonUtils.NONMANDATED));

		if (isValidSid(dataSelectionDTO.getCustomerHierSid())) {
			String customerHierarchySid = dataSelectionDTO.getCustomerHierSid();
			customerHierarchySid = customerHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_HIERARCHY_SID, customerHierarchySid);
		}

		if (isValidSid(dataSelectionDTO.getProdHierSid())) {
			String prodHierarchySid = dataSelectionDTO.getProdHierSid();
			prodHierarchySid = prodHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROD_HIERARCHY_SID, prodHierarchySid);
		}

		if (isValidSid(dataSelectionDTO.getCustomerHierarchyLevel())) {
			String customerHierarchyLevel = dataSelectionDTO.getCustomerHierarchyLevel();
			customerHierarchyLevel = customerHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK,
					CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_HIERARCHY_LEVEL, customerHierarchyLevel);
		}

		if (isValidSid(dataSelectionDTO.getProductHierarchyLevel())) {
			String productHierarchyLevel = dataSelectionDTO.getProductHierarchyLevel();
			productHierarchyLevel = productHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PRODUCT_HIERARCHY_LEVEL, productHierarchyLevel);
		}

		if (isValidSid(dataSelectionDTO.getCustomerGrpSid())) {
			String customerGrpSid = dataSelectionDTO.getCustomerGrpSid();
			customerGrpSid = customerGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_GROUP_SID, customerGrpSid);
		}

		if (isValidSid(dataSelectionDTO.getProdGrpSid())) {
			String prodGrpSid = dataSelectionDTO.getProdGrpSid();
			prodGrpSid = prodGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROD_GROUP_SID, prodGrpSid);
		}

		if (isValidSid(dataSelectionDTO.getCompanySid())) {
			String companySid = dataSelectionDTO.getCompanySid();
			companySid = companySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.COMPANYMASTERSID, companySid);
		}

		resultList = dataSelection.searchDSProjections(parameters);
		returnList = Converters.searchDsProjection(resultList,
				CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(dataSelectionDTO.getModulName()),
				!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(dataSelectionDTO.getModulName()));
		return returnList;
	}

	private boolean isValidCriteria(String value) {
		boolean isValid = false;
		if (value != null && !CommonConstants.NULL.getConstant().equals(String.valueOf(value))
				&& !StringUtils.EMPTY.equals(String.valueOf(value)) && !StringUtils.isEmpty(String.valueOf(value))
				&& !StringUtils.isBlank(String.valueOf(value))) {
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}

	private boolean isValidSid(String sId) {
		boolean isValid = false;
		if (isValidCriteria(String.valueOf(sId))) {
			if (!DASH.equals(String.valueOf(sId))) {
				isValid = true;
			} else {
				isValid = false;
			}
		} else {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * Saves nm projection.
	 *
	 * @param projectionId
	 *            the projection id
	 */
	public void saveNMProjection(int projectionId) {
		dataSelection.saveNMProjection(projectionId);
	}

	/**
	 * Submit the projection.
	 *
	 * @param projectionId
	 *            the projection id
	 * @param userId
	 * @param notes
	 * @param noOfApprovals
	 * @param screenName
	 * @param getUploadedData
	 * @param description
	 * @return
	 */
	public String submitProjection(int projectionId, String userId, String notes, int noOfApprovals, String screenName,
			List<NotesDTO> getUploadedData, String description) {
		LOGGER.debug("Entering submitProjection method");
		DynamicQuery projectionDynamicQuery = ProjectionMasterLocalServiceUtil.dynamicQuery();
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.FORECASTING_TYPE,
					CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED));
		} else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			projectionDynamicQuery.add(
					RestrictionsFactoryUtil.eq(Constant.FORECASTING_TYPE, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED));
		} else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
			projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.FORECASTING_TYPE,
					CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION));
		}
		projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		String workflowStatus = StringUtils.EMPTY;
		List<ProjectionMaster> resultList;
		try {
			resultList = dataSelection.getProjectionMaster(projectionDynamicQuery);

			for (ProjectionMaster pm : resultList) {
				ProjectionMaster projMaster = pm;
				if (projMaster.getIsApproved() != null && (projMaster.getIsApproved().equals(Constant.R)
						|| projMaster.getIsApproved().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)
						|| projMaster.getIsApproved().equals("W"))) {
					workflowStatus = "RC";
				}

				projMaster.setIsApproved("Y");
				projMaster.setModifiedDate(new Date());
				dataSelection.updateProjectionMaster(projMaster);

			}

			WorkflowLogic workflowLogic = new WorkflowLogic();
			if (workflowStatus.equals("RC")) {
				LOGGER.debug("Ending submitProjection ");
				return workflowLogic.updateWorkflowFromForecast(projectionId, notes, userId);
			} else {
				LOGGER.debug("Ending submitProjection ");
				return workflowLogic.saveWorkflow(projectionId, userId, notes, noOfApprovals, screenName,
						getUploadedData, description);
			}

		} catch (PortalException | SystemException e) {
			LOGGER.error(e.getMessage());
			return "Not Saved";
		}

	}

	/**
	 * Gets companyMaster from companyMasterSid
	 *
	 * @param companyMasterSid
	 *            the company master system id
	 * @return list of companies
	 * @throws Exception
	 */
	public List<CompanyMaster> getCompanyFromSid(final int companyMasterSid) throws SystemException {
		List<CompanyMaster> companyMasterList;
		DynamicQuery companyQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
		companyQuery.add(RestrictionsFactoryUtil.eq(Constant.COMPANYMASTERSID, companyMasterSid));
		companyMasterList = dataSelection.getCompanyMasterList(companyQuery);
		return companyMasterList;
	}

	/**
	 * Gets a list of companies
	 *
	 * @param startIndex
	 * @param endIndex
	 * @param filterText
	 * @return
	 * @throws Exception
	 */
	public List<HelperDTO> getCompanies(int startIndex, int endIndex, String filterText) throws SystemException {
		DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
		filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
		dynamicQuery.add(PropertyFactoryUtil.forName("companyType")
				.in(HelperTableLocalServiceUtil.dynamicQuery()
						.add(RestrictionsFactoryUtil.eq(Constant.DESCRIPTION, "GLCOMP"))
						.setProjection(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID))));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, filterText));
		dynamicQuery.setLimit(startIndex, endIndex);
		List<Object[]> returnlist = dataSelection.getCompanies(dynamicQuery);
		List<HelperDTO> companies = new ArrayList<>();
		HelperDTO helperDTO;

		if (startIndex == 0) {
			helperDTO = new HelperDTO();
			helperDTO.setId(0);
			helperDTO.setDescription(Constant.SELECT_ONE);
			companies.add(helperDTO);
		}
		for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
			helperDTO = new HelperDTO();
			Object[] objects = (Object[]) returnlist.get(loop);
			helperDTO.setId(Integer.parseInt(String.valueOf(objects[0])));
			helperDTO.setDescription(String.valueOf(objects[1]));
			companies.add(helperDTO);
		}
		return companies;
	}

	/**
	 * Gets a list of companies
	 *
	 * @param filterText
	 * @return
	 * @throws Exception
	 */
	public int getCompaniesCount(String filterText) throws SystemException {
		filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
		DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("companyType")
				.in(HelperTableLocalServiceUtil.dynamicQuery()
						.add(RestrictionsFactoryUtil.eq(Constant.DESCRIPTION, "GLCOMP"))
						.setProjection(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID))));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, filterText));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		return dataSelection.getCompaniesCount(dynamicQuery);
	}

	public Boolean hasTradingPartner(int projectionId) throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "hasTradingPartner");
		parameters.put(Constant.PROJECTION_ID, projectionId);
		List returnList = dataSelection.executeQuery(parameters);
		if (returnList.isEmpty()) {
			return BOOLEAN_CONSTANT.getTrueFlag();
		} else {
			return (Integer) returnList.get(0) >= 1;
		}
	}

	public DataSelectionDTO getProjection(int projectionId) throws ParseException {

		List projection = dataSelection.getProjection(projectionId);
		if (projection.isEmpty()) {
			return null;
		} else {
			return Converters.getProjection(projection);
		}
	}

	public String getProjectionName(int projectionIdValue) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	public List<String> getHelperTableListNames() throws SystemException {
		DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.LIST_NAME));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		List resultList = dataSelection.getHelperTableListNames(dynamicQuery);
		List<String> returnList = new ArrayList<>();
		for (Object listName : resultList) {
			returnList.add(String.valueOf(listName));
		}
		return returnList;
	}

	public int searchDSProjectionsCount(final DataSelectionDTO dataSelectionDTO, Set<Container.Filter> filters)
			throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		DynamicQuery dynamicQuery = ProjectionMasterLocalServiceUtil.dynamicQuery();
		List resultList;
		parameters.put(Constant.LAZY_LOAD_RESULTS, null);
		parameters.put("moduleName", dataSelectionDTO.getModulName());
		if (isValidCriteria(dataSelectionDTO.getProjectionName())) {
			String projectionName = dataSelectionDTO.getProjectionName();
			projectionName = projectionName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROJECTION_NAME, projectionName);
		}
		if (isValidCriteria(dataSelectionDTO.getDescription())) {
			String projectionDescription = dataSelectionDTO.getDescription();
			projectionDescription = projectionDescription.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROJECTIONDESCRIPTION, projectionDescription);
		}
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECASTING_TYPE, CommonUtils.NONMANDATED));
		if (isValidSid(dataSelectionDTO.getCustomerHierSid())) {
			String customerHierarchySid = dataSelectionDTO.getCustomerHierSid();
			customerHierarchySid = customerHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_HIERARCHY_SID, customerHierarchySid);
		}

		if (isValidSid(dataSelectionDTO.getProdHierSid())) {
			String prodHierarchySid = dataSelectionDTO.getProdHierSid();
			prodHierarchySid = prodHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROD_HIERARCHY_SID, prodHierarchySid);
		}

		if (!dataSelectionDTO.getSelectedCustomerRelationSid().isEmpty()) {
			parameters.put("selectedCustomerRelationSid", dataSelectionDTO.getSelectedCustomerRelationSid());
		}

		if (!dataSelectionDTO.getSelectedProductRelationSid().isEmpty()) {
			parameters.put("selectedProductRelationSid", dataSelectionDTO.getSelectedProductRelationSid());
		}

		if (isValidSid(dataSelectionDTO.getCustomerHierarchyLevel())) {
			String customerHierarchyLevel = dataSelectionDTO.getCustomerHierarchyLevel();
			customerHierarchyLevel = customerHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK,
					CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_HIERARCHY_LEVEL, customerHierarchyLevel);
		}

		if (isValidSid(dataSelectionDTO.getProductHierarchyLevel())) {
			String productHierarchyLevel = dataSelectionDTO.getProductHierarchyLevel();
			productHierarchyLevel = productHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PRODUCT_HIERARCHY_LEVEL, productHierarchyLevel);
		}

		if (isValidSid(dataSelectionDTO.getCustomerGrpSid())) {
			String customerGrpSid = dataSelectionDTO.getCustomerGrpSid();
			customerGrpSid = customerGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_GROUP_SID, customerGrpSid);
		}

		if (isValidSid(String.valueOf(dataSelectionDTO.getDiscountSid()))) {
			String discountSid = String.valueOf(dataSelectionDTO.getDiscountSid());
			discountSid = discountSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put("discountSid", discountSid);
		}

		if (isValidSid(dataSelectionDTO.getProdGrpSid())) {
			String prodGrpSid = dataSelectionDTO.getProdGrpSid();
			prodGrpSid = prodGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROD_GROUP_SID, prodGrpSid);
		}
		if (dataSelectionDTO.getBusinessUnitSystemId() != 0) {
			parameters.put("businessUnit", dataSelectionDTO.getBusinessUnitSystemId());
		}

		if (isValidSid(dataSelectionDTO.getCompanySid())) {
			String companySid = dataSelectionDTO.getCompanySid();
			companySid = companySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.COMPANYMASTERSID, companySid);
		}
		if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getFromPeriod())) {
			if (!StringUtils.isBlank(dataSelectionDTO.getFromPeriod())
					&& !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getFromPeriod())) {
				String fromPeriod = DataSelectionUtil.getDateFromQuarter(dataSelectionDTO.getFromPeriod());
				parameters.put(Constant.FROM_DATE, fromPeriod);
			}
		} else {
			parameters.put(Constant.FROM_DATE, null);
		}
		if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getToPeriod())) {
			if (!StringUtils.isBlank(dataSelectionDTO.getToPeriod())
					&& !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getToPeriod())) {
				String toDate = DataSelectionUtil.getLastDateFromQuarter(dataSelectionDTO.getToPeriod());
				parameters.put(Constant.TO_DATE, toDate);
			}
		} else {
			parameters.put(Constant.TO_DATE, null);
		}
		parameters.put(Constant.SELECT_VALUE, StringUtils.EMPTY);
		parameters.put(Constant.LEFT_JOIN_VALUE, StringUtils.EMPTY);
		parameters.put(Constant.WHERE_FILTER_VALUE, StringUtils.EMPTY);
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION
				.equalsIgnoreCase(dataSelectionDTO.getModulName())) {
			parameters.put(Constant.SELECT_VALUE, "APS.FIELD_NAME,APS.FIELD_VALUES");
			parameters.put(Constant.LEFT_JOIN_VALUE,
					" Left Join ACCRUAL_PROJ_SELECTION APS ON APS.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID  ");
			parameters.put(Constant.WHERE_FILTER_VALUE, "   AND APS.SCREEN_NAME='AccrualRateProjection' ");
			if (isValidSid(dataSelectionDTO.getDeductionLevel())) {
				parameters.put("deductionLevel", dataSelectionDTO.getDeductionLevel());

			}
			if (isValidSid(dataSelectionDTO.getDeductionValue())) {
				parameters.put("deductionValue", dataSelectionDTO.getDeductionValue());

			}
		}

		if (filters != null) {
			for (Container.Filter filter : filters) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
					if ("discount".equals(stringFilter.getPropertyId())) {
						filterString = stringFilter.getFilterString();
						parameters.put(Constant.FILTER + stringFilter.getPropertyId(),
								DataSelectionUtil.filterDiscount(filterString));
					} else if (!"createdBy".equals(stringFilter.getPropertyId())) {
						parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
					} else {

						try {

							filterString = stringFilter.getFilterString();
							parameters.put(Constant.FILTER + stringFilter.getPropertyId(),
									DataSelectionUtil.filterUser(filterString));
						} catch (Exception ex) {
							LOGGER.error(ex.getMessage());
						}
					}
				} else if (filter instanceof Between) {
					Between betweenFilter = (Between) filter;
					Date startValue = (Date) betweenFilter.getStartValue();
					Date endValue = (Date) betweenFilter.getEndValue();
					parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + Constant.TILT_FROM,
							String.valueOf(startValue));
					parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
				} else if (filter instanceof Compare) {
					Compare compare = (Compare) filter;
					Compare.Operation operation = compare.getOperation();
					Date value = (Date) compare.getValue();
					if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
						parameters.put(Constant.FILTER + compare.getPropertyId() + Constant.TILT_FROM,
								String.valueOf(value));
					} else {
						parameters.put(Constant.FILTER + compare.getPropertyId() + "~to", String.valueOf(value));
					}
				}
			}
		}
		resultList = dataSelection.searchDSProjections(parameters);
		return Integer.parseInt(String.valueOf(resultList.get(0)));
	}

	public List<DataSelectionDTO> searchDSProjectionsLazy(final DataSelectionDTO dataSelectionDTO, int startIndex,
			int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns)
			throws ParseException, SystemException {
		Map<String, Object> parameters = new HashMap<>();
		DynamicQuery dynamicQuery = ProjectionMasterLocalServiceUtil.dynamicQuery();
		List resultList;
		List<DataSelectionDTO> returnList = null;
		parameters.put("startIndex", startIndex);
		parameters.put("offset", offset);
		parameters.put(Constant.LAZY_LOAD_RESULTS, Constant.LAZY_LOAD_RESULTS);
		parameters.put("moduleName", dataSelectionDTO.getModulName());
		if (isValidCriteria(dataSelectionDTO.getProjectionName())) {
			String projectionName = dataSelectionDTO.getProjectionName();
			projectionName = projectionName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROJECTION_NAME, projectionName);
		}
		if (isValidCriteria(dataSelectionDTO.getDescription())) {
			String projectionDescription = dataSelectionDTO.getDescription();
			projectionDescription = projectionDescription.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROJECTIONDESCRIPTION, projectionDescription);
		}
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.FORECASTING_TYPE, CommonUtils.NONMANDATED));
		if (isValidSid(dataSelectionDTO.getCustomerHierSid())) {
			String customerHierarchySid = dataSelectionDTO.getCustomerHierSid();
			customerHierarchySid = customerHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_HIERARCHY_SID, customerHierarchySid);
		}

		if (isValidSid(dataSelectionDTO.getProdHierSid())) {
			String prodHierarchySid = dataSelectionDTO.getProdHierSid();
			prodHierarchySid = prodHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROD_HIERARCHY_SID, prodHierarchySid);
		}
		if (!dataSelectionDTO.getSelectedCustomerRelationSid().isEmpty()) {
			parameters.put("selectedCustomerRelationSid", dataSelectionDTO.getSelectedCustomerRelationSid());
		}

		if (!dataSelectionDTO.getSelectedProductRelationSid().isEmpty()) {
			parameters.put("selectedProductRelationSid", dataSelectionDTO.getSelectedProductRelationSid());
		}

		if (isValidSid(dataSelectionDTO.getCustomerHierarchyLevel())) {
			String customerHierarchyLevel = dataSelectionDTO.getCustomerHierarchyLevel();
			customerHierarchyLevel = customerHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK,
					CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_HIERARCHY_LEVEL, customerHierarchyLevel);
		}

		if (isValidSid(dataSelectionDTO.getProductHierarchyLevel())) {
			String productHierarchyLevel = dataSelectionDTO.getProductHierarchyLevel();
			productHierarchyLevel = productHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PRODUCT_HIERARCHY_LEVEL, productHierarchyLevel);
		}
		if (isValidSid(String.valueOf(dataSelectionDTO.getDiscountSid()))) {
			String discountSid = String.valueOf(dataSelectionDTO.getDiscountSid());
			discountSid = discountSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put("discountSid", discountSid);
		}

		if (isValidSid(dataSelectionDTO.getCustomerGrpSid())) {
			String customerGrpSid = dataSelectionDTO.getCustomerGrpSid();
			customerGrpSid = customerGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.CUSTOMER_GROUP_SID, customerGrpSid);
		}

		if (isValidSid(dataSelectionDTO.getProdGrpSid())) {
			String prodGrpSid = dataSelectionDTO.getProdGrpSid();
			prodGrpSid = prodGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.PROD_GROUP_SID, prodGrpSid);
		}

		if (isValidSid(dataSelectionDTO.getCompanySid())) {
			String companySid = dataSelectionDTO.getCompanySid();
			companySid = companySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
			parameters.put(Constant.COMPANYMASTERSID, companySid);
		}

		if (dataSelectionDTO.getBusinessUnitSystemId() != 0) {
			parameters.put("businessUnit", dataSelectionDTO.getBusinessUnitSystemId());
		}

		if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getFromPeriod())) {
			if (!StringUtils.isBlank(dataSelectionDTO.getFromPeriod())
					&& !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getFromPeriod())) {
				String fromPeriod = DataSelectionUtil.getDateFromQuarter(dataSelectionDTO.getFromPeriod());
				parameters.put(Constant.FROM_DATE, fromPeriod);
			}
		} else {
			parameters.put(Constant.FROM_DATE, null);
		}
		if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getToPeriod())) {
			if (!StringUtils.isBlank(dataSelectionDTO.getToPeriod())
					&& !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getToPeriod())) {
				String toDate = DataSelectionUtil.getLastDateFromQuarter(dataSelectionDTO.getToPeriod());
				parameters.put(Constant.TO_DATE, toDate);
			}
		} else {
			parameters.put(Constant.TO_DATE, null);
		}

		parameters.put(Constant.SELECT_VALUE, StringUtils.EMPTY);
		parameters.put(Constant.LEFT_JOIN_VALUE, StringUtils.EMPTY);
		parameters.put(Constant.WHERE_FILTER_VALUE, StringUtils.EMPTY);
		if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION
				.equalsIgnoreCase(dataSelectionDTO.getModulName())) {
			parameters.put(Constant.SELECT_VALUE, ",APS.FIELD_NAME,APS.FIELD_VALUES");
			parameters.put(Constant.LEFT_JOIN_VALUE,
					"  Left Join ACCRUAL_PROJ_SELECTION APS ON APS.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID  ");
			parameters.put(Constant.WHERE_FILTER_VALUE, "   AND APS.SCREEN_NAME='AccrualRateProjection' ");
			if (isValidSid(dataSelectionDTO.getDeductionLevel())) {
				parameters.put("deductionLevel", dataSelectionDTO.getDeductionLevel());

			}
			if (isValidSid(dataSelectionDTO.getDeductionValue())) {
				parameters.put("deductionValue", dataSelectionDTO.getDeductionValue());

			}
		}

		if (filters != null) {
			for (Container.Filter filter : filters) {
				if (filter instanceof SimpleStringFilter) {
					SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
					if ("discount".equals(stringFilter.getPropertyId())) {
						filterString = stringFilter.getFilterString();
						parameters.put(Constant.FILTER + stringFilter.getPropertyId(),
								DataSelectionUtil.filterDiscount(filterString));
					} else if (!"createdBy".equals(stringFilter.getPropertyId())) {
						parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
					} else {
						filterString = stringFilter.getFilterString();
						parameters.put(Constant.FILTER + stringFilter.getPropertyId(),
								DataSelectionUtil.filterUser(filterString));
					}
				} else if (filter instanceof Between) {
					Between betweenFilter = (Between) filter;
					Date startValue = (Date) betweenFilter.getStartValue();
					Date endValue = (Date) betweenFilter.getEndValue();
					parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + Constant.TILT_FROM,
							String.valueOf(startValue));
					parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
				} else if (filter instanceof Compare) {
					Compare compare = (Compare) filter;
					Compare.Operation operation = compare.getOperation();
					Date value = (Date) compare.getValue();
					if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
						parameters.put(Constant.FILTER + compare.getPropertyId() + Constant.TILT_FROM,
								String.valueOf(value));
					} else {
						parameters.put(Constant.FILTER + compare.getPropertyId() + "~to", String.valueOf(value));
					}
				}
			}
		}

		parameters.put(Constant.ISORDERED, "false");

		for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
			SortByColumn orderByColumn = (SortByColumn) iterator.next();
			String columnId = orderByColumn.getName();
			if (orderByColumn.getType() == SortByColumn.Type.ASC) {
				parameters.put(Constant.ORDERBY + columnId, "asc");
				parameters.put(Constant.ISORDERED, Constant.TRUE);
			} else {
				parameters.put(Constant.ORDERBY + columnId, "desc");
				parameters.put(Constant.ISORDERED, Constant.TRUE);
			}
		}

		resultList = dataSelection.searchDSProjections(parameters);
		returnList = Converters.searchDsProjection(resultList,
				!CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(dataSelectionDTO.getModulName())
						? CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION
								.equals(dataSelectionDTO.getModulName()) ? true : false
						: true,
				!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(dataSelectionDTO.getModulName()));
		return returnList;
	}

	public void updateSaveFlag(final int projectionId) throws SystemException, PortalException {
		ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
		if (!StringUtils.isEmpty(String.valueOf(projectionId))
				&& !CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
			projectionMaster = dataSelection.getProjectionMaster(projectionId);
		}
		projectionMaster.setSaveFlag(true);
		dataSelection.updateProjectionMaster(projectionMaster);
	}

	public void tempInsert(final SessionDTO inputDto, final String customSqlId) {
		Map<String, Object> input = new HashMap<>();
		input.put("?PID", inputDto.getProjectionId());
		input.put("?PSY", inputDto.getForecastDTO().getProjectionStartYear());
		input.put("?PEY", inputDto.getForecastDTO().getForecastEndYear());
		String customSql = SQlUtil.getQuery(getClass(),customSqlId);
		for (String key : input.keySet()) {
			if (customSql.contains(key)) {
				customSql = customSql.replace(key, String.valueOf(input.get(key)));
			}
		}
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(customSql, inputDto.getCurrentTableNames()));
	}

	public Object saveTampToMain(final SessionDTO inputDto) throws SystemException {
		Map<String, Object> input = new HashMap<>();
		input.put("?UID", inputDto.getUserId());
		input.put("?SID", inputDto.getSessionId());
		return dataSelection.tempOperation(input, "nm.saveToMainTable");
	}

	public Object deleteTempBySession() throws SystemException {
		return null;
	}

	public void setSingleBrand(final SessionDTO inputDto) throws SystemException {
		Map<String, Object> input = new HashMap<>();
		input.put("?PID", inputDto.getProjectionId());
		inputDto.setPpaIndicator((Integer) ((List) dataSelection.tempOperation(input, "ds.singleBrand")).get(0) == 1);
	}

	public Object deleteTempByProjection(final int projectionSid) throws SystemException {
		Map<String, Object> input = new HashMap<>();
		input.put("?PID", projectionSid);
		return dataSelection.tempOperation(input, "nm.dleteTempProjectionTables");
	}

	public int updateBasicsProjectionMaster(final DataSelectionDTO dataSelectionDTO, int projectionId,
			final boolean markAsSaved) throws PortalException, SystemException {

		final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
		LOGGER.debug("Entering updateBasicsProjectionMaster ");
		if (!StringUtils.isEmpty(String.valueOf(projectionId))
				&& !CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
			projectionMaster = dataSelection.getProjectionMaster(projectionId);
		}
		projectionMaster
				.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
		projectionMaster
				.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));
		projectionMaster.setForecastingType(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
		projectionMaster.setModifiedBy(Integer.parseInt(userId));
		projectionMaster.setModifiedDate(new Date());
		if (markAsSaved) {
			projectionMaster.setSaveFlag(true);
		}
		projectionMaster = dataSelection.updateProjectionMaster(projectionMaster);
		return projectionMaster.getProjectionMasterSid();
	}

	public void tempInsert(final SessionDTO inputDto) throws SystemException, ParseException, PortalException {

		final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
		Date tempDate = fmt.parse(inputDto.getSessionDate());
		insertIntoTempTables(inputDto.getUserId(), inputDto.getSessionId(), tempDate,
				String.valueOf(inputDto.getProjectionId()));
		if (Constant.EDIT.equalsIgnoreCase(inputDto.getAction())
				|| Constant.VIEW.equalsIgnoreCase(inputDto.getAction())) {
			SupplementalDiscountProjectionLogic logic = new SupplementalDiscountProjectionLogic();
			String pojectionDetailsSids = CommonUtils.CollectionToString(logic.getProjectionDetailsSid(inputDto),
					false);
			logic.masterToTempCopy(inputDto, pojectionDetailsSids);
		}
		Object[] orderedArgs = { inputDto.getProjectionId(), inputDto.getMarketTypeValue(), inputDto.getUserId(),
				inputDto.getSessionId() };
		if (!ACTION_VIEW.getConstant().equalsIgnoreCase(inputDto.getAction())) {
			CommonLogic.callProcedureforUpdate(Constant.SUPPLEMENTAL_INSERT_PRC, orderedArgs);
		}
		Object[] orderedArgs1 = { inputDto.getProjectionId(), inputDto.getUserId(), "SPAP", inputDto.getSessionId() };
		CommonLogic.callProcedureforUpdate("PRC_M_DISCOUNT_INSERT", orderedArgs1);
	}

	public void insertIntoTempTables(String userId, String sessionId, Date lastModifiedDate, String projectionId)
			throws PortalException, SystemException {
		insertTempSalesProjectionMaster(userId, sessionId, lastModifiedDate, projectionId);
		insertTempActualSales(userId, sessionId, lastModifiedDate, projectionId);
		insertTempSalesProjection(userId, sessionId, lastModifiedDate, projectionId);
	}

	public void insertTempSalesProjectionMaster(String userId, String sessionId, Date lastModifiedDate,
			String projectionId) throws PortalException, SystemException {
		final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
		String lastModified = fmt.format(lastModifiedDate);
		String insertQuery = "INSERT INTO ST_M_SALES_PROJECTION_MASTER(\n" + "	PROJECTION_DETAILS_SID,\n"
				+ "			METHODOLOGY,\n" + "			CALCULATION_PERIODS,\n" + "			CALCULATION_BASED,\n"
				+ "			CHECK_RECORD, \n" + "	USER_ID,\n" + "			SESSION_ID, \n"
				+ "			LAST_MODIFIED_DATE)\n" + "	SELECT A.PROJECTION_DETAILS_SID,\n"
				+ "			A.METHODOLOGY,\n" + "			A.CALCULATION_PERIODS,\n"
				+ "			A.CALCULATION_BASED,\n" + "			A.CHECK_RECORD, \n" + "			" + userId
				+ "  USER_ID, \n" + "			" + sessionId + " SESSION_ID ,\n" + "			'" + lastModified
				+ "' LAST_MODIFIED_DATE  \n" + "		FROM dbo.M_SALES_PROJECTION_MASTER A,\n"
				+ "		dbo.PROJECTION_DETAILS B\n" + "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID \n"
				+ "			AND B.PROJECTION_MASTER_SID = " + projectionId + ";";

		SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();
		salesDAO.executeUpdateQuery(insertQuery);

	}

	public void insertTempSalesProjection(String userId, String sessionId, Date lastModifiedDate, String projectionId)
			throws PortalException, SystemException {
		final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
		String lastModified = fmt.format(lastModifiedDate);
		String insertQuery = "INSERT INTO dbo.ST_M_SALES_PROJECTION(\n" + "			PROJECTION_DETAILS_SID,\n"
				+ "			ACCOUNT_GROWTH,\n" + "			PRODUCT_GROWTH,\n" + "			PROJECTION_SALES,\n"
				+ "			PROJECTION_UNITS,\n" + "			PERIOD_SID,\n" + "			ADJUSTMENT_TYPE,\n"
				+ "			ADJUSTMENT_BASIS,\n" + "			ADJUSTMENT_VARIABLE,\n"
				+ "			ADJUSTMENT_METHODOLOGY,\n" + "			ADJUSTMENT_VALUES,\n" + "			USER_ID,\n"
				+ "			SESSION_ID,\n" + "			LAST_MODIFIED_DATE )\n"
				+ "		SELECT A.PROJECTION_DETAILS_SID,\n" + "			A.ACCOUNT_GROWTH,\n"
				+ "			A.PRODUCT_GROWTH,\n" + "			A.PROJECTION_SALES,\n"
				+ "			A.PROJECTION_UNITS,\n" + "			A.PERIOD_SID,\n" + "			A.ADJUSTMENT_TYPE,\n"
				+ "			A.ADJUSTMENT_BASIS,\n" + "			A.ADJUSTMENT_VARIABLE,\n"
				+ "			A.ADJUSTMENT_METHODOLOGY,\n" + "			A.ADJUSTMENT_VALUES,\n" + "			" + userId
				+ " USER_ID,\n" + "			" + sessionId + " SESSION_ID,\n" + "			'" + lastModified
				+ "' LAST_MODIFIED_DATE \n" + "		FROM dbo.M_SALES_PROJECTION A,\n"
				+ "			dbo.PROJECTION_DETAILS B\n"
				+ "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
				+ "			AND B.PROJECTION_MASTER_SID=" + projectionId + ";";

		SalesProjectionDAO DAO = new SalesProjectionDAOImpl();
		DAO.executeUpdateQuery(insertQuery);

	}

	public void insertTempActualSales(String userId, String sessionId, Date lastModifiedDate, String projectionId)
			throws PortalException, SystemException {
		final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
		String lastModified = fmt.format(lastModifiedDate);
		String insertQuery = "INSERT INTO dbo.ST_M_ACTUAL_SALES(\n" + "			PROJECTION_DETAILS_SID,\n"
				+ "			PERIOD_SID,\n" + "			ACTUAL_SALES,\n" + "			ACTUAL_UNITS,\n"
				+ "			ACTUAL_PROJECTION_SALES,\n" + "			ACTUAL_PROJECTION_UNITS,\n" + "			USER_ID,\n"
				+ "			SESSION_ID,\n" + "			LAST_MODIFIED_DATE)\n"
				+ "		SELECT A.PROJECTION_DETAILS_SID,\n" + "			A.PERIOD_SID,\n"
				+ "			A.ACTUAL_SALES,\n" + "			A.ACTUAL_UNITS,\n"
				+ "			A.ACTUAL_PROJECTION_SALES,\n" + "			A.ACTUAL_PROJECTION_UNITS,\n" + "			"
				+ userId + " USER_ID,\n" + "			" + sessionId + " SESSION_ID,\n" + "			'"
				+ lastModified + "' LAST_MODIFIED_DATE \n" + "		FROM dbo.M_ACTUAL_SALES A,\n"
				+ "			dbo.PROJECTION_DETAILS B\n"
				+ "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
				+ "			AND B.PROJECTION_MASTER_SID=" + projectionId + ";";

		SalesProjectionDAO spDAO = new SalesProjectionDAOImpl();
		spDAO.executeUpdateQuery(insertQuery);

	}

	public void tempInsertReturns(final SessionDTO inputDto, final String query) {
		String insertQuery = StringUtils.EMPTY;
		try {
			insertQuery = CommonUtils.getReturnsProperties(query);
			insertQuery = insertQuery.replace(Constant.AT_PROJECTION_MASTER_SID,
					StringUtils.EMPTY + inputDto.getProjectionId());
			insertQuery = insertQuery.replace("@USER_ID", StringUtils.EMPTY + inputDto.getUserId());
			insertQuery = insertQuery.replace("@SESSION_ID", StringUtils.EMPTY + inputDto.getSessionId());

			SalesProjectionDAO salesProjDAO = new SalesProjectionDAOImpl();
			salesProjDAO.executeUpdateQuery(QueryUtil.replaceTableNames(insertQuery, inputDto.getCurrentTableNames()));
		} catch (PortalException | SystemException e) {
			LOGGER.error("Query:======================>= {}" , insertQuery);
			LOGGER.error(e.getMessage());
		}
	}

	public void tempTableInsertReturns(final SessionDTO inputDto, final String query) {
		String insertQuery = StringUtils.EMPTY;
		try {
			insertQuery = CommonUtils.getReturnsProperties(query);
			insertQuery = insertQuery.replace(Constant.AT_PROJECTION_MASTER_SID,
					StringUtils.EMPTY + inputDto.getProjectionId());

			SalesProjectionDAO insertDAO = new SalesProjectionDAOImpl();
			insertDAO.executeUpdateQuery(QueryUtil.replaceTableNames(insertQuery, inputDto.getCurrentTableNames()));
		} catch (PortalException | SystemException e) {
			LOGGER.error("Query:======================>= {}" , insertQuery);
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Method checks for the actuals for the Trading Partner / Customer used in
	 * the projection.
	 *
	 * @param projectionId
	 * @return true - if at least one trading partners / customer does not have
	 *         actuals. false - if all the trading partners / customer have
	 *         actuals.
	 * @throws PortalException
	 * @throws Exception
	 */
	public boolean checkForZeroActuals(final SessionDTO session) throws PortalException, SystemException {

		String query = SQlUtil.getQuery("checktpcustomeractual");
		List<Object> list = (List<Object>) salesProjectionDAO
				.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		return list != null && !list.isEmpty() && (Integer) list.get(0) > 0;

	}

	/**
	 * Method removes the Trading Partner / Customer that have no actuals.
	 *
	 * @param projectionId
	 * @throws PortalException
	 * @throws Exception
	 */
	public void removeTPOrCustomerFromProjection(final SessionDTO session) throws PortalException, SystemException {

		String query = SQlUtil.getQuery("remove-tp-customer-with-no-actuals");
		query = query.replace("@PROJECTION_ID", String.valueOf(session.getProjectionId()));
		salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

	}

	public void deleteTempData(final SessionDTO inputDto, final String queryName) {
		String insertQuery = StringUtils.EMPTY;
		try {

			insertQuery = CommonUtils.getReturnsProperties(queryName);
			insertQuery = insertQuery.replace(Constant.AT_PROJECTION_MASTER_SID,
					StringUtils.EMPTY + inputDto.getProjectionId());
			SalesProjectionDAO deleteDAO = new SalesProjectionDAOImpl();
			deleteDAO.executeUpdateQuery(QueryUtil.replaceTableNames(insertQuery, inputDto.getCurrentTableNames()));
		} catch (PortalException | SystemException e) {
			LOGGER.error(e.getMessage());
		}

	}

	public static List getARPView(String viewValue, String forecastType, String userId, String viewType) {

		List<Object> list;
		String viewQueryARPP = "Select FVM.VIEW_NAME viewName, PM.PROJECTION_DESCRIPTION description, PM.PROJECTION_NAME projectionName, PM.BRAND_TYPE brandType, PM.MODIFIED_DATE modifiedDate, PM.CREATED_BY createdBy,"
				+ "                        PM.CREATED_DATE createdDate, PM.PRODUCT_HIERARCHY_LEVEL productLevel, PM.CUSTOMER_HIERARCHY_LEVEL customerLevel, CUST_HD.HIERARCHY_NAME customerHierarchy, PROD_HD.HIERARCHY_NAME productHierarchy,"
				+ "                        CM.COMPANY_NAME company, CMG.COMPANY_GROUP_NAME customerGroup, IG.ITEM_GROUP_NAME productGroup, PM.PROJECTION_MASTER_SID projectionId, FVM.VIEW_ID viewId, PM.CUSTOMER_HIERARCHY_SID customerHierarchySid,"
				+ "                        PM.PRODUCT_HIERARCHY_SID productHierarchySid, PM.COMPANY_GROUP_SID companyGroupSid, PM.ITEM_GROUP_SID productGroupSid, PM.COMPANY_MASTER_SID companyMasterSid, PM.FROM_DATE fromDate, PM.TO_DATE toDate, "
				+ "                        PM.CUSTOMER_HIERARCHY_INNER_LEVEL customerInnerLevel,PM.PRODUCT_HIERARCHY_INNER_LEVEL productInnerLevel, PM.CUST_RELATIONSHIP_BUILDER_SID custRelationshipBuilderSid, "
				+ "     PM.PROD_RELATIONSHIP_BUILDER_SID prodRelationshipBuilderSid  "
				+ ",APS.FIELD_NAME,APS.FIELD_VALUES,HT.DESCRIPTION AS DEDUCTION_VALUE ,PM.BUSINESS_UNIT AS BUSINESSUNIT, CM1.COMPANY_NAME AS  BUSINESSUNITNAME "
				+ "                        from PROJECTION_MASTER PM " + "                        "
				+ "                        LEFT JOIN FORECASTING_VIEW_MASTER FVM "
				+ "                        on FVM.projection_Id  = PM.projection_Master_Sid " + ""
				+ "                        LEFT JOIN HIERARCHY_DEFINITION CUST_HD "
				+ "                        on CUST_HD.HIERARCHY_DEFINITION_SID =PM.CUSTOMER_HIERARCHY_SID " + ""
				+ "                        LEFT JOIN HIERARCHY_DEFINITION PROD_HD "
				+ "                        on PROD_HD.HIERARCHY_DEFINITION_SID = PM.PRODUCT_HIERARCHY_SID " + ""
				+ "                        LEFT JOIN COMPANY_GROUP CMG "
				+ "                        on CMG.COMPANY_GROUP_SID  = PM.COMPANY_GROUP_SID" + ""
				+ "                        LEFT JOIN ITEM_GROUP IG "
				+ "                        on IG.ITEM_GROUP_SID  = PM.ITEM_GROUP_SID" + ""
				+ "                        LEFT JOIN COMPANY_MASTER CM "
				+ "                        on PM.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID "
				+ "                        LEFT JOIN COMPANY_MASTER CM1 "
				+ "                        on PM.BUSINESS_UNIT = CM1.COMPANY_MASTER_SID "
				+ "                         LEFT JOIN  ACCRUAL_PROJ_SELECTION APS "
				+ "                        on APS.projection_Master_Sid= PM.projection_Master_Sid   "
				+ "                        LEFT JOIN HELPER_TABLE HT on HT.HELPER_TABLE_SID = APS.FIELD_VALUES"
				+ "                       WHERE " + "FVM.view_Type ='@VIEW_TYPE' "
				+ "AND FVM.view_Name LIKE '@VIEW_NAME' " + " AND PM.forecasting_Type = '@FORECAST_TYPE'  "
				+ " AND FVM.created_By =@CREATED_BY ";
		viewQueryARPP = viewQueryARPP.replace("@VIEW_TYPE", viewType);
		viewQueryARPP = viewQueryARPP.replace("@VIEW_NAME", viewValue);
		viewQueryARPP = viewQueryARPP.replace("@FORECAST_TYPE", forecastType);
		viewQueryARPP = viewQueryARPP.replace("@CREATED_BY", userId);
		list = (List) HelperTableLocalServiceUtil.executeSelectQuery(viewQueryARPP);
		return list;
	}

	public String getWorkflowStatus(int projectionId, final String screenName) {
		DynamicQuery projectionDynamicQuery = ProjectionMasterLocalServiceUtil.dynamicQuery();
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.FORECASTING_TYPE,
					CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED));
		} else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			projectionDynamicQuery.add(
					RestrictionsFactoryUtil.eq(Constant.FORECASTING_TYPE, CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED));
		} else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
			projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.FORECASTING_TYPE,
					CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION));
		}
		projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		String workflowStatus = StringUtils.EMPTY;
		List<ProjectionMaster> resultList;

		try {
			resultList = dataSelection.getProjectionMaster(projectionDynamicQuery);

			for (ProjectionMaster pm : resultList) {
				workflowStatus = pm.getIsApproved();
			}
		} catch (PortalException | SystemException ex) {
			LOGGER.error(ex.getMessage());
		}
		return workflowStatus;
	}

	/**
	 * Used to insert or update the data from temp to main tables(CALLED IN SAVE
	 * AND UPDATE). Screen wise all the insert done parallely. sales_master will
	 * be in one thread, sales_proj will be in one thread and sales_actual in
	 * one thread etc
	 * 
	 * @param session
	 * @param service
	 * @param saveFutureList
	 * @param discountListView
	 */
	public void saveTempToMain(SessionDTO session, ExecutorService service, List<Future> saveFutureList,
			Future discountListView) {
		// SALES MASTER TABLE INSERT
		String query = SQlUtil.getQuery("Sales_Temp_Main_MASTER").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES PROJECTION TABLE INSERT
		query = SQlUtil.getQuery("Sales_Temp_Main_PROJ").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES ACTUAL INSERT
		query = SQlUtil.getQuery("Sales_Temp_Main_ACTUAL").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// PPA MASTER INSERT
		query = SQlUtil.getQuery("PPA_Temp_Main_MASTER").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// PPA PROJ INSERT
		query = SQlUtil.getQuery("PPA_Temp_Main_PROJ").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// PPA ACTUAL INSERT
		query = SQlUtil.getQuery("PPA_Temp_Main_ACTUAL").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// DISCOUNT MASTER INSERT
		query = SQlUtil.getQuery("Discount_Temp_Main_MASTER").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()), discountListView)));
		// DISCOUNT PROJ INSERT
		query = SQlUtil.getQuery("Discount_Temp_Main_PROJ").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()), discountListView)));
		// DISCOUNT ACTUAL INSERT
		query = SQlUtil.getQuery("Discount_Temp_Main_ACTUAL").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()), discountListView)));
	}

	/**
	 * Used to insert the data from main to temp tables(CALLED IN EDIT MODE).
	 * table wise all the insert done parallely. sales_master will be in one
	 * thread, sales_proj will be in one thread and sales_actual in one thread
	 * etc
	 * 
	 * @param session
	 * @param service
	 * @param futureList
	 */
	public void mainToTempTableInsert(SessionDTO session, ExecutorService service) {
		List<Future> tempInsertFutureList = new ArrayList<>();
		// SALES MASTER TABLE INSERT
		String query = SQlUtil.getQuery("Sales_Main_Temp_Master_Insert").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		tempInsertFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES PROJECTION INSERT
		query = SQlUtil.getQuery("Sales_Main_Temp_Proj_Insert").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		tempInsertFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES ACTUAL INSERT
		query = SQlUtil.getQuery("Sales_Main_Temp_Actual_Insert").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		tempInsertFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));

		for (Future futureObject : tempInsertFutureList) {
			CommonUtil.getInstance().waitsForOtherThreadsToComplete(futureObject);
		}

		session.addFutureMap(Constant.DISCOUNT_LOWER_CASE,
				new Future[] {
						// DISCOUNT MASTER INSERT
						service.submit(
								CommonUtil.getInstance()
										.createRunnable(
												Constant.INSERTORUPDATE, QueryUtil.replaceTableNames(
														SQlUtil.getQuery("Discount_Main_Temp_Master_Insert").replace(
																Constant.AT_PROJECTION_MASTER_SID,
																String.valueOf(session.getProjectionId())),
														session.getCurrentTableNames()))),
						// DISCOUNT PROJ INSERT
						service.submit(
								CommonUtil.getInstance()
										.createRunnable(
												Constant.INSERTORUPDATE, QueryUtil.replaceTableNames(
														SQlUtil.getQuery("Discount_Main_Temp_Proj_Insert").replace(
																Constant.AT_PROJECTION_MASTER_SID,
																String.valueOf(session.getProjectionId())),
														session.getCurrentTableNames()))),
						// DISCOUNT ACTUAL INSERT
						service.submit(
								CommonUtil.getInstance()
										.createRunnable(
												Constant.INSERTORUPDATE, QueryUtil.replaceTableNames(
														SQlUtil.getQuery("Discount_Main_Temp_Actual_Insert").replace(
																Constant.AT_PROJECTION_MASTER_SID,
																String.valueOf(session.getProjectionId())),
														session.getCurrentTableNames()))) });

		session.addFutureMap(Constant.PPA_SMALL,
				new Future[] {
						// PPA MASTER INSERT
						service.submit(
								CommonUtil.getInstance()
										.createRunnable(Constant.INSERTORUPDATE,
												QueryUtil
														.replaceTableNames(
																SQlUtil.getQuery("PPA_Main_Temp_Master_Insert").replace(
																		Constant.AT_PROJECTION_MASTER_SID,
																		String.valueOf(session.getProjectionId())),
																session.getCurrentTableNames()))),
						// PPA PROJ INSERT
						service.submit(
								CommonUtil.getInstance()
										.createRunnable(
												Constant.INSERTORUPDATE, QueryUtil.replaceTableNames(
														SQlUtil.getQuery("PPA_Main_Temp_Proj_Insert").replace(
																Constant.AT_PROJECTION_MASTER_SID,
																String.valueOf(session.getProjectionId())),
														session.getCurrentTableNames()))),
						// PPA ACTUAL INSERT
						service.submit(
								CommonUtil.getInstance()
										.createRunnable(
												Constant.INSERTORUPDATE, QueryUtil.replaceTableNames(
														SQlUtil.getQuery("PPA_Main_Temp_Actual_Insert").replace(
																Constant.AT_PROJECTION_MASTER_SID,
																String.valueOf(session.getProjectionId())),
														session.getCurrentTableNames()))) });
	}

	/**
	 * Used to insert the data from main to temp tables(CALLED IN EDIT MODE).
	 * table wise all the insert done parallely. sales_master will be in one
	 * thread, sales_proj will be in one thread and sales_actual in one thread
	 * etc
	 * 
	 * @param session
	 * @param service
	 * @param futureList
	 */
	public void mainToTempTableInsertForMandated(SessionDTO session, ExecutorService service) {
		List<Future> tempInsertFutureList = new ArrayList<>();

		// SALES MASTER TABLE INSERT
		String query = SQlUtil.getQuery("Master_Sales_Main_Temp_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		tempInsertFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES PROJECTION INSERT
		query = SQlUtil.getQuery("Proj_Sales_Main_Temp_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		tempInsertFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES ACTUAL INSERT
		query = SQlUtil.getQuery("Actuals_Sales_Main_Temp_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		tempInsertFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));

		for (Future futureObject : tempInsertFutureList) {
			CommonUtil.getInstance().waitsForOtherThreadsToComplete(futureObject);
		}
		session.addFutureMap(Constant.SUPPLEMENTAL, new Future[] {
				// SUPPLEMENTAL MASTER INSERT
				service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
						QueryUtil.replaceTableNames(
								SQlUtil.getQuery("Supp_Discount_Master_Main_Temp_Mandated").replace(
										Constant.AT_PROJECTION_MASTER_SID, String.valueOf(session.getProjectionId())),
								session.getCurrentTableNames()))),
				// SUPPLEMENTAL PROJ INSERT
				service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
						QueryUtil.replaceTableNames(
								SQlUtil.getQuery("Supp_Discount_Proj_Main_Temp_Mandated").replace(
										Constant.AT_PROJECTION_MASTER_SID, String.valueOf(session.getProjectionId())),
								session.getCurrentTableNames()))),
				// SUPPLEMENTAL ACTUAL INSERT
				service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
						QueryUtil.replaceTableNames(
								SQlUtil.getQuery("Supp_Discount_Actuals_Main_Temp_Mandated").replace(
										Constant.AT_PROJECTION_MASTER_SID, String.valueOf(session.getProjectionId())),
								session.getCurrentTableNames()))) });
	}

	/**
	 * Used to insert or update the data from temp to main tables(CALLED IN SAVE
	 * AND UPDATE). Screen wise all the insert done parallely. sales_master will
	 * be in one thread, sales_proj will be in one thread and sales_actual in
	 * one thread etc
	 * 
	 * @param session
	 * @param service
	 * @param saveFutureList
	 * @param discountListView
	 */
	public void saveTempToMainForMandated(SessionDTO session, ExecutorService service, List<Future> saveFutureList) {

		// SALES MASTER TABLE INSERT
		String query = SQlUtil.getQuery("Master_Sales_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES PROJECTION TABLE INSERT
		query = SQlUtil.getQuery("Proj_Sales_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// SALES ACTUAL INSERT
		query = SQlUtil.getQuery("Actuals_Sales_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// DISCOUNT PROJ INSERT
		query = SQlUtil.getQuery("Discount_Proj_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
		// DISCOUNT ACTUAL INSERT
		query = SQlUtil.getQuery("Discount_Actual_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));

		query = SQlUtil.getQuery("Supp_Discount_Master_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));

		query = SQlUtil.getQuery("Supp_Discount_Proj_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));

		query = SQlUtil.getQuery("Supp_Discount_Actuals_Temp_Main_Mandated").replace(Constant.AT_PROJECTION_MASTER_SID,
				String.valueOf(session.getProjectionId()));
		saveFutureList.add(service.submit(CommonUtil.getInstance().createRunnable(Constant.INSERTORUPDATE,
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()))));
	}

	public void loadPFDFromMainToTemp(SessionDTO session) {
		// PROJECTION_FILES_DETAILS
		String query = SQlUtil.getQuery("PFD_MAIN_TO_TEMP").replace("@PROJECTIONMASTERSID",
				String.valueOf(session.getProjectionId()));
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
	}
}
