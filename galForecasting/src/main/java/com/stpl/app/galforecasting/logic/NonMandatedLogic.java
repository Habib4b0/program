/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.DataSelectionDAO;
import com.stpl.app.galforecasting.dao.PPAProjectionDao;
import com.stpl.app.galforecasting.dao.ProjectionVarianceDAO;
import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.galforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.galforecasting.dao.impl.ProjectionVarianceDAOImpl;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.discountProjection.logic.SupplementalDiscountProjectionLogic;
import com.stpl.app.galforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.galforecasting.dto.CustomerProductDTO;
import com.stpl.app.galforecasting.dto.PMPYCalculatorDTO;
import com.stpl.app.galforecasting.dto.PMPYTradingPartnerDTO;
import com.stpl.app.galforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.AlternateLookupSource;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.galforecasting.utils.Converters;
import com.stpl.app.galforecasting.utils.DataSelectionUtil;
import com.stpl.app.galforecasting.utils.DataSourceConnection;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.galforecasting.workflow.logic.WorkflowLogic;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;
import com.stpl.app.utils.Constants.CommonConstants;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.dto.ViewDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
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
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class NonMandatedLogic.
 *
 * @author lokeshwari
 */
public class NonMandatedLogic {

    /**
     * The SALES_SMALL projection dao.
     */
    private SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

    /**
     * The p pa projection dao.
     */
    private PPAProjectionDao pPAProjectionDao = new PPAProjectionDaoImpl();

    /**
     * The projection variance dao.
     */
    private ProjectionVarianceDAO projectionVarianceDAO = new ProjectionVarianceDAOImpl();
    /**
     * The data selection.
     */
    public static DataSelectionDAO dataSelection = new DataSelectionDAOImpl();
    /**
     * The formatter.
     */
    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy_HH:mm:ss");

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(NonMandatedLogic.class);

    /**
     * the SALES_SMALL dao.
     */
    SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();

    /**
     * Searh view.
     *
     * @param viewName the view name
     * @param forecastType the forecast type
     * @param viewType the view type
     * @return the list< view dto>
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static List<ViewDTO> searhView(final String viewName, final String forecastType, final String viewType)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Entering searchView method");
        List list = null;
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        String viewValue = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(viewName)) {
            viewValue = viewName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        list = dataSelection.findViewByName(viewValue, forecastType, userId, viewType);
        LOGGER.info("End of searchView method");
        return Converters.getCustomizedViews(list, false);
    }

    public static List<ViewDTO> searhViewARP(final String viewName, final String forecastType, final String viewType)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Entering searchView method");
        List list = null;
        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        String viewValue = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(viewName)) {
            viewValue = viewName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        list = getARPView(viewValue, forecastType, userId, viewType);
        LOGGER.info("End of searchView method");
        return Converters.getCustomizedViews(list, true);
    }

    /**
     * Delete view.
     *
     * @param viewId the view id
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static String deleteView(final int viewId) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering deleteView method with viewId " + viewId);
        final ForecastingViewMaster fvm = dataSelection.deleteForecastingViewMaster(viewId);
        LOGGER.info("End of deleteView method");
        return fvm.getViewName();
    }

    /**
     * Update projection.
     *
     * @param dataSelectionDTO the data selection dto
     * @param projectionId
     * @param markAsSaved
     * @param screenName
     * @return the string
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int updateProjection(final DataSelectionDTO dataSelectionDTO, int projectionId, final boolean markAsSaved, final String screenName) throws PortalException, SystemException, Exception {

        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        LOGGER.info("Entering updateProjection ");
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = dataSelection.getProjectionMaster(projectionId);
        }
        projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
        projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
        projectionMaster.setForecastingType(screenName);
        projectionMaster.setModifiedBy(Integer.parseInt(userId));
        projectionMaster.setModifiedDate(new Date());
        projectionMaster.setBrandType(true);
        projectionMaster.setCustomerHierarchySid(dataSelectionDTO.getCustomerHierSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerHierSid()));
        projectionMaster.setProductHierarchySid(dataSelectionDTO.getProdHierSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getProdHierSid()));
        projectionMaster.setCustomerHierarchyLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyLevel()));
        projectionMaster.setProductHierarchyLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyLevel()));

        projectionMaster.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
        projectionMaster.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));

        projectionMaster.setCustomerHierVersionNo(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyVer()));
        projectionMaster.setProductHierVersionNo(Integer.parseInt(dataSelectionDTO.getProductHierarchyVer()));
        projectionMaster.setCompanyGroupSid(dataSelectionDTO.getCustomerGrpSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
        projectionMaster.setItemGroupSid(dataSelectionDTO.getProdGrpSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getProdGrpSid()));
        projectionMaster.setCompanyMasterSid(dataSelectionDTO.getCompanySid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCompanySid()));
        projectionMaster.setFromDate(dataSelectionDTO.getFromDate());
        if (projectionMaster.getProjectionMasterSid() == 0) {
            projectionMaster.setToDate(dataSelectionDTO.getToDate());
        }
        projectionMaster.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
        projectionMaster.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        if (markAsSaved) {
            projectionMaster.setSaveFlag(true);
        }

        projectionMaster = dataSelection.updateProjectionMaster(projectionMaster);
        return projectionMaster.getProjectionMasterSid();
    }

    /**
     * Update customers and contracts.
     *
     * @param projectionId the projection id
     * @param selectedCustomersList the selected customers
     * @return the string
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private String updateCustomersHierarchy(final int projectionId, final List<Leveldto> selectedCustomersList) throws SystemException, Exception {
        List<ProjectionCustDetails> details;

        LOGGER.info("Entering updateCustomersHierarchy  ::::");
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_ID, projectionId));
        details = dataSelection.findCustDetailsByProjectionId(dynamicQuery);
        for (final ProjectionCustDetails cust : details) {
            dataSelection.deleteProjectionCustDetails(cust);
        }
        for (int i = 0; i < selectedCustomersList.size(); i++) {
            final Leveldto projCC = selectedCustomersList.get(i);
            final ProjectionCustDetails projectionCC = ProjectionCustDetailsLocalServiceUtil.createProjectionCustDetails(0);
            projectionCC.setProjectionId(projectionId);

            dataSelection.addProjectionCustDetails(projectionCC);
        }

        LOGGER.info("Ending updateCustomersAndContracts return  status ::::" + Constant.SUCCESS);
        return Constant.SUCCESS;
    }

    /**
     * Update products.
     *
     * @param projectionId the projection id
     * @param selectedProducts the selected products
     * @return the string
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    private String updateProducts(final int projectionId, final List<CustomerProductDTO> selectedProducts) throws SystemException, PortalException, Exception {

        LOGGER.info("Entering updateProducts  ::::");

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_ID, projectionId));
        for (int i = 0; i < selectedProducts.size(); i++) {
            final CustomerProductDTO projCC = selectedProducts.get(i);
            List<ProjectionProdDetails> details;
            details = dataSelection.findProdDetailsByProjectionId(dynamicQuery);
            for (int j = 0; j < details.size(); j++) {
                final ProjectionProdDetails cust = details.get(j);
                dataSelection.deleteProjectionProdDetailsById(cust.getProductDetailsId());
            }

            final ProjectionProdDetails projectionprod = ProjectionProdDetailsLocalServiceUtil.createProjectionProdDetails(0);
            projectionprod.setProjectionId(projectionId);
            projectionprod.setProductNo(projCC.getProductNo());
            projectionprod.setProductName(projCC.getProductName());
            projectionprod.setBrandName(projCC.getBrandId());
            dataSelection.addProjectionProdDetails(projectionprod);
        }

        LOGGER.info("Ending updateProducts return  projectionId ::::" + projectionId);
        return String.valueOf(projectionId);
    }

    /**
     * Search alternate customer and brand.
     *
     * @param searchBinder the search binder
     * @param searchType the search type
     * @return the alternate lookup source
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public AlternateLookupSource searchAlternateCustomerAndBrand(final CustomFieldGroup searchBinder, final String searchType, boolean flag) throws SystemException, Exception {

        List<CompanyMaster> resultTPList = new ArrayList<CompanyMaster>();
        List<BrandMaster> resultBrandList = new ArrayList<BrandMaster>();
        final AlternateLookupSource alternate = new AlternateLookupSource();

        LOGGER.info("Entering searchAlternateBrand  ::::");

        if (Constant.TP.equalsIgnoreCase(searchType)) {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            if (!flag) {
                if (String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()) != null
                        && !StringUtils.EMPTY.equals(String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()))
                        && !CommonUtils.STRING_NULL.equals(String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()))) {

                    String contractHolderName = String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue());
                    contractHolderName = contractHolderName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                    dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, contractHolderName));

                }

                if (String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()) != null && !StringUtils.EMPTY.equals(String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()))
                        && !CommonUtils.STRING_NULL.equals(String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()))) {
                    String customerId = String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue());

                    customerId = customerId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                    dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NO, customerId));
                }
            } else {
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, Constant.PERCENT));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NO, Constant.PERCENT));

            }
            dynamicQuery.add(PropertyFactoryUtil.forName(Constant.COMPANYMASTERSID).in(
                    DynamicQueryFactoryUtil.forClass(ContractMaster.class).setProjection(ProjectionFactoryUtil.property("contHoldCompanyMasterSid"))));
            resultTPList = dataSelection.getCompanyMasterList(dynamicQuery);
            LOGGER.info("Size of resultTPList " + resultTPList.size());

            List<AlternateHistoryDTO> temp = new ArrayList<AlternateHistoryDTO>();
            temp = getAlternateTP(resultTPList);
            alternate.setCustomersList(temp);
        }

        if (CommonUtils.BRAND.equalsIgnoreCase(searchType)) {
            final DynamicQuery brandDynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);

            String brandName = (String.valueOf(searchBinder.getField(Constant.BRAND_SEARCH).getValue()) == null
                    || CommonUtils.STRING_NULL.equals(String.valueOf(searchBinder.getField(Constant.BRAND_SEARCH).getValue())) ? StringUtils.EMPTY : String.valueOf(searchBinder.getField(
                                            Constant.BRAND_SEARCH).getValue()));
            brandName = brandName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

            brandDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.BRAND_NAME, brandName));

            resultBrandList = dataSelection.getBrandMasterList(brandDynamicQuery);

            LOGGER.info("Size of resultBrandList " + resultBrandList.size());

            List<AlternateHistoryDTO> brand = new ArrayList<AlternateHistoryDTO>();
            brand = getAlternateBrand(resultBrandList);
            alternate.setBrandList(brand);
        }

        LOGGER.info("Ending searchAlternateBrand    ::::");
        return alternate;
    }

    /**
     * Gets the alternate tp.
     *
     * @param list the list
     * @return the alternate tp
     */
    public List<AlternateHistoryDTO> getAlternateTP(final List<CompanyMaster> list) {
        final List<AlternateHistoryDTO> resultList = new ArrayList<AlternateHistoryDTO>();

        LOGGER.info("Entering getAlternateTP  ::::");

        final int index = list.size();

        for (int i = 0; i < index; i++) {
            final CompanyMaster comapny = list.get(i);
            final AlternateHistoryDTO alternateTP = new AlternateHistoryDTO();
            alternateTP.setCustomerId(comapny.getCompanyNo());
            alternateTP.setContractHolder(comapny.getCompanyName());
            alternateTP.setContractSid(comapny.getCompanyMasterSid());
            resultList.add(alternateTP);
        }

        LOGGER.info("Ending getAlternateTP return  size ::::" + resultList.size());
        return resultList;
    }

