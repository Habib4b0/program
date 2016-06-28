/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.gtnbalancereport.dao.DataSelectionDAO;
import com.stpl.app.accountclose.gtnbalancereport.dao.daoImpl.DataSelectionDaoImpl;
import com.stpl.app.accountclose.gtnbalancereport.dto.CustomerProductDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.ViewDTO;
import com.stpl.app.accountclose.gtnbalancereport.utils.Constants;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_SQL_DATE_FORMAT;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_PRIVATE_VIEW;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.vaadin.data.Container;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Santanukumar
 */
public class DataSelectionLogic {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionLogic.class);
    public static final DataSelectionDAO dataSelection = new DataSelectionDaoImpl();
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    BaseRateDAO dao = new BaseRateDAOImpl();
    DataSelectionDAO dsDAO = new DataSelectionDaoImpl();

    public List<CustomerProductDTO> getAvailableCustomers(CustomerProductDTO cpdto, Set<Container.Filter> filters) throws Exception {
        List<CustomerProductDTO> availableList = new ArrayList<CustomerProductDTO>();
        List input = new ArrayList();
        input.add(cpdto.getMarketTypeId());
        input.add(cpdto.getDeductionTypeId());

        if (StringUtils.isNotBlank(cpdto.getDeductionSubTypeId()) && !"0".equals(cpdto.getDeductionSubTypeId())) {
            input.add(cpdto.getDeductionSubTypeId());
        } else {
            input.add("%");
        }
        if (StringUtils.isNotBlank(cpdto.getContractId()) && !"0".equals(cpdto.getContractId())) {
            input.add(cpdto.getContractId());
        } else {
            input.add("%");
        }

        input.add(AbstractFilter.getInstance().getavailableFilter(filters).toString());
        input.add(cpdto.getStartIndex());
        input.add(cpdto.getEndIndex());
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "availableCustomers");
        availableList = getCustomizedAvailableList(resultList, "customer");
        return availableList;
    }

    public int getAvailableCustomersCount(CustomerProductDTO cpdto, Set<Container.Filter> filters) throws Exception {
        List input = new ArrayList();
        input.add(cpdto.getMarketTypeId());
        input.add(cpdto.getDeductionTypeId());
        if (StringUtils.isNotBlank(cpdto.getDeductionSubTypeId()) && !"0".equals(cpdto.getDeductionSubTypeId())) {
            input.add(cpdto.getDeductionSubTypeId());
        } else {
            input.add("%");
        }
        if (StringUtils.isNotBlank(cpdto.getContractId()) && !"0".equals(cpdto.getContractId())) {
            input.add(cpdto.getContractId());
        } else {
            input.add("%");
        }
        input.add(AbstractFilter.getInstance().getavailableFilter(filters).toString());
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "availableCustomersCount");
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    private List<CustomerProductDTO> getCustomizedAvailableList(List resultList, String flag) {
        int size = resultList.size();
        List<CustomerProductDTO> finalList = new ArrayList<CustomerProductDTO>();
        if ("customer".equals(flag)) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) resultList.get(i);
                CustomerProductDTO dTo = new CustomerProductDTO();

                dTo.setContractMasterSid(Converters.convertNullToEmpty(arr[0]));
                dTo.setContractNo(Converters.convertNullToEmpty(arr[1]));
                dTo.setContractName(Converters.convertNullToEmpty(arr[2]));
                dTo.setCompanyMasterSid(Converters.convertNullToEmpty(arr[3]));
                dTo.setCustomerName(Converters.convertNullToEmpty(arr[4]));
                dTo.setCustomerNo(Converters.convertNullToEmpty(arr[5]));

                finalList.add(dTo);
            }
        } else if ("product".equals(flag)) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) resultList.get(i);
                CustomerProductDTO dTo = new CustomerProductDTO();
                dTo.setItemMasterSid(Converters.convertNullToEmpty(arr[0]));
                dTo.setSub_ledgerCode(Converters.convertNullToEmpty(arr[1]));
                dTo.setProductName(Converters.convertNullToEmpty(arr[2]));
                dTo.setBrandName(Converters.convertNullToEmpty(arr[3]));
                dTo.setCostCenter(Converters.convertNullToEmpty(arr[4]));
                finalList.add(dTo);
            }
        }

        return finalList;
    }

    public int getAvailableProductsCount(CustomerProductDTO cpdto, Set<Container.Filter> filters) throws Exception {
        List input = new ArrayList();

        if (StringUtils.isNotBlank(cpdto.getCompanyMasterSid()) && !"0".equals(cpdto.getCompanyMasterSid())) {
            input.add(cpdto.getCompanyMasterSid());
        } else {
            input.add("%");
        }
        input.add(AbstractFilter.getInstance().getavailableProductFilter(filters).toString());

        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "availableProductsCount");
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<CustomerProductDTO> getAvailableProducts(CustomerProductDTO cpdto, Set<Container.Filter> filters) throws Exception {
        List<CustomerProductDTO> availableList = new ArrayList<CustomerProductDTO>();
        List input = new ArrayList();
        if (StringUtils.isNotBlank(cpdto.getCompanyMasterSid()) && !"0".equals(cpdto.getCompanyMasterSid())) {
            input.add(cpdto.getCompanyMasterSid());
        }
        input.add(AbstractFilter.getInstance().getavailableProductFilter(filters).toString());
        input.add(cpdto.getStartIndex());
        input.add(cpdto.getEndIndex());
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "availableProducts");
        availableList = getCustomizedAvailableList(resultList, "product");
        return availableList;
    }

    public void insertIntoTempTable(CustomerProductDTO customerProductDTO, SessionDTO session) {
        List input = new ArrayList();
        String query = StringUtils.EMPTY;

        if ("customer".equals(customerProductDTO.getIdenfier())) {
            input.add("CONTRACT_MASTER_SID");
            input.add("CONTRACT_NO");
            input.add("CONTRACT_NAME");
            input.add("COMPANY_MASTER_SID");
            input.add("COMPANY_NO");
            input.add(", COMPANY_NAME");
            input.add(customerProductDTO.getContractMasterSid());
            input.add(customerProductDTO.getContractNo());
            input.add(customerProductDTO.getContractName());
            input.add(customerProductDTO.getCompanyMasterSid());
            input.add(customerProductDTO.getCustomerNo());
            input.add(customerProductDTO.getCustomerName());
            input.add(Constants.DS_SELECTED_CUSTOMERS);
            query = "insertcustomersIntoST_ACC_CLOSURE_DETAILS";
        } else {
            input.add("ITEM_MASTER_SID");
            input.add("NDC8");
            input.add("ITEM_NAME");
            input.add("BRAND_NAME");
            input.add("COMPANY_COST_CENTER");
            input.add(customerProductDTO.getItemMasterSid());
            input.add(customerProductDTO.getSub_ledgerCode());
            input.add(customerProductDTO.getProductName());
            input.add(customerProductDTO.getBrandName());
            input.add(customerProductDTO.getCostCenter());
            input.add(Constants.DS_SELECTED_PRODUCTS);
            query = "insertproductsIntoST_ACC_CLOSURE_DETAILS";
        }
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(CommonLogic.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));

        try {
            QueryUtils.executeUpdateQuery(input, query);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public int getCommonCountForSelectedCP(CustomerProductDTO customerProductDTO, Set<Container.Filter> filters, SessionDTO sessionDTO) throws Exception {
        List input = new ArrayList();
        if ("customer".equals(customerProductDTO.getIdenfier())) {
            input.add("CONTRACT_MASTER_SID");
            input.add("CONTRACT_NO");
            input.add("CONTRACT_NAME");
            input.add("COMPANY_MASTER_SID");
            input.add("COMPANY_NO");
            input.add(", COMPANY_NAME");
            input.add(Constants.DS_SELECTED_CUSTOMERS);

        } else {
            input.add("ITEM_MASTER_SID");
            input.add("NDC8");
            input.add("ITEM_NAME");
            input.add("BRAND_NAME");
            input.add("COMPANY_COST_CENTER");
            input.add(StringUtils.EMPTY);
            input.add(Constants.DS_SELECTED_PRODUCTS);

        }
        input.add(sessionDTO.getUserId());
        input.add(sessionDTO.getSessionId());
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "commonCountForCP");
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<CustomerProductDTO> getCommonResultForSelectedCP(CustomerProductDTO customerProductDTO, Set<Container.Filter> filters, SessionDTO sessionDTO) throws Exception {
        List input = new ArrayList();
        if ("customer".equals(customerProductDTO.getIdenfier())) {
            input.add("CONTRACT_MASTER_SID");
            input.add("CONTRACT_NO");
            input.add("CONTRACT_NAME");
            input.add("COMPANY_MASTER_SID");
            input.add("COMPANY_NO");
            input.add(", COMPANY_NAME");
            input.add(Constants.DS_SELECTED_CUSTOMERS);
            input.add(sessionDTO.getUserId());
            input.add(sessionDTO.getSessionId());
            input.add("ORDER BY TEMP. COMPANY_MASTER_SID OFFSET " + customerProductDTO.getStartIndex() + "  ROWS FETCH NEXT " + customerProductDTO.getEndIndex() + " ROWS ONLY");
        } else {
            input.add("ITEM_MASTER_SID");
            input.add("NDC8");
            input.add("ITEM_NAME");
            input.add("BRAND_NAME");
            input.add("COMPANY_COST_CENTER");
            input.add(StringUtils.EMPTY);
            input.add(Constants.DS_SELECTED_PRODUCTS);
            input.add(sessionDTO.getUserId());
            input.add(sessionDTO.getSessionId());
            input.add("ORDER BY TEMP. ITEM_MASTER_SID OFFSET " + customerProductDTO.getStartIndex() + "  ROWS FETCH NEXT " + customerProductDTO.getEndIndex() + " ROWS ONLY");

        }
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "commonQueryForCP");
        return getSelectedCustomerProducts(resultList, customerProductDTO);
    }

    private List<CustomerProductDTO> getSelectedCustomerProducts(List results, CustomerProductDTO customerProductDTO) {
        List<CustomerProductDTO> customerProductDTOs = new ArrayList<CustomerProductDTO>();
        int size = results.size();
        if ("customer".equals(customerProductDTO.getIdenfier())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                CustomerProductDTO dTo = new CustomerProductDTO();
                dTo.setContractMasterSid(Converters.convertNullToEmpty(arr[0]));
                dTo.setCompanyMasterSid(Converters.convertNullToEmpty(arr[3]));
                dTo.setContractNo(Converters.convertNullToEmpty(arr[1]));
                dTo.setContractName(Converters.convertNullToEmpty(arr[2]));
                dTo.setCustomerNo(Converters.convertNullToEmpty(arr[4]));
                dTo.setCustomerName(Converters.convertNullToEmpty(arr[5]));
                customerProductDTOs.add(dTo);
            }
        } else {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                CustomerProductDTO dTo = new CustomerProductDTO();
                dTo.setItemMasterSid(Converters.convertNullToEmpty(arr[0]));
                dTo.setSub_ledgerCode(Converters.convertNullToEmpty(arr[1]));
                dTo.setProductName(Converters.convertNullToEmpty(arr[2]));
                dTo.setBrandName(Converters.convertNullToEmpty(arr[3]));
                dTo.setCostCenter(Converters.convertNullToEmpty(arr[4]));
                customerProductDTOs.add(dTo);
            }
        }
        return customerProductDTOs;
    }

    public void removeSelectedCP(SessionDTO session, CustomerProductDTO customerProductDTO) {
        List input = new ArrayList();
        input.add("ST_ACC_CLOSURE_DETAILS");
        input.add("USER_ID");
        input.add(session.getUserId());
        input.add("SESSION_ID");
        input.add(session.getSessionId());
        input.add("MODULE_NAME");
        if ("customer".equals(customerProductDTO.getIdenfier())) {
            input.add(Constants.DS_SELECTED_CUSTOMERS);
            input.add(" AND CONTRACT_MASTER_SID =" + customerProductDTO.getContractMasterSid() + " AND COMPANY_MASTER_SID =" + customerProductDTO.getCompanyMasterSid() + ";");
        } else {
            input.add(Constants.DS_SELECTED_PRODUCTS);
            input.add(" AND ITEM_MASTER_SID=" + customerProductDTO.getItemMasterSid() + ";");
        }
        try {
            QueryUtils.executeUpdateQuery(input, "tempDataDelete");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public ForecastConfig getTimePeriod() throws Exception {
        List<ForecastConfig> resultList = null;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", getHelperCode("BUSINESS_PROCESS_TYPE", "Accounting Closure")));
        dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
        resultList = dataSelection.getForecastConfig(dynamicQuery);
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        return forecastConfig;
    }

    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        int code = 0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.valueOf(result.get(0).toString());
        }
        return code;
    }

    public int getSearchCount(DataSelectionDTO dataSelectionDTO, Set<Container.Filter> filters) throws Exception {
        List input = getIndexInput(dataSelectionDTO, filters);
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "dsIndexCount");
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<DataSelectionDTO> getSearchResults(DataSelectionDTO dataSelectionDTO, int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws PortalException, Exception {
        dataSelectionDTO.setStartIndex(start);
        dataSelectionDTO.setEndIndex(offset);
        List input = getIndexInput(dataSelectionDTO, filters);
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "dsIndex");
        return getDataselectionDto(resultList);
    }

    public void insertToAccCloserDetails(SessionDTO session) {
        List<Object> input = new ArrayList<Object>();
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(Constants.DS_SELECTED_CUSTOMERS);
        input.add(session.getUserId());
        input.add(session.getSessionId());
        input.add(Constants.DS_SELECTED_PRODUCTS);
        input.add(String.valueOf(session.getAcctCloserMasterId()));
        input.add(session.getDataSelectionDTO().getDeductionType());

        try {
            QueryUtils.executeUpdateQuery(input, "insertacctcloserdetails");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private List<DataSelectionDTO> getDataselectionDto(List resultList) throws ParseException, PortalException, Exception {
        List<DataSelectionDTO> finalList = new ArrayList<DataSelectionDTO>();
        int size = resultList.size();
        for (int i = 0; i < size; i++) {
            Object arr[] = (Object[]) resultList.get(i);
            DataSelectionDTO dTO = new DataSelectionDTO();
            dTO.setAccountClosureSid(Converters.convertNullToEmpty(arr[0]));
            dTO.setReportName(Converters.convertNullToEmpty(arr[1]));
            dTO.setDescription(Converters.convertNullToEmpty(arr[2]));
            dTO.setCompany(Converters.convertNullToEmpty(arr[3]));
            dTO.setCompanySid(Converters.convertNullToEmpty(arr[4]));
            dTO.setMarketType(Converters.convertNullToEmpty(arr[5]));
            dTO.setDeductionType(Converters.convertNullToEmpty(arr[7]));
            dTO.setDeductionSubType(Converters.convertNullToEmpty(arr[9]));
            dTO.setCustomerGroup(Converters.convertNullToEmpty(arr[11]));
            dTO.setContractName(Converters.convertNullToEmpty(arr[12]));
            dTO.setProductGroup(Converters.convertNullToEmpty(arr[14]));
            dTO.setFromPeriod(Converters.convertNullToEmpty(arr[15]));
            dTO.setToPeriod(Converters.convertNullToEmpty(arr[16]));
            dTO.setFromDate((Date) (arr[15]));
            dTO.setToDate((Date) (arr[16]));
            dTO.setProductName(Converters.convertNullToEmpty(arr[17]));
            dTO.setProductIdentifier(Converters.convertNullToEmpty(arr[18]));
            if (StringUtils.isNotBlank(String.valueOf(arr[19])) && arr[19] != "null") {
                dTO.setCreatedDate(DBDate.parse(DBDate.format((Date) arr[19])));
            } else {
                dTO.setCreatedDate(null);
            }
            if (StringUtils.isNotBlank(String.valueOf(arr[20])) && arr[20] != "null") {
                dTO.setModifiedDate(DBDate.parse(DBDate.format((Date) arr[20])));
            } else {
                dTO.setModifiedDate(null);
            }
            if (StringUtils.isNotBlank(Converters.convertNullToEmpty(arr[21]))) {
                User user = new CommonLogic().getUserById(Converters.convertNullToEmpty(arr[21]));
                dTO.setCreatedBy(user.getLastName() + "," + user.getFirstName());
            }
            if (StringUtils.isNotBlank(Converters.convertNullToEmpty(arr[22]))) {
                User user = new CommonLogic().getUserById(Converters.convertNullToEmpty(arr[22]));
                dTO.setModifiedBy(user.getLastName() + "," + user.getFirstName());
            }
            finalList.add(dTO);
        }
        return finalList;
    }

    public List<ViewDTO> getSearchViews(String indicator, String value) throws PortalException, Exception {
        List input = new ArrayList();
        input.add(CommonUtil.astToPerConverter(value));
        if (INDICATOR_PRIVATE_VIEW.getConstant().equals(indicator)) {
            input.add("Private");
        } else {
            input.add("Public");
        }
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "viewSearch");
        return getViewDto(resultList);
    }

    private List<ViewDTO> getViewDto(List resultList) throws PortalException, Exception {
        List<ViewDTO> finalList = new ArrayList<ViewDTO>();
        int size = resultList.size();
        HelperDTO helperDTO;
        for (int i = 0; i < size; i++) {
            Object arr[] = (Object[]) resultList.get(i);
            ViewDTO dTO = new ViewDTO();
            dTO.setViewMasterId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
            dTO.setViewName(Converters.convertNullToEmpty(arr[1]));
            dTO.setAccountClosureId(Integer.valueOf(Converters.convertNullToEmpty(arr[2])));
            dTO.setReportName(Converters.convertNullToEmpty(arr[3]));
            dTO.setDescription(Converters.convertNullToEmpty(arr[4]));
            helperDTO = new HelperDTO();
            helperDTO.setId(arr[6] == null ? 0 : (Integer) arr[6]);
            helperDTO.setDescription(arr[5] == null || "-Select One-".equals(String.valueOf(arr[5])) ? "-Select One-" : (String) arr[5]);
            dTO.setCompany_DTO(helperDTO);
            dTO.setCompany(Converters.convertNullToEmpty(arr[5]));
            helperDTO = new HelperDTO();
            helperDTO.setId(arr[8] == null ? 0 : (Integer) arr[8]);
            helperDTO.setDescription(arr[7] == null || "-Select One-".equals(String.valueOf(arr[7])) ? "-Select One-" : (String) arr[7]);
            dTO.setMartketType_DTO(helperDTO);
            dTO.setMarketType(Converters.convertNullToEmpty(arr[7]));
            helperDTO = new HelperDTO();
            helperDTO.setId(arr[10] == null ? 0 : (Integer) arr[10]);
            helperDTO.setDescription(arr[9] == null || "-Select One-".equals(String.valueOf(arr[9])) ? "-Select One-" : (String) arr[9]);
            dTO.setDeductionType_DTO(helperDTO);
            dTO.setDeductionType(Converters.convertNullToEmpty(arr[9]));
            helperDTO = new HelperDTO();
            helperDTO.setId(arr[12] == null ? 0 : (Integer) arr[12]);
            helperDTO.setDescription(arr[11] == null || "-Select One-".equals(String.valueOf(arr[11])) ? "-Select One-" : (String) arr[11]);
            dTO.setSubDeductionType_DTO(helperDTO);
            dTO.setDeductionSubType(Converters.convertNullToEmpty(arr[11]));
            dTO.setCustomerGroup(Converters.convertNullToEmpty(arr[13]));
            helperDTO = new HelperDTO();
            helperDTO.setId(arr[15] == null ? 0 : (Integer) arr[15]);
            helperDTO.setDescription(arr[14] == null || "-Select One-".equals(String.valueOf(arr[14])) ? "-Select One-" : (String) arr[14]);
            dTO.setContract_DTO(helperDTO);
            dTO.setContract(arr[15] == null ? "0" : String.valueOf(arr[15]));
            dTO.setProductGroup(Converters.convertNullToEmpty(arr[16]));
            dTO.setFromPeriod(DBDate.format((Date) arr[17]));
            dTO.setToPeriod(DBDate.format((Date) arr[18]));
            dTO.setProductIdentifier(Converters.convertNullToEmpty(arr[20]));
            if (StringUtils.isNotBlank(String.valueOf(arr[21])) && arr[21] != "null") {
                dTO.setCreatedDate(DBDate.format((Date) arr[21]));
            } else {
                dTO.setCreatedDate(Converters.convertNullToEmpty(arr[21]));
            }
            if (arr[22] != null && arr[22] != "null") {
                dTO.setModifiedDate(DBDate.format((Date) arr[22]));
            } else {
                dTO.setModifiedDate(Converters.convertNullToEmpty(arr[22]));
            }
            if (StringUtils.isNotBlank(Converters.convertNullToEmpty(arr[23]))) {
                User user = new CommonLogic().getUserById(Converters.convertNullToEmpty(arr[23]));
                dTO.setCreatedBy(user.getLastName() + "," + user.getFirstName());
            }
            dTO.setViewType(Converters.convertNullToEmpty(arr[25]));
            finalList.add(dTO);
        }
        return finalList;
    }

    public static String deleteView(int viewMasterId) {
        LOGGER.info("Entering deleteView method with viewId " + viewMasterId);
        try {
            AccClosureViewMaster fvm = null;
            if (viewMasterId != 0) {
                fvm = dataSelection.deleteForecastingViewMaster(viewMasterId);
            }
            return fvm.getViewName();
        } catch (Exception e) {
            LOGGER.error(e);
            return StringUtils.EMPTY;
        } finally {
            LOGGER.info("End of deleteView method");
        }
    }

    public boolean isDuplicateReportName(final String reportName) throws SystemException, Exception {
        LOGGER.info("Entering isDuplicateReportName method with reportName " + reportName);
        List input = new ArrayList();
        input.add(reportName);
        List resultList = (List<Object[]>) QueryUtils.executeSelectQuery(input, "reportNameCount");
        Object obj = null;
        if (!resultList.isEmpty()) {
            obj = resultList.get(0);
        }
        int count = obj == null ? 0 : (Integer) obj;
        LOGGER.info("End of isDuplicateReportName with size: " + count);
        return count > Constants.ZERO;
    }

    private List getIndexInput(final DataSelectionDTO dataSelectionDTO, Set<Container.Filter> filters) {
        List list = new ArrayList();
        if (dataSelectionDTO.getReportName() != null && !dataSelectionDTO.getReportName().isEmpty()) {
            list.add(dataSelectionDTO.getReportName().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getDescription() != null && !dataSelectionDTO.getDescription().isEmpty()) {
            list.add(dataSelectionDTO.getDescription().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getMarketType() != null && !dataSelectionDTO.getMarketType().isEmpty() && !"0".equals(dataSelectionDTO.getMarketType())) {
            list.add(dataSelectionDTO.getMarketType().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getCompanySid() != null && !dataSelectionDTO.getCompanySid().isEmpty() && !dataSelectionDTO.getCompanySid().equals("0")) {
            list.add(dataSelectionDTO.getCompanySid().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getProductGroup() != null && !dataSelectionDTO.getProductGroup().isEmpty()) {
            list.add(dataSelectionDTO.getProductGroup().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getDeductionType() != null && !dataSelectionDTO.getDeductionType().isEmpty() && !dataSelectionDTO.getDeductionType().equals("0")) {
            list.add(dataSelectionDTO.getDeductionType().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getCustomerGroup() != null && !dataSelectionDTO.getCustomerGroup().isEmpty()) {
            list.add(dataSelectionDTO.getCustomerGroup().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getItemSid() != null && !dataSelectionDTO.getItemSid().isEmpty() && !dataSelectionDTO.getItemSid().equals("0")) {
            list.add(dataSelectionDTO.getItemSid().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getDeductionSubType() != null && !dataSelectionDTO.getDeductionSubType().isEmpty() && !dataSelectionDTO.getDeductionSubType().equals("0")) {
            list.add(dataSelectionDTO.getDeductionSubType().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getContractSid() != null && !dataSelectionDTO.getContractSid().isEmpty() && !dataSelectionDTO.getContractSid().equals("0")) {
            list.add(dataSelectionDTO.getContractSid().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (dataSelectionDTO.getProductIdentifier() != null && !dataSelectionDTO.getProductIdentifier().isEmpty() && !dataSelectionDTO.getProductIdentifier().equals("0")) {
            list.add(dataSelectionDTO.getProductIdentifier().replace("*", "%"));
        } else {
            list.add("%");
        }
        if (true) {
            list.add("%");
        }
        if (dataSelectionDTO.getFromDate() != null) {
            list.add(" AND ( ACM.FROM_DATE >= '" + DBDate.format(dataSelectionDTO.getFromDate()) + "')");
        } else {
            list.add(" ");
        }
        if (dataSelectionDTO.getToDate() != null) {
            list.add(" AND ( ACM.TO_DATE >= '" + DBDate.format(dataSelectionDTO.getToDate()) + "')");
        } else {
            list.add(" ");
        }
        StringBuilder sql = AbstractFilter.getInstance().getDSFilter(filters);
        if (sql != null) {
            list.add(sql);
        } else {
            list.add(StringUtils.EMPTY);
        }
        if (dataSelectionDTO.isCount()) {
            list.add(StringUtils.EMPTY + dataSelectionDTO.getStartIndex());
            list.add(StringUtils.EMPTY + dataSelectionDTO.getEndIndex());
        }
        return list;
    }
}
