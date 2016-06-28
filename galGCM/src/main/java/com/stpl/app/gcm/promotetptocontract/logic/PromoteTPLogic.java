
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.discount.dao.ContractDetailsDAO;
import com.stpl.app.gcm.discount.dao.DiscountDAO;
import com.stpl.app.gcm.discount.dao.impl.ContractDetailsDaoImpl;
import com.stpl.app.gcm.discount.dao.impl.DiscountDaoImpl;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.gcm.promotetptocontract.dao.PromoteTpDAO;
import com.stpl.app.gcm.promotetptocontract.dao.impl.PromoteTpDAOImpl;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.promotetptocontract.dto.ContractHolderDTO;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.dto.RebatePlanDTO;
import com.stpl.app.gcm.promotetptocontract.lazyload.DropdownListCriteria;
import com.stpl.app.gcm.promotetptocontract.lazyload.LoadDropdownListDAO;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.stpl.app.gcm.transfercontract.util.CommonUtil;
import com.stpl.app.gcm.util.CommonUtils;
import static com.stpl.app.gcm.util.CommonUtils.convertToInteger;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.COMPANY_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ITEM_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PRICE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.REBATE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_TRANSFER_CONTRACT;
import com.stpl.app.gcm.util.Converters;
import static com.stpl.app.gcm.util.Converters.convertNullToEmpty;
import static com.stpl.app.gcm.util.Converters.formatDate;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.ComboBox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author alok.v
 */