    /**
     * Gets the alternate brand.
     *
     * @param list the list
     * @return the alternate brand
     */
    public List<AlternateHistoryDTO> getAlternateBrand(final List<BrandMaster> list) {
        final List<AlternateHistoryDTO> resultList = new ArrayList<AlternateHistoryDTO>();

        LOGGER.info("Entering getAlternateBrand  ::::");
        final int index = list.size();
        for (int i = 0; i < index; i++) {
            final BrandMaster brand = list.get(i);
            final AlternateHistoryDTO alternateBrand = new AlternateHistoryDTO();
            alternateBrand.setBrandSearch(brand.getBrandName());
            alternateBrand.setBrandSid(brand.getBrandMasterSid());
            resultList.add(alternateBrand);
        }

        LOGGER.info("Ending getAlternateBrand return  size ::::" + resultList.size());

        return resultList;
    }

    /**
     * Trading partner look up.
     *
     * @param searchBinder the search binder
     * @return the list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<PMPYTradingPartnerDTO> tradingPartnerLookUp(String tpNo, String tpName, Object contractHolder) throws SystemException, Exception {
        LOGGER.info("Entering tradingPartnerLookUp  ::::");
        List input = new ArrayList<String>();
        String queryName = StringUtils.EMPTY;
        boolean isContractHolder = Boolean.FALSE;
        if (contractHolder != null) {
            isContractHolder = Boolean.TRUE;
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
        List<PMPYTradingPartnerDTO> tradingpartner = new ArrayList<PMPYTradingPartnerDTO>();
        tradingpartner = getTPLookedUp(resultTPList);
        LOGGER.info("Ending tradingPartnerLookUp    ::::");
        return tradingpartner;
    }

    /**
     * Gets the TP looked up.
     *
     * @param list the list
     * @return the TP looked up
     */
    public List<PMPYTradingPartnerDTO> getTPLookedUp(final List<Object[]> list) {
        final List<PMPYTradingPartnerDTO> resultList = new ArrayList<PMPYTradingPartnerDTO>();

        LOGGER.info("Entering getTPLookedUp  ::::");
        final int index = list.size();
        for (int i = 0; i < index; i++) {
            Object[] obj = list.get(i);
            final PMPYTradingPartnerDTO lookedUpTP = new PMPYTradingPartnerDTO();
            lookedUpTP.setTradingPartnerNo(String.valueOf(obj[0]));
            lookedUpTP.setTradingPartnerName(String.valueOf(obj[1]));
            lookedUpTP.setCompanySysId(Integer.valueOf(String.valueOf(obj[2])));
            resultList.add(lookedUpTP);
        }

        LOGGER.info("Ending getTPLookedUp return  size ::::" + resultList.size());

        return resultList;
    }

    /**
     * Pmpy generate logic.
     *
     * @param contract the contract
     * @param tpName the tp name
     * @return the pmpy calculator dto
     * @throws SystemException the system exception
     * @throws PortalException ,Exception
     * @throws Exception the exception
     */
    public PMPYCalculatorDTO pmpyGenerateLogic(final String contract, final String tpName) throws SystemException, PortalException, Exception {
        final PMPYCalculatorDTO bean = new PMPYCalculatorDTO();
        String contract1;
        String tpName1;

        if (contract == null || CommonUtils.STRING_NULL.equals(contract)) {
            contract1 = StringUtils.EMPTY;
        } else {
            contract1 = contract;
        }

        if (tpName == null || CommonUtils.STRING_NULL.equals(tpName)) {
            tpName1 = StringUtils.EMPTY;
        } else {
            tpName1 = tpName;
        }

        LOGGER.info("Entering pmpyGenerateLogic  ::::");
        SalesProjectionLogic logic = new SalesProjectionLogic();
        final List chList = new ArrayList();

        final List<PMPYCalculatorDTO> chListFinal = getChList(chList);
        bean.setContrachHolderList(chListFinal);

        final List tpList = salesDAO.getPMPYTradingPartnerList(tpName1);

        final List<PMPYCalculatorDTO> tpListFinal = getTpList(tpList);

        bean.setTradingPartnerList(tpListFinal);

        LOGGER.info("Ending pmpyGenerateLogic    ::::");

        return bean;
    }

    /**
     * Load pmpy contract holders.
     *
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public ComboBox loadPMPYContractHolders(int projectionId, ComboBox contract, String contractHolder) throws SystemException, PortalException, Exception {

        LOGGER.info("Entering loadPmpyContractHolders  ::::");

        List list = PPAQuerys.getPPAData(new ArrayList(), "PMPY-Load CH", null);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                Object obj[] = (Object[]) list.get(i);

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
     * @param projectionId the projection id
     * @param salesOrUnit the SALES_SMALL or UNIT
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public void manualEntrysalesCalculation(final int projectionId, final String salesOrUnit) throws SystemException, Exception {
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;

        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.info("Entering manualEntrysalesCalculation  ::::");

            if (connection != null) {

                statement = connection.prepareCall("{call PRC_NM_SALES_PROJECTION_MANUAL(?,?)}");
                statement.setInt(1, projectionId);
                statement.setString(2, salesOrUnit);
                statement.execute();
            }

            LOGGER.info("Ending manualEntrysalesCalculation    ::::");

        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

    }

    /**
     * Gets the ch list.
     *
     * @param chList the ch list
     * @return the ch list
     * @throws Exception the exception
     */
    private List<PMPYCalculatorDTO> getChList(final List chList) throws Exception {
        final List lives = new ArrayList();
        final List sales = new ArrayList();
        final List units = new ArrayList();

        final List<PMPYCalculatorDTO> resultList = new ArrayList<PMPYCalculatorDTO>();

        LOGGER.info("Entering getChList  ::::");
        final int index = chList.size();

        for (int i = 0; i < index; i++) {
            final Object[] obj = (Object[]) chList.get(i);

            if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0])) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
                    && Constant.C_LIVES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

                lives.add(Constant.LIVES);
                lives.add(emptyChecker(obj[1]));
                lives.add(emptyChecker(obj[2]));
                lives.add(emptyChecker(obj[3]));
                lives.add(emptyChecker(obj[4]));
                lives.add(emptyChecker(obj[5]));
                lives.add(emptyChecker(obj[6]));
                lives.add(emptyChecker(obj[7]));
                lives.add(emptyChecker(obj[8]));
                lives.add(emptyChecker(obj[9]));
                lives.add(emptyChecker(obj[10]));
                lives.add(emptyChecker(obj[11]));
                lives.add(emptyChecker(obj[12]));
            }

            if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0])) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
                    && Constant.A_SALES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

                sales.add(Constant.ACTUAL_SALES);
                sales.add(emptyChecker(obj[1]));
                sales.add(emptyChecker(obj[2]));
                sales.add(emptyChecker(obj[3]));
                sales.add(emptyChecker(obj[4]));
                sales.add(emptyChecker(obj[5]));
                sales.add(emptyChecker(obj[6]));
                sales.add(emptyChecker(obj[7]));
                sales.add(emptyChecker(obj[8]));
                sales.add(emptyChecker(obj[9]));
                sales.add(emptyChecker(obj[10]));
                sales.add(emptyChecker(obj[11]));
                sales.add(emptyChecker(obj[12]));
            }
            if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0])) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
                    && Constant.B_UNITS.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

                units.add(Constant.UNIT_VOLUME);
                units.add(emptyChecker(obj[1]));
                units.add(emptyChecker(obj[2]));
                units.add(emptyChecker(obj[3]));
                units.add(emptyChecker(obj[4]));
                units.add(emptyChecker(obj[5]));
                units.add(emptyChecker(obj[6]));
                units.add(emptyChecker(obj[7]));
                units.add(emptyChecker(obj[8]));
                units.add(emptyChecker(obj[9]));
                units.add(emptyChecker(obj[10]));
                units.add(emptyChecker(obj[11]));
                units.add(emptyChecker(obj[12]));
            }
        }
        if (sales != null && !sales.isEmpty()) {

            for (int is = 0; is < 1; is++) { // Setting SALES_SMALL
                final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

                dto.setType(String.valueOf(sales.get(0)));
                dto.setQ1y1Ch(getSales(sales.get(1)));
                dto.setQ2y1Ch(getSales(sales.get(2)));
                dto.setQ3y1Ch(getSales(sales.get(3)));
                dto.setQ4y1Ch(getSales(sales.get(4)));
                dto.setQ1y2Ch(getSales(sales.get(5)));
                dto.setQ2y2Ch(getSales(sales.get(6)));
                dto.setQ3y2Ch(getSales(sales.get(7)));
                dto.setQ4y2Ch(getSales(sales.get(8)));
                dto.setQ1y3Ch(getSales(sales.get(9)));
                dto.setQ2y3Ch(getSales(sales.get(10)));
                dto.setQ3y3Ch(getSales(sales.get(11)));
                dto.setQ4y3Ch(getSales(sales.get(12)));

                // Yearly
                final Double yearOne = getSales(sales.get(1)) + getSales(sales.get(2)) + getSales(sales.get(3)) + getSales(sales.get(4));
                final Double yearTwo = getSales(sales.get(5)) + getSales(sales.get(6)) + getSales(sales.get(7)) + getSales(sales.get(8));
                final Double yearThree = getSales(sales.get(9)) + getSales(sales.get(10)) + getSales(sales.get(11)) + getSales(sales.get(12));

                dto.setY1Ch(yearThree);
                dto.setY2Ch(yearTwo);
                dto.setY3Ch(yearOne);

                // semi-annuals
                final Double s1Y1 = getSales(sales.get(1)) + getSales(sales.get(2));
                final Double s2Y1 = getSales(sales.get(3)) + getSales(sales.get(4));
                final Double s1Y2 = getSales(sales.get(5)) + getSales(sales.get(6));
                final Double s2Y2 = getSales(sales.get(7)) + getSales(sales.get(8));
                final Double s1y3 = getSales(sales.get(9)) + getSales(sales.get(10));
                final Double s2y3 = getSales(sales.get(11)) + getSales(sales.get(12));

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
                dto.setQ2y1Ch(getUnits(units.get(2)));
                dto.setQ3y1Ch(getUnits(units.get(3)));
                dto.setQ4y1Ch(getUnits(units.get(4)));
                dto.setQ1y2Ch(getUnits(units.get(5))); // q1 of current year - 2
                dto.setQ2y2Ch(getUnits(units.get(6)));
                dto.setQ3y2Ch(getUnits(units.get(7)));
                dto.setQ4y2Ch(getUnits(units.get(8)));
                dto.setQ1y3Ch(getUnits(units.get(9))); // q1 of current year - 1
                dto.setQ2y3Ch(getUnits(units.get(10)));
                dto.setQ3y3Ch(getUnits(units.get(11)));
                dto.setQ4y3Ch(getUnits(units.get(12)));

                // Yearly
                final Double yearOne = getUnits(units.get(1)) // current year -
                        // 3
                        + getUnits(units.get(2)) + getUnits(units.get(3)) + getUnits(units.get(4));
                final Double yearTwo = getUnits(units.get(5)) // current year -
                        // 2
                        + getUnits(units.get(6)) + getUnits(units.get(7)) + getUnits(units.get(8));
                final Double yearThree = getUnits(units.get(9)) // current year
                        // - 1
                        + getUnits(units.get(10)) + getUnits(units.get(11)) + getUnits(units.get(12));

                dto.setY1Ch(yearOne); // current year - 3
                dto.setY2Ch(yearTwo); // current year - 2
                dto.setY3Ch(yearThree); // current year - 1

                // semi-annuals
                final Double s1Y1 = getUnits(units.get(1)) // q1, q2 current
                        // year - 3
                        + getUnits(units.get(2));
                final Double s2Y1 = getUnits(units.get(3)) // q3, q4 current
                        // year - 3
                        + getUnits(units.get(4));
                final Double s1Y2 = getUnits(units.get(5)) // q1, q2 current
                        // year - 2
                        + getUnits(units.get(6));
                final Double s2Y2 = getUnits(units.get(7)) // q3, q4 current
                        // year - 2
                        + getUnits(units.get(8));
                final Double s1y3 = getUnits(units.get(9)) // q1, q2 current
                        // year - 1
                        + getUnits(units.get(10));
                final Double s2y3 = getUnits(units.get(11)) // q3, q4 current
                        // year - 1
                        + getUnits(units.get(12));

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
                dto.setQ2y1Ch(Double.valueOf(String.valueOf(lives.get(2))));
                dto.setQ3y1Ch(Double.valueOf(String.valueOf(lives.get(3))));
                dto.setQ4y1Ch(Double.valueOf(String.valueOf(lives.get(4))));
                dto.setQ1y2Ch(Double.valueOf(String.valueOf(lives.get(5))));
                dto.setQ2y2Ch(Double.valueOf(String.valueOf(lives.get(6))));
                dto.setQ3y2Ch(Double.valueOf(String.valueOf(lives.get(7))));
                dto.setQ4y2Ch(Double.valueOf(String.valueOf(lives.get(8))));
                dto.setQ1y3Ch(Double.valueOf(String.valueOf(lives.get(9))));
                dto.setQ2y3Ch(Double.valueOf(String.valueOf(lives.get(10))));
                dto.setQ3y3Ch(Double.valueOf(String.valueOf(lives.get(11))));
                dto.setQ4y3Ch(Double.valueOf(String.valueOf(lives.get(12))));

                dto.setY1Ch(Double.valueOf(String.valueOf(lives.get(1))));
                dto.setY2Ch(Double.valueOf(String.valueOf(lives.get(2))));
                dto.setY3Ch(Double.valueOf(String.valueOf(lives.get(3))));

                dto.setS1y1Ch(Double.valueOf(String.valueOf(lives.get(4))));
                dto.setS2y1Ch(Double.valueOf(String.valueOf(lives.get(5))));
                dto.setS1y2Ch(Double.valueOf(String.valueOf(lives.get(6))));
                dto.setS2y2Ch(Double.valueOf(String.valueOf(lives.get(7))));
                dto.setS1y3Ch(Double.valueOf(String.valueOf(lives.get(8))));
                dto.setS2y3Ch(Double.valueOf(String.valueOf(lives.get(9))));

                resultList.add(dto);
            }
        }

        LOGGER.info("Ending getChList return  size ::::" + resultList.size());
        return resultList;
    }

    /**
     * Gets the tp list.
     *
     * @param tpList the tp list
     * @return the tp list
     * @throws Exception the exception
     */
    private List<PMPYCalculatorDTO> getTpList(final List tpList) throws Exception {
        final List lives = new ArrayList();
        final List sales = new ArrayList();
        final List units = new ArrayList();

        final List<PMPYCalculatorDTO> resultList = new ArrayList<PMPYCalculatorDTO>();

        LOGGER.info("Entering getTpList  ::::");
        final int index = tpList.size();
        for (int i = 0; i < index; i++) {
            final Object[] obj = (Object[]) tpList.get(i);
            if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0])) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
                    && Constant.C_LIVES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

                lives.add(Constant.LIVES);
                lives.add(emptyChecker(obj[1]));
                lives.add(emptyChecker(obj[2]));
                lives.add(emptyChecker(obj[3]));
                lives.add(emptyChecker(obj[4]));
                lives.add(emptyChecker(obj[5]));
                lives.add(emptyChecker(obj[6]));
                lives.add(emptyChecker(obj[7]));
                lives.add(emptyChecker(obj[8]));
                lives.add(emptyChecker(obj[9]));
                lives.add(emptyChecker(obj[10]));
                lives.add(emptyChecker(obj[11]));
                lives.add(emptyChecker(obj[12]));
            }

            if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0])) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
                    && Constant.A_SALES.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

                sales.add(Constant.ACTUAL_SALES);
                sales.add(emptyChecker(obj[1]));
                sales.add(emptyChecker(obj[2]));
                sales.add(emptyChecker(obj[3]));
                sales.add(emptyChecker(obj[4]));
                sales.add(emptyChecker(obj[5]));
                sales.add(emptyChecker(obj[6]));
                sales.add(emptyChecker(obj[7]));
                sales.add(emptyChecker(obj[8]));
                sales.add(emptyChecker(obj[9]));
                sales.add(emptyChecker(obj[10]));
                sales.add(emptyChecker(obj[11]));
                sales.add(emptyChecker(obj[12]));
            }
            if (String.valueOf(obj[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(obj[0])) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj[0]))
                    && Constant.B_UNITS.equalsIgnoreCase(String.valueOf(obj[0]))) { // Filtering

                units.add(Constant.UNIT_VOLUME);
                units.add(emptyChecker(obj[1]));
                units.add(emptyChecker(obj[2]));
                units.add(emptyChecker(obj[3]));
                units.add(emptyChecker(obj[4]));
                units.add(emptyChecker(obj[5]));
                units.add(emptyChecker(obj[6]));
                units.add(emptyChecker(obj[7]));
                units.add(emptyChecker(obj[8]));
                units.add(emptyChecker(obj[9]));
                units.add(emptyChecker(obj[10]));
                units.add(emptyChecker(obj[11]));
                units.add(emptyChecker(obj[12]));
            }
        }
        if (sales != null && !sales.isEmpty()) {

            for (int is = 0; is < 1; is++) { // Setting SALES_SMALL
                final PMPYCalculatorDTO dto = new PMPYCalculatorDTO();

                dto.setType(String.valueOf(sales.get(0)));
                dto.setQ1y1Tp(getSales(sales.get(1)));
                dto.setQ2y1Tp(getSales(sales.get(2)));
                dto.setQ3y1Tp(getSales(sales.get(3)));
                dto.setQ4y1Tp(getSales(sales.get(4)));
                dto.setQ1y2Tp(getSales(sales.get(5)));
                dto.setQ2y2Tp(getSales(sales.get(6)));
                dto.setQ3y2Tp(getSales(sales.get(7)));
                dto.setQ4y2Tp(getSales(sales.get(8)));
                dto.setQ1y3Tp(getSales(sales.get(9)));
                dto.setQ2y3Tp(getSales(sales.get(10)));
                dto.setQ3y3Tp(getSales(sales.get(11)));
                dto.setQ4y3Tp(getSales(sales.get(12)));

                // Yearly
                final Double yearOne = getSales(sales.get(1)) + getSales(sales.get(2)) + getSales(sales.get(3)) + getSales(sales.get(4));
                final Double yearTwo = getSales(sales.get(5)) + getSales(sales.get(6)) + getSales(sales.get(7)) + getSales(sales.get(8));
                final Double yearThree = getSales(sales.get(9)) + getSales(sales.get(10)) + getSales(sales.get(11)) + getSales(sales.get(12));

                dto.setY1Tp(yearOne);
                dto.setY2Tp(yearTwo);
                dto.setY3Tp(yearThree);

                // semi-annuals
                final Double s1Y1 = getSales(sales.get(1)) + getSales(sales.get(2));
                final Double s2Y1 = getSales(sales.get(3)) + getSales(sales.get(4));
                final Double s1Y2 = getSales(sales.get(5)) + getSales(sales.get(6));
                final Double s2Y2 = getSales(sales.get(7)) + getSales(sales.get(8));
                final Double s1y3 = getSales(sales.get(9)) + getSales(sales.get(10));
                final Double s2y3 = getSales(sales.get(11)) + getSales(sales.get(12));

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
                dto.setQ2y1Tp(getUnits(units.get(2)));
                dto.setQ3y1Tp(getUnits(units.get(3)));
                dto.setQ4y1Tp(getUnits(units.get(4)));
                dto.setQ1y2Tp(getUnits(units.get(5)));
                dto.setQ2y2Tp(getUnits(units.get(6)));
                dto.setQ3y2Tp(getUnits(units.get(7)));
                dto.setQ4y2Tp(getUnits(units.get(8)));
                dto.setQ1y3Tp(getUnits(units.get(9)));
                dto.setQ2y3Tp(getUnits(units.get(10)));
                dto.setQ3y3Tp(getUnits(units.get(11)));
                dto.setQ4y3Tp(getUnits(units.get(12)));

                // Yearly
                final Double yearOne = getUnits(units.get(1)) + getUnits(units.get(2)) + getUnits(units.get(3)) + getUnits(units.get(4));
                final Double yearTwo = getUnits(units.get(5)) + getUnits(units.get(6)) + getUnits(units.get(7)) + getUnits(units.get(8));
                final Double yearThree = getUnits(units.get(9)) + getUnits(units.get(10)) + getUnits(units.get(11)) + getUnits(units.get(12));

                dto.setY1Tp(yearOne);
                dto.setY2Tp(yearTwo);
                dto.setY3Tp(yearThree);

                // semi-annuals
                final Double s1Y1 = getUnits(units.get(1)) + getUnits(units.get(2));
                final Double s2Y1 = getUnits(units.get(3)) + getUnits(units.get(4));
                final Double s1Y2 = getUnits(units.get(5)) + getUnits(units.get(6));
                final Double s2Y2 = getUnits(units.get(7)) + getUnits(units.get(8));
                final Double s1y3 = getUnits(units.get(9)) + getUnits(units.get(10));
                final Double s2y3 = getUnits(units.get(11)) + getUnits(units.get(12));

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
                dto.setQ2y1Tp(Double.valueOf(String.valueOf(lives.get(2))));
                dto.setQ3y1Tp(Double.valueOf(String.valueOf(lives.get(3))));
                dto.setQ4y1Tp(Double.valueOf(String.valueOf(lives.get(4))));
                dto.setQ1y2Tp(Double.valueOf(String.valueOf(lives.get(5))));
                dto.setQ2y2Tp(Double.valueOf(String.valueOf(lives.get(6))));
                dto.setQ3y2Tp(Double.valueOf(String.valueOf(lives.get(7))));
                dto.setQ4y2Tp(Double.valueOf(String.valueOf(lives.get(8))));
                dto.setQ1y3Tp(Double.valueOf(String.valueOf(lives.get(9))));
                dto.setQ2y3Tp(Double.valueOf(String.valueOf(lives.get(10))));
                dto.setQ3y3Tp(Double.valueOf(String.valueOf(lives.get(11))));
                dto.setQ4y3Tp(Double.valueOf(String.valueOf(lives.get(12))));

                dto.setY1Tp(Double.valueOf(String.valueOf(lives.get(1))));
                dto.setY2Tp(Double.valueOf(String.valueOf(lives.get(2))));
                dto.setY3Tp(Double.valueOf(String.valueOf(lives.get(3))));
                dto.setS1y1Tp(Double.valueOf(String.valueOf(lives.get(4))));
                dto.setS2y1Tp(Double.valueOf(String.valueOf(lives.get(5))));
                dto.setS1y2Tp(Double.valueOf(String.valueOf(lives.get(6))));
                dto.setS2y2Tp(Double.valueOf(String.valueOf(lives.get(7))));
                dto.setS1y3Tp(Double.valueOf(String.valueOf(lives.get(8))));
                dto.setS2y3Tp(Double.valueOf(String.valueOf(lives.get(9))));

                resultList.add(dto);

            }
        }

        LOGGER.info("Ending getTpList return  size ::::" + resultList.size());

        return resultList;
    }

    /**
     * Empty checker.
     *
     * @param obj the obj
     * @return the string
     */
    public String emptyChecker(final Object obj) {
        String value = "0.0";

        LOGGER.info("Entering emptyChecker  ::::");

        if (String.valueOf(obj) != null && !StringUtils.EMPTY.equals(String.valueOf(obj)) && !CommonUtils.STRING_NULL.equals(String.valueOf(obj))) {
            value = String.valueOf(obj);
        }

        LOGGER.info("Ending emptyChecker return  STRING ::::" + value);

        return value;
    }

    /**
     * Gets the SALES_SMALL.
     *
     * @param obj the obj
     * @return the SALES_SMALL
     */
    public Double getSales(final Object obj) {
        Double value = 0.0;
        final DecimalFormat format = new DecimalFormat("#");

        LOGGER.info("Entering getSales  ::::");

        value = Double.valueOf(String.valueOf(obj));
        format.format(value);

        LOGGER.info("Ending getSales return  DOUBLE ::::" + value);

        return value;
    }

    /**
     * Gets the UNITS_SMALL.
     *
     * @param obj the obj
     * @return the UNITS_SMALL
     */
    public Double getUnits(final Object obj) {
        Double value = 0.0;
        final DecimalFormat format = new DecimalFormat("#.0");

        LOGGER.info("Entering getUnits  ::::");

        value = Double.valueOf(String.valueOf(obj));
        format.format(value);

        LOGGER.info("Ending getUnits return  DOUBLE ::::" + value);

        return value;
    }

    /**
     * Generate projection variance.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the up to 5 projections for comparison
     * @param frequency the selected frequency
     * @param level the level
     * @param discountLevel the discountLevel
     * @return the list of projection data
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ProjectionVarianceDTO> generateProjectionVariance(final int projectionId, final String indicator,
            final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Generate Projection Variance");
        return null;
    }

    /**
     * Generate projection variance for Pivot.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the up to 5 projections for comparison
     * @param frequency the selected frequency
     * @param level the level
     * @param discountLevel the discountLevel
     * @return the list of projection data
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ProjectionVarianceDTO> generatePivotProjectionVariance(final int projectionId, final String indicator,
            final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Generate Pivot Projection Variance");

        return projectionVarianceDAO.generatePivotProjectionVariance(projectionId, indicator,
                comparisonProjections, frequency, level, discountLevel);
    }

    /**
     * Generate PPA Group for Projection variance.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the up to 5 projections for comparison
     * @param frequency the selected frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the list of projection data
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ProjectionVarianceDTO> getVariancePPAGroup(final int projectionId, final String indicator,
            final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Generate PPA Group for Projection Variance");

        return pPAProjectionDao.getContractHolderSummary(projectionId, indicator, comparisonProjections, frequency, level, discountLevel);
    }

    /**
     * Generate Sales Group for Projection variance.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the up to 5 projections for comparison
     * @param frequency the selected frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the list of projection data
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ProjectionVarianceDTO> getVarianceSalesGroup(final int projectionId, final String indicator,
            final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Generate PPA Group for Projection Variance");

        return salesProjectionDAO.getContractHolderSummary(projectionId, indicator, comparisonProjections, frequency, level, discountLevel);
    }

    /**
     * Generate Discount Group for Projection variance.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the up to 5 projections for comparison
     * @param frequency the selected frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the list of projection data
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<ProjectionVarianceDTO> getVarianceDiscountGroup(final int projectionId, final String indicator,
            final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
            throws SystemException, PortalException, Exception {
        LOGGER.info("Generate PPA Group for Projection Variance");

        return projectionVarianceDAO.getContractHolderSummary(projectionId, indicator, comparisonProjections, frequency, level, discountLevel);
    }

    /**
     * Search for projections in comparison lookup.
     *
     * @param workflowStatus the workflow status
     * @param marketType the market type
     * @param brand the brand
     * @param projName the proj name
     * @param contHldr the cont hldr
     * @param ndcNo the ndc no
     * @param ndcName the ndc name
     * @param desc the desc
     * @param contract the contract
     * @param from the from
     * @param to the to
     * @return the list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public List<DataSelectionDTO> searchForProjections(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to) throws SystemException, PortalException, Exception {

        return projectionVarianceDAO.searchForProjections(workflowStatus, marketType, brand, projName,
                contHldr, ndcNo, ndcName, desc, contract, from, to);
    }

    /**
     * Save or updates projection variance custom view hierarchies.
     *
     * @param indicator to indicate whether action is SAVE/UPDATE
     * @param projectionId the projection id
     */
    public void saveProjectionVarianceView(String indicator, int projectionId) {

        projectionVarianceDAO.saveProjectionVarianceView(indicator, projectionId);

    }

    /**
     * Gets the hierarchy data.
     *
     * @param indicator the indicator whether Customer hierarchy or Product
     * hierarchy
     * @param hierarchyName the hierarchy name
     * @param hierarchyType the hierarchy type
     * @return the hierarchy data
     */
    public List<HierarchyLookupDTO> getHierarchyData(final String indicator,
            final String hierarchyName, final String hierarchyType) {
        return dataSelection.getHierarchyData(indicator, hierarchyName, hierarchyType);
    }

    /**
     * Gets the customer group.
     *
     * @param customerName the customer group name search criteria
     * @param customerNo the customer group no search criteria
     * @param companySids
     * @return the customer group result list
     * @throws java.lang.Exception
     */
    public List<GroupDTO> getCustomerGroup(String customerName, String customerNo, List<String> companySids) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
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
     * @param productName the Product group name search criteria
     * @param productNo the Product group no search criteria
     * @return the Product group result list
     * @throws java.lang.Exception
     */
    public List<GroupDTO> getProductGroup(String productName, String productNo, List<String> itemSids) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
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
     * @param dataSelectionDTO the data selection dto
     * @return the projection ID
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public int saveProjection(final DataSelectionDTO dataSelectionDTO, String screenName) throws SystemException, Exception {
        String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        projectionMaster.setProjectionName(dataSelectionDTO.getProjectionName());
        projectionMaster.setProjectionDescription(dataSelectionDTO.getDescription());
        projectionMaster.setForecastingType(screenName);
        projectionMaster.setCreatedBy(UiUtils.parseStringToInteger(userId));
        projectionMaster.setCreatedDate(new Date());
        projectionMaster.setBrandType(true);
        projectionMaster.setCustomerHierarchySid(dataSelectionDTO.getCustomerHierSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerHierSid()));
        projectionMaster.setProductHierarchySid(dataSelectionDTO.getProdHierSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getProdHierSid()));
        projectionMaster.setCustomerHierarchyLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyLevel()));
        projectionMaster.setProductHierarchyLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyLevel()));
        projectionMaster.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
        projectionMaster.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));
        projectionMaster.setCustomerHierVersionNo(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyVer()));
        projectionMaster.setProductHierVersionNo(Integer.parseInt(dataSelectionDTO.getProductHierarchyVer()));
        projectionMaster.setCompanyGroupSid(dataSelectionDTO.getCustomerGrpSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCustomerGrpSid()));
        projectionMaster.setItemGroupSid(dataSelectionDTO.getProdGrpSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getProdGrpSid()));
        projectionMaster.setCompanyMasterSid(dataSelectionDTO.getCompanySid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCompanySid()));
        projectionMaster.setFromDate(dataSelectionDTO.getFromDate());   //Obtain from Admin Console
        projectionMaster.setToDate(dataSelectionDTO.getToDate());
        projectionMaster.setSaveFlag(false);
        projectionMaster.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getCustRelationshipBuilderSid()));
        projectionMaster.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid().equals(DASH) ? null : String.valueOf(dataSelectionDTO.getProdRelationshipBuilderSid()));
        projectionMaster.setDiscountType(dataSelectionDTO.getDiscountSid());
        projectionMaster.setForecastingType(screenName);
        projectionMaster = dataSelection.addProjectionMaster(projectionMaster);
        return projectionMaster.getProjectionMasterSid();

    }

    /**
     * Search for projections.
     *
     * @param dataSelectionDTO the dataSelectionDTO
     * @return the projection master result list
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public List<DataSelectionDTO> searchDSProjections(final DataSelectionDTO dataSelectionDTO) throws SystemException, ParseException, PortalException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);
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
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastingType", CommonUtils.NONMANDATED));

        if (isValidSid(dataSelectionDTO.getCustomerHierSid())) {
            String customerHierarchySid = dataSelectionDTO.getCustomerHierSid();
            customerHierarchySid = customerHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerHierarchySid", customerHierarchySid);
        }

        if (isValidSid(dataSelectionDTO.getProdHierSid())) {
            String prodHierarchySid = dataSelectionDTO.getProdHierSid();
            prodHierarchySid = prodHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("prodHierarchySid", prodHierarchySid);
        }

        if (isValidSid(dataSelectionDTO.getCustomerHierarchyLevel())) {
            String customerHierarchyLevel = dataSelectionDTO.getCustomerHierarchyLevel();
            customerHierarchyLevel = customerHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerHierarchyLevel", customerHierarchyLevel);
        }

        if (isValidSid(dataSelectionDTO.getProductHierarchyLevel())) {
            String productHierarchyLevel = dataSelectionDTO.getProductHierarchyLevel();
            productHierarchyLevel = productHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("productHierarchyLevel", productHierarchyLevel);
        }

        if (isValidSid(dataSelectionDTO.getCustomerGrpSid())) {
            String customerGrpSid = dataSelectionDTO.getCustomerGrpSid();
            customerGrpSid = customerGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerGroupSid", customerGrpSid);
        }

        if (isValidSid(dataSelectionDTO.getProdGrpSid())) {
            String prodGrpSid = dataSelectionDTO.getProdGrpSid();
            prodGrpSid = prodGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("prodGroupSid", prodGrpSid);
        }

        if (isValidSid(dataSelectionDTO.getCompanySid())) {
            String companySid = dataSelectionDTO.getCompanySid();
            companySid = companySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(Constant.COMPANYMASTERSID, companySid);
        }

        resultList = dataSelection.searchDSProjections(parameters);
        returnList = Converters.searchDsProjection(resultList, CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(dataSelectionDTO.getModulName()));
        return returnList;
    }

    private boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !CommonConstants.NULL.getConstant().equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
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
     * @param projectionId the projection id
     */
    public void saveNMProjection(int projectionId) {
        dataSelection.saveNMProjection(projectionId);
    }

    /**
     * Submit the projection.
     *
     * @param projectionId the projection id
     * @param userId
     * @param notes
     * @param noOfApprovals
     * @param screenName
     * @param getUploadedData
     * @param description
     * @return
     */
    public String submitProjection(int projectionId, String userId, String notes, int noOfApprovals, String screenName, List<NotesDTO> getUploadedData, String description) {
        LOGGER.info("Entering submitProjection method");
        DynamicQuery projectionDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ProjectionMaster.class);
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(
                    "forecastingType", CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED));
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(
                    "forecastingType", CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED));
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(
                    "forecastingType", CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION));
        }
        projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID,
                projectionId));
        String workflowStatus = StringUtils.EMPTY;
        List<ProjectionMaster> resultList;
        try {
            resultList = dataSelection.getProjectionMaster(projectionDynamicQuery);

            for (ProjectionMaster pm : resultList) {
                ProjectionMaster projMaster = pm;
                if (projMaster.getIsApproved() != null && (projMaster.getIsApproved().equals(Constant.R) || projMaster.getIsApproved().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY) || projMaster.getIsApproved().equals("W"))) {
                    workflowStatus = "RC";
                }

                projMaster.setIsApproved("Y");
                projMaster.setModifiedDate(new Date());
                dataSelection.updateProjectionMaster(projMaster);

            }

            WorkflowLogic workflowLogic = new WorkflowLogic();
            if (workflowStatus.equals("RC")) {
                LOGGER.info("Ending submitProjection ");
                return workflowLogic.updateWorkflowFromForecast(projectionId, notes, userId);
            } else {
                LOGGER.info("Ending submitProjection ");
                return workflowLogic.saveWorkflow(projectionId, userId, notes, noOfApprovals, screenName, getUploadedData, description);
            }

        } catch (Exception e) {
            LOGGER.error(new Date() + e.getMessage());
            return "Not Saved";
        }

    }

    /**
     * Gets the SALES_SMALL group.
     *
     * @param projId the proj id
     * @return the SALES_SMALL group
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public static List<String> getSalesGroup(final int projId) throws SystemException, Exception {

        return null;
    }

    /**
     * Save hierarchy.
     *
     * @param mode the mode
     * @param projectionId the projection id
     */
    public void saveHierarchy(final String mode, final String projectionId) {

    }

    /**
     * Gets companyMaster from companyMasterSid
     *
     * @param companyMasterSid the company master system id
     * @return list of companies
     * @throws Exception
     */
    public List<CompanyMaster> getCompanyFromSid(final int companyMasterSid) throws Exception {
        List<CompanyMaster> companyMasterList;
        DynamicQuery companyQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
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
    public List<HelperDTO> getCompanies(int startIndex, int endIndex, String filterText) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        dynamicQuery.add(PropertyFactoryUtil.forName("companyType").in(
                DynamicQueryFactoryUtil.forClass(HelperTable.class)
                .add(RestrictionsFactoryUtil.eq(Constant.DESCRIPTION, "GLCOMP")).setProjection(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID))));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, filterText));
        dynamicQuery.setLimit(startIndex, endIndex);
        List<Object[]> returnlist = dataSelection.getCompanies(dynamicQuery);
        List<HelperDTO> companies = new ArrayList<HelperDTO>();
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
            helperDTO.setId(Integer.valueOf(String.valueOf(objects[0])));
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
    public int getCompaniesCount(String filterText) throws Exception {
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        dynamicQuery.add(PropertyFactoryUtil.forName("companyType").in(
                DynamicQueryFactoryUtil.forClass(HelperTable.class)
                .add(RestrictionsFactoryUtil.eq(Constant.DESCRIPTION, "GLCOMP")).setProjection(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID))));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, filterText));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        return dataSelection.getCompaniesCount(dynamicQuery);
    }

    public Boolean hasTradingPartner(int projectionId) throws SystemException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "hasTradingPartner");
        parameters.put(Constant.PROJECTION_ID, projectionId);
        List returnList = dataSelection.executeQuery(parameters);
        if (returnList.isEmpty()) {
            return true;
        } else {
            return (Integer) returnList.get(0) >= 1;
        }
    }

    public DataSelectionDTO getProjection(int projectionId) throws SystemException, Exception {

        List projection = dataSelection.getProjection(projectionId);
        if (projection.isEmpty()) {
            return null;
        } else {
            return Converters.getProjection(projection);
        }
    }

    public String getProjectionName(int projectionIdValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getHelperTableListNames() throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.LIST_NAME));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List resultList = dataSelection.getHelperTableListNames(dynamicQuery);
        List<String> returnList = new ArrayList<String>();
        for (Object listName : resultList) {
            returnList.add(String.valueOf(listName));
        }
        return returnList;
    }

    public int searchDSProjectionsCount(final DataSelectionDTO dataSelectionDTO, Set<Container.Filter> filters) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);
        List resultList;
        parameters.put("lazyLoadResults", null);
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
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastingType", CommonUtils.NONMANDATED));
        if (isValidSid(dataSelectionDTO.getCustomerHierSid())) {
            String customerHierarchySid = dataSelectionDTO.getCustomerHierSid();
            customerHierarchySid = customerHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerHierarchySid", customerHierarchySid);
        }

        if (isValidSid(dataSelectionDTO.getProdHierSid())) {
            String prodHierarchySid = dataSelectionDTO.getProdHierSid();
            prodHierarchySid = prodHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("prodHierarchySid", prodHierarchySid);
        }

        if (!dataSelectionDTO.getSelectedCustomerRelationSid().isEmpty()) {
            parameters.put("selectedCustomerRelationSid", dataSelectionDTO.getSelectedCustomerRelationSid());
        }

        if (!dataSelectionDTO.getSelectedProductRelationSid().isEmpty()) {
            parameters.put("selectedProductRelationSid", dataSelectionDTO.getSelectedProductRelationSid());
        }

        if (isValidSid(dataSelectionDTO.getCustomerHierarchyLevel())) {
            String customerHierarchyLevel = dataSelectionDTO.getCustomerHierarchyLevel();
            customerHierarchyLevel = customerHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerHierarchyLevel", customerHierarchyLevel);
        }

        if (isValidSid(dataSelectionDTO.getProductHierarchyLevel())) {
            String productHierarchyLevel = dataSelectionDTO.getProductHierarchyLevel();
            productHierarchyLevel = productHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("productHierarchyLevel", productHierarchyLevel);
        }

        if (isValidSid(dataSelectionDTO.getCustomerGrpSid())) {
            String customerGrpSid = dataSelectionDTO.getCustomerGrpSid();
            customerGrpSid = customerGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerGroupSid", customerGrpSid);
        }

        if (isValidSid(String.valueOf(dataSelectionDTO.getDiscountSid()))) {
            String discountSid = String.valueOf(dataSelectionDTO.getDiscountSid());
            discountSid = discountSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("discountSid", discountSid);
        }

        if (isValidSid(dataSelectionDTO.getProdGrpSid())) {
            String prodGrpSid = dataSelectionDTO.getProdGrpSid();
            prodGrpSid = prodGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("prodGroupSid", prodGrpSid);
        }

        if (isValidSid(dataSelectionDTO.getCompanySid())) {
            String companySid = dataSelectionDTO.getCompanySid();
            companySid = companySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(Constant.COMPANYMASTERSID, companySid);
        }
        if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getFromPeriod())) {
            if (!StringUtils.isBlank(dataSelectionDTO.getFromPeriod()) && !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getFromPeriod())) {
                String fromPeriod = DataSelectionUtil.getDateFromQuarter(dataSelectionDTO.getFromPeriod());
                parameters.put("fromDate", fromPeriod);
            }
        } else {
            parameters.put("fromDate", null);
        }
        if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getToPeriod())) {
            if (!StringUtils.isBlank(dataSelectionDTO.getToPeriod()) && !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getToPeriod())) {
                String toDate = DataSelectionUtil.getLastDateFromQuarter(dataSelectionDTO.getToPeriod());
                parameters.put("toDate", toDate);
            }
        } else {
            parameters.put("toDate", null);
        }
        parameters.put("selectValue", StringUtils.EMPTY);
        parameters.put("leftJoinValue", StringUtils.EMPTY);
        parameters.put("whereFilterValue", StringUtils.EMPTY);
        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(dataSelectionDTO.getModulName())) {
            parameters.put("selectValue", "APS.FIELD_NAME,APS.FIELD_VALUES");
            parameters.put("leftJoinValue", " Left Join ACCRUAL_PROJ_SELECTION APS ON APS.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID  ");
            parameters.put("whereFilterValue", "   AND APS.SCREEN_NAME='AccrualRateProjection' ");
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
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), DataSelectionUtil.filterDiscount(filterString));
                    } else if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    } else {
                        String lastName = StringUtils.EMPTY;
                        String firstName = StringUtils.EMPTY;
                        if (filterString.contains(",")) {
                            String array[] = filterString.split(",");
                            lastName = array[0].trim();
                            firstName = array[1].trim();
                        } else {
                            lastName = filterString;
                            lastName = lastName.trim();
                        }

                        try {

                            filterString = stringFilter.getFilterString();
                            parameters.put(Constant.FILTER + stringFilter.getPropertyId(), DataSelectionUtil.filterUser(filterString));
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~from", String.valueOf(startValue));
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~from", String.valueOf(value));
                    } else {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~to", String.valueOf(value));
                    }
                }
            }
        }
        resultList = dataSelection.searchDSProjections(parameters);
        return Integer.parseInt(String.valueOf(resultList.get(0)));
    }

    public List<DataSelectionDTO> searchDSProjectionsLazy(final DataSelectionDTO dataSelectionDTO, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);
        List resultList;
        List<DataSelectionDTO> returnList = null;
        parameters.put("startIndex", startIndex);
        parameters.put("offset", offset);
        parameters.put("lazyLoadResults", "lazyLoadResults");
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
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("forecastingType", CommonUtils.NONMANDATED));
        if (isValidSid(dataSelectionDTO.getCustomerHierSid())) {
            String customerHierarchySid = dataSelectionDTO.getCustomerHierSid();
            customerHierarchySid = customerHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerHierarchySid", customerHierarchySid);
        }

        if (isValidSid(dataSelectionDTO.getProdHierSid())) {
            String prodHierarchySid = dataSelectionDTO.getProdHierSid();
            prodHierarchySid = prodHierarchySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("prodHierarchySid", prodHierarchySid);
        }
        if (!dataSelectionDTO.getSelectedCustomerRelationSid().isEmpty()) {
            parameters.put("selectedCustomerRelationSid", dataSelectionDTO.getSelectedCustomerRelationSid());
        }

        if (!dataSelectionDTO.getSelectedProductRelationSid().isEmpty()) {
            parameters.put("selectedProductRelationSid", dataSelectionDTO.getSelectedProductRelationSid());
        }

        if (isValidSid(dataSelectionDTO.getCustomerHierarchyLevel())) {
            String customerHierarchyLevel = dataSelectionDTO.getCustomerHierarchyLevel();
            customerHierarchyLevel = customerHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerHierarchyLevel", customerHierarchyLevel);
        }

        if (isValidSid(dataSelectionDTO.getProductHierarchyLevel())) {
            String productHierarchyLevel = dataSelectionDTO.getProductHierarchyLevel();
            productHierarchyLevel = productHierarchyLevel.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("productHierarchyLevel", productHierarchyLevel);
        }
        if (isValidSid(String.valueOf(dataSelectionDTO.getDiscountSid()))) {
            String discountSid = String.valueOf(dataSelectionDTO.getDiscountSid());
            discountSid = discountSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("discountSid", discountSid);
        }

        if (isValidSid(dataSelectionDTO.getCustomerGrpSid())) {
            String customerGrpSid = dataSelectionDTO.getCustomerGrpSid();
            customerGrpSid = customerGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("customerGroupSid", customerGrpSid);
        }

        if (isValidSid(dataSelectionDTO.getProdGrpSid())) {
            String prodGrpSid = dataSelectionDTO.getProdGrpSid();
            prodGrpSid = prodGrpSid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("prodGroupSid", prodGrpSid);
        }

        if (isValidSid(dataSelectionDTO.getCompanySid())) {
            String companySid = dataSelectionDTO.getCompanySid();
            companySid = companySid.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put(Constant.COMPANYMASTERSID, companySid);
        }
        if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getFromPeriod())) {
            if (!StringUtils.isBlank(dataSelectionDTO.getFromPeriod()) && !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getFromPeriod())) {
                String fromPeriod = DataSelectionUtil.getDateFromQuarter(dataSelectionDTO.getFromPeriod());
                parameters.put("fromDate", fromPeriod);
            }
        } else {
            parameters.put("fromDate", null);
        }
        if (!SELECT_ONE.equalsIgnoreCase(dataSelectionDTO.getToPeriod())) {
            if (!StringUtils.isBlank(dataSelectionDTO.getToPeriod()) && !CommonConstants.NULL.getConstant().equals(dataSelectionDTO.getToPeriod())) {
                String toDate = DataSelectionUtil.getLastDateFromQuarter(dataSelectionDTO.getToPeriod());
                parameters.put("toDate", toDate);
            }
        } else {
            parameters.put("toDate", null);
        }

        parameters.put("selectValue", StringUtils.EMPTY);
        parameters.put("leftJoinValue", StringUtils.EMPTY);
        parameters.put("whereFilterValue", StringUtils.EMPTY);
        if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(dataSelectionDTO.getModulName())) {
            parameters.put("selectValue", ",APS.FIELD_NAME,APS.FIELD_VALUES");
            parameters.put("leftJoinValue", "  Left Join ACCRUAL_PROJ_SELECTION APS ON APS.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID  ");
            parameters.put("whereFilterValue", "   AND APS.SCREEN_NAME='AccrualRateProjection' ");
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
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), DataSelectionUtil.filterDiscount(filterString));
                    } else if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    } else {
                        filterString = stringFilter.getFilterString();
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), DataSelectionUtil.filterUser(filterString));
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~from", String.valueOf(startValue));
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~from", String.valueOf(value));
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
        returnList = Converters.searchDsProjection(resultList, !CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(dataSelectionDTO.getModulName()) ? CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(dataSelectionDTO.getModulName()) ? true : false : true);
        return returnList;
    }

    public void deleteCompleteProjection(final int projectionId, final String screenName) throws PortalException, SystemException {
        if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
            deleteReturnsDetails(projectionId);
            deleteReturnsMap(projectionId);
            deleteProjectionProdHierarchy(projectionId, null);
//            deleteProjectionMaster(projectionId, null);
        } else {
            deleteCustomView(projectionId, screenName);
            deleteProjectionDetails(projectionId, null);
            deleteProjectionCustHierarchy(projectionId, null);
            deleteProjectionProdHierarchy(projectionId, null);
//            deleteProjectionMaster(projectionId, null);
        }
    }

    private void deleteCustomView(final int projectionId, String screenName) {
        String deleteQuery = StringUtils.EMPTY;
        try {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName)) {
                deleteQuery = SQlUtil.getQuery("deleteMandatedCVD").replace("@PROJECTIONSID", String.valueOf(projectionId));
            } else {
                deleteQuery = SQlUtil.getQuery("deleteNonMandatedCVD").replace("@PROJECTIONSID", String.valueOf(projectionId));
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
        } catch (Exception ex) {
            LOGGER.info("Error Query= = >" + deleteQuery);
            LOGGER.error(ex);
        }
    }

    private void deleteReturnsDetails(final int projectionId) throws PortalException, SystemException {
        String deleteQuery = SQlUtil.getQuery("deleteReturnsDetail").replace("@PROJECTIONSID", String.valueOf(projectionId));
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
    }

    private void deleteReturnsMap(final int projectionId) throws PortalException, SystemException {
        String deleteQuery = SQlUtil.getQuery("deleteReturnsMap").replace("@PROJECTIONSID", String.valueOf(projectionId));
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
    }

    private void deleteProjectionDetails(final int projectionId, final List<Integer> projectionSids) throws PortalException, SystemException {
        List<ProjectionDetails> projectionDetailsList = new ArrayList<ProjectionDetails>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
        if (projectionId != 0 && projectionSids == null) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.PROJECTION_MASTER_SID, projectionSids));
        }
        projectionDetailsList = dataSelection.getProjectionDetails(dynamicQuery);
        for (ProjectionDetails projectionDetails : projectionDetailsList) {
            dataSelection.deleteProjectionDetails(projectionDetails.getProjectionDetailsSid());
        }
    }

    private void deleteProjectionCustHierarchy(final int projectionId, final List<Integer> projectionSids) throws PortalException, SystemException {
        List<ProjectionCustHierarchy> projectionCustHierarchyList = new ArrayList<ProjectionCustHierarchy>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class);
        if (projectionId != 0 && projectionSids == null) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.PROJECTION_MASTER_SID, projectionSids));
        }
        projectionCustHierarchyList = dataSelection.getProjectionCustHierarchy(dynamicQuery);
        for (ProjectionCustHierarchy projectionCustHierarchy : projectionCustHierarchyList) {
            dataSelection.deleteProjectionCustHierarchy(projectionCustHierarchy.getProjectionCustHierarchySid());
        }
    }

    private void deleteProjectionProdHierarchy(final int projectionId, final List<Integer> projectionSids) throws PortalException, SystemException {
        List<ProjectionProdHierarchy> projectionProdHierarchyList = new ArrayList<ProjectionProdHierarchy>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class);
        if (projectionId != 0 && projectionSids == null) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.PROJECTION_MASTER_SID, projectionSids));
        }
        projectionProdHierarchyList = dataSelection.getProjectionProdHierarchy(dynamicQuery);
        for (ProjectionProdHierarchy projectionProdHierarchy : projectionProdHierarchyList) {
            dataSelection.deleteProjectionProdHierarchy(projectionProdHierarchy.getProjectionProdHierarchySid());
        }
    }

    private void deleteProjectionMaster(final int projectionId, final List<Integer> projectionSids) throws PortalException, SystemException {
        List<ProjectionMaster> projectionMasterList = new ArrayList<ProjectionMaster>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);
        if (projectionId != 0 && projectionSids == null) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        } else {
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.PROJECTION_MASTER_SID, projectionSids));
        }
        projectionMasterList = dataSelection.getProjectionMaster(dynamicQuery);
        for (ProjectionMaster projectionMaster : projectionMasterList) {
            dataSelection.deleteProjectionMaster(projectionMaster.getProjectionMasterSid());
        }
    }

    public void updateSaveFlag(final int projectionId) throws SystemException, Exception {
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = dataSelection.getProjectionMaster(projectionId);
        }
        projectionMaster.setSaveFlag(true);
        dataSelection.updateProjectionMaster(projectionMaster);
    }

    public Object tempInsert(final SessionDTO inputDto, final String customSqlId) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", inputDto.getUserId());
        input.put("?SID", inputDto.getSessionId());
        input.put("?PID", inputDto.getProjectionId());
        input.put("?LD", inputDto.getSessionDate());
        input.put("?PSY", inputDto.getForecastDTO().getProjectionStartYear());
        input.put("?PEY", inputDto.getForecastDTO().getForecastEndYear());

        return dataSelection.tempOperation(input, customSqlId);
    }

    public Object clearTemp(final SessionDTO inputDto, final String customSqlId) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", inputDto.getUserId());
        input.put("?SID", inputDto.getSessionId());
        Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        tempDate.setDate(tempDate.getDate() - 2);
        input.put("?LD", fmt.format(tempDate));

        return dataSelection.tempOperation(input, customSqlId);
    }

    public Object saveTampToMain(final SessionDTO inputDto) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", inputDto.getUserId());
        input.put("?SID", inputDto.getSessionId());
        return dataSelection.tempOperation(input, "nm.saveToMainTable");
    }

    public Object deleteTempBySession(final SessionDTO inputDto, final String screenName) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", inputDto.getUserId());
        input.put("?SID", inputDto.getSessionId());
        if(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)){
            return dataSelection.tempOperation(input, "nm.dleteTemp");
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName)){
            return dataSelection.tempOperation(input, "m.dleteTemp");
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)){
            return dataSelection.tempOperation(input, "returns.dleteTemp");
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)){
            return dataSelection.tempOperation(input, "accural.dleteTemp");
        }
        return null;
    }

    public Object deleteMasterTables(final SessionDTO inputDto) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", inputDto.getProjectionId());
        return dataSelection.tempOperation(input, "nm.dleteMasterTables");
    }

    public Object deleteTempByProjection(final SessionDTO inputDto) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", inputDto.getProjectionId());
        return dataSelection.tempOperation(input, "nm.dleteTempProjectionTables");
    }

    public void setSingleBrand(final SessionDTO inputDto) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", inputDto.getProjectionId());
        inputDto.setPpaIndicator((Integer) ((List) dataSelection.tempOperation(input, "ds.singleBrand")).get(0) == 1);
    }

    public Object deleteTempByProjection(final int projectionSid) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projectionSid);
        return dataSelection.tempOperation(input, "nm.dleteTempProjectionTables");
    }

    public Object deleteCustomView(final int projectionSid) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projectionSid);
        return dataSelection.tempOperation(input, "nm.deleteCustomViewTables");
    }

    public Object deleteProjectionSelection(final int projectionSid) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projectionSid);
        return dataSelection.tempOperation(input, "nm.deleteProjectionSelection");
    }

    public void deleteUnsavedProjections(final String[] detailsTable, final String[] projectionTable, final String forecastingType) {
        String deleteQuery = StringUtils.EMPTY;
        String query = forecastingType.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED) ? SQlUtil.getQuery("getDeleteCvd")
                : forecastingType.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED) ? SQlUtil.getQuery("getDeleteCvdMandated") : StringUtils.EMPTY;

        String detailQuery = forecastingType.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION)
                ? buildDeleteQuery(detailsTable, SQlUtil.getQuery("getforecastDeleteDetailsQueryAccural"))
                : forecastingType.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)
                        ? buildDeleteQuery(detailsTable, SQlUtil.getQuery("getforecastDeleteDetailsQueryReturns")) : buildDeleteQuery(detailsTable, SQlUtil.getQuery("getforecastDeleteDetailsQuery"));
        
        String deleteSelectQuery = SQlUtil.getQuery(forecastingType.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)
                ? "getDeleteQueryReturns" : forecastingType.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION) ? "getDeleteQueryAccural" : "getDeleteQuery");
        try {
            //DYNAMIC DELETE 
            deleteQuery = deleteSelectQuery.replace("@FORCASTING_TYPE", forecastingType);
            deleteQuery += (detailQuery + " " + query
                    + " " + buildDeleteQuery(projectionTable, SQlUtil.getQuery("getforecastDeleteProjectionQuery")));
            HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
        } catch (Exception ex) {
            LOGGER.info("Intial Session Delete QUERY ERROR--> " + deleteQuery);
            LOGGER.error(ex.getMessage());
        }
    }

    private String getUserFilter(String lastName, String firstName) throws PortalException, SystemException {
        List<Object> loggedUserDetails = null;
        String userIds = Constant.PERCENT;
        DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class);
        userQuery.add(RestrictionsFactoryUtil.like(Constant.LASTNAME, lastName));
        if (!StringUtils.isBlank(firstName) && !CommonConstants.NULL.getConstant().equals(firstName)) {
            userQuery.add(RestrictionsFactoryUtil.like(Constant.FIRSTNAME, firstName));
        }
        userQuery.add(RestrictionsFactoryUtil.like(Constant.LASTNAME, lastName));
        try {
            ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
            projectionList.add(ProjectionFactoryUtil.distinct((ProjectionFactoryUtil.property(Constant.USER_ID))));
            userQuery.setProjection(projectionList);
            loggedUserDetails = UserLocalServiceUtil.dynamicQuery(userQuery);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        if (loggedUserDetails != null) {
            userIds = UiUtils.stringListToString(UiUtils.objectListToStringList(loggedUserDetails));
        }
        return userIds;
    }

    public int updateBasicsProjectionMaster(final DataSelectionDTO dataSelectionDTO, int projectionId, final boolean markAsSaved) throws PortalException, SystemException, Exception {

        final String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        ProjectionMaster projectionMaster = ProjectionMasterLocalServiceUtil.createProjectionMaster(0);
        LOGGER.info("Entering updateBasicsProjectionMaster ");
        if (!StringUtils.isEmpty(String.valueOf(projectionId)) && !CommonConstants.NULL.getConstant().equalsIgnoreCase(String.valueOf(projectionId))) {
            projectionMaster = dataSelection.getProjectionMaster(projectionId);
        }
        projectionMaster.setCustomerHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getCustomerHierarchyInnerLevel()));
        projectionMaster.setProductHierarchyInnerLevel(Integer.parseInt(dataSelectionDTO.getProductHierarchyInnerLevel()));
        projectionMaster.setForecastingType(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
        projectionMaster.setModifiedBy(Integer.parseInt(userId));
        projectionMaster.setModifiedDate(new Date());
        if (markAsSaved) {
            projectionMaster.setSaveFlag(true);
        }
        projectionMaster = dataSelection.updateProjectionMaster(projectionMaster);
        return projectionMaster.getProjectionMasterSid();
    }

    public void clearTemp(final SessionDTO inputDto) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", inputDto.getUserId());
        input.put("?SID", inputDto.getSessionId());
        Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        tempDate.setDate(tempDate.getDate() - 2);
        input.put("?LD", fmt.format(tempDate));

        clearTempTables(tempDate);
    }

    public void clearTempTables(Date lastModified1) throws PortalException, Exception {
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        String lastModified = fmt.format(lastModified1);

        String deleteQuery = "DELETE FROM dbo.ST_M_SALES_PROJECTION WHERE LAST_MODIFIED_DATE< '" + lastModified + "';"
                + "DELETE FROM dbo.ST_M_ACTUAL_SALES WHERE LAST_MODIFIED_DATE<'" + lastModified + "';"
                + "DELETE FROM dbo.ST_M_SALES_PROJECTION_MASTER WHERE LAST_MODIFIED_DATE<'" + lastModified + "';";

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(deleteQuery);

    }

    public void tempInsert(final SessionDTO inputDto) throws SystemException, Exception {

        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        Date tempDate = fmt.parse(inputDto.getSessionDate());
        insertIntoTempTables(inputDto.getUserId(), inputDto.getSessionId(), tempDate, String.valueOf(inputDto.getProjectionId()));
        if (Constant.EDIT.equalsIgnoreCase(inputDto.getAction()) || Constant.VIEW.equalsIgnoreCase(inputDto.getAction())) {
            SupplementalDiscountProjectionLogic logic = new SupplementalDiscountProjectionLogic();
            String pojectionDetailsSids = CommonUtils.CollectionToString(logic.getProjectionDetailsSid(inputDto), false);
            logic.masterToTempCopy(inputDto, pojectionDetailsSids);
        }
        Object[] orderedArgs = {inputDto.getProjectionId(), inputDto.getMarketTypeValue(), inputDto.getUserId(), inputDto.getSessionId()};
        CommonLogic.callProcedureforUpdate(Constant.SUPPLEMENTAL_INSERT_PRC, orderedArgs);
        Object[] orderedArgs1 = {inputDto.getProjectionId(), inputDto.getUserId(), "SPAP", inputDto.getSessionId()};
        CommonLogic.callProcedure("PRC_M_DISCOUNT_INSERT", orderedArgs1);
    }

    public void insertIntoTempTables(String userId, String sessionId, Date lastModifiedDate, String projectionId) throws PortalException, Exception {
        insertTempSalesProjectionMaster(userId, sessionId, lastModifiedDate, projectionId);
        insertTempActualSales(userId, sessionId, lastModifiedDate, projectionId);
        insertTempSalesProjection(userId, sessionId, lastModifiedDate, projectionId);
    }

    public void insertTempSalesProjectionMaster(String userId, String sessionId, Date lastModifiedDate, String projectionId) throws PortalException, Exception {
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        String lastModified = fmt.format(lastModifiedDate);
        String insertQuery = "INSERT INTO ST_M_SALES_PROJECTION_MASTER(\n"
                + "			PROJECTION_DETAILS_SID,\n"
                + "			METHODOLOGY,\n"
                + "			CALCULATION_PERIODS,\n"
                + "			CALCULATION_BASED,\n"
                + "			CHECK_RECORD, \n"
                + "			USER_ID,\n"
                + "			SESSION_ID,\n"
                + "			LAST_MODIFIED_DATE)\n"
                + "		SELECT A.PROJECTION_DETAILS_SID,\n"
                + "			A.METHODOLOGY,\n"
                + "			A.CALCULATION_PERIODS,\n"
                + "			A.CALCULATION_BASED,\n"
                + "			A.CHECK_RECORD, \n"
                + "			" + userId + " USER_ID,\n"
                + "			" + sessionId + " SESSION_ID,\n"
                + "			'" + lastModified + "' LAST_MODIFIED_DATE \n"
                + "		FROM dbo.M_SALES_PROJECTION_MASTER A,\n"
                + "			dbo.PROJECTION_DETAILS B\n"
                + "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                + "			AND B.PROJECTION_MASTER_SID=" + projectionId + ";";

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(insertQuery);

    }

    public void insertTempSalesProjection(String userId, String sessionId, Date lastModifiedDate, String projectionId) throws PortalException, Exception {
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        String lastModified = fmt.format(lastModifiedDate);
        String insertQuery = "INSERT INTO dbo.ST_M_SALES_PROJECTION(\n"
                + "			PROJECTION_DETAILS_SID,\n"
                + "			ACCOUNT_GROWTH,\n"
                + "			PRODUCT_GROWTH,\n"
                + "			PROJECTION_SALES,\n"
                + "			PROJECTION_UNITS,\n"
                + "			PERIOD_SID,\n"
                + "			ADJUSTMENT_TYPE,\n"
                + "			ADJUSTMENT_BASIS,\n"
                + "			ADJUSTMENT_VARIABLE,\n"
                + "			ADJUSTMENT_METHODOLOGY,\n"
                + "			ADJUSTMENT_VALUES,\n"
                + "			USER_ID,\n"
                + "			SESSION_ID,\n"
                + "			LAST_MODIFIED_DATE )\n"
                + "		SELECT A.PROJECTION_DETAILS_SID,\n"
                + "			A.ACCOUNT_GROWTH,\n"
                + "			A.PRODUCT_GROWTH,\n"
                + "			A.PROJECTION_SALES,\n"
                + "			A.PROJECTION_UNITS,\n"
                + "			A.PERIOD_SID,\n"
                + "			A.ADJUSTMENT_TYPE,\n"
                + "			A.ADJUSTMENT_BASIS,\n"
                + "			A.ADJUSTMENT_VARIABLE,\n"
                + "			A.ADJUSTMENT_METHODOLOGY,\n"
                + "			A.ADJUSTMENT_VALUES,\n"
                + "			" + userId + " USER_ID,\n"
                + "			" + sessionId + " SESSION_ID,\n"
                + "			'" + lastModified + "' LAST_MODIFIED_DATE \n"
                + "		FROM dbo.M_SALES_PROJECTION A,\n"
                + "			dbo.PROJECTION_DETAILS B\n"
                + "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                + "			AND B.PROJECTION_MASTER_SID=" + projectionId + ";";

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(insertQuery);

    }

    public void insertTempActualSales(String userId, String sessionId, Date lastModifiedDate, String projectionId) throws PortalException, Exception {
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        String lastModified = fmt.format(lastModifiedDate);
        String insertQuery = "INSERT INTO dbo.ST_M_ACTUAL_SALES(\n"
                + "			PROJECTION_DETAILS_SID,\n"
                + "			PERIOD_SID,\n"
                + "			ACTUAL_SALES,\n"
                + "			ACTUAL_UNITS,\n"
                + "			ACTUAL_PROJECTION_SALES,\n"
                + "			ACTUAL_PROJECTION_UNITS,\n"
                + "			USER_ID,\n"
                + "			SESSION_ID,\n"
                + "			LAST_MODIFIED_DATE)\n"
                + "		SELECT A.PROJECTION_DETAILS_SID,\n"
                + "			A.PERIOD_SID,\n"
                + "			A.ACTUAL_SALES,\n"
                + "			A.ACTUAL_UNITS,\n"
                + "			A.ACTUAL_PROJECTION_SALES,\n"
                + "			A.ACTUAL_PROJECTION_UNITS,\n"
                + "			" + userId + " USER_ID,\n"
                + "			" + sessionId + " SESSION_ID,\n"
                + "			'" + lastModified + "' LAST_MODIFIED_DATE \n"
                + "		FROM dbo.M_ACTUAL_SALES A,\n"
                + "			dbo.PROJECTION_DETAILS B\n"
                + "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                + "			AND B.PROJECTION_MASTER_SID=" + projectionId + ";";

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(insertQuery);

    }

    public void deleteTempBySessionMan(final SessionDTO inputDto) throws SystemException, Exception {

        String deleteQuery = "DELETE FROM dbo.ST_M_SALES_PROJECTION WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId() + ";"
                + "DELETE FROM dbo.ST_M_ACTUAL_SALES WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId() + ";"
                + "DELETE FROM dbo.ST_M_SALES_PROJECTION_MASTER WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId() + ";"
                + "DELETE FROM dbo.ST_M_SUPPLEMENTAL_DISC_PROJ WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId() + ";"
                + "DELETE FROM dbo.ST_M_SUPPLEMENTAL_DISC_ACTUALS WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId() + ";"
                + "DELETE FROM dbo.ST_M_SUPPLEMENTAL_DISC_MASTER WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId() + ";"
                + "DELETE FROM dbo.ST_M_ASSUMPTIONS WHERE USER_ID = " + inputDto.getUserId() + " AND SESSION_ID = " + inputDto.getSessionId();

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(deleteQuery);

    }

    public void tempInsertReturns(final SessionDTO inputDto, final String query) {
        String insertQuery = StringUtils.EMPTY;
        try {
            insertQuery = CommonUtils.getReturnsProperties(query);
            insertQuery = insertQuery.replace("@PROJECTION_MASTER_SID", StringUtils.EMPTY + inputDto.getProjectionId());
            insertQuery = insertQuery.replace("@USER_ID", StringUtils.EMPTY + inputDto.getUserId());
            insertQuery = insertQuery.replace("@SESSION_ID", StringUtils.EMPTY + inputDto.getSessionId());

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.executeUpdateQuery(insertQuery);
        } catch (Exception e) {
            LOGGER.error("Query:======================>" + insertQuery);
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method checks for the actuals for the Trading Partner / Customer used in
     * the projection.
     *
     * @param projectionId
     * @return true - if at least one trading partners / customer does not have
     * actuals. false - if all the trading partners / customer have actuals.
     * @throws PortalException
     * @throws Exception
     */
    public boolean checkForZeroActuals(final int projectionId) throws PortalException, Exception {

        String query = CustomSQLUtil.get("checktpcustomeractual");
        query = query.replace("@PROJECTION_ID", String.valueOf(projectionId));
        List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(query);
        return list != null && !list.isEmpty() && (Integer) list.get(0) > 0;

    }

    /**
     * Method removes the Trading Partner / Customer that have no actuals.
     *
     * @param projectionId
     * @throws PortalException
     * @throws Exception
     */
    public void removeTPOrCustomerFromProjection(final int projectionId) throws PortalException, Exception {

        String query = CustomSQLUtil.get("remove-tp-customer-with-no-actuals");
        query = query.replace("@PROJECTION_ID", String.valueOf(projectionId));
        salesProjectionDAO.executeUpdateQuery(query);

    }

    public void deleteTempData(final SessionDTO inputDto, final String queryName) {
        String insertQuery = StringUtils.EMPTY;
        try {

            insertQuery = CommonUtils.getReturnsProperties(queryName);
            insertQuery = insertQuery.replace("@PROJECTION_MASTER_SID", StringUtils.EMPTY + inputDto.getProjectionId());
            insertQuery = insertQuery.replace("@USER_ID", StringUtils.EMPTY + inputDto.getUserId());
            insertQuery = insertQuery.replace("@SESSION_ID", StringUtils.EMPTY + inputDto.getSessionId());
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.executeUpdateQuery(insertQuery);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    private String buildDeleteQuery(final String[] tableName, final String query) {
        StringBuilder buildDeleteQuery = new StringBuilder();
        for (String table : tableName) {
            buildDeleteQuery.append(query.replace("@TABLE_NAME", table));
        }
        return buildDeleteQuery.toString();
    }

    public static List getARPView(String viewValue, String forecastType, String userId, String viewType) {

        List<Object> list = new ArrayList<Object>();
        String viewQueryARPP = "Select FVM.VIEW_NAME viewName, PM.PROJECTION_DESCRIPTION description, PM.PROJECTION_NAME projectionName, PM.BRAND_TYPE brandType, PM.MODIFIED_DATE modifiedDate, PM.CREATED_BY createdBy,\n"
                + "                        PM.CREATED_DATE createdDate, PM.PRODUCT_HIERARCHY_LEVEL productLevel, PM.CUSTOMER_HIERARCHY_LEVEL customerLevel, CUST_HD.HIERARCHY_NAME customerHierarchy, PROD_HD.HIERARCHY_NAME productHierarchy,\n"
                + "                        CM.COMPANY_NAME company, CMG.COMPANY_GROUP_NAME customerGroup, IG.ITEM_GROUP_NAME productGroup, PM.PROJECTION_MASTER_SID projectionId, FVM.VIEW_ID viewId, PM.CUSTOMER_HIERARCHY_SID customerHierarchySid,\n"
                + "                        PM.PRODUCT_HIERARCHY_SID productHierarchySid, PM.COMPANY_GROUP_SID companyGroupSid, PM.ITEM_GROUP_SID productGroupSid, PM.COMPANY_MASTER_SID companyMasterSid, PM.FROM_DATE fromDate, PM.TO_DATE toDate, \n"
                + "                        PM.CUSTOMER_HIERARCHY_INNER_LEVEL customerInnerLevel,PM.PRODUCT_HIERARCHY_INNER_LEVEL productInnerLevel, PM.CUST_RELATIONSHIP_BUILDER_SID custRelationshipBuilderSid, "
                + "     PM.PROD_RELATIONSHIP_BUILDER_SID prodRelationshipBuilderSid  " + ",APS.FIELD_NAME,APS.FIELD_VALUES,HT.DESCRIPTION AS DEDUCTION_VALUE   \n"
                + "                        from PROJECTION_MASTER PM \n"
                + "                        \n"
                + "                        LEFT JOIN FORECASTING_VIEW_MASTER FVM \n"
                + "                        on FVM.projection_Id  = PM.projection_Master_Sid \n"
                + "\n"
                + "                        LEFT JOIN HIERARCHY_DEFINITION CUST_HD \n"
                + "                        on CUST_HD.HIERARCHY_DEFINITION_SID =PM.CUSTOMER_HIERARCHY_SID \n"
                + "\n"
                + "                        LEFT JOIN HIERARCHY_DEFINITION PROD_HD \n"
                + "                        on PROD_HD.HIERARCHY_DEFINITION_SID = PM.PRODUCT_HIERARCHY_SID \n"
                + "\n"
                + "                        LEFT JOIN COMPANY_GROUP CMG \n"
                + "                        on CMG.COMPANY_GROUP_SID  = PM.COMPANY_GROUP_SID\n"
                + "\n"
                + "                        LEFT JOIN ITEM_GROUP IG \n"
                + "                        on IG.ITEM_GROUP_SID  = PM.ITEM_GROUP_SID\n"
                + "\n"
                + "                        LEFT JOIN COMPANY_MASTER CM \n"
                + "                        on PM.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
                + "                         LEFT JOIN  ACCRUAL_PROJ_SELECTION APS \n"
                + "                        on APS.projection_Master_Sid= PM.projection_Master_Sid   "
                + "                        LEFT JOIN HELPER_TABLE HT on HT.HELPER_TABLE_SID = APS.FIELD_VALUES\n"
                + "                       WHERE \n"
                + "FVM.view_Type ='@VIEW_TYPE' \n"
                + "AND FVM.view_Name LIKE '@VIEW_NAME' \n"
                + " AND PM.forecasting_Type = '@FORECAST_TYPE'  \n"
                + " AND FVM.created_By =@CREATED_BY ";
        viewQueryARPP = viewQueryARPP.replace("@VIEW_TYPE", viewType);
        viewQueryARPP = viewQueryARPP.replace("@VIEW_NAME", viewValue);
        viewQueryARPP = viewQueryARPP.replace("@FORECAST_TYPE", forecastType);
        viewQueryARPP = viewQueryARPP.replace("@CREATED_BY", userId);
        list = (List) HelperTableLocalServiceUtil.executeSelectQuery(viewQueryARPP);
        return list;
    }

}