public class PromoteTPLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(PromoteTPLogic.class);
    public static PromoteTpDAO promoteTpDAO = new PromoteTpDAOImpl();
    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private final ContractDetailsDAO dao = new ContractDetailsDaoImpl();
    DiscountDAO discountDAO = new DiscountDaoImpl();
    TradingPartnerDAO tpDao = new TradingPartnerDAOImpl();
    QueryUtils queryUtils = new QueryUtils();
    DataQueryLogic dqLogic = new DataQueryLogic();
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd ");
    CommonDao DAO = CommonImpl.getInstance();
    ContractSelectionLogic logic = new ContractSelectionLogic();

    /**
     * Get Company Count
     *
     * @param promoteTpToChDto
     * @param sc
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public int searchCompanyCount(final PromoteTpToChDto promoteTpToChDto, BeanSearchCriteria sc) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List resultList;
        parameters.put("lazyLoadResults", null);
        String compName = StringUtils.EMPTY;
        String compId = StringUtils.EMPTY;
        String compNo = StringUtils.EMPTY;
        String compType = StringUtils.EMPTY;
        if (isValidCriteria(promoteTpToChDto.getCompanyName())) {
            compName = promoteTpToChDto.getCompanyName();
            compName = compName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyName", compName);
        }
        if (isValidCriteria(promoteTpToChDto.getCompanyId())) {
            compId = promoteTpToChDto.getCompanyId();
            compId = compId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyId", compId);
        }

        if (isValidCriteria(promoteTpToChDto.getCompanyNo())) {
            compNo = promoteTpToChDto.getCompanyNo();
            compNo = compNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyNo", compNo);
        }
        if (isValidCriteria(promoteTpToChDto.getCompanyType())) {
            compType = promoteTpToChDto.getCompanyType();
            compType = compType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyType", compType);
        }
        if (isValidCriteria(promoteTpToChDto.getCompanyCategory())) {
            String compCategory = promoteTpToChDto.getCompanyCategory();
            compCategory = compCategory.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyCategory", compCategory);
        }
        if (isValidCriteria(promoteTpToChDto.getTradeClass())) {
            String tradeClas = promoteTpToChDto.getTradeClass();
            tradeClas = tradeClas.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("tradeClass", tradeClas);
        }

        resultList = promoteTpDAO.searchTPCompanies(parameters);
        return Integer.parseInt(String.valueOf(resultList.get(0)));
    }

    /**
     * Valid criteria check
     *
     * @param value
     * @return
     */
    private boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !Constants.NULL.equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Search Company - lazy load search
     *
     * @param promoteTpToChDto
     * @param startIndex
     * @param offset
     * @param sc
     * @param orderByColumns
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public List<PromoteTpToChDto> searchCompaniesLazy(final PromoteTpToChDto promoteTpToChDto, int startIndex, int offset, BeanSearchCriteria sc, List<OrderByColumn> orderByColumns) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List resultList;
        List<PromoteTpToChDto> returnList = null;
        parameters.put("startIndex", startIndex);
        parameters.put("offset", offset);
        parameters.put("lazyLoadResults", "lazyLoadResults");

        if (isValidCriteria(promoteTpToChDto.getCompanyName())) {
            String compName = promoteTpToChDto.getCompanyName();
            compName = compName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyName", compName);
        }
        if (isValidCriteria(promoteTpToChDto.getCompanyId())) {
            String compId = promoteTpToChDto.getCompanyId();
            compId = compId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyId", compId);
        }
        if (isValidCriteria(promoteTpToChDto.getCompanyNo())) {
            String compNo = promoteTpToChDto.getCompanyNo();
            compNo = compNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyNo", compNo);
        }

        if (isValidCriteria(promoteTpToChDto.getCompanyType())) {
            String compType = promoteTpToChDto.getCompanyType();
            compType = compType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyType", compType);
        }
        if (isValidCriteria(promoteTpToChDto.getCompanyCategory())) {
            String compCategory = promoteTpToChDto.getCompanyCategory();
            compCategory = compCategory.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("companyCategory", compCategory);
        }
        if (isValidCriteria(promoteTpToChDto.getTradeClass())) {
            String tradeClas = promoteTpToChDto.getTradeClass();
            tradeClas = tradeClas.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            parameters.put("tradeClass", tradeClas);
        }

        parameters.put(Constants.IS_ORDERED, "false");

        for (Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            String columnId = orderByColumn.getName();
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                parameters.put("orderBy~" + columnId, "asc");
                parameters.put(Constants.IS_ORDERED, Constants.TRUE);
            } else {
                parameters.put("orderBy~" + columnId, "desc");
                parameters.put(Constants.IS_ORDERED, Constants.TRUE);
            }
        }

        resultList = promoteTpDAO.searchTPCompanies(parameters);
        returnList = Converters.searchTPCompany(resultList);
        return returnList;
    }

    public void LazyLoadDdlb(final ComboBox comboBox, String countFlag, String findFlag) {
        LazyContainer containerData = new LazyContainer(HelperDTO.class, new LoadDropdownListDAO(countFlag, findFlag), new DropdownListCriteria());
        comboBox.setPageLength(7);
        comboBox.setContainerDataSource(containerData);
        comboBox.setNullSelectionItemId(ddlbDefaultValue);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        containerData.setMinFilterLength(0);
    }

    public int getDdlbCount(String QueryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public List<HelperDTO> getDdlbList(String QueryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        if (Integer.valueOf(String.valueOf(input.get(1))) == 0) {
            HelperDTO defaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
            resultList.add(defaultValue);
        }
        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
            dto.setDescription(str[1] == null ? Constants.ZEROSTRING : String.valueOf(str[1]));
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * get Current Contract Count
     *
     * @param binderDto
     * @return
     */
    public int getSearchCount(CurrentContractDTO binderDto) {
        List<Object[]> list = null;
        try {
            list = ItemQueries.getItemData(getSearchSelection(binderDto), "searchPromoteTpToChContractCount", null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return getCount(list);

    }

    /**
     * get Count method
     *
     * @param list
     * @return
     */
    private int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    /**
     * Get Current Contract Search Results
     *
     * @param binderDto
     * @return
     */
    public List<CurrentContractDTO> getSearchResults(CurrentContractDTO binderDto) {
        List<Object[]> list = ItemQueries.getItemData(getSearchSelection(binderDto), "searchPromoteTpToChContract", null);
        List<CurrentContractDTO> finalResult = new ArrayList<CurrentContractDTO>();
        for (Object[] str : list) {
            CurrentContractDTO dto = new CurrentContractDTO();
            dto.setContractHolder(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setContractNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setContractName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setMarketType(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setStartDate(str[4] == null ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setEndDate(str[5] == null ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setRebateScheduleNo(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setRebateScheduleName(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setRarCategory(str[8] == null ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setStatus(str[9] == null ? StringUtils.EMPTY : String.valueOf(str[9]));
            dto.setCompanyStartDate(str[10] == null ? StringUtils.EMPTY : String.valueOf(str[10]));
            dto.setCompanyEndDate((Date) str[11]);
            dto.setCfpContSid(str[16] == null ? StringUtils.EMPTY : String.valueOf(str[16]));
            dto.setRsContSid(str[17] == null ? StringUtils.EMPTY : String.valueOf(str[17]));
            dto.setIfpContSid(str[18] == null ? StringUtils.EMPTY : String.valueOf(str[18]));
            dto.setPsContSid(str[19] == null ? StringUtils.EMPTY : String.valueOf(str[19]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    /**
     * get search selection criteria
     *
     * @param binderDto
     * @return
     */
    private List getSearchSelection(CurrentContractDTO binderDto) {
        List<Object> input = new ArrayList();
        if (binderDto.getContractNo() != null && !binderDto.getContractNo().isEmpty()) {
            input.add(binderDto.getContractNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getContractName() != null && !binderDto.getContractName().isEmpty()) {
            input.add(binderDto.getContractName().replace("*", "%"));
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    /**
     * Set Component Info to Component Info Table
     *
     * @param componentSelectionValue
     * @param id
     * @return
     */
    public Map<String, List> getComponentInfo(String componentSelectionValue, String id) {
        Map<String, List> componentInfoMap = new HashMap<String, List>();
        List<Object[]> componentInformationList = promoteTpDAO.getComponentInfo(componentSelectionValue, id);

        if (componentInformationList != null && !componentInformationList.isEmpty()) {
            componentInfoMap.put("FieldData", Arrays.asList(componentInformationList.get(0)));

            componentInformationList.remove(0);

            List<ComponentInfoDTO> componentInfoList = new ArrayList<ComponentInfoDTO>();
            ComponentInfoDTO dto;
            if (componentInformationList != null && !componentInformationList.isEmpty()) {
                for (Object[] componentInformationList1 : componentInformationList) {
                    try {
                        final Object[] obj = (Object[]) componentInformationList1;
                        dto = new ComponentInfoDTO();
                        dto.setItemNo(convertNullToEmpty(obj[0]));
                        dto.setItemName(convertNullToEmpty(obj[1]));
                        dto.setTherapyClass(convertNullToEmpty(obj[2]));
                        dto.setBrand(convertNullToEmpty(obj[3]));
                        dto.setStatus(convertNullToEmpty(obj[4]));
                        dto.setItemStartDate(!String.valueOf(obj[5]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[5].toString(), "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy")) : null);
                        dto.setItemEndDate(!String.valueOf(obj[6]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[6].toString(), "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy")) : null);
                        dto.setRebatePlan(convertNullToEmpty(obj[7]));
                        dto.setFormulaId(convertNullToEmpty(obj[8]));
                        componentInfoList.add(dto);
                    } catch (ParseException ex) {
                        java.util.logging.Logger.getLogger(PromoteTPLogic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            componentInfoMap.put("TableData", componentInfoList);
        }
        return componentInfoMap;
    }

    public ExtTreeContainer<CurrentContractDTO> getLevel1Hierarchy(final String contractId, final ExtTreeContainer<CurrentContractDTO> container, final int start, final int end) throws SystemException {
        LOGGER.info("Entering getLevel1Hierarchy method");
        final List<CurrentContractDTO> contractList = getContractList(contractId, CurrentContractDTO.LEVEL1, start, end);
        container.removeAllItems();
        try {
            for (final Iterator<CurrentContractDTO> iterator = contractList.iterator(); iterator.hasNext();) {
                final CurrentContractDTO contractMember = (CurrentContractDTO) iterator.next();
                container.addBean(contractMember);
                if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel2ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                    container.setChildrenAllowed(contractMember, true);

                } else {
                    container.setChildrenAllowed(contractMember, false);
                }
            }
            LOGGER.info("End of getLevel1Hierarchy method");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return container;
    }

    /**
     * Gets the contract list.
     *
     * @param contractId the contract id
     * @param level the level
     * @return the contract list
     */
    private List<CurrentContractDTO> getContractList(final String contractId, final int level, final int start, final int end) throws SystemException {
        LOGGER.info("Entering getContractList method");

        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(Constants.IndicatorConstants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(Constants.IndicatorConstants.CHAR_ASTERISK.getConstant(), Constants.IndicatorConstants.CHAR_PERCENT.getConstant());
        }
        final List<CurrentContractDTO> contractList = new ArrayList<CurrentContractDTO>();
        // TODO change the limits in the query
        try {
            final List<ContractMaster> contractML = dao.contractMasterDynamicQuery(getProcessedQuery(contract, start, end));

            CurrentContractDTO contractDetails;
            ContractMaster contractMaster;
            for (final Iterator<ContractMaster> iterator = contractML.iterator(); iterator.hasNext();) {
                contractMaster = (ContractMaster) iterator.next();
                contractDetails = new CurrentContractDTO();
                contractDetails.setSystemId(contractMaster.getContractMasterSid());
                contractDetails.setContractName(contractMaster.getContractName());
                contractDetails.setContractId(contractMaster.getContractId());
                contractDetails.setContractNo(contractMaster.getContractNo());
                contractDetails.setCategory(Constants.IndicatorConstants.CONTRACT.getConstant());
                contractDetails.setLevel(level);
                contractDetails.setInternalId(contractMaster.getContractMasterSid());
                contractList.add(contractDetails);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of getContractList method");
        return contractList;
    }

    /**
     * Method used for getProcessedQuery.
     *
     * @param contractId the contract id
     * @param start
     * @param end
     * @return the processed query
     */
    public DynamicQuery getProcessedQuery(final String contractId, final int start, final int end) {
        LOGGER.info("Entering getProcessedQuery method");

        final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
        String contract;
        try {
            if (contractId.trim().equals(StringUtils.EMPTY)) {
                contract = Constants.IndicatorConstants.CHAR_PERCENT.getConstant();
            } else {
                contract = contractId.replace(Constants.IndicatorConstants.CHAR_ASTERISK.getConstant(), Constants.IndicatorConstants.CHAR_PERCENT.getConstant());
            }
            contractQuery.add(RestrictionsFactoryUtil.eq("processStatus", true));
            contractQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, contract));
            contractQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like("inboundStatus", "D")));
            contractQuery.setLimit(start, end);
            LOGGER.info("End of getProcessedQuery method");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return contractQuery;
    }

    /**
     * Checks if is level2 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level2 list avlbl
     */
    private boolean isLevel2ListAvlbl(final int contractSystemId, final String category) throws SystemException {
        LOGGER.info("Entering isLevel2ListAvlbl method");
        boolean available = false;
        try {
            if (!Constants.IndicatorConstants.CFP.getConstant().equals(category) && getCFPQueriedCount(contractSystemId) > Constants.ZERO) {
                available = true;
            } else if (!Constants.IndicatorConstants.IFP.getConstant().equals(category) && getIFPQueriedCount(contractSystemId) > Constants.ZERO) {
                available = true;
            } else if (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) {
                available = true;
            } else if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO) {
                available = true;
            } else {
                available = false;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of isLevel2ListAvlbl method");
        return available;
    }

    public int getCFPQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getCFPQueriedCount method");

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        try {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of getCFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(cfpDynamicQuery);

    }

    public int getIFPQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getIFPQueriedCount method");

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getIFPQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(ifpDynamicQuery);
    }

    /**
     * Gets the ps queried count.
     *
     * @param contractSystemId the contract system id
     * @return the PS queried count
     */
    public int getPSQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getPSQueriedCount method");

        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getPSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(psDynamicQuery);
    }

    public int getRSQueriedCount(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getRSQueriedCount method");

        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        LOGGER.info("End of getRSQueriedCount method");
        return (int) dao.contractMasterDynamicQueryCount(rsDynamicQuery);
    }

    public List<ComponentInfoDTO> getLevelDetails(CurrentContractDTO tableBean) {
        List<ComponentInfoDTO> levelsDetails = new ArrayList<ComponentInfoDTO>();
        try {
            final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            contractQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", tableBean.getInternalId()));
            List<RsContractDetails> contractDetails = discountDAO.getContractDetails(contractQuery);
            levelsDetails = getNewDiscountTabDto(contractDetails);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return levelsDetails;
    }

    private List<ComponentInfoDTO> getNewDiscountTabDto(List<RsContractDetails> contractDetails) {
        List<ComponentInfoDTO> levelsDetails = new ArrayList<ComponentInfoDTO>();
        try {
            if (!contractDetails.isEmpty()) {
                for (RsContractDetails contractDetail : contractDetails) {
                    ComponentInfoDTO discountTabDto = new ComponentInfoDTO();
                    ItemMaster itemMaster = discountDAO.getItemDetails(contractDetail.getItemMasterSid());
                    discountTabDto.setItemNo(itemMaster.getItemNo());
                    discountTabDto.setItemName(itemMaster.getItemName());
                    discountTabDto.setTherapyClass(CommonLogic.getDescriptionFromID(itemMaster.getTherapeuticClass()));
                    discountTabDto.setBrand(String.valueOf(itemMaster.getBrandMasterSid()));
                    discountTabDto.setItemStatus(CommonLogic.getDescriptionFromID(itemMaster.getItemStatus()));
                    discountTabDto.setStartDate(Converters.convertNullToEmpty(itemMaster.getItemStartDate().toString()));
                    discountTabDto.setEndDate(Converters.convertNullToEmpty(itemMaster.getItemEndDate()));
                    levelsDetails.add(discountTabDto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return levelsDetails;
    }

    public ExtTreeContainer<CurrentContractDTO> getLevel2Hierarchy(final CurrentContractDTO parent, final ExtTreeContainer<CurrentContractDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel2Hierarchy method");
        container.removeAllItems();
        container.addBean(parent);
        container.setChildrenAllowed(parent, !Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent.getCategory()));
        CurrentContractDTO contractMember;
        final List<CurrentContractDTO> contractList = getLevel2List(parent, start, end);
        for (final Iterator<CurrentContractDTO> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();
            container.addBean(contractMember);
            if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel3ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }
            container.setParent(contractMember, parent);
        }
        LOGGER.info("End of getLevel2Hierarchy method");
        return container;
    }

    public List<CurrentContractDTO> getLevel2List(final CurrentContractDTO parent1, final int start, final int end) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel2List method");
        List<CurrentContractDTO> level2List;
        if (getCFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getCFPList(parent1, CurrentContractDTO.LEVEL2, start, end);
        } else if (getIFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getIFPList(parent1, null, CurrentContractDTO.LEVEL2, start, end);
        } else if (getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getPSList(parent1, null, null, CurrentContractDTO.LEVEL2, start, end);
        } else if (getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getRSList(parent1, null, null, null, CurrentContractDTO.LEVEL2, start, end);
        } else {
            level2List = new ArrayList<CurrentContractDTO>();
        }
        LOGGER.info("End of getLevel2List method");
        return level2List;
    }

    private List<CurrentContractDTO> getCFPList(final CurrentContractDTO parent1, final int level, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getCFPList method");

        final List<CurrentContractDTO> cfpList = new ArrayList<CurrentContractDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        cfpDynamicQuery.setLimit(start, end);
        final List<CfpContract> cfpMasterList = dao.cfpMasterDynamicQuery(cfpDynamicQuery);

        CurrentContractDTO contractMember;
        CfpContract cfpMaster;
        for (final Iterator<CfpContract> iterator = cfpMasterList.iterator(); iterator.hasNext();) {
            cfpMaster = (CfpContract) iterator.next();
            contractMember = new CurrentContractDTO();
            contractMember.setSystemId(cfpMaster.getContractMasterSid());
            contractMember.setContractName(cfpMaster.getCfpName());

            CfpModel cfpModel = CfpModelLocalServiceUtil.getCfpModel(cfpMaster.getCfpModelSid());
            contractMember.setContractId(cfpModel.getCfpId());
            contractMember.setContractNo(cfpModel.getCfpNo());
            contractMember.setModelSysId(cfpModel.getCfpModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.CFP.getConstant());
            contractMember.setInternalId(cfpMaster.getCfpContractSid());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            cfpList.add(contractMember);

        }
        LOGGER.info("End of getCFPList method");
        return cfpList;
    }

    private List<CurrentContractDTO> getIFPList(final CurrentContractDTO parent1, final CurrentContractDTO parent2, final int level, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getIFPList method");

        final List<CurrentContractDTO> ifpList = new ArrayList<CurrentContractDTO>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.IndicatorConstants.CFP)) {

                if (parent2.getInternalId() == 0) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull("cfpContractSid"));
                } else {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid", String.valueOf(parent2.getInternalId())));
                }

            }
        }
        ifpDynamicQuery.setLimit(start, end);
        final List<IfpContract> ifpMasterList = dao.ifpMasterDynamicQuery(ifpDynamicQuery);
        CurrentContractDTO contractMember;
        IfpContract ifpContract;
        for (final Iterator<IfpContract> iterator = ifpMasterList.iterator(); iterator.hasNext();) {
            ifpContract = (IfpContract) iterator.next();
            contractMember = new CurrentContractDTO();
            contractMember.setSystemId(ifpContract.getContractMasterSid());
            contractMember.setContractName(ifpContract.getIfpName());
            IfpModel ifpModel = IfpModelLocalServiceUtil.getIfpModel(ifpContract.getIfpModelSid());
            contractMember.setContractId(ifpModel.getIfpId());
            contractMember.setContractNo(ifpModel.getIfpNo());
            contractMember.setModelSysId(ifpModel.getIfpModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.IFP.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setInternalId(ifpContract.getIfpContractSid());
            ifpList.add(contractMember);
        }
        LOGGER.info("End of getIFPList method");
        return ifpList;
    }

    /**
     * Gets the ps list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @param parent3 the parent3
     * @param level the level
     * @return the PS list
     */
    private List<CurrentContractDTO> getPSList(final CurrentContractDTO parent1, final CurrentContractDTO parent2, final CurrentContractDTO parent3, final int level, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getPSList method");

        final List<CurrentContractDTO> psList = new ArrayList<CurrentContractDTO>();
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent2.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
        }

        psDynamicQuery.setLimit(start, end);
        final List<PsContract> psMasterList = dao.psMasterDynamicQuery(psDynamicQuery);

        CurrentContractDTO contractMember;

        for (final Iterator<PsContract> iterator = psMasterList.iterator(); iterator.hasNext();) {
            final PsContract psMaster = (PsContract) iterator.next();
            contractMember = new CurrentContractDTO();
            contractMember.setSystemId(psMaster.getContractMasterSid());
            contractMember.setContractName(psMaster.getPsName());
            PsModel psModel = PsModelLocalServiceUtil.getPsModel(psMaster.getPsModelSid());
            contractMember.setContractId(psModel.getPsId());
            contractMember.setContractNo(psModel.getPsNo());
            contractMember.setModelSysId(psModel.getPsModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setInternalId(psMaster.getPsContractSid());
            psList.add(contractMember);
        }
        LOGGER.info("End of getPSList method");
        return psList;
    }

    private List<CurrentContractDTO> getRSList(final CurrentContractDTO parent1, final CurrentContractDTO parent2, final CurrentContractDTO parent3, final CurrentContractDTO parent4, final int level, final int start, final int end)
            throws SystemException, PortalException {
        LOGGER.info("Entering getRSList method");

        final List<CurrentContractDTO> rsList = new ArrayList<CurrentContractDTO>();
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }

            }
            if (parent2.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }

            }
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
        }
        if (parent4 != null) {

            if (parent4.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
        }

        rsDynamicQuery.setLimit(start, end);
        final List<RsContract> rsMasterList = dao.rsMasterDynamicQuery(rsDynamicQuery);

        CurrentContractDTO contractMember;
        RsContract rsMaster;
        for (final Iterator<RsContract> iterator = rsMasterList.iterator(); iterator.hasNext();) {
            rsMaster = (RsContract) iterator.next();
            contractMember = new CurrentContractDTO();
            contractMember.setSystemId(rsMaster.getContractMasterSid());
            contractMember.setContractName(rsMaster.getRsName());
            RsModel rsModel = RsModelLocalServiceUtil.getRsModel(rsMaster.getRsModelSid());
            contractMember.setContractId(rsModel.getRsId());
            contractMember.setContractNo(rsModel.getRsNo());
            contractMember.setModelSysId(rsModel.getRsModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setParent4(parent4);
            contractMember.setInternalId(rsMaster.getRsContractSid());

            rsList.add(contractMember);
        }
        LOGGER.info("End of getRSList method");
        return rsList;
    }

    /**
     * Checks if is level3 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level3 list avlbl
     */
    public boolean isLevel3ListAvlbl(final int contractSystemId, final String category) throws SystemException {

        LOGGER.info("Entering isLevel3ListAvlbl method");
        boolean available;
        if (!Constants.IndicatorConstants.IFP.getConstant().equals(category) && getIFPQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            available = true;
        } else {
            available = false;
        }
        LOGGER.info("End of isLevel3ListAvlbl method");
        return available;
    }

    /**
     * To get Level 3Hierarachy
     *
     * @param parent2
     * @param container
     * @return
     * @throws SystemException
     */
    public ExtTreeContainer<CurrentContractDTO> getLevel3Hierarchy(final CurrentContractDTO parent2, final ExtTreeContainer<CurrentContractDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel3Hierarchy method");
        container.removeAllItems();

        container.addBean(parent2.getParent1());

        container.setChildrenAllowed(parent2.getParent1(), true);
        container.addBean(parent2);
        container.setChildrenAllowed(parent2, true);
        container.setParent(parent2, parent2.getParent1());

        final List<CurrentContractDTO> contractML = getLevel3List(parent2.getParent1(), parent2, start, end);

        CurrentContractDTO contractMember;
        for (final Iterator<CurrentContractDTO> iterator = contractML.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent2);

        }
        LOGGER.info("End of getLevel3Hierarchy method");
        return container;
    }

    /**
     * Gets the level3 list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @return the level3 list
     */
    public List<CurrentContractDTO> getLevel3List(final CurrentContractDTO parent1, final CurrentContractDTO parent2, final int start, final int end) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel3List method");
        List<CurrentContractDTO> level3List;
        if (!Constants.IndicatorConstants.IFP.getConstant().equals(parent2.getCategory()) && getIFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getIFPList(parent1, parent2, ContractsDetailsDto.LEVEL3, start, end);
        } else if (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(parent2.getCategory()) && getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getPSList(parent1, parent2, null, ContractsDetailsDto.LEVEL3, start, end);
        } else if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent2.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getRSList(parent1, parent2, null, null, ContractsDetailsDto.LEVEL3, start, end);
        } else {
            level3List = new ArrayList<CurrentContractDTO>();
        }

        LOGGER.info("End of getLevel3List method");
        return level3List;
    }

    public boolean isLevel4ListAvlbl(final int contractSystemId, final String category) throws SystemException {

        LOGGER.info("Entering isLevel4ListAvlbl method");
        boolean available;
        available = (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(category) && getPSQueriedCount(contractSystemId) > Constants.ZERO) || (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(category) && getRSQueriedCount(contractSystemId) > Constants.ZERO);

        LOGGER.info("End of isLevel4ListAvlbl method");
        return available;
    }

    /**
     * to get Level 4 Hierarchy
     *
     * @param parent3
     * @param container
     * @return
     * @throws SystemException
     */
    public ExtTreeContainer<CurrentContractDTO> getLevel4Hierarchy(final CurrentContractDTO parent3, final ExtTreeContainer<CurrentContractDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel4Hierarchy method");

        container.removeAllItems();
        container.addBean(parent3.getParent1());
        container.setChildrenAllowed(parent3.getParent1(), true);
        container.addBean(parent3.getParent2());
        container.setChildrenAllowed(parent3.getParent2(), true);
        container.setParent(parent3.getParent2(), parent3.getParent1());
        container.addBean(parent3);
        container.setChildrenAllowed(parent3, true);
        container.setParent(parent3, parent3.getParent2());

        final List<CurrentContractDTO> contractList = getLevel4List(parent3.getParent1(), parent3.getParent2(), parent3, start, end);
        CurrentContractDTO contractMember;
        for (final Iterator<CurrentContractDTO> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent3);

        }
        LOGGER.info("End of getLevel4Hierarchy method");
        return container;
    }

    /**
     * Gets the level4 list.
     *
     * @param parent1 the parent1
     * @param parent2 the parent2
     * @param parent3 the parent3
     * @return the level4 list
     */
    public List<CurrentContractDTO> getLevel4List(final CurrentContractDTO parent1, final CurrentContractDTO parent2, final CurrentContractDTO parent3, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel4List method");

        List<CurrentContractDTO> level4List;
        if (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(parent3.getCategory()) && getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level4List = getPSList(parent1, parent2, parent3, CurrentContractDTO.LEVEL4, start, end);
        } else if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level4List = getRSList(parent1, parent2, parent3, null, CurrentContractDTO.LEVEL4, start, end);
        } else {
            level4List = new ArrayList<CurrentContractDTO>();
        }

        LOGGER.info("End of getLevel4List method");
        return level4List;
    }

    /**
     * to get Level 5 Hierarchy
     *
     * @param parent4
     * @param container
     * @return
     * @throws SystemException
     */
    public ExtTreeContainer<CurrentContractDTO> getLevel5Hierarchy(final CurrentContractDTO parent4, final ExtTreeContainer<CurrentContractDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel5Hierarchy method");

        container.removeAllItems();
        container.addBean(parent4.getParent1());
        container.setChildrenAllowed(parent4.getParent1(), true);
        container.addBean(parent4.getParent2());
        container.setChildrenAllowed(parent4.getParent2(), true);
        container.setParent(parent4.getParent2(), parent4.getParent1());
        container.addBean(parent4.getParent3());
        container.setChildrenAllowed(parent4.getParent3(), true);
        container.setParent(parent4.getParent3(), parent4.getParent2());
        container.addBean(parent4);
        container.setChildrenAllowed(parent4, true);
        container.setParent(parent4, parent4.getParent3());

        if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent4.getCategory()) && isLevel5ListAvlbl(parent4.getSystemId())) {
            final List<CurrentContractDTO> contractList = getLevel5List(parent4.getParent1(), parent4.getParent2(), parent4.getParent3(), parent4, start, end);
            CurrentContractDTO contractMember;
            for (final Iterator<CurrentContractDTO> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                container.addBean(contractMember);

                container.setChildrenAllowed(contractMember, false);

                container.setParent(contractMember, parent4);

            }
        }
        LOGGER.info("End of getLevel5Hierarchy method");
        return container;
    }

    /**
     * Gets the level5 list.
     *
     * @param parent1
     * @param parent2
     * @param parent3
     * @param parent4
     * @return
     * @throws SystemException
     */
    public List<CurrentContractDTO> getLevel5List(final CurrentContractDTO parent1, final CurrentContractDTO parent2, final CurrentContractDTO parent3, final CurrentContractDTO parent4, final int start, final int end) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel5List method");

        List<CurrentContractDTO> level5List;
        if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level5List = getRSList(parent1, parent2, parent3, parent4, CurrentContractDTO.LEVEL5, start, end);
        } else {
            level5List = new ArrayList<CurrentContractDTO>();
        }

        LOGGER.info("End of getLevel5List method");
        return level5List;
    }

    /**
     * Checks if is level5 list avlbl.
     *
     * @param contractSystemId the contract system id
     * @return true, if checks if is level5 list avlbl
     */
    private boolean isLevel5ListAvlbl(final int contractSystemId) throws SystemException {
        LOGGER.info("Entering isLevel5ListAvlbl method");
        if (getRSQueriedCount(contractSystemId) > Constants.ZERO) {
            return true;
        }
        LOGGER.info("End of isLevel5ListAvlbl method");
        return false;
    }

    /**
     * Get count of Contract Containing the Selected Trading Partner
     *
     * @param conSelDto
     * @return
     */
    public int getSelectedTPContractCount(CurrentContractDTO conSelDto, String userId, String sessionId) {
        int count = 0;
        String query = getContractQuery(conSelDto, userId, sessionId, 0, 0, false);
        try {
            List list = (List) DAO.executeSelect(query.toString());
            if (!list.isEmpty()) {
                count = list.size();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    /**
     * Formation of Contract Search Query
     *
     * @param conSelDTO
     * @param start
     * @param end
     * @return
     */
    public String buildContractSearchQuery(CurrentContractDTO conSelDTO, String userId, String sessionId, int start, int end) {

        StringBuilder query = new StringBuilder("  ");
        query.append(" SELECT Distinct CM.COMPANY_NAME AS 'Contract Holder',CON.CONTRACT_NO,CON.CONTRACT_NAME,HEL.DESCRIPTION AS 'CONTRACT_TYPE', \n");
        query.append(" CON.START_DATE as 'CONTRACT_START_DATE',CON.END_DATE as 'CONTRACT_END_DATE', \n ");
        query.append(" CFP_CON.CFP_NAME,CFP_MOD.CFP_NO as 'CFP NO',CFP_MOD.CFP_ID,CFP_MOD.CFP_STATUS,CFP_CON.CFP_START_DATE,CFP_CON.CFP_END_DATE, \n ");
        query.append(" IFP_CON.IFP_NAME,IFP_MOD.IFP_NO as 'IFP NO',IFP_MOD.IFP_ID,IFP_MOD.IFP_STATUS,IFP_CON.IFP_START_DATE,IFP_CON.IFP_END_DATE, \n ");
        query.append(" PS_CON.PS_NAME,PS_MOD.PS_NO as 'PS NO',PS_MOD.PS_ID,PS_MOD.PS_STATUS,PS_CON.PS_START_DATE,PS_CON.PS_END_DATE,   \n ");
        query.append(" RS_CON.RS_NAME,RS_MOD.RS_NO as 'RAR NO',RS_CON.RS_ID as 'RAR CATEGORY',RS_MOD.RS_ID,RS_MOD.RS_STATUS,RS_CON.RS_START_DATE,RS_CON.RS_END_DATE,   \n ");
        query.append(" CFP_CON.CFP_CONTRACT_SID,RS_CON.RS_CONTRACT_SID,IFP_CON.IFP_CONTRACT_SID,PS_CON.PS_CONTRACT_SID,CON.CONTRACT_MASTER_SID,  \n ");
        query.append(" TEMP_TABLE.START_DATE as 'COMPANY_START_DATE',TEMP_TABLE.END_DATE as 'COMPANY_END_DATE',TEMP_TABLE.ATTACHED_STATUS,TEMP_TABLE.CHECK_RECORD,(case when  HEL_TAB1.HELPER_TABLE_SID=0 then ' ' else HEL_TAB1.DESCRIPTION end) AS 'STATUS_DESCRIPTION'   \n ");

        query.append(" FROM CONTRACT_MASTER CON   \n ");
        query.append(" LEFT JOIN COMPANY_MASTER  CM ON  CON.CONT_HOLD_COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID    \n ");
        query.append(" JOIN CFP_CONTRACT CFP_CON ON CFP_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID    \n ");
        query.append(" JOIN IFP_CONTRACT IFP_CON ON IFP_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID      \n ");
        query.append(" JOIN RS_CONTRACT RS_CON ON RS_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID     \n ");
        query.append(" JOIN PS_CONTRACT PS_CON ON PS_CON.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID    \n ");
        query.append(" JOIN HELPER_TABLE HEL ON HEL.HELPER_TABLE_SID=CON.CONTRACT_TYPE  \n ");

        if (!conSelDTO.getCompanySystemId().equals(StringUtils.EMPTY) && !conSelDTO.getCompanySystemId().equals(Constants.NULL)) {
            query.append(" JOIN CFP_CONTRACT_DETAILS CFP_CON_DET ON CFP_CON_DET.CFP_CONTRACT_SID = CFP_CON.CFP_CONTRACT_SID \n");
            query.append(" JOIN COMPANY_MASTER CM1 ON CM1.COMPANY_MASTER_SID = CFP_CON_DET.COMPANY_MASTER_SID \n");
        }

        query.append(" INNER JOIN IMTD_ITEM_PRICE_REBATE_DETAILS TEMP_TABLE ON TEMP_TABLE.CFP_MODEL_SID=CFP_CON.CFP_CONTRACT_SID AND \n");
        query.append(" TEMP_TABLE.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID AND TEMP_TABLE.IFP_MODEL_SID=IFP_CON.IFP_CONTRACT_SID  \n");
        query.append(" AND TEMP_TABLE.RS_MODEL_SID=RS_CON.RS_CONTRACT_SID AND TEMP_TABLE.PS_MODEL_SID=PS_CON.PS_CONTRACT_SID \n");
        query.append(" AND TEMP_TABLE.USERS_SID='").append(userId).append("' AND TEMP_TABLE.SESSION_ID='").append(sessionId).append("' \n");

        if (!conSelDTO.getScreenName().equals(StringUtils.EMPTY) && !conSelDTO.getScreenName().equals(Constants.NULL)) {
            query.append(" AND TEMP_TABLE.OPERATION = '").append(conSelDTO.getScreenName()).append("'");
        }

        String udcValue = "1";
        if (conSelDTO.getScreenName().equals(TAB_TRANSFER_CONTRACT.getConstant())) {
            udcValue = "2";
        }
        query.append(" AND TEMP_TABLE.UDC1 <> '").append(udcValue).append("' ");

        query.append("LEFT JOIN (SELECT CFP_MOD.CFP_NO, CFP_MODEL_SID, CFP_MOD.CFP_ID, CFP_ST.DESCRIPTION AS CFP_STATUS FROM CFP_MODEL CFP_MOD \n"
                + "        INNER JOIN HELPER_TABLE CFP_ST ON CFP_ST.HELPER_TABLE_SID = CFP_MOD.CFP_STATUS)CFP_MOD \n"
                + "         ON CFP_MOD.CFP_MODEL_SID = CFP_CON.CFP_MODEL_SID\n"
                + "   LEFT JOIN (SELECT IFP_MOD.IFP_NO, IFP_MODEL_SID, IFP_MOD.IFP_ID, IFP_ST.DESCRIPTION AS IFP_STATUS FROM IFP_MODEL IFP_MOD \n"
                + "        JOIN HELPER_TABLE IFP_ST ON IFP_ST.HELPER_TABLE_SID = IFP_MOD.IFP_STATUS) IFP_MOD \n"
                + "        ON IFP_MOD.IFP_MODEL_SID = IFP_CON.IFP_MODEL_SID\n"
                + "   LEFT JOIN (SELECT RS_MOD.RS_NO, RS_MODEL_SID, RS_MOD.RS_ID, RS_ST.DESCRIPTION AS RS_STATUS FROM RS_MODEL RS_MOD \n"
                + "        JOIN HELPER_TABLE RS_ST ON RS_ST.HELPER_TABLE_SID = RS_MOD.RS_STATUS) RS_MOD \n"
                + "        ON RS_MOD.RS_MODEL_SID = RS_CON.RS_MODEL_SID\n"
                + "   LEFT JOIN (SELECT PS_MOD.PS_NO, PS_MODEL_SID, PS_MOD.PS_ID, PS_ST.DESCRIPTION AS PS_STATUS FROM   PS_MODEL PS_MOD\n"
                + "        JOIN HELPER_TABLE PS_ST ON PS_ST.HELPER_TABLE_SID = PS_MOD.PS_STATUS) PS_MOD \n"
                + "        ON PS_MOD.PS_MODEL_SID = PS_CON.PS_MODEL_SID ");

        query.append(" LEFT JOIN HELPER_TABLE HEL_TAB1 ON HEL_TAB1.HELPER_TABLE_SID=TEMP_TABLE.ATTACHED_STATUS \n");
        query.append(" WHERE CON.RECORD_LOCK_STATUS='false' AND CFP_CON.RECORD_LOCK_STATUS='false'   \n ");
        query.append(" AND IFP_CON.RECORD_LOCK_STATUS='false'  AND PS_CON.RECORD_LOCK_STATUS='false' AND RS_CON.RECORD_LOCK_STATUS='false'  \n");
        query.append(" AND CON.INBOUND_STATUS <> 'D' AND CFP_CON.INBOUND_STATUS<> 'D' AND IFP_CON.INBOUND_STATUS<> 'D' \n");
        query.append(" AND PS_CON.INBOUND_STATUS <> 'D' AND RS_CON.INBOUND_STATUS <> 'D' \n");

        boolean where = false;
        if (!conSelDTO.getContractNo().equals(StringUtils.EMPTY) && !conSelDTO.getContractNo().equals(Constants.NULL)) {
            String contractNo = conSelDTO.getContractNo().replace('*', '%');
            query.append("AND CON.CONTRACT_NO like '" + contractNo + "'  \n ");
            where = true;
        }
        if (!conSelDTO.getCompanySystemId().equals(StringUtils.EMPTY) && !conSelDTO.getCompanySystemId().equals(Constants.NULL)) {
            String companyMasterSid = conSelDTO.getCompanySystemId();
            if (where) {
                query.append("AND CM1.COMPANY_MASTER_SID = '" + companyMasterSid + "'  \n ");
            } else {
                query.append(" AND CM1.COMPANY_MASTER_SID = '" + companyMasterSid + "'   \n ");
                where = true;
            }
        }
        if (!conSelDTO.getContractName().equals(StringUtils.EMPTY) && !conSelDTO.getContractName().equals(Constants.NULL)) {
            String contractName = conSelDTO.getContractName().replace('*', '%');
            if (where) {
                query.append(" AND CON.CONTRACT_NAME like '" + contractName + "'  \n ");
            } else {
                query.append(" AND CON.CONTRACT_NAME like '" + contractName + "'  \n ");
                where = true;
            }
        }
        if (!conSelDTO.getContractHolder().equals(StringUtils.EMPTY) && !conSelDTO.getContractHolder().equals(Constants.NULL)) {
            String contractHolder = conSelDTO.getContractHolder();
            if (where) {
                query.append(" AND CON.CONT_HOLD_COMPANY_MASTER_SID='" + contractHolder + "'  \n ");
            } else {
                query.append(" AND CON.CONT_HOLD_COMPANY_MASTER_SID ='" + contractHolder + "'  \n ");
                where = true;
            }
        }
        if (!conSelDTO.getMarketType().equals(StringUtils.EMPTY) && !conSelDTO.getMarketType().equals(Constants.NULL)) {
            String contractType = conSelDTO.getMarketType();
            if (where) {
                query.append(" AND CON.CONTRACT_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + contractType + "' and HT.LIST_NAME = 'CONTRACT_TYPE' ) \n ");
            } else {
                query.append(" AND CON.CONTRACT_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + contractType + "' and HT.LIST_NAME = 'CONTRACT_TYPE' ) \n ");
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleId().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleId().equals(Constants.NULL)) {
            String rsId = conSelDTO.getRebateScheduleId().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_ID like '" + rsId + "'  \n ");
            } else {
                query.append(" AND RS_CON.RS_ID like '" + rsId + "'  \n ");
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleName().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleName().equals(Constants.NULL)) {
            String rsName = conSelDTO.getRebateScheduleName().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NAME like '" + rsName + "'  \n ");
            } else {
                query.append(" AND RS_CON.RS_NAME like '" + rsName + "'  \n ");
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleType().equals(Constants.NULL)) {
            String rsType = conSelDTO.getRebateScheduleType();
            if (where) {
                query.append(" AND RS_CON.RS_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + rsType + "' and HT.LIST_NAME = 'RS_TYPE' ) \n ");
            } else {
                query.append(" AND RS_CON.RS_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + rsType + "' and HT.LIST_NAME = 'RS_TYPE' ) \n ");
                where = true;
            }
        }

        if (!conSelDTO.getRebateScheduleNo().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleNo().equals(Constants.NULL)) {
            String rsNo = conSelDTO.getRebateScheduleNo().replace('*', '%');
            if (where) {
                query.append(" AND RS_CON.RS_NO like '" + rsNo + "' \n ");
            } else {
                query.append(" AND RS_CON.RS_NO like '" + rsNo + "' \n ");
                where = true;
            }
        }
        if (!conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY)) {
            String rsCategory = conSelDTO.getRebateScheduleCategory();
            if (where) {
                query.append(" AND RS_CON.RS_CATEGORY in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + rsCategory + "' and HT.LIST_NAME = 'RS_CATEGORY' ) \n ");
            } else {
                query.append(" AND RS_CON.RS_CATEGORY in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + rsCategory + "' and HT.LIST_NAME = 'RS_CATEGORY' ) \n ");
                where = true;
            }
        }

        if (!conSelDTO.getRebateProgramType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateProgramType().equals(Constants.NULL)) {
            String rsProgType = conSelDTO.getRebateProgramType();
            if (where) {
                query.append(" AND RS_CON.REBATE_PROGRAM_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + rsProgType + "' and HT.LIST_NAME = 'RS_PROGRAM_TYPE' ) \n ");
            } else {
                query.append(" AND RS_CON.REBATE_PROGRAM_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HT.DESCRIPTION ='" + rsProgType + "' and HT.LIST_NAME = 'RS_PROGRAM_TYPE' ) \n ");
                where = true;
            }
        }

        query.append(" ORDER BY  CFP_CON.CFP_CONTRACT_SID,RS_CON.RS_CONTRACT_SID,IFP_CON.IFP_CONTRACT_SID,PS_CON.PS_CONTRACT_SID   OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY ");
        return query.toString();
    }

    /**
     * Get Contracts containing selected trading parter i.e. Company
     *
     * @param query
     * @return
     */
    public List<CurrentContractDTO> getSelectedTPContractResults(String query) {
        List list = new ArrayList();
        List<CurrentContractDTO> resultList = new ArrayList<CurrentContractDTO>();
        CurrentContractDTO dto = null;
        try {
            list = (List) DAO.executeSelect(query);
            int listsize = list.size();

            if (!list.isEmpty()) {
                for (int i = 0; i < listsize; i++) {
                    Object obj[] = (Object[]) list.get(i);
                    dto = new CurrentContractDTO();
                    dto.setContractHolder(convertNullToEmpty(String.valueOf(obj[0])));
                    dto.setContractNo(convertNullToEmpty(String.valueOf(obj[1])));
                    dto.setContractName(convertNullToEmpty(String.valueOf(obj[2])));
                    dto.setMarketType(convertNullToEmpty(String.valueOf(obj[3])));
                    dto.setStartDate(formatDate(String.valueOf(obj[4])));
                    dto.setEndDate(formatDate(String.valueOf(obj[5])));
                    dto.setRebateScheduleNo(convertNullToEmpty(String.valueOf(obj[6])));
                    dto.setRebateScheduleName(convertNullToEmpty(String.valueOf(obj[7])));
                    dto.setRarCategory(convertNullToEmpty(String.valueOf(obj[8])));
                    dto.setStatus(convertNullToEmpty(String.valueOf(obj[9])));
                    dto.setCompanyStartDate(formatDate(String.valueOf(obj[10])));
                    dto.setCompanyEndDate((Date) obj[11]);
                    dto.setCfpContSid(convertNullToEmpty(String.valueOf(obj[12])));
                    dto.setIfpContSid(convertNullToEmpty(String.valueOf(obj[13])));
                    dto.setPsContSid(convertNullToEmpty(String.valueOf(obj[14])));
                    dto.setRsContSid(convertNullToEmpty(String.valueOf(obj[15])));
                    dto.setContractSid(Integer.parseInt(String.valueOf(obj[16])));
                    dto.setProjectionId(convertNullToEmpty(String.valueOf(obj[17])));
                    resultList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;

    }

    public Map<String, List> getComponentInfo(String componentSelectionValue, String[] id) {
        Map<String, List> componentInfoMap = new HashMap<String, List>();
        List<Object[]> componentInfoList = logic.getComponentInformationData(componentSelectionValue, id, false, false, 0, 0, null);

        if (componentInfoList != null && !componentInfoList.isEmpty()) {
            componentInfoMap.put("FieldData", Arrays.asList(componentInfoList.get(0)));

            componentInfoList.remove(0);

            List<ComponentInfoDTO> componentInfoList2 = new ArrayList<ComponentInfoDTO>();
            ComponentInfoDTO dto;
            if (componentInfoList != null && !componentInfoList.isEmpty()) {
                for (Object[] componentInformationList1 : componentInfoList) {
                    try {
                        final Object[] obj = (Object[]) componentInformationList1;
                        dto = new ComponentInfoDTO();

                        if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));

                        } else if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setItemStartDate(!String.valueOf(obj[4]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[4].toString(), "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy")) : null);
                            dto.setItemEndDate(!String.valueOf(obj[5]).equals(Constants.NULL) ? new Date(convertStringToDate(obj[5].toString(), "yyyy-MM-dd HH:mm:ss.SSS", "MM/dd/yyyy")) : null);
                            dto.setRebatePlan(convertNullToEmpty(obj[10]));
                            dto.setFormulaId(convertNullToEmpty(obj[7]));
                        }

                        componentInfoList2.add(dto);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
            componentInfoMap.put("TableData", componentInfoList2);
        }
        return componentInfoMap;
    }

    /**
     * Submit Button Logic
     *
     * @param contractType
     * @param userId
     * @param sessionId
     * @param flag
     */
    public void updateSubmitFlag(String contractType, String userId, String sessionId, boolean flag,CurrentContractDTO dto) {
        promoteTpDAO.updateSubmitFlag(contractType, userId, sessionId, flag,dto);
    }

    /**
     * Mass Update Logic
     *
     * @param fieldName
     * @param userId
     * @param sessionId
     * @param value
     * @return
     */
    public boolean massUpdate(String fieldName, String userId, String sessionId, Object value) {
        boolean updateStatus = false;

        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE IMTD_ITEM_PRICE_REBATE_DETAILS SET   ");
        if (fieldName.equals("Status")) {

            query.append(" ATTACHED_STATUS='" + (Integer) value + "' ");
        } else if (fieldName.equals("Company Start Date")) {

            query.append(" START_DATE='" + dateFormater.format((Date) value) + "' ");
        } else if (fieldName.equals("Company End Date")) {
            query.append(" END_DATE='" + dateFormater.format((Date) value) + "' ");
        }
        query.append("WHERE CHECK_RECORD='1'");

        query.append("AND USERS_SID='" + userId + "'  ");
        query.append("AND SESSION_ID='" + sessionId + "'  ");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return updateStatus;
    }

    public int getRebateScheduleCount(ComponentInfoDTO newDiscountTabDto) throws Exception {
        List results = new ArrayList();
        int count = 0;
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            String searchField = newDiscountTabDto.getSearchField();
            String searchFieldValue = newDiscountTabDto.getSearchFieldValue();
            if (searchField.equals("RS ID") || searchField.equals("RS No") || searchField.equals("RS Name") || searchField.equals("RS Status") || searchField.equals("RS Type")) {
                searchFieldValue = searchFieldValue.replaceAll("\\*", "%");
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "select count(*) from dbo.RS_MODEL WHERE " + composedValue1 + " like '" + composedValue + "'";
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (null != list && list.size() > 0) {
                    Object obj = list.get(0);
                    count = Integer.valueOf(String.valueOf(obj));
                    return count;
                }
            }
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            String searchField = newDiscountTabDto.getSearchField();
            String searchFieldValue = newDiscountTabDto.getSearchFieldValue();
            if (searchField.equals(Constants.IFP_ID) || searchField.equals(Constants.IFP_NO) || searchField.equals(Constants.IfpNAME) || searchField.equals(Constants.IFP_STATUS) || searchField.equals(Constants.IFPTYPE)) {
                searchFieldValue = searchFieldValue.replaceAll("\\*", "%");
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "select count(*) from dbo.IFP_MODEL WHERE " + composedValue1 + " like '" + composedValue + "'";
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (null != list && list.size() > 0) {
                    Object obj = list.get(0);
                    count = Integer.valueOf(String.valueOf(obj));
                    return count;
                }
            }
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            String searchField = newDiscountTabDto.getSearchField();
            String searchFieldValue = newDiscountTabDto.getSearchFieldValue();
            if (searchField.equals("PS ID") || searchField.equals("PS No") || searchField.equals("PS Name") || searchField.equals("PS Status") || searchField.equals("PS Type")) {
                searchFieldValue = searchFieldValue.replaceAll("\\*", "%");
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "select count(*) from dbo.PS_MODEL WHERE " + composedValue1 + " like '" + composedValue + "'";
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (null != list && list.size() > 0) {
                    Object obj = list.get(0);
                    count = Integer.valueOf(String.valueOf(obj));
                    return count;
                }
            }
        }
        return results.size();
    }

    public boolean getNull(String value) {
        boolean check = false;
        if (!StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value) && value != null) {
            check = true;
        }
        return check;
    }

    public List<ComponentInfoDTO> getRebateSchedule(ComponentInfoDTO newDiscountTabDto) throws Exception {
        List<ComponentInfoDTO> searchList = new ArrayList<ComponentInfoDTO>();
        String searchValue = Converters.convertNullToEmpty(newDiscountTabDto.getSearchFieldValue());
        if (searchValue.trim().equals(StringUtils.EMPTY)) {

            searchValue = Constants.IndicatorConstants.CHAR_PERCENT.getConstant();
        } else {
            searchValue = searchValue.replace(Constants.IndicatorConstants.CHAR_ASTERISK.getConstant(), Constants.IndicatorConstants.CHAR_PERCENT.getConstant());
        }
        List results = new ArrayList();
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            String query = queryUtils.getTpRebateSearchQuery(newDiscountTabDto, false, false);
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            String searchField = newDiscountTabDto.getSearchField();
            String searchFieldValue = newDiscountTabDto.getSearchFieldValue();
            if (searchField.equals(Constants.IFP_ID) || searchField.equals(Constants.IFP_NO) || searchField.equals(Constants.IfpNAME) || searchField.equals(Constants.IFP_STATUS) || searchField.equals(Constants.IFPTYPE)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "select IFP.IFP_MODEL_SID,IFP.IFP_ID,IFP.IFP_NO,IFP.IFP_NAME,HT.DESCRIPTION AS STATUS,IFP.IFP_START_DATE,IFP.IFP_END_DATE,HT1.DESCRIPTION AS TYPE from dbo.IFP_MODEL IFP\n"
                        + " LEFT JOIN dbo.HELPER_TABLE HT ON IFP.IFP_STATUS=HT.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT1 ON IFP.IFP_TYPE=HT1.HELPER_TABLE_SID WHERE IFP." + composedValue1 + " like '" + composedValue + "'";
                results = discountDAO.getRebates(query);
            }
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            String query = queryUtils.getTpRebateSearchQuery(newDiscountTabDto, false, true);
            results = discountDAO.getRebates(query);
        }
        searchList = getTpCustomisedDto(results, newDiscountTabDto);
        return searchList;
    }

    private List<ComponentInfoDTO> getTpCustomisedDto(List results, ComponentInfoDTO newDiscountTabDto) throws Exception {

        List<ComponentInfoDTO> searchList = new ArrayList<ComponentInfoDTO>();
        int size = results.size();
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ComponentInfoDTO tabDto = new ComponentInfoDTO();
                tabDto.setRsSid(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                tabDto.setStartDate(formatDate(String.valueOf(arr[5])));
                tabDto.setEndDate(formatDate(String.valueOf(arr[6])));
                tabDto.setIfpSystemId(Converters.convertNullToEmpty(arr[7]));
                tabDto.setIfpName(Converters.convertNullToEmpty(arr[8]));
                tabDto.setFrequency(Converters.convertNullToEmpty(arr[9]));
                tabDto.setRarType(Converters.convertNullToEmpty(arr[10]));
                tabDto.setRsType(Converters.convertNullToEmpty(arr[10]));
                tabDto.setBasis(Converters.convertNullToEmpty(StringUtils.EMPTY));
                tabDto.setCategory(Converters.convertNullToEmpty(arr[11]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ComponentInfoDTO tabDto = new ComponentInfoDTO();
                tabDto.setPsSid(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                tabDto.setStartDate(formatDate(String.valueOf(arr[5])));
                tabDto.setEndDate(formatDate(String.valueOf(arr[6])));
                tabDto.setIfpName(Converters.convertNullToEmpty(arr[7]));
                tabDto.setType(Converters.convertNullToEmpty(arr[8]));
                tabDto.setPsCategory(Converters.convertNullToEmpty(arr[9]));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.CFP.toString())) {
            for (Iterator it = results.iterator(); it.hasNext();) {
                CfpModel results1 = (CfpModel) it.next();
                ComponentInfoDTO tabDto = new ComponentInfoDTO();
                tabDto.setCfpId(Integer.parseInt(Converters.convertNullToEmpty(results1.getCfpModelSid())));
                tabDto.setId(Converters.convertNullToEmpty(results1.getCfpId()));
                tabDto.setNumber(Converters.convertNullToEmpty(results1.getCfpNo()));
                tabDto.setName(Converters.convertNullToEmpty(results1.getCfpName()));
                tabDto.setStatus(Converters.convertNullToEmpty(CommonLogic.getDescriptionFromID(results1.getCfpStatus())));
                tabDto.setStartDate(formatDate(String.valueOf(results1.getCfpStartDate())));
                tabDto.setEndDate(formatDate(String.valueOf(results1.getCfpEndDate())));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(Converters.convertNullToEmpty(results1.getCfpModelSid()))));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            for (int i = 0; i < results.size(); i++) {

                Object arr[] = (Object[]) results.get(i);
                ComponentInfoDTO tabDto = new ComponentInfoDTO();
                tabDto.setIfpSystemId(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                tabDto.setStartDate(formatDate(String.valueOf(arr[5])));
                tabDto.setEndDate(formatDate(String.valueOf(arr[6])));
                tabDto.setType(Converters.convertNullToEmpty(arr[4]));
                searchList.add(tabDto);

            }
        }

        return searchList;
    }

    public int getItemsFromRsCount(ComponentInfoDTO newDiscountTabDto) throws Exception {
        int count = queryUtils.getTpItemsFromRsCount(newDiscountTabDto);
        return count;
    }

    public List<ComponentInfoDTO> getItemsFromRs(ComponentInfoDTO newDiscountTabDto) throws Exception {
        List<ComponentInfoDTO> searchList = new ArrayList<ComponentInfoDTO>();
        String component = newDiscountTabDto.getComponentValue();
        String query = queryUtils.getTpItemsFromRs(newDiscountTabDto, false);
        List results = discountDAO.getRebates(query);
        searchList = getItemDetailsFromIfp(results, component);
        return searchList;
    }

    private List<ComponentInfoDTO> getItemDetailsFromIfp(List results, String component) {
        List<ComponentInfoDTO> searchList = new ArrayList<ComponentInfoDTO>();
        int size = results.size();

        try {
            if (component.equals("Item Family Plan")) {
                for (int i = 0; i < size; i++) {
                    Object arr[] = (Object[]) results.get(i);
                    ComponentInfoDTO tabDto = new ComponentInfoDTO();
                    tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                    tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                    tabDto.setBrand(Converters.convertNullToEmpty(arr[2]));
                    tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                    tabDto.setStartDate(formatDate(String.valueOf(arr[4])));
                    tabDto.setEndDate(formatDate(String.valueOf(arr[5])));
                    searchList.add(tabDto);
                }
            } else if (component.equals(Constants.PRICE_SCHEDULE)) {
                for (int i = 0; i < size; i++) {
                    Object arr[] = (Object[]) results.get(i);
                    ComponentInfoDTO tabDto = new ComponentInfoDTO();
                    tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                    tabDto.setMaxIncrementalChange(Converters.convertNullToEmpty(arr[1]));
                    tabDto.setPriceTolerance(Converters.convertNullToEmpty(arr[2]));
                    tabDto.setResetEligible(Converters.convertNullToEmpty(arr[3]));
                    tabDto.setResetType(Converters.convertNullToEmpty(arr[4]));
                    tabDto.setResetDate(formatDate(String.valueOf(arr[5])));
                    tabDto.setResetInterval(Converters.convertNullToEmpty(arr[6]));
                    tabDto.setResetFrequency(Converters.convertNullToEmpty(arr[7]));
                    searchList.add(tabDto);
                }
            } else if (component.equals(Constants.REBATE_SCHEDULE)) {
                for (int i = 0; i < size; i++) {
                    Object arr[] = (Object[]) results.get(i);
                    ComponentInfoDTO tabDto = new ComponentInfoDTO();
                    tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                    tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                    tabDto.setBrand(Converters.convertNullToEmpty(arr[2]));
                    tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                    tabDto.setStartDate(formatDate(String.valueOf(arr[4])));
                    tabDto.setEndDate(formatDate(String.valueOf(arr[5])));
                    tabDto.setFormulaType(Converters.convertNullToEmpty(arr[6]));
                    tabDto.setFormulaId(Converters.convertNullToEmpty(arr[7]));
                    tabDto.setFormulaName(Converters.convertNullToEmpty(arr[8]));
                    tabDto.setRebatePlanId(Converters.convertNullToEmpty(arr[9]));
                    tabDto.setRebatePlanName(Converters.convertNullToEmpty(arr[10]));
                    tabDto.setRebateAmount(Converters.convertNullToEmpty(arr[11]));
                    tabDto.setBundleNo(Converters.convertNullToEmpty(arr[12]));
                    searchList.add(tabDto);
                }
            }
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return searchList;

    }

    public ExtTreeContainer<ComponentInfoDTO> getLevel1HierarchyForContDashboard(final String contractId, final ExtTreeContainer<ComponentInfoDTO> container, final int start, final int end) throws SystemException {
        LOGGER.info("Entering getLevel1Hierarchy method");
        final List<ComponentInfoDTO> contractList = getContractListForExisting(contractId, ComponentInfoDTO.LEVEL1, start, end);
        container.removeAllItems();
        try {
            for (final Iterator<ComponentInfoDTO> iterator = contractList.iterator(); iterator.hasNext();) {
                final ComponentInfoDTO contractMember = (ComponentInfoDTO) iterator.next();
                container.addBean(contractMember);
                if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel2ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                    container.setChildrenAllowed(contractMember, true);

                } else {
                    container.setChildrenAllowed(contractMember, false);
                }
            }
            LOGGER.info("End of getLevel1Hierarchy method");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return container;
    }

    private List<ComponentInfoDTO> getContractListForExisting(final String contractId, final int level, final int start, final int end) throws SystemException {
        LOGGER.info("Entering getContractList method");

        String contract;
        if (contractId.trim().equals(StringUtils.EMPTY)) {
            contract = String.valueOf(Constants.IndicatorConstants.CHAR_PERCENT);
        } else {
            contract = contractId.replace(Constants.IndicatorConstants.CHAR_ASTERISK.getConstant(), Constants.IndicatorConstants.CHAR_PERCENT.getConstant());
        }
        final List<ComponentInfoDTO> contractList = new ArrayList<ComponentInfoDTO>();
        // TODO change the limits in the query
        try {
            final List<ContractMaster> contractML = dao.contractMasterDynamicQuery(getProcessedQuery(contract, start, end));

            ComponentInfoDTO contractDetails;
            ContractMaster contractMaster;
            for (final Iterator<ContractMaster> iterator = contractML.iterator(); iterator.hasNext();) {
                contractMaster = (ContractMaster) iterator.next();
                contractDetails = new ComponentInfoDTO();
                contractDetails.setSystemId(contractMaster.getContractMasterSid());
                contractDetails.setContractName(contractMaster.getContractName());
                contractDetails.setContractId(contractMaster.getContractId());
                contractDetails.setContractNo(contractMaster.getContractNo());
                contractDetails.setCategory(Constants.IndicatorConstants.CONTRACT.getConstant());
                contractDetails.setLevel(level);
                contractDetails.setInternalId(contractMaster.getContractMasterSid());
                contractList.add(contractDetails);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of getContractList method");
        return contractList;
    }

    public ExtTreeContainer<ComponentInfoDTO> getContDashboardLevel2Hierarchy(final ComponentInfoDTO parent, final ExtTreeContainer<ComponentInfoDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel2Hierarchy method");
        container.removeAllItems();
        container.addBean(parent);
        container.setChildrenAllowed(parent, !Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent.getCategory()));
        ComponentInfoDTO contractMember;
        final List<ComponentInfoDTO> contractList = getDashboardLevel2List(parent, start, end);
        for (final Iterator<ComponentInfoDTO> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();
            container.addBean(contractMember);
            if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel3ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }
            container.setParent(contractMember, parent);
        }
        LOGGER.info("End of getLevel2Hierarchy method");
        return container;
    }

    public ExtTreeContainer<ComponentInfoDTO> getContDashboardLevel3Hierarchy(final ComponentInfoDTO parent2, final ExtTreeContainer<ComponentInfoDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel3Hierarchy method");
        container.removeAllItems();

        container.addBean(parent2.getParent1());

        container.setChildrenAllowed(parent2.getParent1(), true);
        container.addBean(parent2);
        container.setChildrenAllowed(parent2, true);
        container.setParent(parent2, parent2.getParent1());

        final List<ComponentInfoDTO> contractML = getDashboardLevel3List(parent2.getParent1(), parent2, start, end);

        ComponentInfoDTO contractMember;
        for (final Iterator<ComponentInfoDTO> iterator = contractML.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent2);

        }
        LOGGER.info("End of getLevel3Hierarchy method");
        return container;
    }

    public List<ComponentInfoDTO> getDashboardLevel2List(final ComponentInfoDTO parent1, final int start, final int end) throws SystemException, PortalException {

        LOGGER.info("Entering getDashboardLevel2List method");
        List<ComponentInfoDTO> level2List;
        if (getCFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getDashboardCFPList(parent1, ComponentInfoDTO.LEVEL2, start, end);
        } else if (getIFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getDashboardIFPList(parent1, null, ComponentInfoDTO.LEVEL2, start, end);
        } else if (getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getDashboardPSList(parent1, null, null, ComponentInfoDTO.LEVEL2, start, end);
        } else if (getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level2List = getDashboardRSList(parent1, null, null, null, ComponentInfoDTO.LEVEL2, start, end);
        } else {
            level2List = new ArrayList<ComponentInfoDTO>();
        }
        LOGGER.info("End of getDashboardLevel2List method");
        return level2List;
    }

    private List<ComponentInfoDTO> getDashboardCFPList(final ComponentInfoDTO parent1, final int level, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getCFPList method");

        final List<ComponentInfoDTO> cfpList = new ArrayList<ComponentInfoDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        cfpDynamicQuery.setLimit(start, end);
        final List<CfpContract> cfpMasterList = dao.cfpMasterDynamicQuery(cfpDynamicQuery);

        ComponentInfoDTO contractMember;
        CfpContract cfpMaster;
        for (final Iterator<CfpContract> iterator = cfpMasterList.iterator(); iterator.hasNext();) {
            cfpMaster = (CfpContract) iterator.next();
            contractMember = new ComponentInfoDTO();
            contractMember.setSystemId(cfpMaster.getContractMasterSid());
            contractMember.setContractName(cfpMaster.getCfpName());

            CfpModel cfpModel = CfpModelLocalServiceUtil.getCfpModel(cfpMaster.getCfpModelSid());
            contractMember.setContractId(cfpModel.getCfpId());
            contractMember.setContractNo(cfpModel.getCfpNo());
            contractMember.setModelSysId(cfpModel.getCfpModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.CFP.getConstant());
            contractMember.setInternalId(cfpMaster.getCfpContractSid());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            cfpList.add(contractMember);

        }
        LOGGER.info("End of getCFPList method");
        return cfpList;
    }

    private List<ComponentInfoDTO> getDashboardIFPList(final ComponentInfoDTO parent1, final ComponentInfoDTO parent2, final int level, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getIFPList method");

        final List<ComponentInfoDTO> ifpList = new ArrayList<ComponentInfoDTO>();
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.IndicatorConstants.CFP)) {

                if (parent2.getInternalId() == 0) {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        }
        ifpDynamicQuery.setLimit(start, end);
        final List<IfpContract> ifpMasterList = dao.ifpMasterDynamicQuery(ifpDynamicQuery);
        ComponentInfoDTO contractMember;
        IfpContract ifpContract;
        for (final Iterator<IfpContract> iterator = ifpMasterList.iterator(); iterator.hasNext();) {
            ifpContract = (IfpContract) iterator.next();
            contractMember = new ComponentInfoDTO();
            contractMember.setSystemId(ifpContract.getContractMasterSid());
            contractMember.setContractName(ifpContract.getIfpName());
            IfpModel ifpModel = IfpModelLocalServiceUtil.getIfpModel(ifpContract.getIfpModelSid());
            contractMember.setContractId(ifpModel.getIfpId());
            contractMember.setContractNo(ifpModel.getIfpNo());
            contractMember.setModelSysId(ifpModel.getIfpModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.IFP.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setInternalId(ifpContract.getIfpContractSid());
            ifpList.add(contractMember);
        }
        LOGGER.info("End of getIFPList method");
        return ifpList;
    }

    private List<ComponentInfoDTO> getDashboardPSList(final ComponentInfoDTO parent1, final ComponentInfoDTO parent2, final ComponentInfoDTO parent3, final int level, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getPSList method");

        final List<ComponentInfoDTO> psList = new ArrayList<ComponentInfoDTO>();
        final DynamicQuery psDynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class);
        psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        psDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent2.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent2.getInternalId())));
                }

            }
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    psDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
                } else {
                    psDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_CONTRACT_SID, String.valueOf(parent3.getInternalId())));
                }

            }
        }

        psDynamicQuery.setLimit(start, end);
        final List<PsContract> psMasterList = dao.psMasterDynamicQuery(psDynamicQuery);

        ComponentInfoDTO contractMember;

        for (final Iterator<PsContract> iterator = psMasterList.iterator(); iterator.hasNext();) {
            final PsContract psMaster = (PsContract) iterator.next();
            contractMember = new ComponentInfoDTO();
            contractMember.setSystemId(psMaster.getContractMasterSid());
            contractMember.setContractName(psMaster.getPsName());
            PsModel psModel = PsModelLocalServiceUtil.getPsModel(psMaster.getPsModelSid());
            contractMember.setContractId(psModel.getPsId());
            contractMember.setContractNo(psModel.getPsNo());
            contractMember.setModelSysId(psModel.getPsModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setInternalId(psMaster.getPsContractSid());
            psList.add(contractMember);
        }
        LOGGER.info("End of getPSList method");
        return psList;
    }

    private List<ComponentInfoDTO> getDashboardRSList(final ComponentInfoDTO parent1, final ComponentInfoDTO parent2, final ComponentInfoDTO parent3, final ComponentInfoDTO parent4, final int level, final int start, final int end)
            throws SystemException, PortalException {
        LOGGER.info("Entering getRSList method");

        final List<ComponentInfoDTO> rsList = new ArrayList<ComponentInfoDTO>();
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CONTRACT_MASTER_SID.getConstant(), parent1.getSystemId()));
        rsDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        if (parent2 != null) {

            if (parent2.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }
            }
            if (parent2.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }

            }
            if (parent2.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent2.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent2.getInternalId())));
                }

            }
        }
        if (parent3 != null) {

            if (parent3.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
            if (parent3.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent3.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent3.getInternalId())));
                }

            }
        }
        if (parent4 != null) {

            if (parent4.getCategory().equals(Constants.IndicatorConstants.CFP.getConstant())) {
                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.CFP_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(Constants.IndicatorConstants.IFP.getConstant())) {
                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.IFP_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
            if (parent4.getCategory().equals(Constants.IndicatorConstants.PS_VALUE.getConstant())) {

                if (parent4.getInternalId() == 0) {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.isNull(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant()));
                } else {
                    rsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.IndicatorConstants.PS_CONTRACT_SID.getConstant(), String.valueOf(parent4.getInternalId())));
                }

            }
        }

        rsDynamicQuery.setLimit(start, end);
        final List<RsContract> rsMasterList = dao.rsMasterDynamicQuery(rsDynamicQuery);

        ComponentInfoDTO contractMember;
        RsContract rsMaster;
        for (final Iterator<RsContract> iterator = rsMasterList.iterator(); iterator.hasNext();) {
            rsMaster = (RsContract) iterator.next();
            contractMember = new ComponentInfoDTO();
            contractMember.setSystemId(rsMaster.getContractMasterSid());
            contractMember.setContractName(rsMaster.getRsName());
            RsModel rsModel = RsModelLocalServiceUtil.getRsModel(rsMaster.getRsModelSid());
            contractMember.setContractId(rsModel.getRsId());
            contractMember.setContractNo(rsModel.getRsNo());
            contractMember.setModelSysId(rsModel.getRsModelSid());
            contractMember.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
            contractMember.setLevel(level);
            contractMember.setParent1(parent1);
            contractMember.setParent2(parent2);
            contractMember.setParent3(parent3);
            contractMember.setParent4(parent4);
            contractMember.setInternalId(rsMaster.getRsContractSid());

            rsList.add(contractMember);
        }
        LOGGER.info("End of getRSList method");
        return rsList;
    }

    public List<ComponentInfoDTO> getDashboardLevel3List(final ComponentInfoDTO parent1, final ComponentInfoDTO parent2, final int start, final int end) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel3List method");
        List<ComponentInfoDTO> level3List;
        if (!Constants.IndicatorConstants.IFP.getConstant().equals(parent2.getCategory()) && getIFPQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getDashboardIFPList(parent1, parent2, ComponentInfoDTO.LEVEL3, start, end);
        } else if (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(parent2.getCategory()) && getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getDashboardPSList(parent1, parent2, null, ComponentInfoDTO.LEVEL3, start, end);
        } else if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent2.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level3List = getDashboardRSList(parent1, parent2, null, null, ComponentInfoDTO.LEVEL3, start, end);
        } else {
            level3List = new ArrayList<ComponentInfoDTO>();
        }

        LOGGER.info("End of getLevel3List method");
        return level3List;
    }

    public ExtTreeContainer<ComponentInfoDTO> getContDashboardLevel4Hierarchy(final ComponentInfoDTO parent3, final ExtTreeContainer<ComponentInfoDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getcontDashboardLevel4Hierarchy method");

        container.removeAllItems();
        container.addBean(parent3.getParent1());
        container.setChildrenAllowed(parent3.getParent1(), true);
        container.addBean(parent3.getParent2());
        container.setChildrenAllowed(parent3.getParent2(), true);
        container.setParent(parent3.getParent2(), parent3.getParent1());
        container.addBean(parent3);
        container.setChildrenAllowed(parent3, true);
        container.setParent(parent3, parent3.getParent2());

        final List<ComponentInfoDTO> contractList = getDashboardLevel4List(parent3.getParent1(), parent3.getParent2(), parent3, start, end);
        ComponentInfoDTO contractMember;
        for (final Iterator<ComponentInfoDTO> iterator = contractList.iterator(); iterator.hasNext();) {
            contractMember = iterator.next();

            container.addBean(contractMember);

            if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(contractMember.getCategory()) && isLevel4ListAvlbl(contractMember.getSystemId(), contractMember.getCategory())) {
                container.setChildrenAllowed(contractMember, true);
            } else {
                container.setChildrenAllowed(contractMember, false);
            }

            container.setParent(contractMember, parent3);

        }
        LOGGER.info("End of getLevel4Hierarchy method");
        return container;
    }

    public List<ComponentInfoDTO> getDashboardLevel4List(final ComponentInfoDTO parent1, final ComponentInfoDTO parent2, final ComponentInfoDTO parent3, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel4List method");

        List<ComponentInfoDTO> level4List;
        if (!Constants.IndicatorConstants.PS_VALUE.getConstant().equals(parent3.getCategory()) && getPSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level4List = getDashboardPSList(parent1, parent2, parent3, CurrentContractDTO.LEVEL4, start, end);
        } else if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level4List = getDashboardRSList(parent1, parent2, parent3, null, CurrentContractDTO.LEVEL4, start, end);
        } else {
            level4List = new ArrayList<ComponentInfoDTO>();
        }

        LOGGER.info("End of getLevel4List method");
        return level4List;
    }

    public ExtTreeContainer<ComponentInfoDTO> getContDashboardLevel5Hierarchy(final ComponentInfoDTO parent4, final ExtTreeContainer<ComponentInfoDTO> container, final int start, final int end) throws SystemException, PortalException {
        LOGGER.info("Entering getLevel5Hierarchy method");

        container.removeAllItems();
        container.addBean(parent4.getParent1());
        container.setChildrenAllowed(parent4.getParent1(), true);
        container.addBean(parent4.getParent2());
        container.setChildrenAllowed(parent4.getParent2(), true);
        container.setParent(parent4.getParent2(), parent4.getParent1());
        container.addBean(parent4.getParent3());
        container.setChildrenAllowed(parent4.getParent3(), true);
        container.setParent(parent4.getParent3(), parent4.getParent2());
        container.addBean(parent4);
        container.setChildrenAllowed(parent4, true);
        container.setParent(parent4, parent4.getParent3());

        if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent4.getCategory()) && isLevel5ListAvlbl(parent4.getSystemId())) {
            final List<ComponentInfoDTO> contractList = getDashboardLevel5List(parent4.getParent1(), parent4.getParent2(), parent4.getParent3(), parent4, start, end);
            ComponentInfoDTO contractMember;
            for (final Iterator<ComponentInfoDTO> iterator = contractList.iterator(); iterator.hasNext();) {
                contractMember = iterator.next();
                container.addBean(contractMember);

                container.setChildrenAllowed(contractMember, false);

                container.setParent(contractMember, parent4);

            }
        }
        LOGGER.info("End of getLevel5Hierarchy method");
        return container;
    }

    public List<ComponentInfoDTO> getDashboardLevel5List(final ComponentInfoDTO parent1, final ComponentInfoDTO parent2, final ComponentInfoDTO parent3, final ComponentInfoDTO parent4, final int start, final int end) throws SystemException, PortalException {

        LOGGER.info("Entering getLevel5List method");

        List<ComponentInfoDTO> level5List;
        if (!Constants.IndicatorConstants.RS_VALUE.getConstant().equals(parent3.getCategory()) && getRSQueriedCount(parent1.getSystemId()) > Constants.ZERO) {
            level5List = getDashboardRSList(parent1, parent2, parent3, parent4, ComponentInfoDTO.LEVEL5, start, end);
        } else {
            level5List = new ArrayList<ComponentInfoDTO>();
        }

        LOGGER.info("End of getLevel5List method");
        return level5List;
    }

    public List<ComponentInfoDTO> getLevelContDashboardDetails(ComponentInfoDTO tableBean) {
        List<ComponentInfoDTO> levelsDetails = new ArrayList<ComponentInfoDTO>();
        try {
            final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            contractQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", tableBean.getInternalId()));
            List<RsContractDetails> contractDetails = discountDAO.getContractDetails(contractQuery);
            levelsDetails = getNewDiscountTabDto(contractDetails);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return levelsDetails;
    }

    public List<ComponentInfoDTO> getFromCfpCD(Object parent) {
        List<ComponentInfoDTO> retList = new ArrayList<ComponentInfoDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ComponentInfoDTO) {
            inputMap.put("?SID?", String.valueOf(((ComponentInfoDTO) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.cfpFromCD"));
        try {
            for (Object[] temp : resList) {
                ComponentInfoDTO tempDto = new ComponentInfoDTO();
                int j = -1;
                tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                tempDto.setStartDate(formatDate(String.valueOf(temp[++j])));
                tempDto.setEndDate(formatDate(String.valueOf(temp[++j])));
                tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[++j])));
                retList.add(tempDto);
            }
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }

        return retList;
    }

    public List<ContractHolderDTO> getContractHolder(String contractHolderId, String contractHolderNo, String contractHolderName,
            String contractHolderStatus, String contractHolderType, List<String> companySids) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        contractHolderId = contractHolderId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        contractHolderNo = contractHolderNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        contractHolderName = contractHolderName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        parameters.put("contractHolderId", contractHolderId);
        parameters.put("contractHolderNo", contractHolderNo);
        parameters.put("contractHolderName", contractHolderName);
        parameters.put("contractHolderStatus", contractHolderStatus);
        parameters.put("contractHolderType", contractHolderType);
        parameters.put("indicator", "ContractHolder");
        List resultList = dqLogic.getContractHolders(parameters);
        return Converters.convertContractHolderList(resultList);
    }

    /**
     * Load Contract Alias Drop down List
     *
     * @param listType
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.ALL)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = promoteTpDAO.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant()));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }
        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());
        return helperList;
    }

    /**
     * Load Combo Box Method for loading Contract Alias
     *
     * @param comboBox
     * @param dropDownList
     */
    public void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    public void SaveCFPForTransferComponent(String cfpid, String cfpModelSId) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(cfpid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(cfpModelSId);
        promoteTpDAO.updateCFP(input);

    }

    public void SaveIFPForTransferComponent(String ifpId, String ifpModelSid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(ifpId);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(ifpModelSid);
        promoteTpDAO.updateIFP(input);
    }

    public void SavePSForTransferComponent(String psid, String psModelSid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(psid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(psModelSid);
        promoteTpDAO.updatePS(input);
    }

    public void SaveRSForTransferComponent(String rsid, String rsModelSid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(rsid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(rsModelSid);
        promoteTpDAO.updateRS(input);

    }

    public ComboBox getSelectNull(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(4);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.ZERO);
        return select;
    }

    /**
     * Component Item Search Count
     *
     * @param newDiscountTabDto
     * @return
     */
    public int getComponentItemSearchCount(ComponentInfoDTO compInfoDto) {
        int count = 0;
        String componentvalue = compInfoDto.getComponentValue();
        if (componentvalue.equals("Item Family Plan")) {
            String searchField = compInfoDto.getSearchField();
            String searchFieldValue = compInfoDto.getSearchFieldValue();
            if (searchField.equals(Constants.ITEM_ID) || searchField.equals(Constants.ITEM_NAME) || searchField.equals(Constants.ITEM_TYPE) || searchField.equals(Constants.ITEM_STATUS)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "select count(*) from dbo.ITEM_MASTER where " + composedValue1 + " like '" + composedValue + "'";
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (null != list && list.size() > 0) {
                    Object obj = list.get(0);
                    count = Integer.valueOf(String.valueOf(obj));
                    return count;
                }

            }
        } else if (componentvalue.equals(Constants.PRICE_SCHEDULE)) {
            String searchField = compInfoDto.getSearchField();
            String searchFieldValue = compInfoDto.getSearchFieldValue();
            if (searchField.equals(Constants.ITEM_ID) || searchField.equals(Constants.ITEM_NAME) || searchField.equals(Constants.ITEM_TYPE) || searchField.equals(Constants.ITEM_STATUS)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "select count(PM.PS_NAME) from dbo. PS_MODEL PM LEFT JOIN dbo.PS_DETAILS PSD ON PM.PS_MODEL_SID=PSD.PS_MODEL_SID\n"
                        + " LEFT JOIN dbo.ITEM_MASTER IM ON PSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                        + " LEFT JOIN dbo.BRAND_MASTER BM ON IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID JOIN dbo.HELPER_TABLE HT ON IM.ITEM_STATUS=HT.HELPER_TABLE_SID\n"
                        + "  LEFT JOIN dbo.HELPER_TABLE HT1 ON PSD.PRICE_PROTECTION_STATUS=HT1.HELPER_TABLE_SID\n"
                        + " LEFT JOIN dbo.HELPER_TABLE HT2 ON PSD.PRICE_PROTECTION_PRICE_TYPE=HT2.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT3\n"
                        + " ON PSD.PRICE_TOLERANCE_INTERVAL=HT3.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT4 ON PSD.PRICE_TOLERANCE_FREQUENCY=HT4.HELPER_TABLE_SID\n"
                        + " LEFT JOIN dbo.HELPER_TABLE HT5 ON PSD.PRICE_TOLERANCE=HT5.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT6 ON PSD.RESET_TYPE=HT6.HELPER_TABLE_SID\n"
                        + " LEFT JOIN dbo.HELPER_TABLE HT7 ON PSD.RESET_FREQUENCY=HT7.HELPER_TABLE_SID WHERE IM." + composedValue1 + " like '" + composedValue + "'";
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (null != list && list.size() > 0) {
                    Object obj = list.get(0);
                    count = Integer.valueOf(String.valueOf(obj));
                    return count;
                }

            }
        } else if (componentvalue.equals(Constants.REBATE_SCHEDULE)) {
            String searchField = compInfoDto.getSearchField();
            String searchFieldValue = compInfoDto.getSearchFieldValue();
            if (searchField.equals(Constants.ITEM_ID) || searchField.equals(Constants.ITEM_NAME) || searchField.equals(Constants.ITEM_TYPE) || searchField.equals(Constants.ITEM_STATUS)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query = "SELECT COUNT(*) FROM (SELECT\n" +
                                "    DISTINCT IFP_M.IFP_ID,\n" +
                                "    IFP_M.IFP_NAME,\n" +
                                "    BR.BRAND_NAME AS BRAND,\n" +
                                "  ST.DESCRIPTION AS Status,\n" +
                                "    RS_D.iteM_REBATE_START_DATE AS ITEMSTART,\n" +
                                "    RS_D.ITEM_REBATE_END_DATE AS ITEM_END,\n" +
                                "    '' AS FORMULA_TYPE,\n" +
                                "    FORMULA.FORMULA_NO AS FORMULA_ID,\n" +
                                "    FORMULA.FORMULA_DESC AS FORMULA_NAME,\n" +
                                "    RL.REBATE_PLAN_ID AS REBATE_PID,\n" +
                                "    RL.REBATE_PLAN_NAME AS REBATE_PNAME,\n" +
                                "    RS_D.REBATE_AMOUNT AS RE_AMNT,\n" +
                                "    RS_D.BUNDLE_NO BUNDLE_NO,\n" +
                                "    RS_D.ITEM_RS_ATTACHED_DATE AS ATT_DATE,\n" +
                                "       IFP_M.IFP_MODEL_SID\n" +
                                "    ,IM.ITEM_NO, IM.ITEM_NAME  FROM\n" +
                                "\n" +
                                "       RS_MODEL RS_M JOIN RS_DETAILS RS_D\n" +
                                "        ON RS_M.RS_MODEL_SID=RS_D.RS_MODEL_SID\n" +
                                "        JOIN IFP_MODEL IFP_M\n" +
                                "        ON IFP_M.IFP_MODEL_SID = RS_D.IFP_MODEL_SID LEFT JOIN ITEM_MASTER IM\n" +
                                "        ON IM.ITEM_MASTER_SID = RS_D.ITEM_MASTER_SID LEFT JOIN BRAND_MASTER BR\n" +
                                "        ON BR.BRAND_MASTER_SID = IM.BRAND_MASTER_SID LEFT JOIN HELPER_TABLE ST\n" +
                                "        ON ST.HELPER_TABLE_SID = IM.ITEM_STATUS LEFT JOIN FORMULA_DETAILS_MASTER FORMULA\n" +
                                "        ON FORMULA.FORMULA_ID = RS_D.FORMULA_ID LEFT JOIN REBATE_PLAN_MASTER RL\n" +
                                "        ON RL.REBATE_PLAN_MASTER_SID = RS_D.REBATE_PLAN_MASTER_SID WHERE IM." + composedValue1 + " like '" + composedValue + "' )A";
                System.out.println("------query---------"+query);
                List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                if (null != list && list.size() > 0) {
                    Object obj = list.get(0);
                    count = Integer.valueOf(String.valueOf(obj));
                    return count;
                }

            }
        }

        String query = queryUtils.getTpItemsCount(compInfoDto);
        List results = new ArrayList();
        try {
            results = promoteTpDAO.getItems(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return results.size();
    }

    /**
     * Component Item Search Result
     *
     * @param newDiscountTabDto
     * @return
     */
    public List<ComponentInfoDTO> getComponentItemSearchResult(ComponentInfoDTO compInfoDto) {
        List<ComponentInfoDTO> searchList = new ArrayList<ComponentInfoDTO>();
        String componentvalue = compInfoDto.getComponentValue();
        String query = StringUtils.EMPTY;
        if (componentvalue.equals("Item Family Plan")) {
            String searchField = compInfoDto.getSearchField();
            String searchFieldValue = compInfoDto.getSearchFieldValue();
            if (searchField.equals(Constants.ITEM_ID) || searchField.equals(Constants.ITEM_NAME) || searchField.equals(Constants.ITEM_TYPE) || searchField.equals(Constants.ITEM_STATUS)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                query = "select IM.ITEM_MASTER_SID,IM.ITEM_NO,IM.ITEM_NAME,BM.BRAND_NAME,HT.DESCRIPTION AS STATUS,IM.ITEM_START_DATE,IM.ITEM_END_DATE\n"
                        + " from dbo.ITEM_MASTER IM join BRAND_MASTER BM ON IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID\n"
                        + "JOIN HELPER_TABLE HT ON IM.ITEM_STATUS=HT.HELPER_TABLE_SID";
                query = query + " AND IM." + composedValue1 + " like'" + composedValue + "'";
                query = query += "ORDER BY IM.ITEM_MASTER_SID OFFSET " + compInfoDto.getStartIndex() + "  ROWS FETCH NEXT " + compInfoDto.getEndIndex() + " ROWS ONLY";
            }

            List results = new ArrayList();
            try {
                results = promoteTpDAO.getItems(query);
                return (setItemDetails(results, Constants.IFP));
            } catch (Exception ex) {
                LOGGER.error(ex);
            }

        } else if (componentvalue.equals(Constants.PRICE_SCHEDULE)) {
            String searchField = compInfoDto.getSearchField();
            String searchFieldValue = compInfoDto.getSearchFieldValue();
            if (searchField.equals(Constants.ITEM_ID) || searchField.equals(Constants.ITEM_NAME)  || searchField.equals(Constants.ITEM_TYPE) || searchField.equals(Constants.ITEM_STATUS)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query1 = "select PM.PS_NAME,IM.ITEM_MASTER_SID,IM.ITEM_NO,IM.ITEM_NAME,BM.BRAND_NAME,HT.DESCRIPTION AS ITEMSTATUS,IM.ITEM_START_DATE,IM.ITEM_END_DATE,PSD.NET_PRICE_TYPE,HT1.DESCRIPTION PPSTATUS\n"
                        + ",PSD.PRICE_PROTECTION_START_DATE,PSD.PRICE_PROTECTION_END_DATE,HT2.DESCRIPTION AS PPPTYPE,HT3.DESCRIPTION AS PTI,HT4.DESCRIPTION PLF,HT5.DESCRIPTION PT,\n"
                        + " PSD.RESET_ELIGIBLE,HT6.DESCRIPTION RT,HT7.DESCRIPTION RF from dbo. PS_MODEL PM LEFT JOIN dbo.PS_DETAILS PSD ON PM.PS_MODEL_SID=PSD.PS_MODEL_SID  \n"
                        + "LEFT JOIN dbo.ITEM_MASTER IM ON PSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                        + "LEFT JOIN dbo.BRAND_MASTER BM ON IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID JOIN dbo.HELPER_TABLE HT ON IM.ITEM_STATUS=HT.HELPER_TABLE_SID \n"
                        + " LEFT JOIN dbo.HELPER_TABLE HT1 ON PSD.PRICE_PROTECTION_STATUS=HT1.HELPER_TABLE_SID\n"
                        + "LEFT JOIN dbo.HELPER_TABLE HT2 ON PSD.PRICE_PROTECTION_PRICE_TYPE=HT2.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT3 \n"
                        + "ON PSD.PRICE_TOLERANCE_INTERVAL=HT3.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT4 ON PSD.PRICE_TOLERANCE_FREQUENCY=HT4.HELPER_TABLE_SID\n"
                        + "LEFT JOIN dbo.HELPER_TABLE HT5 ON PSD.PRICE_TOLERANCE=HT5.HELPER_TABLE_SID LEFT JOIN dbo.HELPER_TABLE HT6 ON PSD.RESET_TYPE=HT6.HELPER_TABLE_SID\n"
                        + "LEFT JOIN dbo.HELPER_TABLE HT7 ON PSD.RESET_FREQUENCY=HT7.HELPER_TABLE_SID WHERE IM." + composedValue1 + " like '" + composedValue + "'";
                query1 = query1 += "ORDER BY PM.PS_NAME OFFSET " + compInfoDto.getStartIndex() + "  ROWS FETCH NEXT " + compInfoDto.getEndIndex() + " ROWS ONLY";
                List results = new ArrayList();
                try {
                    results = promoteTpDAO.getItems(query1);
                    return (setItemDetails(results, Constants.PS));
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        } else if (componentvalue.equals(Constants.REBATE_SCHEDULE)) {
            String searchField = compInfoDto.getSearchField();
            String searchFieldValue = compInfoDto.getSearchFieldValue();
            if (searchField.equals(Constants.ITEM_ID) || searchField.equals(Constants.ITEM_NAME)  || searchField.equals(Constants.ITEM_TYPE) || searchField.equals(Constants.ITEM_STATUS)) {
                String composedValue1 = searchField.replaceAll(" ", "_");
                String composedValue = searchFieldValue.replaceAll("\\*", "%");
                String query1 = "SELECT\n" +
                                "    DISTINCT IFP_M.IFP_ID,\n" +
                                "    IFP_M.IFP_NAME,\n" +
                                "    BR.BRAND_NAME AS BRAND,\n" +
                                "  ST.DESCRIPTION AS Status,\n" +
                                "    RS_D.iteM_REBATE_START_DATE AS ITEMSTART,\n" +
                                "    RS_D.ITEM_REBATE_END_DATE AS ITEM_END,\n" +
                                "    '' AS FORMULA_TYPE,\n" +
                                "    FORMULA.FORMULA_NO AS FORMULA_ID,\n" +
                                "    FORMULA.FORMULA_DESC AS FORMULA_NAME,\n" +
                                "    RL.REBATE_PLAN_ID AS REBATE_PID,\n" +
                                "    RL.REBATE_PLAN_NAME AS REBATE_PNAME,\n" +
                                "    RS_D.REBATE_AMOUNT AS RE_AMNT,\n" +
                                "    RS_D.BUNDLE_NO BUNDLE_NO,\n" +
                                "    RS_D.ITEM_RS_ATTACHED_DATE AS ATT_DATE,\n" +
                                "       IFP_M.IFP_MODEL_SID\n" +
                                "    ,IM.ITEM_MASTER_SID, IM.ITEM_NO, IM.ITEM_NAME  FROM\n" +
                                "\n" +
                                "       RS_MODEL RS_M JOIN RS_DETAILS RS_D\n" +
                                "        ON RS_M.RS_MODEL_SID=RS_D.RS_MODEL_SID\n" +
                                "        JOIN IFP_MODEL IFP_M\n" +
                                "        ON IFP_M.IFP_MODEL_SID = RS_D.IFP_MODEL_SID LEFT JOIN ITEM_MASTER IM\n" +
                                "        ON IM.ITEM_MASTER_SID = RS_D.ITEM_MASTER_SID LEFT JOIN BRAND_MASTER BR\n" +
                                "        ON BR.BRAND_MASTER_SID = IM.BRAND_MASTER_SID LEFT JOIN HELPER_TABLE ST\n" +
                                "        ON ST.HELPER_TABLE_SID = IM.ITEM_STATUS LEFT JOIN FORMULA_DETAILS_MASTER FORMULA\n" +
                                "        ON FORMULA.FORMULA_ID = RS_D.FORMULA_ID LEFT JOIN REBATE_PLAN_MASTER RL\n" +
                                "        ON RL.REBATE_PLAN_MASTER_SID = RS_D.REBATE_PLAN_MASTER_SID WHERE IM." + composedValue1 + " like '" + composedValue + "'";
                query1 = query1 += " ORDER BY IFP_M.IFP_NAME OFFSET " + compInfoDto.getStartIndex() + "  ROWS FETCH NEXT " + compInfoDto.getEndIndex() + " ROWS ONLY";
                List results = new ArrayList();
                try {
                    results = promoteTpDAO.getItems(query1);
                    return (setItemDetails(results, Constants.RS));
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        }
        return null;

    }

    /**
     * Set Item Details to DTO
     *
     * @param results
     * @return
     */
    private List<ComponentInfoDTO> setItemDetails(List results, String componentType) {
        List<ComponentInfoDTO> searchList = new ArrayList<ComponentInfoDTO>();
        if (componentType.equals(Constants.IFP)) {
            int size = results.size();
            try {
                for (int i = 0; i < size; i++) {
                    Object arr[] = (Object[]) results.get(i);
                    ComponentInfoDTO tabDto = new ComponentInfoDTO();
                    tabDto.setItemMasterId(Converters.convertNullToEmpty(arr[0]));
                    tabDto.setItemNo(Converters.convertNullToEmpty(arr[1]));
                    tabDto.setItemName(Converters.convertNullToEmpty(arr[2]));
                    tabDto.setBrand(Converters.convertNullToEmpty(arr[3]));
                    tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                    tabDto.setStartDate(formatDate(String.valueOf(arr[5])));
                    tabDto.setEndDate(formatDate(String.valueOf(arr[6])));
                    searchList.add(tabDto);
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if (componentType.equals(Constants.PS)) {
            int size = results.size();
            try {
                for (int i = 0; i < size; i++) {
                    Object arr[] = (Object[]) results.get(i);
                    ComponentInfoDTO tabDto = new ComponentInfoDTO();
                    tabDto.setItemMasterId(Converters.convertNullToEmpty(arr[1]));
                    tabDto.setItemNo(Converters.convertNullToEmpty(arr[2]));
                    tabDto.setItemName(Converters.convertNullToEmpty(arr[3]));
                    tabDto.setBrand(Converters.convertNullToEmpty(arr[4]));
                    tabDto.setStatus(Converters.convertNullToEmpty(arr[5]));
                    tabDto.setItemStatus(Converters.convertNullToEmpty(arr[5]));
                    tabDto.setStartDate(formatDate(String.valueOf(arr[6])));
                    tabDto.setEndDate(formatDate(String.valueOf(arr[7])));
                    tabDto.setPriceType(Converters.convertNullToEmpty(arr[8]));
                    tabDto.setPriceProtectionStatus(Converters.convertNullToEmpty(arr[9]));
                    tabDto.setPriceProtectionStartDate(formatDate(String.valueOf(arr[10])));
                    tabDto.setPriceProtectionEndDate(formatDate(String.valueOf(arr[11])));
                    tabDto.setPriceProtectionPriceType(Converters.convertNullToEmpty(arr[12]));
                    tabDto.setPriceToleranceInterval(Converters.convertNullToEmpty(arr[13]));
                    tabDto.setPriceToleranceFrequency(Converters.convertNullToEmpty(arr[14]));
                    tabDto.setPriceTolerance(Converters.convertNullToEmpty(arr[15]));
                    tabDto.setResetEligible(Converters.convertNullToEmpty(arr[16]));
                    tabDto.setResetType(Converters.convertNullToEmpty(arr[17]));
                    tabDto.setResetFrequency(Converters.convertNullToEmpty(arr[18]));
                    searchList.add(tabDto);
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } else if (componentType.equals(Constants.RS)) {
            int size = results.size();
            try {
                for (int i = 0; i < size; i++) {
                    Object arr[] = (Object[]) results.get(i);
                    ComponentInfoDTO tabDto = new ComponentInfoDTO();
                    tabDto.setIfpId(Converters.convertNullToEmpty(arr[0]));
                    tabDto.setIfpName(Converters.convertNullToEmpty(arr[1]));
                    tabDto.setBrand(Converters.convertNullToEmpty(arr[2]));
                    tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                    tabDto.setStartDate(formatDate(String.valueOf(arr[4])));
                    tabDto.setEndDate(formatDate(String.valueOf(arr[5])));
                    tabDto.setFormulaType((String.valueOf(arr[6])));
                    tabDto.setFormulaId(Converters.convertNullToEmpty(arr[7]));
                    tabDto.setFormulaName(Converters.convertNullToEmpty(arr[8]));
                    tabDto.setRebatePlanId(Converters.convertNullToEmpty(arr[9]));
                    tabDto.setRebatePlanName(Converters.convertNullToEmpty(arr[10]));
                    tabDto.setRebateAmount(Converters.convertNullToEmpty(arr[11]));
                    tabDto.setBundleNo(Converters.convertNullToEmpty(arr[12]));
                    tabDto.setAttachedDate(formatDate(String.valueOf(arr[13])));
                    tabDto.setModelId(Converters.convertNullToEmpty(arr[14]));
                    tabDto.setItemMasterId(String.valueOf(arr[15]));
                    tabDto.setItemNo(Converters.convertNullToEmpty(arr[16]));
                    tabDto.setItemName(Converters.convertNullToEmpty(arr[17]));
                    searchList.add(tabDto);
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return searchList;
    }

    /**
     * Get The Selected Component Information
     *
     * @param componentSelectionValue
     * @param id
     * @return
     */
    public Map<String, List> getComponentInformation(String componentSelectionValue, String[] id) {
        Map<String, List> componentInformationMap = new HashMap<String, List>();
        List<Object[]> componentInformationList = promoteTpDAO.getComponentInformation(componentSelectionValue, id);

        if (componentInformationList != null && !componentInformationList.isEmpty()) {
            componentInformationMap.put("FieldData", Arrays.asList(componentInformationList.get(0)));

            componentInformationList.remove(0);

            List<ComponentInfoDTO> componentInfoList = new ArrayList<ComponentInfoDTO>();
            ComponentInfoDTO dto;
            if (componentInformationList != null && !componentInformationList.isEmpty()) {
                for (Object[] componentInformationList1 : componentInformationList) {
                    try {
                        final Object[] obj = (Object[]) componentInformationList1;
                        dto = new ComponentInfoDTO();

                        if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setStartDate(formatDate(String.valueOf(obj[4])));
                            dto.setEndDate(formatDate(String.valueOf(obj[5])));
                            dto.setAttachedDate(formatDate(String.valueOf(obj[6])));

                        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setStartDate(formatDate(String.valueOf(obj[4])));
                            dto.setEndDate(formatDate(String.valueOf(obj[5])));
                            dto.setAttachedDate(formatDate(String.valueOf(obj[6])));
                            dto.setPriceType(convertNullToEmpty(obj[7]));
                            dto.setPricePlanNo(convertNullToEmpty(obj[8]));
                            dto.setPricePlanName(convertNullToEmpty(obj[9]));
                            dto.setPriceProtectionStatus(convertNullToEmpty(obj[10]));
                            dto.setPriceProtectionStartDate(formatDate(String.valueOf(obj[11])));
                            dto.setPriceProtectionEndDate(formatDate(String.valueOf(obj[12])));
                            dto.setPriceProtectionPriceType(convertNullToEmpty(obj[13]));
                            dto.setPriceToleranceInterval(convertNullToEmpty(obj[14]));
                            dto.setPriceToleranceFrequency(convertNullToEmpty(obj[15]));
                            dto.setPriceToleranceType(convertNullToEmpty(obj[16]));
                            dto.setMaxIncrementalChange(convertNullToEmpty(obj[17]));
                            dto.setPriceTolerance(convertNullToEmpty(obj[18]));
                            dto.setResetEligible(convertNullToEmpty(obj[19]));
                            dto.setResetType(convertNullToEmpty(obj[20]));
                            dto.setResetDate(formatDate(String.valueOf(obj[21])));
                            dto.setResetInterval(convertNullToEmpty(obj[22]));
                            dto.setResetFrequency(convertNullToEmpty(obj[23]));

                        } else if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setStartDate(formatDate(String.valueOf(obj[4])));
                            dto.setEndDate(formatDate(String.valueOf(obj[5])));
                            dto.setFormulaType(convertNullToEmpty(obj[6]));
                            dto.setFormulaId(convertNullToEmpty(obj[7]));
                            dto.setFormulaName(convertNullToEmpty(obj[8]));
                            dto.setRebatePlanId(convertNullToEmpty(obj[9]));
                            dto.setRebatePlanName(convertNullToEmpty(obj[10]));
                            dto.setRebateAmount(convertNullToEmpty(obj[11]));
                            dto.setBundleNo(convertNullToEmpty(obj[12]));
                        }

                        componentInfoList.add(dto);
                    } catch (ParseException ex) {
                        LOGGER.error(ex);
                    }
                }
            }
            componentInformationMap.put("TableData", componentInfoList);
        }
        return componentInformationMap;
    }

    public List<Boolean> startDateAndEndDateValidation(String userId, String sessionId, String screenName) {
        List<Boolean> dateCheckList = new ArrayList<Boolean>();
        List<Object[]> checkList = promoteTpDAO.startDateAndEndDateValidation(userId, sessionId, screenName);
        if (checkList != null && !checkList.isEmpty()) {

            if (checkList.size() > 0 && convertToInteger(String.valueOf(checkList.get(0))) != 0) {
                dateCheckList.add(Boolean.FALSE);
            } else {
                dateCheckList.add(Boolean.TRUE);
            }

            if (checkList.size() > 1 && convertToInteger(String.valueOf(checkList.get(1))) != 0) {
                dateCheckList.add(Boolean.FALSE);
            } else {
                dateCheckList.add(Boolean.TRUE);
            }
        }
        return dateCheckList;
    }

    /**
     * This method is used to update the selected record in the table
     *
     * @param checkValue
     * @param dto
     * @param contractType
     * @param session
     * @return
     */
    public int callCheckRecUpdate(boolean checkValue, CurrentContractDTO dto, String contractType, SessionDTO session) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        /*This query is used to check whether record is preset or not in the Table */
        query.append("SELECT * FROM GCM_GLOBAL_DETAILS ");
        query.append("WHERE CONTRACT_MASTER_SID='").append(!dto.getContractSid().equals(StringUtils.EMPTY) ? dto.getContractSid() : 0).append("'  ");
        query.append("AND CFP_CONTRACT_SID='").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append("'  ");
        query.append("AND IFP_CONTRACT_SID='").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append("'  ");
        query.append("AND RS_CONTRACT_SID='").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append("'  ");
        query.append("AND PS_CONTRACT_SID='").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append("'  ");
        query.append("AND PROJECTION_MASTER_SID='").append(!dto.getProjectionId().equals(StringUtils.EMPTY) ? dto.getProjectionId() : 0).append("'  ");
        query.append("AND USER_ID='").append(session.getUserId()).append("'  ");
        query.append("AND SESSION_ID='").append(session.getSessionId()).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME='").append(contractType).append("'");
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        if (list.size() > 0) {
            /*This query is used to update the record */
            query = new StringBuilder("   ");
            query.append("UPDATE GCM_GLOBAL_DETAILS SET  CHECK_RECORD='").append(checkValue ? 1 : 0).append("'");
            query.append("WHERE CONTRACT_MASTER_SID='").append(!dto.getContractSid().equals(StringUtils.EMPTY) ? dto.getContractSid() : 0).append("'  ");
            query.append("AND CFP_CONTRACT_SID='").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append("'  ");
            query.append("AND IFP_CONTRACT_SID='").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append("'  ");
            query.append("AND RS_CONTRACT_SID='").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append("'  ");
            query.append("AND PS_CONTRACT_SID='").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append("'  ");
            query.append("AND PROJECTION_MASTER_SID='").append(!dto.getProjectionId().equals(StringUtils.EMPTY) ? dto.getProjectionId() : 0).append("'  ");
            query.append("AND USER_ID='").append(session.getUserId()).append("'  ");
            query.append("AND SESSION_ID='").append(session.getSessionId()).append("'  ");
            if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
                query.append("AND SCREEN_NAME='").append(contractType).append("'");
            }
            count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        } else {
            /*If record is not present in the table, this query will insert the record*/
            query = new StringBuilder("   ");
            query.append("INSERT INTO dbo.GCM_GLOBAL_DETAILS(CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,PS_CONTRACT_SID,RS_CONTRACT_SID,"
                    + "PROJECTION_MASTER_SID,CHECK_RECORD,USER_ID,SESSION_ID,SCREEN_NAME)VALUES(");
            query.append(!dto.getContractSid().equals(StringUtils.EMPTY) ? dto.getContractSid() : 0).append(",");
            query.append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append(",");
            query.append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append(",");
            query.append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append(", ");
            query.append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append(",");
            query.append(!dto.getProjectionId().equals(StringUtils.EMPTY) ? dto.getProjectionId() : 0).append(",'");
            query.append(checkValue ? 1 : 0).append("','");
            query.append(session.getUserId()).append("','");
            query.append(session.getSessionId()).append("','Current Contract')");
            HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
        }
        return count;
    }

    /**
     * Inserting the checked record to temp table
     *
     * @param checkValue
     * @param dto
     * @param contractType
     * @param session
     * @return
     */
    public int callCheckRecInsert(boolean checkValue, CurrentContractDTO dto, String contractType, SessionDTO session) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO GCM_GLOBAL_DETAILS ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(" (CHECK_RECORD,CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,PROJECTION_MASTER_SID,USER_ID,SESSION_ID,SCREEN_NAME)  ");
        } else {
            query.append(" (CHECK_RECORD,CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,PROJECTION_MASTER_SID,USER_ID,SESSION_ID)  ");
        }

        query.append("VALUES(  ");
        query.append("'").append(checkValue ? 1 : 0).append("'");
        query.append(",").append(!dto.getContractSid().equals(StringUtils.EMPTY) ? dto.getContractSid() : 0);
        query.append(",").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0);
        query.append(",").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0);
        query.append(",").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0);
        query.append(",").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0);
        query.append(",").append(!dto.getProjectionId().equals(StringUtils.EMPTY) ? dto.getProjectionId() : 0);
        query.append(",'").append(session.getUserId()).append("'");
        query.append(",'").append(session.getSessionId()).append("'");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(",'").append(contractType).append("'");
        }
        query.append("  )");
        LOGGER.info("insert query " + query.toString());
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    /**
     * Method to check if any record is selected
     *
     * @param userId
     * @param sessionId
     * @param screenName
     * @return
     */
    public boolean isAnyRecordSelected(String userId, String sessionId, String screenName) {
        List<Object[]> checkList = promoteTpDAO.isAnyRecordSelected(userId, sessionId, screenName);

        if (checkList.size() > 0 && convertToInteger(String.valueOf(checkList.get(0))) != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mass Update Logic
     *
     * @param fieldName
     * @param userId
     * @param sessionId
     * @param contractType
     * @param value
     * @return
     */
    public boolean massUpdate(String fieldName, String userId, String sessionId, String contractType, Object value) {
        boolean updateStatus = false;

        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE GCM_GLOBAL_DETAILS SET   ");
        if (fieldName.equals("Status")) {

            query.append(" STATUS='").append((Integer) value).append("' ");
        } else if (fieldName.equals("Company Start Date")) {

            query.append(" START_DATE='").append(dateFormater.format((Date) value)).append("' ");
        } else if (fieldName.equals("Company End Date")) {
            query.append(" END_DATE='").append(dateFormater.format((Date) value)).append("' ");
        }
        query.append("WHERE CHECK_RECORD='1'");

        query.append("AND USER_ID='").append(userId).append("'  ");
        query.append("AND SESSION_ID='").append(sessionId).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME='").append(contractType).append("'");
        }
        LOGGER.info("update query " + query.toString());

        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return updateStatus;
    }

    /**
     * Date Update Logic
     *
     * @param date
     * @param dto
     * @param session
     * @param contractType
     * @param startOrEnd
     * @return
     */
    public int callDateUpdate(Date date, CurrentContractDTO dto, SessionDTO session, String contractType, String startOrEnd) {
        int count = 0;
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        StringBuilder query = new StringBuilder("   ");

        query.append("UPDATE GCM_GLOBAL_DETAILS SET  ").append(startOrEnd).append("='").append(dateFormater.format(date)).append("'");
        query.append("WHERE CONTRACT_MASTER_SID='").append(!dto.getContractSid().equals(StringUtils.EMPTY) ? dto.getContractSid() : 0).append("'  ");
        query.append("AND CFP_CONTRACT_SID='").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0).append("'  ");
        query.append("AND IFP_CONTRACT_SID='").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0).append("'  ");
        query.append("AND RS_CONTRACT_SID='").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0).append("'  ");
        query.append("AND PS_CONTRACT_SID='").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0).append("'  ");
        query.append("AND PROJECTION_MASTER_SID='").append(!dto.getProjectionId().equals(StringUtils.EMPTY) ? dto.getProjectionId() : 0).append("'  ");
        query.append("AND USER_ID='").append(session.getUserId()).append("'  ");
        query.append("AND SESSION_ID='").append(session.getSessionId()).append("'  ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append("AND SCREEN_NAME='").append(contractType).append("'");
        }
        LOGGER.info("update query " + query.toString());

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    /**
     * Date Insert Logic
     *
     * @param date
     * @param dto
     * @param session
     * @param contractType
     * @param startOrEnd
     * @return
     */
    public int callDateInsert(Date date, CurrentContractDTO dto, SessionDTO session, String contractType, String startOrEnd) {
        int count = 0;
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO GCM_GLOBAL_DETAILS ");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(" (").append(startOrEnd).append(",CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,PROJECTION_MASTER_SID,USER_ID,SESSION_ID,SCREEN_NAME)  ");
        } else {
            query.append(" (").append(startOrEnd).append(",CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,RS_CONTRACT_SID,PS_CONTRACT_SID,PROJECTION_MASTER_SID,USER_ID,SESSION_ID)  ");
        }

        query.append("VALUES(  ");
        query.append("'").append(dateFormater.format(date)).append("'");
        query.append(",").append(!dto.getContractSid().equals(StringUtils.EMPTY) ? dto.getContractSid() : 0);
        query.append(",").append(!dto.getCfpContSid().equals(StringUtils.EMPTY) ? dto.getCfpContSid() : 0);
        query.append(",").append(!dto.getIfpContSid().equals(StringUtils.EMPTY) ? dto.getIfpContSid() : 0);
        query.append(",").append(!dto.getRsContSid().equals(StringUtils.EMPTY) ? dto.getRsContSid() : 0);
        query.append(",").append(!dto.getPsContSid().equals(StringUtils.EMPTY) ? dto.getPsContSid() : 0);
        query.append(",").append(!dto.getProjectionId().equals(StringUtils.EMPTY) ? dto.getProjectionId() : 0);
        query.append(",'").append(session.getUserId()).append("'");
        query.append(",'").append(session.getSessionId()).append("'");
        if (!contractType.equals(StringUtils.EMPTY) && !contractType.equals(Constants.NULL)) {
            query.append(",'").append(contractType).append("'");
        }
        query.append("  )");
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public List<CurrentContractDTO> getCurrentContractTPResults(String query) {
        List list = new ArrayList();
        List<CurrentContractDTO> resultList = new ArrayList<CurrentContractDTO>();
        CurrentContractDTO dto = null;

        list = (List) DAO.executeSelect(query);
        Date date = new Date();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object obj[] = (Object[]) list.get(i);
                    dto = new CurrentContractDTO();

                    dto.setContractHolder(String.valueOf(obj[0]));
                    dto.setContractNo(String.valueOf(obj[1]));
                    dto.setContractName(String.valueOf(obj[2]));
                    dto.setMarketType(String.valueOf(obj[3]));
                    dto.setStartDate(formatDate(String.valueOf(obj[4])));
                    dto.setEndDate(formatDate(String.valueOf(obj[5])));
                    dto.setCfpName(String.valueOf(obj[6]));
                    dto.setIfpName(String.valueOf(obj[8]));

                    dto.setRsName(String.valueOf(obj[12]));
                    dto.setRebateScheduleNo(String.valueOf(obj[13]));

                    dto.setRarCategory(String.valueOf(obj[14]));
                    dto.setCfpContSid(String.valueOf(obj[15]));
                    dto.setRsContSid(String.valueOf(obj[16]));
                    dto.setIfpContSid(String.valueOf(obj[17]));
                    dto.setPsContSid(String.valueOf(obj[18]));
                    dto.setContractSid(Integer.parseInt(String.valueOf(obj[19])));
                    dto.setCheckRecord(!String.valueOf(obj[23]).equals(Constants.NULL) ? String.valueOf(obj[23]).equals(Constants.TRUE) ? true : false : false);
                    resultList.add(dto);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        return resultList;
    }

    public String convertStringToDate(String stringDate, String inputFormat, String outputFormat) throws ParseException {
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
        Date date = new Date();
        date = inputDateFormatter.parse(stringDate);
        return outputDateFormatter.format(date);
    }

    /**
     * Method to get Rebate Plan Details
     *
     * @param rebatePlanId
     * @param rebatePlanNo
     * @param rebatePlanName
     * @param rebatePlanStatus
     * @param rebatePlanType
     * @param companySids
     * @return
     * @throws Exception
     */
    public List<RebatePlanDTO> getRebatePlanDetails(String rebatePlanId, String rebatePlanNo, String rebatePlanName,
            String rebatePlanStatus, String rebatePlanType, List<String> companySids) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        rebatePlanId = rebatePlanId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        rebatePlanNo = rebatePlanNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        rebatePlanName = rebatePlanName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        parameters.put("rebatePlanId", rebatePlanId);
        parameters.put("rebatePlanNo", rebatePlanNo);
        parameters.put("rebatePlanName", rebatePlanName);
        parameters.put("rebatePlanStatus", rebatePlanStatus);
        parameters.put("rebatePlanType", rebatePlanType);
        parameters.put("indicator", "RebatePlan");
        List resultList = dqLogic.getRebatePlanInfo(parameters);
        return Converters.convertRebatePlanList(resultList);
    }

    /**
     * Get the selected contract in next Transfer Component Page
     *
     * @param query
     * @return
     */
    public List<CurrentContractDTO> getSelectedTPContract(List list) {
        List<CurrentContractDTO> resultList = new ArrayList<CurrentContractDTO>();
        CurrentContractDTO dto = null;
        try {
            int listsize = list.size();
            if (!list.isEmpty()) {
                for (int i = 0; i < listsize; i++) {
                    Object obj[] = (Object[]) list.get(i);
                    dto = new CurrentContractDTO();
                    dto.setContractHolder(convertNullToEmpty(String.valueOf(obj[0])));
                    dto.setContractNo(convertNullToEmpty(String.valueOf(obj[1])));
                    dto.setContractName(convertNullToEmpty(String.valueOf(obj[2])));
                    dto.setMarketType(convertNullToEmpty(String.valueOf(obj[3])));
                    dto.setStartDate(formatDate(String.valueOf(obj[4])));
                    dto.setEndDate(formatDate(String.valueOf(obj[5])));
                    dto.setIfpName(convertNullToEmpty(String.valueOf(obj[8])));
                    dto.setPsName(convertNullToEmpty(String.valueOf(obj[10])));
                    dto.setRsName(convertNullToEmpty(String.valueOf(obj[12])));
                    dto.setRarCategory(convertNullToEmpty(String.valueOf(obj[14])));
                    dto.setStatus(convertNullToEmpty(String.valueOf(obj[22])));
                    dto.setCompanyStartDate(formatDate(String.valueOf(obj[20])));
                    dto.setCompanyEndDate((Date) obj[21]);
                    dto.setCfpContSid(convertNullToEmpty(String.valueOf(obj[15])));
                    dto.setRsContSid(convertNullToEmpty(String.valueOf(obj[16])));
                    dto.setIfpContSid(convertNullToEmpty(String.valueOf(obj[17])));
                    dto.setPsContSid(convertNullToEmpty(String.valueOf(obj[18])));
                    dto.setContractSid(Integer.parseInt(String.valueOf(obj[19])));
                    resultList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    public List<CurrentContractDTO> getSelectedTPContractSummary(List contList) {
        List<CurrentContractDTO> contResultList = new ArrayList<CurrentContractDTO>();
        CurrentContractDTO currentContractDTO = null;
        try {
            int listSize = contList.size();
            if (!contList.isEmpty()) {
                for (int i = 0; i < listSize; i++) {
                    Object obj[] = (Object[]) contList.get(i);
                    currentContractDTO = new CurrentContractDTO();
                    currentContractDTO.setContractHolder(convertNullToEmpty(String.valueOf(obj[0])));
                    currentContractDTO.setContractNo(convertNullToEmpty(String.valueOf(obj[1])));
                    currentContractDTO.setContractName(convertNullToEmpty(String.valueOf(obj[2])));
                    currentContractDTO.setMarketType(convertNullToEmpty(String.valueOf(obj[3])));
                    currentContractDTO.setStartDate(formatDate(String.valueOf(obj[4])));
                    currentContractDTO.setEndDate(formatDate(String.valueOf(obj[5])));
                    currentContractDTO.setCfpName(convertNullToEmpty(String.valueOf(obj[6])));
                    currentContractDTO.setIfpName(convertNullToEmpty(String.valueOf(obj[7])));
                    currentContractDTO.setPsName(convertNullToEmpty(String.valueOf(obj[8])));
                    currentContractDTO.setRsName(convertNullToEmpty(String.valueOf(obj[9])));
                    currentContractDTO.setCompanyStartDate(formatDate(String.valueOf(obj[10])));
                    currentContractDTO.setCompanyEndDate(obj[11] != null ? (Date) obj[11] : new Date());
                    currentContractDTO.setStatus(convertNullToEmpty(String.valueOf(obj[12])));
                    contResultList.add(currentContractDTO);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return contResultList;

    }

    public int callCompanyInsert(boolean checkValue, PromoteTpToChDto dto, String updateType, String searchSessionId) {
      
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO IMTD_COMPANY_CONTRACT_DETAILS ");
        query.append(" (CHECK_RECORD,COMPANY_MASTER_SID,COMPANY_NAME, OPERATION, SESSION_ID)  ");
        query.append("VALUES(  ");
        query.append("'").append(checkValue ? 1 : 0).append("'");;
        query.append(",").append(!dto.getCompanySystemId().equals(StringUtils.EMPTY) ? dto.getCompanySystemId() : 0);
        query.append(",").append("'").append(dto.getCompanyName()).append("'");
        query.append(",").append("'").append(updateType).append("'");
        query.append(",").append("'").append(searchSessionId).append("'");
        query.append("  )");
     
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public int callCompanyUpdate(boolean checkValue, PromoteTpToChDto dto, String updateType, String searchSessionId) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE IMTD_COMPANY_CONTRACT_DETAILS SET CHECK_RECORD='").append(checkValue ? 1 : 0).append("'");
        query.append("WHERE COMPANY_MASTER_SID='").append(!dto.getCompanySystemId().equals(StringUtils.EMPTY) ? dto.getCompanySystemId() : 0).append("'  ");
        query.append(" AND OPERATION = '").append(updateType).append("'");
        query.append(" AND SESSION_ID = '").append(searchSessionId).append("'");

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    public String getContractQuery(CurrentContractDTO conSelDTO, String userId, String sessionId, int start, int end, boolean loadData) {
        String query = "SELECT  distinct  CM.CONT_HOLD_COMPANY_MASTER_SID AS CONTRACT_HOLDER,\n"
                + "          CM.CONTRACT_NO,\n"
                + "          CM.CONTRACT_NAME,\n"
                + "          H_MARKET_TYPE.DESCRIPTION       AS MARKET_TYPE,\n"
                + "          CM.START_DATE                   AS CONTRACT_START_DATE,\n";
        if (!conSelDTO.isIsCustomerDetailsTab()) {
            query = query + "          CM.END_DATE                     AS CONTRACT_END_DATE,\n";
        } else {
            query = query + "          GCM.END_DATE                     AS CONTRACT_END_DATE,\n";
        }
        query = query + "          RS_C.RS_NO                      AS REBATE_SCHEDULE_NO,\n"
                + "          RS_C.RS_NAME                    AS REBATE_SCHEDULE_NAME,\n"
                + "          H_RS_CAT.DESCRIPTION            AS RS_CATEGORY,\n"
                + "          H_CON_STATUS.DESCRIPTION        AS CONTRACT_STATUS,\n"
                + "          C.COMPANY_START_DATE,\n"
                + "          C.COMPANY_END_DATE,\n"
                + "          CFP_C.CFP_CONTRACT_SID,\n"
                + "          IFP_C.IFP_CONTRACT_SID,\n"
                + "          PS_C.PS_CONTRACT_SID,\n"
                + "          RS_C.RS_CONTRACT_SID,\n"
                + "          CM.CONTRACT_MASTER_SID,C.PROJECTION_MASTER_SID FROM      PROJECTION_DETAILS A\n"
                + "JOIN      (SELECT TOP 1 PD.PROJECTION_MASTER_SID,\n"
                + "                        COM.COMPANY_MASTER_SID,\n"
                + "                        COMPANY_START_DATE,\n"
                + "                        COMPANY_END_DATE\n"
                + "           FROM   WORKFLOW_MASTER WM\n"
                + "           JOIN   PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                + "           JOIN   CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                + "           JOIN   CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                + "           JOIN   COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                + "           JOIN   HELPER_TABLE H ON H.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID\n"
                + "           WHERE  COM.COMPANY_MASTER_SID =" + conSelDTO.getCompanySystemId() + "\n"
                + "                  AND H.DESCRIPTION = 'Approved'\n"
                + "           ORDER  BY ISNULL(WM.MODIFIED_DATE, WM.CREATED_DATE) DESC)C ON A.PROJECTION_MASTER_SID = C.PROJECTION_MASTER_SID\n"
                + "JOIN      CCP_DETAILS B ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID\n"
                + "JOIN      CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID\n"
                + "JOIN      CFP_CONTRACT CFP_C ON CFP_C.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID\n"
                + "JOIN      CFP_CONTRACT_DETAILS CFP_D ON CFP_C.CFP_CONTRACT_SID = CFP_D.CFP_CONTRACT_SID\n"
                + "                                    AND C.COMPANY_MASTER_SID = CFP_D.COMPANY_MASTER_SID\n"
                + "JOIN      IFP_CONTRACT IFP_C ON IFP_C.CONTRACT_MASTER_SID = CFP_C.CONTRACT_MASTER_SID\n"
                + "                            AND IFP_C.CFP_CONTRACT_SID = CFP_C.CFP_CONTRACT_SID\n"
                + " JOIN PS_CONTRACT PS_C ON PS_C.CONTRACT_MASTER_SID = CFP_C.CONTRACT_MASTER_SID\n"
                + "                          AND PS_C.CFP_CONTRACT_SID = CFP_C.CFP_CONTRACT_SID\n"
                + "                          AND PS_C.IFP_CONTRACT_SID = IFP_C.IFP_CONTRACT_SID\n"
                + " JOIN RS_CONTRACT RS_C ON RS_C.CONTRACT_MASTER_SID = CFP_C.CONTRACT_MASTER_SID\n"
                + "                          AND RS_C.CFP_CONTRACT_SID = CFP_C.CFP_CONTRACT_SID\n"
                + "                          AND RS_C.IFP_CONTRACT_SID = IFP_C.IFP_CONTRACT_SID\n"
                + "                          AND RS_C.PS_CONTRACT_SID = PS_C.PS_CONTRACT_SID\n"
                + "LEFT JOIN      HELPER_TABLE H_MARKET_TYPE ON H_MARKET_TYPE.HELPER_TABLE_SID = CM.CONTRACT_TYPE\n"
                + "LEFT JOIN      HELPER_TABLE H_CON_STATUS ON H_CON_STATUS.HELPER_TABLE_SID = CM.CONTRACT_STATUS\n"
                + "LEFT JOIN      HELPER_TABLE H_RS_CAT ON H_RS_CAT.HELPER_TABLE_SID = RS_C.RS_CATEGORY\n"
                + "LEFT JOIN      HELPER_TABLE H_RS_TYPE ON H_RS_TYPE.HELPER_TABLE_SID = RS_C.RS_TYPE\n"
                + "LEFT JOIN      HELPER_TABLE H_REB_TYPE ON H_REB_TYPE.HELPER_TABLE_SID = RS_C.REBATE_PROGRAM_TYPE\n"
                + "LEFT JOIN      HELPER_TABLE H_RS_ALIAS ON H_RS_ALIAS.HELPER_TABLE_SID = RS_C.RS_ALIAS\n"
                + "  JOIN GCM_GLOBAL_DETAILS GCM ON GCM.PROJECTION_MASTER_SID=C.PROJECTION_MASTER_SID "
                + " and gcm.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID  \n";

        if (!conSelDTO.isIsCustomerDetailsTab()) {
            boolean searCriteria = false;
           
            if (!conSelDTO.getContractNo().equals(StringUtils.EMPTY) && !conSelDTO.getContractNo().equals(Constants.NULL)) {
                String contractno = conSelDTO.getContractNo().replaceAll("\\*", "%");
                query = query + "WHERE CM.CONTRACT_NO like '" + contractno + "'";
                searCriteria = true;
            }
            if (!conSelDTO.getContractName().equals(StringUtils.EMPTY) && !conSelDTO.getContractName().equals(Constants.NULL)) {
                String contractName = conSelDTO.getContractName().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND CM.CONTRACT_NAME like '" + contractName + "'";
                } else {
                    query = query + "WHERE CM.CONTRACT_NAME like '" + contractName + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getContractHolder().equals(StringUtils.EMPTY) && !conSelDTO.getContractHolder().equals(Constants.NULL)) {
                String contractHolder = conSelDTO.getContractHolder().replaceAll("\\*", "%");
                searCriteria = true;
            }

            if (!conSelDTO.getMarketType().equals(StringUtils.EMPTY) && !conSelDTO.getMarketType().equals(Constants.NULL)) {
                String marketType = conSelDTO.getMarketType().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND H_MARKET_TYPE.DESCRIPTION='" + marketType + "'";
                } else {
                    query = query + "WHERE H_MARKET_TYPE.DESCRIPTION='" + marketType + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getRebateScheduleId().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleId().equals(Constants.NULL)) {
                String rebateScheduleId = conSelDTO.getRebateScheduleId().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND RS_C.RS_ID like '" + rebateScheduleId + "'";
                } else {
                    query = query + "WHERE RS_C.RS_ID like '" + rebateScheduleId + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getRebateScheduleNo().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleNo().equals(Constants.NULL)) {
                String rebateScheduleNo = conSelDTO.getRebateScheduleNo().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND RS_C.RS_NO like '" + rebateScheduleNo + "'";
                } else {
                    query = query + "WHERE RS_C.RS_NO like '" + rebateScheduleNo + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getRebateScheduleName().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleName().equals(Constants.NULL)) {
                String rebateScheduleName = conSelDTO.getRebateScheduleName().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND RS_C.RS_NAME like '" + rebateScheduleName + "'";
                } else {
                    query = query + "WHERE RS_C.RS_NAME like '" + rebateScheduleName + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getRebateScheduleType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleType().equals(Constants.NULL)) {
                String rebateScheduleType = conSelDTO.getRebateScheduleType().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND H_RS_TYPE.DESCRIPTION='" + rebateScheduleType + "'";
                } else {
                    query = query + "WHERE H_RS_TYPE.DESCRIPTION='" + rebateScheduleType + "'";
                }
                searCriteria = true;
            }
            if (!conSelDTO.getRebateScheduleCategory().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleCategory().equals(Constants.NULL)) {
                String rebateScheduleCategory = conSelDTO.getRebateScheduleCategory().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND H_RS_CAT.DESCRIPTION='" + rebateScheduleCategory + "'";
                } else {
                    query = query + "WHERE H_RS_CAT.DESCRIPTION='" + rebateScheduleCategory + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getRebateProgramType().equals(StringUtils.EMPTY) && !conSelDTO.getRebateProgramType().equals(Constants.NULL)) {
                String rebateProgramType = conSelDTO.getRebateProgramType().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND H_REB_TYPE.DESCRIPTION='" + rebateProgramType + "'";
                } else {
                    query = query + "WHERE H_REB_TYPE.DESCRIPTION='" + rebateProgramType + "'";
                }
                searCriteria = true;
            }

            if (!conSelDTO.getRebateScheduleAlias().equals(StringUtils.EMPTY) && !conSelDTO.getRebateScheduleAlias().equals(Constants.NULL)) {
                String alias = conSelDTO.getRebateScheduleAlias().replaceAll("\\*", "%");
                if (searCriteria) {
                    query = query + " AND H_RS_ALIAS.DESCRIPTION like '" + alias + "'";
                } else {
                    query = query + "WHERE H_RS_ALIAS.DESCRIPTION like '" + alias + "'";
                }
                searCriteria = true;
            }
            query = query + " and gcm.sessIOn_id='" + sessionId + "'  and user_id=' " + userId + "' ";
        } else {
            query = query + "WHERE GCM.OPERATION = '1' and GCM.USER_ID = '" + userId + "' AND GCM.SESSION_ID = '" + sessionId + "'  AND GCM.CHECK_RECORD = '0' AND GCM.SCREEN_NAME = 'Current Contract'";
        }
        if (loadData) {
            query = query + " ORDER BY  C.PROJECTION_MASTER_SID  OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY ";
        }
        return query;
    }

    public Boolean duplicateCheck(String field, String value, String componentType) {
        Boolean flag = false;
        int count = 0;
        String query = StringUtils.EMPTY;
        if ("Contract".equals(componentType)) {
            query = "select count(*) from dbo.CONTRACT_MASTER WHERE " + field + "='" + value + "'";
        }
        if ("cfp".equals(componentType)) {
            query = "select count(*) from dbo.CFP_MODEL WHERE " + field + "='" + value + "'";
        }
        if ("ifp".equals(componentType)) {
            query = "select count(*) from dbo.IFP_MODEL WHERE " + field + "='" + value + "'";
        }
        if ("ps".equals(componentType)) {
            query = "select count(*) from dbo.PS_MODEL WHERE " + field + "='" + value + "'";
        }
        if ("rs".equals(componentType)) {
            query = "select count(*) from dbo.RS_MODEL WHERE " + field + "='" + value + "'";
        }
        if (!StringUtils.EMPTY.equals(query)) {
            try {
                List list = (List) DAO.executeSelect(query.toString());
                if (!list.isEmpty()) {
                    count = Integer.parseInt(String.valueOf(list.get(0)));
                    if (count > 0) {
                        flag = true;
                        return flag;
                    }
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }

        return flag;
    }

}
